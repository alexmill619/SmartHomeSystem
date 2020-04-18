package SmartHomeSystem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The DeviceRole class determines the types of roles that an existing user can
 * have and creates a device role for that user. The Device class depends on
 * this class to set roles to the device and store its role in the database.
 * 
 * @author tahmid97
 * @author alexmill
 * @author partner2
 * @author partner3
 * @author partner4
 *
 */
public class Role {

    /**
     * This list was marked as private in the UML. But I think making it public
     * makes more sense.
     */
    private String name;
    private ArrayList<PermissionSet> permissions;
    
    public final static HashMap<Integer, Role> roles = new HashMap<>();


    public Role(String name) {
    	this.name = name;
    	permissions = new ArrayList<>();
    }
    
    
    public void addPermission(PermissionSet permission) {
    	if (this.permissions.contains(permission)) {
    		this.permissions.remove(permission);
    	}
    	this.permissions.add(permission);
    }
    
    public ArrayList<PermissionSet> getPermissions() { return permissions; }
    
    public boolean canExecute(Command command) {
    	for (PermissionSet permission : permissions) {
    		if (permission.getCommand().equals(command)) {
    			return permission.hasPermission();
    		}
    	}
    	return true;
    }
    
    public String convertToData() {
    	return name;
    }

}
