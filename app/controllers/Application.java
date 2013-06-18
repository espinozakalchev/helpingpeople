package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
		List<CanPost> canPosts = CanPost.find("from CanPost order by createdDate desc").fetch();
		List<NeedPost> needPosts = NeedPost.find("from NeedPost order by createdDate desc").fetch();
		render(canPosts, needPosts);
    }

}