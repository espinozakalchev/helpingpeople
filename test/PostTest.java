import models.CanPost;
import models.Post;
import models.User;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 */
public class PostTest extends UnitTest {

    @Test
    public void createCanPostTest(){
        User user = User.find("byUsername", "administrator").first();
        CanPost canpost = new CanPost("My I can post", "My description", user, new Date());
        long id=canpost.getId();

        CanPost retrievedCanpost = CanPost.findById(id);
        assertEquals("My I can post", retrievedCanpost.getTitle());
        assertEquals("My description", retrievedCanpost.getDescription());
        assertEquals(user,retrievedCanpost.getUser());

    }
}
