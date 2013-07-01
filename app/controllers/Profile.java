package controllers;

import models.*;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.With;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import static controllers.Security.isConnected;

@With(Secure.class)
public class Profile extends Controller {

    // profile page
	public static void index(){
        User user = User.find("byUsername", Security.connected()).first();
    	render(user);
    }
    
	// profile general info edit page display 
    public static void edit(){
        User user = User.find("byUsername", Security.connected()).first();
    	render(user);
    }

    // profile general info save 
    public static void save(Long userId, String firstName, String lastName, String email, String phone, String occupation, String description){
    	
    	String successMsg = "";
    	
    	User user = User.findById(userId);
    	
    	user.setEmail(email);
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	user.setPhone(phone);
    	user.setOccupation(occupation);
    	user.setDescription(description);
    	user.save(); // user info save
    	
    	successMsg += "Profile information(s) have been updated successfully";
		flash.success(successMsg);
		
    	// redirect to profile 
    	index();
    }
    
    // password change 
    public static void changePassword(){
        render();
    }
    
    // save - password 
    public static void savePassword(String curpass, String newpass, String confirmpass){
    	
    	User user = User.find("byUsername", Security.connected()).first();
    	
    	boolean valid = true;
        String errors="";
        String successMsg="";
        
    	if(!newpass.equals(confirmpass)){
            errors+="New password and Confirm password do not match<br/>";
            valid=false;
        }
    	else if(!user.getPassword().equals(new Crypto().encryptAES(curpass))){
    		errors+="Current password does not match !<br/>";
            valid=false;
    	}
    	    	
    	if(valid){
    		user.setPassword(newpass);
    		user.save(); // user info save
    		successMsg += "Password has been changed successfully";
    		flash.success(successMsg);
    		index(); // redirect to profile page
        } else {
            flash.error(errors);
            changePassword(); // redirect to change password page again on error
        }
    }
    
    // update photo  
    public static void changePhoto(){
        render();
    }
    
   // save photo  
    public static void savePhoto(File image){
        
    	User user = User.find("byUsername", Security.connected()).first();
        long id = user.getId();
        
    	String successMsg = "";

		if (image != null) {
			File dir = new File(String.format("public/user/%d", id));
			dir.mkdirs();
			File copy = new File(dir, image.getName());

			FileInputStream in = null;
			FileOutputStream out = null;
			try {
				in = new FileInputStream(image);
				out = new FileOutputStream(copy);
				IOUtils.copy(in, out);
				successMsg += "Photo has uploaded successfully";
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
        
		// save photo location
		user.setPhoto(image.getName());
		user.save();
		
		flash.success(successMsg);
		index(); // redirect to profile page
    }
    
    
    
}
