import models.User;
import org.junit.Test;
import play.libs.Crypto;
import play.mvc.Before;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * Date: 6/8/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest extends UnitTest {

    @Test
    public void userTest(){
        User user = new User("tumuser", "tumuser@tum.de", "pass", "pass", "Tum", "Garching",  "123456", "", "");
        assertNotNull(user);
        assertEquals("tumuser", user.getUsername());
        assertEquals("tumuser@tum.de", user.getEmail());
        assertEquals(new Crypto().encryptAES("pass"), user.getPassword());
        assertEquals("Tum",user.getFirstName());
        assertEquals("Garching",user.getLastName());
    }
}