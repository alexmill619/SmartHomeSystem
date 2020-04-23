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
    
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEquals() {
        setUp();
        assert(!command.equals("command"));
        
        Device.devices.put(0, new Device("Fridge", "Appliances", "Samsung"));
        Device.devices.put(1, new Device("Oven", "Appliances", "LG"));
        
        Command same = new Command("Alert User: Door Not Closed!", 0);
        Command diff = new Command("Alert User: Door Not Closed!", 1);     
        assert(command.equals(same));
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
    
} //end command Test
