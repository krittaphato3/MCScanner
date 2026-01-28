package com.scanner.core;

import com.scanner.ui.ScannerFrame;
import com.scanner.utils.IPUtils;

import javax.swing.*;

import org.krittaphato3.mcping.MCPinger;
import org.krittaphato3.mcping.PingOptions;
import org.krittaphato3.mcping.model.ServerResponse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ScannerEngine {

    private final ScannerFrame gui;
    private ExecutorService threadPool;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    
    private final AtomicInteger scannedCount = new AtomicInteger(0);
    private int totalTargets = 0;
    
    private final List<Object[]> resultBuffer = new ArrayList<>();
    private final Object bufferLock = new Object();
    private Timer uiUpdateTimer;

    public ScannerEngine(ScannerFrame gui) {
        this.gui = gui;
    }

    public void startScan(String ipInput, String portInput, int threads, int timeout) {
        if (isRunning.get()) return;
        isRunning.set(true);
        scannedCount.set(0);

        uiUpdateTimer = new Timer(100, e -> flushBuffer());
        uiUpdateTimer.start();

        gui.updateStatus("CALCULATING SEARCH SPACE...");

        new Thread(() -> {
            try {
                List<String> targets = IPUtils.parseInput(ipInput);
                int[] ports = parsePorts(portInput);
                int portCount = ports[1] - ports[0] + 1;
                totalTargets = targets.size() * portCount;

                threadPool = createSmartExecutor(threads);

                String mode = (threadPool.getClass().getSimpleName().contains("Virtual") || threads > 5000) 
                        ? "HYPER-THREADING" 
                        : "STANDARD POOL";
                
                gui.updateStatus("INITIALIZED " + mode + " (" + totalTargets + " TARGETS)");

                for (String ip : targets) {
                    if (!isRunning.get()) break;
                    for (int port = ports[0]; port <= ports[1]; port++) {
                        if (!isRunning.get()) break;
                        
                        final int currentPort = port;
                        threadPool.submit(() -> scanTarget(ip, currentPort, timeout));
                    }
                }

                shutdownPool();
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(gui, "Engine Error: " + e.getMessage()));
                stopScan();
            }
        }).start();
    }

    public void stopScan() {
        isRunning.set(false);
        if (threadPool != null) {
            threadPool.shutdownNow();
        }
        if (uiUpdateTimer != null) {
            uiUpdateTimer.stop();
        }
        flushBuffer();
        gui.updateStatus("TERMINATED");
        gui.toggleButtons(true);
    }

    private ExecutorService createSmartExecutor(int threads) {
        try {
            Method m = Executors.class.getMethod("newVirtualThreadPerTaskExecutor");
            if (threads >= 2000) {
                return (ExecutorService) m.invoke(null);
            }
        } catch (Exception e) {
        }
        return Executors.newFixedThreadPool(threads);
    }

    private void scanTarget(String ip, int port, int timeout) {
        try {
            PingOptions options = PingOptions.builder()
                    .hostname(ip)
                    .port(port)
                    .timeout(timeout)
                    .build();
            
            MCPinger pinger = MCPinger.builder().pingOptions(options).build();
            ServerResponse response = pinger.fetchData();

            if (response != null) {
                String motd = (response.getDescription() != null) ? response.getDescription().getText() : "Unknown";
                String version = (response.getVersion() != null) ? response.getVersion().getName() : "Unknown";
                String players = (response.getPlayers() != null) ? 
                        response.getPlayers().getOnline() + "/" + response.getPlayers().getMax() : "?/?";
                
                addToBuffer(ip, port, version, players, motd);
            }

        } catch (Exception e) {
        } finally {
            int done = scannedCount.incrementAndGet();
            if (done % 100 == 0) { 
                SwingUtilities.invokeLater(() -> gui.updateStatus("SCANNED " + done + " / " + totalTargets));
            }
        }
    }

    private void addToBuffer(String ip, int port, String version, String players, String motd) {
        synchronized (bufferLock) {
            resultBuffer.add(new Object[]{ip, port, version, players, motd});
        }
    }

    private void flushBuffer() {
        synchronized (bufferLock) {
            if (resultBuffer.isEmpty()) return;
            
            List<Object[]> copy = new ArrayList<>(resultBuffer);
            resultBuffer.clear();
            
            SwingUtilities.invokeLater(() -> {
                for (Object[] row : copy) {
                    gui.addResult(
                        (String)row[0], 
                        (int)row[1], 
                        (String)row[2], 
                        (String)row[3], 
                        (String)row[4]
                    );
                }
            });
        }
    }

    private void shutdownPool() {
        if (threadPool != null) {
            threadPool.shutdown();
            try {
                if (threadPool.awaitTermination(2, TimeUnit.HOURS)) {
                    SwingUtilities.invokeLater(() -> {
                        flushBuffer();
                        gui.updateStatus("SCAN COMPLETE");
                        gui.toggleButtons(true);
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isRunning.set(false);
    }

    private int[] parsePorts(String portInput) {
        if (portInput.contains("-")) {
            String[] parts = portInput.split("-");
            return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
        } else {
            int p = Integer.parseInt(portInput);
            return new int[]{p, p};
        }
    }
}