package SmartHomeSystem;

import java.util.ArrayList;

/** 
 * @author tahmid97
 * @author alexmill
 * @author partner2
 * @author partner3
 * @author partner4
 *
 */
public class PermissionSet {
	
	private int commandId;
	private boolean hasPermission;
        
    public PermissionSet(int commandId, boolean hasPermission) {
        this.commandId = commandId;
        this.hasPermission = hasPermission;
    }
    
    public boolean hasPermission() { return hasPermission; }
    
    public void toggle() {hasPermission = !hasPermission; }
    
    public Command getCommand() { return Command.commands.get(commandId); }
    
    @Override
    public boolean equals(Object o) {
    	if (o instanceof PermissionSet) {
    		PermissionSet other = (PermissionSet) o;
    		return other.commandId == this.commandId;
    	}
    	return false;
    }
    
    public String convertToData() {
    	return String.format("%d,%s", commandId, hasPermission);
    }
    
} //end PermissionSet
