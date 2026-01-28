package com.scanner.ui;

import com.scanner.core.ScannerEngine;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScannerFrame extends JFrame {

    private JTextField ipField, portField, threadsField, timeoutField;
    private JButton startButton, stopButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    
    private final ScannerEngine engine;

    private final Color BG_DARK = new Color(18, 18, 24);
    private final Color CARD_BG = new Color(28, 28, 36);
    private final Color ACCENT_CYAN = new Color(80, 227, 194);
    private final Color TEXT_MUTED = new Color(120, 120, 140);
    private final Color TEXT_BRIGHT = new Color(240, 240, 250);
    private final Color BORDER_COLOR = new Color(45, 45, 55);

    // DEFAULTS
    private final String DEFAULT_PORTS = "25000-26000";
    private final String DEFAULT_THREADS = "2000";
    private final String DEFAULT_TIMEOUT = "1500";

    public ScannerFrame() {
        setTitle("PortScope Pro // Neural Net Scanner");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        getRootPane().putClientProperty("JRootPane.titleBarBackground", BG_DARK);
        getRootPane().putClientProperty("JRootPane.titleBarForeground", TEXT_BRIGHT);
        
        engine = new ScannerEngine(this);

        JPanel mainContainer = new JPanel(new BorderLayout(0, 20));
        mainContainer.setBackground(BG_DARK);
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainContainer.add(createControlDeck(), BorderLayout.NORTH);
        mainContainer.add(createResultsDeck(), BorderLayout.CENTER);
        mainContainer.add(createSmartStatusBar(), BorderLayout.SOUTH);

        add(mainContainer);
    }

    private JPanel createControlDeck() {
        JPanel deck = new JPanel(new GridBagLayout());
        deck.setBackground(CARD_BG);
        deck.setBorder(new CompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(20, 25, 20, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        deck.add(createHeaderLabel("TARGET SYSTEMS"), gbc);
        
        gbc.gridy = 1;
        ipField = createPremiumField("e.g. 192.168.1.1-255 or play.server.net");
        deck.add(ipField, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.2;
        deck.add(createHeaderLabel("PORT RANGE"), gbc);

        gbc.gridy = 1;
        portField = createPremiumField(DEFAULT_PORTS); 
        deck.add(portField, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0.1;
        deck.add(createHeaderLabel("THREADS"), gbc);

        gbc.gridy = 1;
        threadsField = createPremiumField(DEFAULT_THREADS); 
        deck.add(threadsField, gbc);
        
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 0.1;
        deck.add(createHeaderLabel("TIMEOUT (MS)"), gbc);

        gbc.gridy = 1;
        timeoutField = createPremiumField(DEFAULT_TIMEOUT); 
        deck.add(timeoutField, gbc);

        gbc.gridx = 4; gbc.gridy = 0; gbc.gridheight = 2; gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        
        JPanel actionPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        actionPanel.setBackground(CARD_BG);
        
        startButton = createPremiumButton("INITIATE", ACCENT_CYAN);
        stopButton = createPremiumButton("TERMINATE", new Color(255, 59, 48));
        stopButton.setEnabled(false);
        stopButton.setOpaque(false);

        actionPanel.add(startButton);
        actionPanel.add(stopButton);
        deck.add(actionPanel, gbc);

        setupActions();
        return deck;
    }

    private JPanel createResultsDeck() {
        JPanel deck = new JPanel(new BorderLayout());
        deck.setBackground(CARD_BG);
        deck.setBorder(new LineBorder(BORDER_COLOR, 1, true));

        String[] columns = {"STATUS", "HOST ADDRESS", "PORT", "VERSION / PROTOCOL", "PLAYERS", "DESCRIPTION"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        resultTable = new JTable(tableModel);
        resultTable.setBackground(CARD_BG);
        resultTable.setForeground(TEXT_BRIGHT);
        resultTable.setRowHeight(35);
        resultTable.setShowVerticalLines(false);
        resultTable.setShowHorizontalLines(true);
        resultTable.setGridColor(BORDER_COLOR);
        resultTable.setFocusable(false);
        resultTable.setSelectionBackground(new Color(40, 40, 50));
        resultTable.setSelectionForeground(ACCENT_CYAN);
        
        resultTable.getTableHeader().setBackground(CARD_BG);
        resultTable.getTableHeader().setForeground(TEXT_MUTED);
        resultTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 10));
        resultTable.getTableHeader().setBorder(new LineBorder(BORDER_COLOR, 0, false));

        resultTable.getColumnModel().getColumn(0).setCellRenderer(new StatusRenderer());
        resultTable.getColumnModel().getColumn(0).setMaxWidth(80);
        resultTable.getColumnModel().getColumn(2).setMaxWidth(80);

        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = resultTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    String ip = tableModel.getValueAt(row, 1).toString();
                    String port = tableModel.getValueAt(row, 2).toString();
                    
                    String copyText = ip + ":" + port;
                    
                    StringSelection selection = new StringSelection(copyText);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
                    
                    updateStatus("COPIED TO CLIPBOARD: " + copyText);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.getViewport().setBackground(CARD_BG);
        scrollPane.setBorder(null);

        deck.add(scrollPane, BorderLayout.CENTER);
        return deck;
    }

    private JPanel createSmartStatusBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(BG_DARK);
        
        statusLabel = new JLabel(" :: SYSTEM IDLE");
        statusLabel.setForeground(ACCENT_CYAN);
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(200, 4));
        progressBar.setForeground(ACCENT_CYAN);
        progressBar.setBackground(new Color(40, 40, 50));
        progressBar.setBorderPainted(false);
        
        bar.add(statusLabel, BorderLayout.WEST);
        bar.add(progressBar, BorderLayout.EAST);
        return bar;
    }

    private void setupActions() {
        startButton.addActionListener(e -> {
            toggleUI(true);
            statusLabel.setText(" :: SCANNING NEURAL NET...");
            progressBar.setIndeterminate(true);
            
            try {
                String portVal = portField.getText().trim();
                if (portVal.isEmpty()) portVal = DEFAULT_PORTS;

                String threadVal = threadsField.getText().trim();
                if (threadVal.isEmpty()) threadVal = DEFAULT_THREADS;

                String timeoutVal = timeoutField.getText().trim();
                if (timeoutVal.isEmpty()) timeoutVal = DEFAULT_TIMEOUT;

                int threads = Integer.parseInt(threadVal);
                int timeout = Integer.parseInt(timeoutVal);

                engine.startScan(ipField.getText(), portVal, threads, timeout);
            } catch(Exception ex) {
                statusLabel.setText(" :: INPUT ERROR");
                toggleUI(false);
                JOptionPane.showMessageDialog(this, "Check your inputs. \n" + ex.getMessage());
            }
        });

        stopButton.addActionListener(e -> engine.stopScan());
    }

    private JTextField createPremiumField(String placeholder) {
        JTextField field = new JTextField();
        field.putClientProperty("JTextField.placeholderText", placeholder);
        field.putClientProperty("JTextField.showClearButton", true);
        field.setFont(new Font("Monospaced", Font.PLAIN, 12));
        field.setBackground(new Color(20, 20, 26));
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return field;
    }

    private JButton createPremiumButton(String text, Color glowColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 11));
        btn.setBackground(glowColor);
        btn.setForeground(glowColor == ACCENT_CYAN ? Color.BLACK : Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JLabel createHeaderLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        lbl.setForeground(TEXT_MUTED);
        return lbl;
    }

    public synchronized void addResult(String ip, int port, String version, String players, String motd) {
        boolean isMc = !motd.contains("Non-MC");
        tableModel.addRow(new Object[]{ isMc ? "ONLINE" : "OPEN", ip, port, version, players, motd });
        resultTable.scrollRectToVisible(resultTable.getCellRect(resultTable.getRowCount() - 1, 0, true));
    }

    public void updateStatus(String msg) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(" :: " + msg.toUpperCase()));
    }

    public void toggleButtons(boolean finished) {
        toggleUI(!finished);
    }

    private void toggleUI(boolean scanning) {
        startButton.setEnabled(!scanning);
        stopButton.setEnabled(scanning);
        if(!scanning) {
            progressBar.setIndeterminate(false);
            statusLabel.setText(" :: READY");
        }
    }

    class StatusRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = (String) value;
            c.setText("  " + status + "  ");
            c.setFont(new Font("Monospaced", Font.BOLD, 11));
            
            if ("ONLINE".equals(status)) {
                c.setForeground(ACCENT_CYAN);
            } else if ("OPEN".equals(status)) {
                c.setForeground(Color.ORANGE);
            } else {
                c.setForeground(Color.GRAY);
            }
            return c;
        }
    }
}
