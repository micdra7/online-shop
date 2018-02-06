package drabik.michal.validation;

public class UserDataError {

    public static final String EXISTING_USERNAME = "This username is already in use";
    public static final String SHORT_PASSWORD = "This password is too short. It should consist of at least 8 characters";
    public static final String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password";
    private String username;
    private String password;

    public UserDataError() {}

    public UserDataError(String usernameError, String passwordError) {
        username = usernameError;
        password = passwordError;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
