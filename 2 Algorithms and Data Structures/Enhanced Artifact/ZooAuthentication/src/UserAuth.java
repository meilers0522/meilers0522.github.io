// Mark Eilers
// 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
// CS 499: Senior Capstone
// July 23, 2024

// Import necessary classes for reading input and handling collections
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UserAuth {
    // Constants for predefined usernames
    public static final String ZOOKEEPER_USER = "Parker.Emily";
    public static final String ADMIN_USER = "Smith.John";
    public static final String VET_USER = "Mitchell.James";
    public static final String ZOOKEEPER_USER_1 = "Hernandez.Sophia";
    public static final String ADMIN_USER_1 = "Robinson.Daniel";
    public static final String VET_USER_1 = "Sanders.Olivia";

    // Constant for exit character
    private static final String QUIT_CHAR = "q";

    // Variables for tracking login attempts
    private static int attemptCount = 0;
    private static final int MAX_ATTEMPTS = 3;

    // Map to store user credentials
    private static Map<String, String> userCredentials;

    // Constructor (not used in this example)
    public UserAuth() {
    }

    public static void main(String[] args) throws Exception {
        // Create UserCredentials objects for each user
        UserCredentials zookeeper = new UserCredentials("Parker.Emily", "zookeeper");
        UserCredentials zookeeper1 = new UserCredentials("Hernandez.Sophia", "zookeeper1");
        UserCredentials admin = new UserCredentials("Smith.John", "admin");
        UserCredentials admin1 = new UserCredentials("Robinson.Daniel", "admin1");
        UserCredentials veterinarian = new UserCredentials("Mitchell.James", "veterinarian");
        UserCredentials veterinarian1 = new UserCredentials("Sanders.Olivia", "veterinarian1");

        // Initialize the userCredentials map and populate it with user data
        userCredentials = new HashMap<>();
        userCredentials.put(zookeeper.getUserName(), zookeeper.getPassword());
        userCredentials.put(zookeeper1.getUserName(), zookeeper1.getPassword());
        userCredentials.put(admin.getUserName(), admin.getPassword());
        userCredentials.put(admin1.getUserName(), admin1.getPassword());
        userCredentials.put(veterinarian.getUserName(), veterinarian.getPassword());
        userCredentials.put(veterinarian1.getUserName(), veterinarian1.getPassword());

        // BufferedReader to read input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Loop until the maximum number of attempts is reached
        while (attemptCount < MAX_ATTEMPTS) {
            // Prompt for username
            System.out.println("Please enter your username:");
            String inputUsername = reader.readLine();

            // Prompt for password
            System.out.println("Please enter your password:");
            String inputPassword = reader.readLine();

            // Check if the user wants to exit
            if (inputUsername.equalsIgnoreCase(QUIT_CHAR) || inputPassword.equalsIgnoreCase(QUIT_CHAR)) {
                return;
            }

            // Validate user credentials
            if (AuthHandler.isValidUser(userCredentials, inputUsername, inputPassword)) {
                AuthHandler.showGreetings(inputUsername); // Show greeting if valid
            } else {
                System.out.println("Incorrect username or password.");
                System.out.println();
                attemptCount++; // Increment attempt count
            }
        }

        // Message after exceeding maximum attempts
        System.out.println("Maximum login attempts exceeded. Try again later.");
    }
}
