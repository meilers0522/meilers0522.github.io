// Mark Eilers
// 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
// CS 499: Senior Capstone
// July 23, 2024

import java.security.MessageDigest;

public class PasswordHasher {

    public static void main(String[] args) throws Exception {

        // Define the original password to be hashed
        String userInput = "letmein"; // Replace "letmein" with the actual password entered from the user

        // Create an MD5 MessageDigest instance
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Update the digest using the password's bytes
        md.update(userInput.getBytes());

        // Complete the hash computation and obtain the resulting byte array
        byte[] hashBytes = md.digest();

        // Convert the byte array into a hex string
        StringBuffer hexString = new StringBuffer();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b & 0xff));
        }

        // Print the original and hashed password
        System.out.println("Original password: " + userInput);
        System.out.println("Hashed password: " + hexString.toString()); // hexString.toString() is what you'll need to compare password strings
    }

}
