package cw2;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private CSVFileManager csvFileManager;
    private List<PacketDetails> packetDetailsBuffer;

    public DataStorage(String filename) {
        this.csvFileManager = new CSVFileManager(filename);
        this.packetDetailsBuffer = new ArrayList<>();
    }

    // Method to add packet details to the buffer
    public void addPacketDetails(PacketDetails details) {
        packetDetailsBuffer.add(details);
        if (packetDetailsBuffer.size() >= 10) {  // Adjust this value if needed
            flush();
        }
    }

    // Method to save packet details to the CSV file
    public void savePacketDetails() {
        csvFileManager.writePacketDetails(packetDetailsBuffer);
        packetDetailsBuffer.clear();  // Clear the buffer after saving
    }

    // Optionally, if you want to make sure all data is saved upon program termination or at certain points
    public void flush() {
        if (!packetDetailsBuffer.isEmpty()) {
            savePacketDetails();
            packetDetailsBuffer.clear();
        }
    }
}
