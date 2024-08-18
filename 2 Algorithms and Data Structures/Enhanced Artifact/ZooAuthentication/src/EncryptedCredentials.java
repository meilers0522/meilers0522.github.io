// Mark Eilers
// 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
// CS 499: Senior Capstone
// July 23, 2024

import java.security.MessageDigest;

public class EncryptedCredentials {
    private String password = null;

    // Constructor to initialize the password
    public EncryptedCredentials(String password) {
        this.password = password;
    }

    // Method to generate an encrypted version of the password
    public String generateEncryptedPassword() throws Exception {
        // Use MessageDigest to get an MD5 instance
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Update the digest with the password's bytes
        md.update(password.getBytes());

        // Complete the hash computation and get the resulting byte array
        byte[] hashBytes = md.digest();

        // Convert byte array to hex string
        StringBuffer hexString = new StringBuffer();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b & 0xff));
        }

        // Return the encrypted password as a hex string
        return hexString.toString();
    }
}
