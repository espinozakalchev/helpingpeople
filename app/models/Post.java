package models;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import play.db.jpa.Model;
import play.templates.JavaExtensions;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="post_type")
public abstract class Post extends Model {

    @NotEmpty
	private String title;

	@NotEmpty
	@Lob
    private String description;
	
	@ManyToOne
    private User user;
	
	@NotNull
    private Date createdDate;

	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	@OrderBy(value="postedOn DESC")
    private List<PostComment> comments;
    
    public Post(String title, String description, User user, Date createdDate) {
		super();
		this.title = title;
		this.description = description;
		this.user = user;
		this.createdDate = createdDate;
	}
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public List<PostComment> getComments() {
		return comments;
	}
	public void setComments(List<PostComment> comments) {
		this.comments = comments;
	}

	@Transient
	public PostType getPostType() {
		if (this instanceof CanPost)
			return PostType.CanPost;
		else if (this instanceof NeedPost)
			return PostType.NeedPost;
			
		throw new RuntimeException("Unrecognized post type");
	}

    @Transient
    public String getPostTypeString() {
        if (this instanceof CanPost)
            return String.valueOf(PostType.CanPost);
        else if (this instanceof NeedPost)
            return String.valueOf(PostType.NeedPost);

        throw new RuntimeException("Unrecognized post type");
    }
	
	@Transient
	public String getImagePath() {
		String path = String.format(
				"public/posts/%s/%d", this.getPostType() == PostType.CanPost ? "can" : "need", this.getId());
		
		File dir = new File(path);
		if (dir != null && dir.isDirectory()) {
			 File[] images = dir.listFiles();
			 if (images.length > 0) {
				 return "/".concat(path).concat("/").concat(images[0].getName());
			 }
		}
		
		
		return "/public/images/logo.png";
	}
	
	@Transient
	public boolean hasImage() {
		String path = String.format(
				"public/posts/%s/%d", this.getPostType() == PostType.CanPost ? "can" : "need", this.getId());
		
		File dir = new File(path);
		if (dir != null && dir.isDirectory()) {
			 File[] images = dir.listFiles();
			 return images.length > 0;
		}
		
		return false;
	}
	
	@Transient
	public boolean isCanPost() {
		return this instanceof CanPost;
	}
	
	@Transient
	public boolean isNeedPost() {
		return this instanceof NeedPost;
	}
	
	public enum PostType {
		CanPost,
		NeedPost,
	}

    public static List<Post> search (String searchString) {
        List<Post> posts = find("from Post post where LCASE(post.title) like ?1 or LCASE(post.description) like ?1 order by post.createdDate desc", JavaExtensions.noAccents("%"+searchString.toLowerCase()+"%")).fetch();
        return posts;
    }
}
