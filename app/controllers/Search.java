package controllers;

import models.Post;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * To change this template use File | Settings | File Templates.
 */
@With(Secure.class)
public class Search extends Controller {

    public static void index(String query) {

        if (query == null || query.trim().equals("")) {
            render(null, null);
        }

        List<Post> posts = Post.search(query);
        render(posts, query);
    }


}
