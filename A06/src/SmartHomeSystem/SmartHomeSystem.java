package SmartHomeSystem;
import java.util.Scanner;

/**
 * 
 * Description:
 * 1. The SmartHomeSystem is the central part of the system.
 * 2. It communicates directly with the DeviceManager, which is responsible for
 * controlling connected Devices.
 * 3. SmartHomeSystem also prompts users for a Login and provides a way for new
 * users to Register.
 * 4. SmartHomeSystem also has to check with UserManager to ensure that a User
 * has sufficient permissions to execute a Command.
 * 
 * The SmartHomeSystem class manages the user and device actions and performs
 * executions of devices according to the given command. The Login class depends
 * on this class to allow only the authenticated user to access the Smart Home
 * System Interface.
 * 
 * @author tahmid97
 * @author alexmill
 * @author partner2
 * @author partner3
 * @author partner4
 */

public class SmartHomeSystem {
	private Scanner scan;
	private User user;

    /**
     * Constructor
     */
    public SmartHomeSystem() {
        DataManager.readInfo("userData.csv", "deviceData.csv", "roleData.csv", "commandData.csv", "permissionsData.csv");
        scan = new Scanner(System.in);
    }
    
    
    public void run() {
    	promptOpeningDialog();
    }
    
    private void promptOpeningDialog() {
    	System.out.println("1. Login with existing user");
    	System.out.println("2. Create a new user");
    	System.out.println("3. Exit");
    	int option;
    	option = scan.nextInt();
    	while(option != 1 || option != 2 || option != 3) {
    		System.out.println("Please enter a valid number");
    		option = scan.nextInt();
    	}
    	switch(option) {
    	case 1:
    		login();
    		break;
    	case 2:
    		createUser();
    	case 3:
    		exit();
    	}
    }
    
    private void login() {
    	System.out.print("Enter username: ");
    	String username = scan.next();
    	System.out.print("\nEnter password: ");
    	String password = scan.next();
    	User user;
    	while((user = User.validateLogin(username, password)) == null) {
    		System.out.println("Login info was incorrect. Please try again.");
    		System.out.print("Enter username: ");
        	username = scan.next();
        	System.out.print("\nEnter password: ");
        	password = scan.next();
    	}
    	this.user = user;
    	
    }
    
    private void createUser() {
    	
    }
    
    private void exit() {
    	DataManager.writeInfo("userData.csv", "deviceData.csv", "roleData.csv", "commandData.csv", "permissionsData.csv");
    	scan.close();
    	System.exit(0);
    }
    
    
    
    
    
} //end SmartHomeSystem
