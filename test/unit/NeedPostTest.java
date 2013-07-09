package unit;

import models.NeedPost;
import models.User;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * To change this template use File | Settings | File Templates.
 */
public class NeedPostTest extends UnitTest {

    @Test
    public void createNeedPostTest() {
        User user = User.find("byUsername","tom").first();
        NeedPost needpost = new NeedPost("My I Need post", "My description", user, new Date());

        assertNotNull(needpost);
        assertEquals("My I Need post", needpost.getTitle());
        assertEquals("My description", needpost.getDescription());
        assertEquals(user, needpost.getUser());
        assertNotNull(needpost.getCreatedDate());
        needpost.delete();
    }

    @Test
    public void saveNeedPostTest() {
        User user = User.find("byUsername","tom").first();
        String title = "Save NeedPost Test";
        String desc = "My Description";

        NeedPost newNeedPost = new NeedPost(title, desc, user, new Date());

        NeedPost needPost = NeedPost.find("byTitle", title).first();

        assertNotNull(needPost);
        //assertSame(newNeedPost, needPost);
        assertEquals(title, needPost.getTitle());
        assertEquals(desc, needPost.getDescription());
        assertEquals(user, needPost.getUser());
        assertNotNull(needPost.getCreatedDate());
        needPost.delete();
    }

}
