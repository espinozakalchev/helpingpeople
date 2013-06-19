package controllers;

import models.User;
import play.libs.Crypto;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * Date: 6/19/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Security extends Secure.Security {
    static boolean authenticate(String username, String password){
        User user = User.find("byUsername", username).first();
        return user !=null && user.getPassword().equals(new Crypto().encryptAES(password));
    }

}
