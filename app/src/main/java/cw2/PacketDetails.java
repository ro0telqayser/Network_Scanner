package cw2;

public class PacketDetails {
    private String sourceIp;
    private String destinationIp;
    private String protocol; // New field to store the protocol
    private String hash;

    // Constructor that includes the protocol
    public PacketDetails(String sourceIp, String destinationIp, String protocol, String hash) {
        this.sourceIp = sourceIp;
        this.destinationIp = destinationIp;
        this.protocol = protocol;
        this.hash = hash;
    }

    // Getters and setters for source IP
    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    // Getters and setters for destination IP
    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    // Getter and setter for the protocol
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    // Getters and setters for hash
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    // Overriding toString for debugging purposes
    @Override
    public String toString() {
        return "PacketDetails{" +
                "sourceIp='" + sourceIp + '\'' +
                ", destinationIp='" + destinationIp + '\'' +
                ", protocol='" + protocol + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
