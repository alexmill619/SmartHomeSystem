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
 * @author partner1
 * @author partner2
 * @author partner3
 * @author partner4
 *
 */
public class Command {
    
    private String commandName;
    private boolean state;
    private int value;
    private int deviceId;
    
    public static final HashMap<Integer, Command> commands = new HashMap<>();
    
    public Command(String commandName, int deviceId) {
        this.commandName = commandName;
        this.deviceId = deviceId;
    }
    
    public String getCommandName() {
        return null;
    }
    
    public void setValue(int val) {
        
    }
    
    public int getCurrentValue() {
        return -1;
    }
    
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
    
    public boolean getCurrentPowerState() {
        return false;
    }
    
    public void setPowerState(boolean st) {
        
    }
    
    public void executeCommand(Device device, String commandName) {
        
    }
    
} //end Command
