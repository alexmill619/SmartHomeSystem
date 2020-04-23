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
 * @author taufiq96
 * @author gerritc
 * @author joeu
 */

public class SmartHomeSystem {
	private Scanner scan;
	private User user;

    /**
     * Constructor
     */
    public SmartHomeSystem() {
        DataManager.readInfo("userdata.csv", "devicedata.csv", "roledata.csv", "commanddata.csv", "permissionsdata.csv");
        scan = new Scanner(System.in);
    }
    
    
    public void run() { 
    	promptOpeningDialog();
    }
    
    private void promptOpeningDialog() {
    	int option;
    	System.out.println("1. Login with existing user");
    	System.out.println("2. Create a new user");
    	System.out.println("3. Exit");
    	option = scan.nextInt();
    	
    	while(option != 1 && option != 2 && option != 3) {
    		System.out.println("Please enter a valid number");
    		option = scan.nextInt();
    	}
    	switch(option) {
    	case 1:
    		promptLogin();
    		break;
    	case 2:
    		promptCreateUser();
    		break;
    	case 3:
    		exit();
    		break;
    	}
    }
    
    private void promptLogin() {
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
    	System.out.println("Login Successful!");
    	
    }
    
    private void promptCreateUser() {
        System.out.print("Enter first name: ");
        String firstname = scan.next();
        System.out.print("\nEnter last name: ");
        String lastname = scan.next();
        System.out.print("Enter username: ");
        String username = scan.next();
        System.out.print("\nEnter password: ");
        String password = scan.next();
        System.out.print("Enter email: ");
        String email = scan.next();
        System.out.print("\nEnter phone: ");
        String phone = scan.next();
        System.out.print("\nEnter role: ");
        int role = scan.nextInt();
        boolean userCreated = User.addUser(firstname, lastname, username, password, email, phone, role);
        if(userCreated){
            this.user = User.validateLogin(username,password);
        }
        while(!userCreated) {
            System.out.println("User already created.");
            System.out.println("1. Login with existing user");
            System.out.println("2. Try creating a new user again");
            int option = scan.nextInt();
            switch(option) {
                case 1: 
                    promptLogin();
                    break;
                case 2:
                    System.out.print("Enter first name: ");
                    firstname = scan.next();
                    System.out.print("\nEnter last name: ");
                    lastname = scan.next();
                    System.out.print("Enter username: ");
                    username = scan.next();
                    System.out.print("\nEnter password: ");
                    password = scan.next();
                    System.out.print("Enter email: ");
                    email = scan.next();
                    System.out.print("\nEnter phone: ");
                    phone = scan.next();
                    System.out.print("\nEnter role: ");
                    role = scan.nextInt();
                    userCreated = User.addUser(firstname, lastname, username, password, email, phone, role);
                    if(userCreated){
                        this.user = User.validateLogin(username,password);
                       
                    }
            }
        }
    }
    
    private void exit() {
    	DataManager.writeInfo("userdata.csv", "devicedata.csv", "roledata.csv", "commanddata.csv", "permissionsdata.csv");
    	System.out.println("You have exited successfully!");
    	scan.close();
    	System.exit(0);
    }
    
    
    
    
    
} //end SmartHomeSystem
