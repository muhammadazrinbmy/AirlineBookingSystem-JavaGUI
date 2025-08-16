public class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    
    public String getUserId() {
        return userId;
    }
    
 // ✅ Add this method to allow `Admin` to access name
    public String getName() {
        return name;
    }
}

