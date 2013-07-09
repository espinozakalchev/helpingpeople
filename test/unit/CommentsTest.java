package unit;

import models.CanPost;
import models.PostComment;
import models.User;
import org.junit.Test;
import play.test.UnitTest;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * To change this template use File | Settings | File Templates.
 */
public class CommentsTest extends UnitTest {

    @Test
    public void createCommentsTest() {
        User user = User.find("byUsername","tom").first();
        CanPost post = CanPost.find("byTitle", "Munich city tour").first();

        String testComment = "My Test Comment";
        PostComment comment = new PostComment(user, post, testComment);

        assertNotNull(comment);
        assertEquals(user, comment.getUser());
        assertEquals(post, comment.getPost());
        assertEquals(testComment, comment.getComment());
    }

    @Test
    public void saveCommentsTest() {
        User user = User.find("byUsername","tom").first();
        CanPost post = CanPost.find("byTitle", "Munich city tour").first();
        String testComment = "My Saved Comment";
        new PostComment(user, post, testComment);

        PostComment comment = PostComment.find("byComment", testComment).first();

        assertNotNull(comment);
        assertEquals(user, comment.getUser());
        assertEquals(post, comment.getPost());
        assertEquals(testComment, comment.getComment());

    }
}
