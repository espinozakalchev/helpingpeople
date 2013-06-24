package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.CanPost;
import models.NeedPost;
import models.Post;
import models.Post.PostType;
import models.PostComment;
import models.User;

import org.apache.commons.io.IOUtils;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Posts extends Controller {

	public static void index() {
		render();
	}

	public static void createNewPost() {
		render();
	}

	public static void allPosts() {
		List<CanPost> canPosts = CanPost.find("from CanPost order by createdDate desc").fetch();
		List<NeedPost> needPosts = NeedPost.find("from NeedPost order by createdDate desc").fetch();
		render(canPosts, needPosts);
	}
	
	public static void myPosts() {
		User user = User.find("byUsername", Security.connected()).first();
		List<Post> posts = user.getPosts();
		List<CanPost> canPosts = new ArrayList<CanPost>();
		List<NeedPost> needPosts = new ArrayList<NeedPost>();
		
		for (Post p: posts)  {
			if (p.getPostType() == PostType.CanPost)
				canPosts.add((CanPost) p);
			else {
				needPosts.add((NeedPost) p);
			}
		}
		
		render(canPosts, needPosts);
	}

	public static void create(boolean isICanPost, File image, String title, String description) {
		Post post;
        User user = User.find("byUsername", Security.connected()).first();
		if (isICanPost)
			post = new CanPost(title, description, user, new Date());
		else 
			post = new NeedPost(title, description, user, new Date());



		long id = post.getId();

		if (image != null) {
			File dir = new File(String.format("public/posts/%s/%d", isICanPost ? "can" : "need", id));
			dir.mkdirs();
			File copy = new File(dir, image.getName());

			FileInputStream in = null;
			FileOutputStream out = null;
			try {
				in = new FileInputStream(image);
				out = new FileOutputStream(copy);
				IOUtils.copy(in, out);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (in != null)
						in.close();
					if (out != null)
						out.close();
				} 
				catch (IOException e) {}
			}
		}
		
		viewPost(id);
	}
	
	public static void viewPost(Long postId) {
		Post post = Post.findById(postId);
		
		render(post);
	}
	
	public static void postComment(Long postId, String comment) {
		Post post = Post.findById(postId);

		User user = User.find("byUsername",Security.connected()).first();

        new PostComment(user, post, comment);
		viewPost(postId);
	}
}