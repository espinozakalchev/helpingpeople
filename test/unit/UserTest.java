package unit;

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

    @Test
    public void userSaved(){
        User newUser = new User("mytumuser", "mytumuser@tum.de", "pass", "pass", "Tum", "Garching",  "123456", "", "");
        User user = User.find("byUsername","mytumuser").first();

        assertNotNull(user);
        assertSame(newUser, user);
        assertEquals("mytumuser", user.getUsername());
        assertEquals("mytumuser@tum.de", user.getEmail());
        assertEquals(new Crypto().encryptAES("pass"), user.getPassword());
        assertEquals("Tum",user.getFirstName());
        assertEquals("Garching",user.getLastName());
        user.delete();
    }

    @Test
    public void uniqueUserTest(){
        try{
            new User("tom", "tom@tum.de", "pass", "pass", "Tum", "Garching", "123456", "", "");
        } catch (Exception JdbcSQLException){
            // Do Nothing
        }
        int count = User.find("byUsername","tom").fetch().size();
        assertEquals(1,count);
    }

    @Test
    public void updateUserTest(){
        User oldUser = User.find("byUsername","tom").first();
        oldUser.setPassword("pass3");

        User user = User.find("byUsername","tom").first();
        assertEquals(new Crypto().encryptAES("pass3"), user.getPassword());
    }
}