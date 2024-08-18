// Mark Eilers
// 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
// CS 499: Senior Capstone
// July 23, 2024

public class UserCredentials {

    private String userName = null;
    private String password = null;

    public UserCredentials(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        EncryptedCredentials cred = new EncryptedCredentials(password);
        String encryptedPassword = null;

        try {
            encryptedPassword = cred.generateEncryptedPassword();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.password = encryptedPassword;
        }

    }

}