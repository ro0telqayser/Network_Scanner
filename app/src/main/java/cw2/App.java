package cw2;

import org.pcap4j.core.*;
import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main application class for packet capturing using Pcap4J.
 * This class initializes and controls the packet capturing process,
 * ensuring that packets are captured, processed, and stored appropriately.
 */
public class App {
    // Flag to control the running state of the packet capturing loop.
    private static boolean running = true;

    // DataStorage instance for managing packet data storage.
    private static DataStorage storage = new DataStorage("packets.csv");

    /**
     * The main method - the entry point of the application.
     */
    public static void main(String[] args) {
        try {
            checkAdministratorPrivileges();
            System.out.println("Starting packet capture... Press Ctrl+C to stop.");
            capturePackets();
        } catch (PcapNativeException | NotOpenException | InterruptedException e) {
            System.err.println("Error capturing packets: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Checks if the application is running with administrator privileges.
     * Necessary for capturing packets on some operating systems.
     */
    private static void checkAdministratorPrivileges() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.out.println("Please ensure you are running this application with administrator privileges.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Are you running as administrator? (yes/no)");
            String response = scanner.nextLine();
            if (!"yes".equalsIgnoreCase(response)) {
                System.out.println("Exiting: Administrator privileges required.");
                System.exit(0);
            }
            scanner.close();
        }
    }

    /**
     * Captures packets using a selected network interface.
     * Sets up a packet listener that processes each packet caught by the PcapHandle.
     */
    public static void capturePackets() throws PcapNativeException, NotOpenException, InterruptedException {
        PcapNetworkInterface device = null;
        try {
            device = new NifSelector().selectNetworkInterface();
        } catch (IOException e) {
            System.err.println("IO Exception while selecting network interface: " + e.getMessage());
            return;
        }

        if (device == null) {
            System.out.println("No device chosen. Exiting...");
            return;
        }

        try (PcapHandle handle = device.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10)) {
            PacketListener listener = packet -> processPacket(packet);
            while (running) {
                handle.loop(10, listener);
            }
        } catch (Exception e) {
            System.err.println("Error during packet capture: " + e.getMessage());
        }
    }

    /**
     * Processes each packet, extracting relevant IP details and hashing them.
     * Stores the hashed details using the DataStorage instance.
     */
    private static void processPacket(Packet packet) {
        IpPacket ipPacket = packet.get(IpPacket.class);
        if (ipPacket != null) {
            String srcIp = ipPacket.getHeader().getSrcAddr().toString().substring(1);  // Remove leading '/'
            String dstIp = ipPacket.getHeader().getDstAddr().toString().substring(1);  // Remove leading '/'
            String protocol = ipPacket.getHeader().getProtocol().name();  // Retrieve the protocol name

            String dataToHash = srcIp + dstIp + protocol;  // Data to be hashed
            String hash = DeviceDetailsHasher.hashDeviceDetails(dataToHash);  // Generate hash

            PacketDetails details = new PacketDetails(srcIp, dstIp, protocol, hash);  // Create packet details object
            storage.addPacketDetails(details);  // Store packet details

            System.out.println("Protocol: " + protocol + ", SrcIp: " + srcIp + ", DstIp: " + dstIp + ", hash: " + hash);
        }
    }

    // A shutdown hook to ensure all buffered data is written to file upon program termination.
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down and flushing data...");
            storage.flush();  // Ensure all buffered data is written to file
        }));
    }
}
