package SmartHomeSystem;

import java.io.*;

/**
 * The UserManager class handles the database of the users. In this class, newly
 * registered users are added to the database, profile information of existing
 * users is updated, and account deletion of existing users is performed. Also,
 * the Login class depends on this class to check if the user exists in the
 * database and matches the username and password associated with the account.
 * And, the SmartHomeSystem class depends on it to add a new user, update the
 * existing user�s profile or delete the existing user�s profile.
 * 
 * @author tahmid97
 * @author alexmill
 * @author taufiq96
 * @author gerritc
 * @author joeu
 */ 
public class DataManager {
    public static void readInfo(String userFile, String deviceFile, String roleFile, String commandFile, String permissionFile) {
    	User.users.clear();
    	Device.devices.clear();
    	Role.roles.clear();
    	Command.commands.clear();
    	readUserInfo(userFile);
    	readDeviceInfo(deviceFile);
    	readRoleInfo(roleFile);
    	readCommandInfo(commandFile);
    	readPermissionsInfo(permissionFile);
    }
    private static void readUserInfo(String filename) {
    	try { 
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				User user = new User(data[0], data[1], data[2], data[3], data[4], data[5], Integer.valueOf(data[6]));
				User.users.put(user.getUsername(), user);
			}
			reader.close();
		} catch (FileNotFoundException fnfe) {
			
		} catch (IOException io) {
			
		}
    }
    
    private static void readRoleInfo(String filename) { 
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
    		String line;
    		while((line = reader.readLine()) != null) {
    			String[] data = line.split(",");
    			int id = Integer.valueOf(data[0]);
    			Role.roles.put(id, new Role(data[1]));
    		}
    		reader.close();	
    		
    	} catch (FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    private static void readPermissionsInfo(String filename) {
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
    		String line;
    		while((line = reader.readLine()) != null) {
    			String[] data = line.split(",");
    			int roleId = Integer.valueOf(data[0]);
    			int commandId = Integer.valueOf(data[1]);
    			boolean permission = data[2].contentEquals("true");
    			Role role = Role.roles.get(roleId);
    			role.addPermission(new PermissionSet(commandId, permission));
    		}
    		reader.close();
    	} catch(FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    private static void readDeviceInfo(String filename) {
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
    		String line;
    		while((line = reader.readLine()) != null) {
    			String[] data = line.split(",");
    			int deviceId = Integer.valueOf(data[0]);
    			Device.devices.put(deviceId, new Device(data[1], data[2], data[3]));
    		}
    		reader.close();
    	} catch(FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    private static void readCommandInfo(String filename) {
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
    		String line;
    		while((line = reader.readLine()) != null) {
    			String[] data = line.split(",");
    			int commandId = Integer.valueOf(data[0]);
    			int deviceId = Integer.valueOf(data[2]);
    			Command.commands.put(commandId, new Command(data[1], deviceId));
    			Device.devices.get(deviceId).addCommand(Command.commands.get(commandId));
    		}
    		reader.close();
    	} catch(FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    public static void writeInfo(String userFile, String deviceFile, String roleFile, String commandFile, String permissionFile) {
    	writeUserData(userFile);
    	writeDeviceData(deviceFile);
    	writeRoleData(roleFile);
    	writeCommandData(commandFile);
    	writePermissionData(permissionFile);
    }
    
    private static void writeUserData(String filename) {
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
    		for(User user : User.users.values()) {
    			writer.write(String.format("%s\n", user.convertToData()));
    		}
    		writer.close();
    	} catch (FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    private static void writeDeviceData(String filename) {
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
    		for(int i : Device.devices.keySet()) {
    			writer.write(String.format("%d,%s\n", i, Device.devices.get(i).convertToData()));
    		}
    		writer.close();
    	} catch (FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    private static void writeRoleData(String filename) {
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
    		for(int i : Role.roles.keySet()) {
    			writer.write(String.format("%d,%s\n", i, Role.roles.get(i).convertToData()));
    		}
    		writer.close();
    	} catch (FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    private static void writeCommandData(String filename) {
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
    		for(int i : Command.commands.keySet()) {
    			writer.write(String.format("%d,%s\n", i, Command.commands.get(i).convertToData()));
    		}
    		writer.close();
    	} catch (FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
    
    private static void writePermissionData(String filename) {
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
    		for(int i : Role.roles.keySet()) {
    			for (PermissionSet set : Role.roles.get(i).getPermissions()) {
    				writer.write(String.format("%d,%s\n", i, set.convertToData()));
    			}
    		}
    		writer.close();
    	} catch (FileNotFoundException fnfe) {
    		
    	} catch (IOException io) {
    		
    	}
    }
}
