package com.example.StartActivitiResult;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/26/13
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ServerAuthenticate {
    public String userSignUp(final String name, final String email, final String pass, String authType)throws Exception;
    public String userSignIn(final String user, final String pass, String authType) throws Exception;
}
