package functional;

import org.junit.*;
import play.libs.Crypto;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class FunctionalTest extends play.test.FunctionalTest {

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
    public void testThatAboutPageWorks() {
        Response response = GET("/about");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }


}