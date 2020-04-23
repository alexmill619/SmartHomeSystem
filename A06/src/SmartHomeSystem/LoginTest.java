package SmartHomeSystem;
import static org.junit.Assert.assertEquals;

import org.junit.*;

/**
 * Test class for user Login
 * @author tahmid97
 * @author alexmill
 * @author taufiq96
 * @author gerritc
 * @author joeu
 */
public class LoginTest {

	@Test public void testConvertData() {
		User user = new User("FN", "LN", "Test", "pwd", "", "", 0);
		user.convertToData();
		assert (user.convertToData()) != null;
	}
	
	@Test public void testGetFirstName() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		assertEquals(user.getFirstname(), "FN");
		
	}
	
	@Test public void testGetLastName() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		assertEquals(user.getLastname(), "LN");
	
	}
	
	@Test public void testGetUsername() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		assertEquals(user.getUsername(), "Test");
		
	}
	
	@Test public void testGetPassword() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		assertEquals(user.getPassword(), "pwd");
		
	}
	
	@Test public void testGetEmail() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		assertEquals(user.getEmail(), "fnln@gmail.com");
		
	}
	
	@Test public void testGetPhone() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		assertEquals(user.getPhone(), "12345");
	
	}
	
	@Test public void testGetRole() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		assertEquals(user.getRole(), null);
	}
	
	@Test public void testSetMethods() {
		User user = new User("FN", "LN", "Test", "pwd", "fnln@gmail.com", "12345", 0);
		user.changeFirstname("John");
		user.changeLastname("Doe");
		user.changePassword("abcde"); 
		user.changeEmail("jd@ymail.com");
		user.changePhone("67890");
		user.changeRole(1);
		
		assertEquals(user.getFirstname(), "John");
		assertEquals(user.getLastname(), "Doe");
		assertEquals(user.getUsername(), "Test");
		assertEquals(user.getPassword(), "abcde");
		assertEquals(user.getEmail(), "jd@ymail.com");
		assertEquals(user.getPhone(), "67890");
		assertEquals(user.getRole(), null);
		
	}
	
	@Test public void testUserAdd() { 
		assert(User.addUser("Solid", "Karmo", "sk234", "qwerty", "skarmo@hotmail.com", "135790", 1));
		assert(!User.addUser("Solid", "Karmo", "sk234", "qwerty", "skarmo@hotmail.com", "135790", 1));
		assert(!User.addUser("", "", "", "", "", "", 1));
		assert(!User.addUser(" ", " ", " ", " ", " ", " ", 1));
		assert(!User.addUser(null, null, null, null, null, null, 1));
		assert(User.users.get("Test") == null);
		assert(User.users.get("sk234") != null);
	}
	
	@Test public void testValidateLogin() {
		User.addUser("John", "Doe", "jd12", "asdfgh", "jdoe@vt.edu", "1234567890", 0);
		assert(User.validateLogin("jd12", "asdfgh") != null);
		assert(User.validateLogin("jd12", "Test") == null);
		assert(User.validateLogin("Test", "asdfgh") == null);
	}
}
