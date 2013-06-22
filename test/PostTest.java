import models.CanPost;
import models.NeedPost;
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

        CanPost canpost = new CanPost("My I can post", "My description", null, new Date());

        assertEquals("My I can post", canpost.getTitle());
        assertEquals("My description", canpost.getDescription());
    }

    @Test
    public void createNeedPostTest(){
        NeedPost needpost = new NeedPost("My I Need post", "My description", null, new Date());

        assertEquals("My I Need post", needpost.getTitle());
        assertEquals("My description", needpost.getDescription());
    }
}
