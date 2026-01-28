# MCScanner (PortScope Pro)

A high-performance, multi-threaded Minecraft server scanner and pinger built with Java. Designed for speed and accuracy, it utilizes modern concurrency features (Java 21 Virtual Threads) to scan vast IP ranges efficiently while providing a sleek, responsive user interface.

## 🚀 Features

*   **Hybrid Scanning Engine:**
    *   **Modern Strategy:** Full JSON handshake support for Minecraft 1.7+ servers.
    *   **Legacy Strategy:** Fallback support for older 1.6.4 and 1.5.x servers.
*   **High-Performance Concurrency:**
    *   **Virtual Threads:** Automatically detects and utilizes Java 21+ Virtual Threads for massive parallelism (Hyperscan mode).
    *   **Smart Pooling:** Fallback to optimized fixed thread pools for older Java versions.
*   **Modern UI (PortScope Pro):**
    *   Built with **FlatLaf** for a professional, dark-themed aesthetic.
    *   Real-time result buffering to prevent UI lag during high-speed scans.
    *   "Copy on Click" functionality for easy server address sharing.
*   **Detailed Server Info:**
    *   Retrieves MOTD, Version, Protocol, Player Count (Online/Max), and Player Samples.
    *   Distinguishes between "Online" (MC Server) and "Open" (Port open but not MC) states.

## 📋 Prerequisites

*   **Java Development Kit (JDK):** JDK 21 or higher is **strongly recommended** to enable Virtual Threads (Hyperscan mode).
    *   *Minimum:* Java 17.
*   **Gradle:** The project uses the Gradle wrapper (included), so a manual Gradle installation is not strictly required.

## 🛠️ Installation & Build

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/MCScanner.git
    cd MCScanner
    ```

2.  **Build with Gradle:**
    On Windows:
    ```powershell
    ./gradlew build
    ```
    On Linux/macOS:
    ```bash
    ./gradlew build
    ```

3.  **Run the application:**
    You can run it directly via Gradle:
    ```bash
    ./gradlew run
    ```
    Or execute the built JAR file (located in `build/libs`):
    ```bash
    java -jar build/libs/MCScanner-2.5-HYPER.jar
    ```

## 🖥️ Usage

1.  **Target Systems:** Enter an IP range or a single hostname.
    *   *Example:* `192.168.1.1-255` (Scans from .1 to .255)
    *   *Example:* `play.hypixel.net`
2.  **Port Range:** Specify the ports to scan.
    *   *Default:* `25565` (Standard MC port) or `25000-26000`
3.  **Threads:** Number of concurrent scanners.
    *   *Standard:* `500-1000`
    *   *Hyper (Java 21+):* `2000+` (The engine will switch to Virtual Threads for unlimited scaling).
4.  **Timeout:** Connection timeout in milliseconds (default: `1500`).
5.  **Initiate:** Click **INITIATE** to start the scan. Results will populate the table in real-time.

## ⚠️ Disclaimer

This tool is intended for **educational purposes and legitimate network administration only**.
*   Do not use this tool to scan networks you do not own or have explicit permission to audit.
*   The developer assumes no liability for misuse of this software.

## 📄 License

[MIT License](LICENSE) (or your preferred license)
