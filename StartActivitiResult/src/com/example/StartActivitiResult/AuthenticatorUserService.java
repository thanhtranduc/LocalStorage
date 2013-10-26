package com.example.StartActivitiResult;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/26/13
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticatorUserService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        AuthenticatorUser authenticator = new AuthenticatorUser(this);
        return authenticator.getIBinder();
    }
}
