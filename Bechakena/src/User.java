class User {
    private String username;
    private String password;
    private boolean loggedIn;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = true;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void login() {
        loggedIn = true;
    }

    public void logout() {
        loggedIn = false;
    }
}