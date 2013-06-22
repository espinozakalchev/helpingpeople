import org.junit.*;
import play.libs.Crypto;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatLoginPageWorks() {
        Response response = GET("/secure/login");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatSignupPageWorks() {
        Response response = GET("/users/signup");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void userSaved(){
        new User("mytumuser", "mytumuser@tum.de", "pass", "pass", "Tum", "Garching",  "123456", "", "");
        User user = User.find("byUsername","mytumuser").first();
        assertNotNull(user);
        assertEquals("mytumuser", user.getUsername());
        assertEquals("mytumuser@tum.de", user.getEmail());
        assertEquals(new Crypto().encryptAES("pass"), user.getPassword());
        assertEquals("Tum",user.getFirstName());
        assertEquals("Garching",user.getLastName());
    }

    @Test
    public void uniqueUserTest(){
        try{
            new User("mytumuser", "mytumuser@tum.de", "pass", "pass", "Tum", "Garching", "123456", "", "");
        } catch (Exception JdbcSQLException){
            // Do Nothing
        }
        int count = User.find("byUsername","mytumuser").fetch().size();
        assertEquals(1,count);
    }

    @Test
    public void updateUserTest(){
        User oldUser = User.find("byUsername","mytumuser").first();
        oldUser.setPassword("pass3");

        User user = User.find("byUsername","mytumuser").first();
        assertEquals(new Crypto().encryptAES("pass3"), user.getPassword());
    }
    
}