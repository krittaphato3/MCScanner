package com.scanner.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class IPUtils {

    /**
     * Parses an input string into a list of target IPs.
     * Supports:
     * 1. Port Stripping: 127.0.0.1:25565 -> 127.0.0.1
     * 2. Wildcards:      192.168.1.* -> Scans 192.168.1.0 to 192.168.1.255
     * 3. Ranges:         192.168.1.1-5   -> Scans 192.168.1.1 to 192.168.1.5
     * 4. Singles:        play.server.net
     */
    public static List<String> parseInput(String input) throws UnknownHostException {
        List<String> ips = new ArrayList<>();
        input = input.trim();

        // 1. SMART PORT STRIPPING
        // If the user pastes "IP:Port" (e.g. 85.235.75.1:25565), we strip the port
        // so we can scan just the IP (using the ports defined in the Port Range field).
        if (input.contains(":")) {
            int lastColon = input.lastIndexOf(':');
            String potentialPort = input.substring(lastColon + 1);
            
            // If the part after the colon is numeric, assume it's a port and strip it.
            if (potentialPort.matches("\\d+")) {
                input = input.substring(0, lastColon);
            }
        }

        // 2. WILDCARD LOGIC
        if (input.contains("*")) {
            // Handle Wildcard: 85.235.75.* or 85.235.*.*
            String startIpStr = input.replace("*", "0");
            String endIpStr = input.replace("*", "255");
            
            long start = ipToLong(InetAddress.getByName(startIpStr));
            long end = ipToLong(InetAddress.getByName(endIpStr));

            for (long i = start; i <= end; i++) {
                ips.add(longToIp(i));
            }
            
        } 
        // 3. RANGE LOGIC
        else if (input.contains("-")) {
            // Handle Range: 192.168.1.1-192.168.1.5
            String[] parts = input.split("-");
            if (parts.length == 2) {
                long start = ipToLong(InetAddress.getByName(parts[0].trim()));
                long end = ipToLong(InetAddress.getByName(parts[1].trim()));
                
                // Ensure start is less than end
                if (start > end) {
                    long temp = start;
                    start = end;
                    end = temp;
                }

                for (long i = start; i <= end; i++) {
                    ips.add(longToIp(i));
                }
            }
        } 
        // 4. SINGLE TARGET
        else {
            // Handle Single Domain or IP: "play.hypixel.net" or "127.0.0.1"
            InetAddress address = InetAddress.getByName(input);
            ips.add(address.getHostAddress());
        }
        return ips;
    }

    private static long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xFF;
        }
        return result;
    }

    private static String longToIp(long ip) {
        return ((ip >> 24) & 0xFF) + "." +
               ((ip >> 16) & 0xFF) + "." +
               ((ip >> 8) & 0xFF) + "." +
               (ip & 0xFF);
    }
}