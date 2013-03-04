package drawingtable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lauri Suomalainen
 */
public class User {
    
    public String nick;
    private Layer layer;
    
    public User(String nick) {
        //get session tai jotain
        this.nick = nick;
        layer = new Layer();
    }
    

}
