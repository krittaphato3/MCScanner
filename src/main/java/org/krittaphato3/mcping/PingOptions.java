package org.krittaphato3.mcping;

import lombok.Builder;
import lombok.Value;
import java.net.InetSocketAddress;

@Value
@Builder
public class PingOptions {
    String hostname; // The actual IP to connect to
    int port;        // The actual port to connect to
    int timeout;
    
    // NEW: The hostname to send in the packet (e.g., "play.server.net")
    // If null, it defaults to the 'hostname' IP.
    String virtualHost; 

    @Builder.Default
    int protocolVersion = 47;

    public InetSocketAddress getSocketAddress() {
        return new InetSocketAddress(hostname, port);
    }
}