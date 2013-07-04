package controllers;

import java.util.List;

import models.CanPost;
import models.NeedPost;
import models.Reference;
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
}
