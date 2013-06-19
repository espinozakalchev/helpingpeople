import models.User;
import org.junit.Test;
import play.libs.Crypto;
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
    public void createUserTest(){
        new User("tumuser", "tumuser@tum.de", "pass", "pass", "Tum", "Garching", (new Date()), "123456", "", "");
        User user = User.find("byUsername","tumuser").first();
        assertNotNull(user);
        assertEquals("tumuser@tum.de", user.getEmail());
    }

    @Test
    public void uniqueUserTest(){
        try{
            new User("tumuser", "tumuser@tum.de", "pass", "pass", "Tum", "Garching", (new Date()), "123456", "", "");
        } catch (Exception JdbcSQLException){
            // Do Nothing
        }
        int count = User.find("byUsername","tumuser").fetch().size();
        assertEquals(1,count);
    }

    @Test
    public void updateUserTest(){
        User oldUser = User.find("byUsername","tumuser").first();
        oldUser.setPassword("pass3");
        oldUser.save();

        User user = User.find("byUsername","tumuser").first();
        assertEquals(new Crypto().encryptAES("pass3"), user.getPassword());
    }

}