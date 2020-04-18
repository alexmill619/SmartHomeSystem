package SmartHomeSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import CS2114.*;

/**
 * The Device class handles all the information of an existing user's devices
 * such as device name, device role, device type, and device ID. This class also
 * allows the user to change their device�s name, type, and role. The
 * DeviceManager class depends on this class to update the current device
 * information in the database.
 * 
 * 
 * @author tahmid97
 * @author alexmill
 * @author partner2
 * @author partner3
 * @author partner4
 *
 */
public class Device {
    
	
	public static final HashMap<Integer, Device> devices = new HashMap<>();
	
    private String deviceName;
    private String deviceType;
    private String modelName;

    private ArrayList<Command> commands;
    
    private Shape icon;
    
    
    public Device(String name, String type, String model) {
    	this.deviceName = name;
    	this.deviceType = type;
    	this.modelName = model;
    	this.commands = new ArrayList<>();
    }
    
    public String getDeviceName() {
        return this.deviceName;
    }
    
    public int getDeviceID() {
        return -1;
    }
    
    public String getDeviceType() {
        return null;
    }
    
    public void setDeviceType(String deviceType) {
        
    }
    
    public void setDeviceName(String deviceName) {
        
    }
    
    public void addCommand(Command command) {
    	if (commands.contains(command)) {
    		commands.remove(command);
    	}
    	commands.add(command);
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o instanceof Device) {
    		Device other = (Device) o;
    		return other.deviceName.contentEquals(this.deviceName);
    	}
    	return false;
    }
    
    public String convertToData() {
    	return String.format("%s%s%s", deviceName, deviceType, modelName);
    }
    
    public void createDeviceWindow(Shape shape) {
        Window deviceWindow = new Window(deviceName);
        deviceWindow.setSize(400, 400);
        
        Button quitButton = new Button("Quit"); 
        deviceWindow.addButton(quitButton, WindowSide.SOUTH);
    }
    
    public Shape makeDeviceIcon(int xMul, int yMul) { 
        icon = new Shape(50 + 90*xMul, 270 + 90*yMul, 70, 70, Color.lightGray);
        icon.onClick(this, "createDeviceWindow");
        return icon;
    }
} //end Device
