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
    public void testThatShowAllPostsPageRedirectsToLoginPage() {
        Response response = GET("/posts/allposts");
        assertStatus(302, response);
    }

    @Test
    public void testThatUserProfilePageRedirectsToLoginPage() {
        Response response = GET("/users/viewuserprofile?userId=1");
        assertStatus(302, response);
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

    @Test
    public void testThatContactPageWorks() {
        Response response = GET("/contact");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatTermsAndConditionsPageWorks() {
        Response response = GET("/terms");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatPrivacyPageWorks() {
        Response response = GET("/privacy");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }


}