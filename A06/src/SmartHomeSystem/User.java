package SmartHomeSystem;

import java.util.HashMap;

/**
 * The User class is treated as the login/register interface to guide the users
 * in logging into their existing accounts or opening a new one. The Register
 * class and the Login class depend on it to determine whether the current user
 * wants to log in to their account or open a new one.
 * 
 * @author tahmid97
 * @author alexmill
 * @author partner2
 * @author partner3
 * @author partner4
 *
 */
public class User {
    private String firstname, lastname, username, email, phone, password;
    private int roleId;
    
    public final static HashMap<String, User> users = new HashMap<>();
    
    public User(String firstname, String lastname, String username, String password, String email, String phone, int roleId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
    }
    
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Role getRole() { return Role.roles.get(roleId); }
    
    public void changeUsername(String username) { this.username = username; }
    public void changePassword(String password) { this.password = password; }
    public void changeFirstname(String firstname) {this.firstname = firstname; }
    public void changeLastname(String lastname) {this.lastname = lastname; }
    public void changeEmail(String email) {this.email = email; }
    public void changePhone(String phone) {this.phone = phone; }
    public void changeRole(int roleId) { this.roleId = roleId; }
    
    public static User validateLogin(String username, String password) {
    	User user = users.get(username);
    	return (user == null || !user.password.contentEquals(password)) ? null : user;
    }
    
    public String convertToData() {
    	return String.format("%s,%s,%s,%s,%s,%d", firstname, lastname, username, password, email, phone, roleId);
    }
}
