package org.krittaphato3.mcping;

import lombok.Builder;
import lombok.Value;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.krittaphato3.mcping.model.*;
import org.krittaphato3.mcping.utils.Singleton;

@Value
@Builder
public class MCPinger {

    PingOptions pingOptions;

    public ServerResponse fetchData() {
        try {
            return pingModern();
        } catch (Exception e) {
        }

        try {
            return pingLegacy();
        } catch (Exception e) {
            return null;
        }
    }

    private ServerResponse pingModern() throws IOException {
        InetSocketAddress socketAddress = pingOptions.getSocketAddress();

        try (Socket socket = new Socket()) {
            socket.setSoTimeout(pingOptions.getTimeout());
            socket.connect(socketAddress, pingOptions.getTimeout());

            InputStream inputStream = new BufferedInputStream(socket.getInputStream());
            OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream handshake = new DataOutputStream(b);
            
            handshake.writeByte(0x00);
            writeVarInt(handshake, pingOptions.getProtocolVersion());
            writeVarInt(handshake, socketAddress.getHostString().length()); 
            handshake.writeBytes(socketAddress.getHostString());
            handshake.writeShort(socketAddress.getPort());
            writeVarInt(handshake, 1);

            writeVarInt(dataOutputStream, b.size());
            b.writeTo(dataOutputStream);

            dataOutputStream.writeByte(0x01);
            dataOutputStream.writeByte(0x00);
            dataOutputStream.flush();

            int size = readVarInt(dataInputStream);
            int id = readVarInt(dataInputStream);

            if (id == -1) throw new IOException("Premature end of stream");
            if (id != 0x00) throw new IOException("Invalid packetID: " + id);

            int length = readVarInt(dataInputStream);
            if (length <= 0) throw new IOException("Invalid string length");

            byte[] fetchedData = new byte[length];
            dataInputStream.readFully(fetchedData);
            
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fetchedData);
            return Singleton.dslJson.deserialize(ServerResponse.class, byteArrayInputStream);
        }
    }

    private ServerResponse pingLegacy() throws IOException {
        InetSocketAddress socketAddress = pingOptions.getSocketAddress();
        
        try (Socket socket = new Socket()) {
            socket.setSoTimeout(pingOptions.getTimeout());
            socket.connect(socketAddress, pingOptions.getTimeout());
            
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            
            out.write(new byte[]{(byte) 0xFE, 0x01});
            
            out.flush();

            int kickPacketId = in.read();
            if (kickPacketId != 0xFF) throw new IOException("Invalid kick packet");

            int length = in.read() << 8 | in.read();
            if (length <= 0 || length > 1024) throw new IOException("Invalid legacy length");

            byte[] data = new byte[length * 2];
            new DataInputStream(in).readFully(data);
            
            String response = new String(data, StandardCharsets.UTF_16BE);
            
            String[] parts = response.split("\0");
            
            if (parts.length >= 6 && parts[0].equals("§1")) {
                String versionStr = parts[2];
                String motdStr = parts[3];
                int online = parseIntSafe(parts[4]);
                int max = parseIntSafe(parts[5]);
                int protocol = parseIntSafe(parts[1]);

                Version version = new Version(versionStr, protocol);
                Players players = new Players(max, online, Collections.emptyList());
                Description description = new Description(motdStr, Collections.emptyList());
                
                return new ServerResponse(version, players, description, null, false, false);
            } else {
                 String[] oldParts = response.split("§");
                 if (oldParts.length >= 3) {
                     String motdStr = oldParts[0];
                     int online = parseIntSafe(oldParts[1]);
                     int max = parseIntSafe(oldParts[2]);
                     
                     Version version = new Version("Legacy < 1.6", 0);
                     Players players = new Players(max, online, Collections.emptyList());
                     Description description = new Description(motdStr, Collections.emptyList());
                     return new ServerResponse(version, players, description, null, false, false);
                 }
            }
            throw new IOException("Unknown legacy format");
        }
    }

    private int parseIntSafe(String s) {
        try { return Integer.parseInt(s); } catch (NumberFormatException e) { return 0; }
    }

    private int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        int k;
        do {
            k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) return -1;
        } while ((k & 0x80) == 128);
        return i;
    }

    private void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                out.writeByte(paramInt);
                return;
            }
            out.writeByte(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }
}