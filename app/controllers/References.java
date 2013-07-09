package controllers;

import java.util.List;

import models.CanPost;
import models.NeedPost;
import models.Post;
import models.PostComment;
import models.Reference;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class References extends Controller {
	
	public static void index() {
		render();
	}

	public static void createNewReference() {
		render();
	}

	public static void allReferences() {
		List<Reference> references = Reference.find("from Reference order by createdDate desc").fetch();		
		render(references);
	}
	
	public static void submitReference(Long toUserId, String referenceText) {
		
		User toUser = User.findById(toUserId);
		User fromUser = User.find("byUsername",Security.connected()).first();
		
		System.out.println(referenceText);

        new Reference(fromUser, toUser, referenceText);
		Users.viewUserProfile(toUserId);
	}
}
