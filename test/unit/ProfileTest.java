package unit;

import models.User;
import org.junit.Test;
import play.libs.Crypto;
import play.mvc.Before;
import play.test.Fixtures;
import play.test.UnitTest;


public class ProfileTest extends UnitTest {

	@Test
    public void updateGeneralProfileInformationTest(){
        User user = User.find("byUsername", "tom").first();
        
        user.setEmail("tom1@email.com");
    	user.setFirstName("tom1");
    	user.setLastName("Don");
    	user.setPhone("15166418145");
    	user.setOccupation("Software Developer");
    	user.setDescription("Blah blah ..... tetsing testing");
    	user.save();

    	User newuserinfo = User.find("byUsername", "tom").first();
    	
    	assertNotNull(newuserinfo);
        assertSame(newuserinfo, user);
        assertEquals("tom1@email.com", newuserinfo.getEmail());
        //assertEquals(new Crypto().encryptAES("pass"), user.getPassword());
        assertEquals("tom1", newuserinfo.getFirstName());
        assertEquals("Don", newuserinfo.getLastName());
        assertEquals("15166418145", newuserinfo.getPhone());
        assertEquals("Software Developer", newuserinfo.getOccupation());
        assertEquals("Blah blah ..... tetsing testing", newuserinfo.getDescription());
    }
	
	@Test
    public void updatePasswordTest(){
        User user = User.find("byUsername", "tom").first();
        
        user.setPassword("tom1");
    	user.save();

    	User newuser = User.find("byUsername", "tom").first();
    	
    	assertNotNull(newuser);
        assertSame(newuser, user);
        assertEquals(new Crypto().encryptAES("tom1"), newuser.getPassword());
    }
	
	@Test
    public void updateUserTest(){
        User oldUser = User.find("byUsername","tom").first();
        oldUser.setPassword("pass3");

        User user = User.find("byUsername","tom").first();
        assertEquals(new Crypto().encryptAES("pass3"), user.getPassword());
    }
}