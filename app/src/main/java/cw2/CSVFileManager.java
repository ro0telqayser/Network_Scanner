package cw2;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileManager {
    private String filename;

    public CSVFileManager(String filename) {
        this.filename = filename;
    }

    // Method to write PacketDetails to the CSV file
    public void writePacketDetails(List<PacketDetails> packetDetailsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (PacketDetails details : packetDetailsList) {
                writer.write(formatCsvRow(details));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    // Helper method to format a PacketDetails object into a detailed output string
    private String formatCsvRow(PacketDetails details) {
        return "Protocol: " + details.getProtocol() + ", SrcIp: " + details.getSourceIp() +
               ", DstIp: " + details.getDestinationIp() + ", hash: " + details.getHash();
    }
}
