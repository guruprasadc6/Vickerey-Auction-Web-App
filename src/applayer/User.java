package applayer;
import dataLayer.*;

public class User {
    public  boolean isValidUserCredentials(String username,String password)
    {
            userDB userDBObject = new userDB();

           return userDBObject.isValidUserLogin(username,password);
    }
    public void signupSuccess(String username,String password,String email)
    {
        userDB userOb = new userDB();

        userOb.signup(username,password,email);
    }

    public  int retId(String username)
    {
        userDB userOb = new userDB();
        System.out.println("inside User");
        return  userOb.retId(username);
    }
}
