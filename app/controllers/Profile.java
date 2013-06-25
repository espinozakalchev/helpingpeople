package controllers;

import models.*;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Date;

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
    
}
