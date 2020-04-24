package SmartHomeSystem;
import org.junit.*;

/**
 * 
 * @author tahmid97
 *
 */
public class CommandTest {
    
    private Command command;
    
    @Test
    public void setUp() {
        command = new Command("Alert User: Door Not Closed!", 0);
    }
    
    @Test
    public void testCommandName() {
        setUp();
        assert(command.getCommandName().equals("Alert User: Door Not Closed!"));
    }
    
    @Test
    public void testEquals() {
        setUp();
        Device.devices.put(0, new Device("Fridge", "Appliances", "Samsung"));        
        Command same = new Command("Alert User: Door Not Closed!", 0);
        assert(command.equals(same));   
    }
    
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testDifferntClassesNotEqual() {
        setUp();
        assert(!command.equals("command"));
    }
    
    @Test
    public void testNotEquals() {
        setUp();
        Device.devices.put(1, new Device("Oven", "Appliances", "LG"));
        Command diff = new Command("Alert User: Door Not Closed!", 1); 
        assert(!command.equals(diff));
    }
    
    
    @Test
    public void testConvertToData() {
        setUp();
        assert(command.convertToData().contentEquals("Alert User: Door Not Closed!,0"));
    }
    
    @Test
    public void testExecuteCommand() {
        setUp();
        Role.roles.put(1, new Role("tester"));       
        User user1 = new User("first", "last", "fl", "password", "fake@gmail.com", "112233", 1);
        assert(command.executeCommand(user1));
    }
    
    @Test
    public void testNotExecuteCommand() {

        setUp();
        Role.roles.put(1, new Role("tester"));       
        User user = new User("first", "last", "fl", "password", "fake@gmail.com", "112233", 1);
        Command command2 = new Command("Alert User: Oven heated.", 0);
        
        assert(command2.executeCommand(user));
    }
    
} //end command Test
