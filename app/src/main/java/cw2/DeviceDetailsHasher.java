package cw2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceDetailsHasher {

    public static String hashDeviceDetails(String data) {
        try {
            // Utilizes SHA-256 to generate a hash from the provided data.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(data.getBytes());
            
            // Converts the byte array of the hash into a hex string.
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }    
}
