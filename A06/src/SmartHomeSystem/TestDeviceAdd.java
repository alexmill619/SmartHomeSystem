package SmartHomeSystem;

import static org.junit.Assert.assertTrue;

import org.junit.*;

public class TestDeviceAdd {

	
	private Device dev1;
	private Device empty;
	private Device eqDev1;
	private Device dev2;
	
	
	
	// run this set-up before each test
	
	 private void setup() {
		 
		  dev1 = new Device("Echo Dot", "speaker", "A389234");
		  eqDev1 = new Device("Echo Dot", "speaker", "A389234");
		  dev2 = new Device("Nest Thermostat", "thermostat", "C639538");
		  empty = new Device("", "", "");
		  
		  Device.devices.put(1, dev1);
		  Device.devices.put(2, eqDev1);
		  Device.devices.put(3, dev2);
		  Device.devices.put(4, empty);
	}

	
	// check that convertToData works properly.
	@Test
	public void testConvertToData() {
		setup();
		assertTrue(dev1.convertToData().equals("Echo Dot,speaker,A389234"));
		assertTrue(empty.convertToData().equals(",,"));
	}
	
	// check that devices were added properly.
	@Test
	public void testAddDevice() {
		setup();
		
		assert(Device.addDevice("Echo Dot", "speaker", "A389234") != null);
		assert(Device.addDevice("Echo Dot", "speaker", "A389234") != null);
		assert(Device.addDevice("Nest Thermostat", "thermostat", "C639538") != null);
		assert(Device.addDevice("", "", "") != null);
		
		assertTrue(Device.devices.get(1).equals(dev1));
		assertTrue(Device.devices.get(2).equals(eqDev1));
		assertTrue(Device.devices.get(3).equals(dev2));
		assertTrue(Device.devices.get(4).equals(empty));
	}
	
	@Test public void otherTests() {
		
	}
}
