package models;

import play.db.jpa.Model;

/**
 * Created with IntelliJ IDEA.
 * User: sushil
 * Date: 6/8/13
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */

/*
 * Message in reply to "I Can"
 */
public class ReplyMessage extends Model {
    private CanPost post;
    private User sender;
    private User replier;
    private String message;
}
