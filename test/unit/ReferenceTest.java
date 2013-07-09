package unit;

import models.Reference;
import models.User;
import org.junit.Test;
import play.test.UnitTest;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * To change this template use File | Settings | File Templates.
 */
public class ReferenceTest extends UnitTest {

    @Test
    public void createReferenceTest() {
        User toUser = User.find("byUsername", "tom").first();
        User fromUser = User.find("byUsername", "mark").first();
        String text = "Very honest";
        Reference reference = new Reference(fromUser, toUser, text);

        assertNotNull(reference);
        assertEquals(toUser, reference.getToUser());
        assertEquals(fromUser, reference.getFromUser());
        assertEquals(text, reference.getReferenceText());
        assertNotNull(reference.getDate());
        reference.delete();
    }

    @Test
    public void saveReferenceTest() {
        User toUser = User.find("byUsername", "tom").first();
        User fromUser = User.find("byUsername", "mark").first();
        String text = "Very honest";

        Reference newReference =  new Reference(fromUser, toUser, text);

        Reference reference =  Reference.find("byReferenceText", text).first();

        assertNotNull(reference);
        assertEquals(newReference.getToUser(), reference.getToUser());
        assertEquals(newReference.getFromUser(), reference.getFromUser());
        assertEquals(newReference.getReferenceText(), reference.getReferenceText());
        reference.delete();
    }

}
