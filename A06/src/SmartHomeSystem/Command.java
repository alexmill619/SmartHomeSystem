package SmartHomeSystem;

import java.util.HashMap;

/**
 * The Command class handles the commands given by the user for the appropriate
 * devices. The class is responsible for turning devices on/off, and adjusting
 * their associated values by following correct commands. The SmartHomeSystem
 * class depends on this class to execute commands issued by the user from the
 * Smart Home System Interface. The Device class also depends on this class to
 * get the specific commands designed for a specific device.
 * 
 * @author tahmid97
 * @author alexmill
 * @author taufiq96
 * @author gerritc
 * @author joeu
 *
 */
public class Command {
    
    private String commandName;
    private int deviceId;
    
    public static final HashMap<Integer, Command> commands = new HashMap<>();
    
    public Command(String commandName, int deviceId) {
        this.commandName = commandName;
        this.deviceId = deviceId;
    }
    
    public String getCommandName() { return commandName; }
    
    @Override public boolean equals(Object o) {
    	if (o instanceof Command) {
    		Command other = (Command) o;
    		Device thisDevice = Device.devices.get(this.deviceId);
    		Device otherDevice = Device.devices.get(other.deviceId);
    		return otherDevice.equals(thisDevice) && other.commandName.contentEquals(this.commandName);
    	}
    	return false;
    }
    
    public String convertToData() {
    	return String.format("%s,%d", this.commandName, this.deviceId);
    }
    
    public boolean executeCommand(User user) {
        Role role = user.getRole();
        if (role.canExecute(this)) {
        	System.out.printf("Executed %s\n", this.commandName);
        	return true;
        }
        System.out.printf("User does not have permission to execute %s\n", this.commandName);
        return false;
    }
    
} //end Command
