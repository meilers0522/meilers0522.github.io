// Mark Eilers
// 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
// CS 499: Senior Capstone
// July 23, 2024

import java.util.Map;

public class AuthHandler {
    // Private constructor to prevent instantiation
    private AuthHandler() {
    }

    // Method to validate user credentials
    public static boolean isValidUser(Map<String, String> userCredentials, String username, String password) {
        if (userCredentials != null && username != null && password != null) {
            boolean isValid = false;
            EncryptedCredentials credentials = new EncryptedCredentials(password);

            try {
                // Check if the username exists and the password matches the encrypted password
                if (userCredentials.containsKey(username) && userCredentials.containsValue(credentials.generateEncryptedPassword())) {
                    isValid = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return isValid;
        } else {
            return false;
        }
    }

    // Method to display greetings based on username
    public static boolean showGreetings(String username) {
        boolean isValidUser = false;
        switch (username.hashCode()) {
            case -1996277406:
                if (username.equals("Parker.Emily")) {
                    System.out.println("Welcome, Zookeeper!\n\nAs a zookeeper, you have full access to all animal information and their daily logs. This allows you to monitor their feeding habits, habitat conditions, and overall welfare.");
                    isValidUser = true;
                    break;
                }
                break;
            case -1274872615:
                if (username.equals("Smith.John")) {
                    System.out.println("Welcome, Admin!\n\nAs an administrator, you have access to the zoo's main computer system. This enables you to monitor users in the system and their roles.");
                    isValidUser = true;
                    break;
                }
                break;
            case 815374008:
                if (username.equals("Robinson.Daniel")) {
                    System.out.println("Welcome, Admin!\n\nAs an administrator, you have access to the zoo's main computer system. This enables you to monitor users in the system and their roles.");
                    isValidUser = true;
                    break;
                }
                break;
            case 1048853569:
                if (username.equals("Sanders.Olivia")) {
                    System.out.println("Greetings, Veterinarian!\n\nAs a veterinarian, you have access to all animal health records. This allows you to view each animal's medical history and current treatments, and to maintain a vaccination log.");
                    isValidUser = true;
                    break;
                }
                break;
            case 1611795855:
                if (username.equals("Mitchell.James")) {
                    System.out.println("Greetings, Veterinarian!\n\nAs a veterinarian, you have access to all animal health records. This allows you to view each animal's medical history and current treatments, and to maintain a vaccination log.");
                    isValidUser = true;
                    break;
                }
                break;
            case 1842411339:
                if (username.equals("Hernandez.Sophia")) {
                    System.out.println("Welcome, Zookeeper!\n\nAs a zookeeper, you have full access to all animal information and their daily logs. This allows you to monitor their feeding habits, habitat conditions, and overall welfare.");
                    isValidUser = true;
                    break;
                }
                break;
            default:
                System.out.println("Incorrect username and/or password combination. You have exceeded the maximum number of login attempts. Please try again later.");
                break;
        }

        return isValidUser;
    }
}


