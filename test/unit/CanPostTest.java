package unit;

import models.CanPost;
import models.User;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * To change this template use File | Settings | File Templates.
 */
public class CanPostTest extends UnitTest {
    @Test
    public void createCanPostTest() {
        User user = User.find("byUsername","tom").first();

        CanPost canpost = new CanPost("My I can post", "My description", user, new Date());

        assertNotNull(canpost);
        assertEquals("My I can post", canpost.getTitle());
        assertEquals("My description", canpost.getDescription());
        assertEquals(user, canpost.getUser());
        assertNotNull(canpost.getCreatedDate());
        canpost.delete();
    }

    @Test
    public void saveCanPostTest() {
        User user = User.find("byUsername","tom").first();
        String title = "Save CanPost Test";
        String desc = "My Description";

        CanPost newCanPost = new CanPost(title, desc, user, new Date());

        CanPost canPost = CanPost.find("byTitle", title).first();

        assertNotNull(canPost);
        assertSame(newCanPost, canPost);

        assertEquals(title, canPost.getTitle());
        assertEquals(desc, canPost.getDescription());
        assertEquals(user, canPost.getUser());
        assertNotNull(canPost.getCreatedDate());
        canPost.delete();
    }
}
