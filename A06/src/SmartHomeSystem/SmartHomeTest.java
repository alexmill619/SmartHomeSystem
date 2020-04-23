package SmartHomeSystem;
import org.junit.*;


public class SmartHomeTest {
	@Test public void testAddUser() { 
		assert(User.addUser("FN", "LN", "Test", "pwd", "", "", 0));
		assert(!User.addUser("FN", "LN", "Test", "pwd", "", "", 0));
		assert(User.users.get("Test") != null);
	}
	 
	@Test public void testLogin() {
		User.addUser("FN", "LN", "Test", "pwd", "", "", 0);
		assert(User.validateLogin("Test", "pwd") != null);
		assert(User.validateLogin("DNE", "Test") == null);
		assert(User.validateLogin("Test", "incorrect") == null);
	}
	
	@Test public void testExecuteCommand() {
		setup();
		User testUser = User.users.get("userB");
		Command cmd1 = Command.commands.get(1);
		assert(!cmd1.executeCommand(testUser));
		assert(cmd1.executeCommand(User.users.get("userA")));
	}
	
	
	@Test public void testChangeRoles() {
		setup(); 
		User testUser = User.users.get("userC");
		testUser.changeRole(1);
		assert(testUser.getRole().equals(Role.roles.get(1)));
	}
	
	
	@Test public void testAddDevice() {
		setup();
		assert(Device.addDevice("Test", "Test", "Test") != null);
		assert(Device.devices.keySet().size() == 4);
	}
	private void setup() {
		DataManager.readInfo("userdata.csv", "devicedata.csv", "roledata.csv", "commanddata.csv", "permissionsdata.csv");
	}
}
