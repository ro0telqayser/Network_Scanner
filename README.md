Here’s a sample `README.md` for your Java application:

---

# Packet Capture Application

This is a Java-based packet capture application using the `Pcap4J` library. The application captures network packets, extracts relevant IP information, hashes the details, and stores them in a CSV file. It is designed to run on a selected network interface and process captured packets in real time.

## Features
- **Packet Capture**: Captures live network packets on a selected network interface.
- **IP Packet Processing**: Extracts source IP, destination IP, and protocol details from IP packets.
- **Hashing**: Generates a hash for each packet's source IP, destination IP, and protocol.
- **Data Storage**: Stores the processed packet details in a CSV file for later analysis.
- **Admin Privileges Check**: Ensures the application is running with administrator privileges on Windows for capturing network packets.

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java**: Version 8 or higher.
- **Pcap4J**: A Java library for capturing packets. Refer to [Pcap4J](https://www.pcap4j.org/) documentation for installation and setup.
- **Git**: If you want to clone this repository.
- **Administrator Privileges**: The application requires admin/root privileges to capture packets on most systems.

## How to Run the Application

1. **Clone the Repository** (if applicable):
   ```bash
   git clone https://github.com/your-username/your-repository.git
   cd your-repository
   ```

2. **Compile the Application**:
   Use your preferred Java IDE (like Visual Studio Code) or run the following in your terminal:
   ```bash
   javac -cp pcap4j-core-x.x.x.jar:. cw2/App.java
   ```

3. **Run the Application**:
   Ensure you are running the application with administrator privileges (important for packet capturing). You can run the application with the following command:
   ```bash
   java -cp pcap4j-core-x.x.x.jar:. cw2.App
   ```

   The application will prompt you to select a network interface for capturing packets.

4. **Stopping the Application**:
   To stop the packet capture, use `Ctrl+C`. Upon shutdown, the application will flush any buffered data to the `packets.csv` file.

## How the Application Works

- **Network Interface Selection**: Upon starting, the application asks you to select a network interface from which to capture packets.
- **Packet Capture**: The selected interface is used to capture IP packets, and the app processes each packet to extract the source and destination IP addresses and protocol.
- **Data Hashing**: A hash is generated using a combination of the source IP, destination IP, and protocol for each packet.
- **Data Storage**: The packet details, along with the generated hash, are stored in a CSV file (`packets.csv`).

## Data Storage Format

The packet data is stored in a CSV file with the following format:

| Src IP | Dst IP | Protocol | Hash |
|--------|--------|----------|------|
| x.x.x.x | x.x.x.x | TCP/UDP/etc. | [hash value] |

## Example Output

When running, the application prints the following information for each captured packet:
```
Protocol: TCP, SrcIp: 192.168.1.2, DstIp: 192.168.1.1, hash: [hash value]
```

## Shutdown Hook

The application ensures that all buffered data is written to the CSV file upon termination using a shutdown hook. This ensures that no data is lost if the application is stopped manually.

## Troubleshooting

- **Administrator Privileges**: Make sure to run the application with administrator or root privileges. Without these, packet capturing may not work properly.
- **No Device Chosen**: If no network interface is selected, the application will exit without capturing any packets.

## Future Improvements

- Support for filtering specific types of packets (e.g., only TCP packets).
- More robust error handling for different network conditions.
- GUI for easier selection of network interfaces.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to adjust the README content to fit your project more accurately.
