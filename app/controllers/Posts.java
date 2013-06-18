package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import models.CanPost;
import models.NeedPost;
import models.Post;
import models.PostComment;
import models.Post.PostType;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.results.Result;

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

	public static void create(boolean isICanPost, File image, String title, String description) {
		Post post;
		if (isICanPost)
			post = new CanPost(title, description, null, new Date());
		else 
			post = new NeedPost(title, description, null, new Date());



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
		System.err.println("created post: " + id);
		
		viewPost(id);
	}
	
	public static void viewPost(Long postId) {
		Post post = Post.findById(postId);
		
		render(post);
	}
	
	public static void postComment(Long postId, String comment) {
		Post post = Post.findById(postId);

		System.err.println(postId);
		System.err.println(comment);
		
		new PostComment(null, post, comment);
		viewPost(postId);
	}
}