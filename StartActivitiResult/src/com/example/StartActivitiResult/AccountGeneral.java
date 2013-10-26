package com.example.StartActivitiResult;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/26/13
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccountGeneral {
    //account type id
    public static final String ACCOUNT_TYPE = "com.qsoft.authen";

    //account name
    public static final String ACCOUNT_NAME = "qsoftAuth";

    //authen token types
    public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
    public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL = "Read only access to an qsoft account";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an qsoft account";

    public static final ServerAuthenticate serverAuthenticate = new ParseComServerAuthenticate();

}
