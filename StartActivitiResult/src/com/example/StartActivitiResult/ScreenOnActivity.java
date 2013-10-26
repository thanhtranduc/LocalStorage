package com.example.StartActivitiResult;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/25/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScreenOnActivity extends Activity {
    private EditText inputName;
    private EditText inputMail;
    private AccountManager accountManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_one);
        accountManager.get(this);
        inputName = (EditText)findViewById(R.id.screenOne_etName);
        inputMail = (EditText)findViewById(R.id.screenOne_etEmail);
        Button btnNextScreen = (Button)findViewById(R.id.screen_one_btnext);
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent nextScreen = new Intent(getApplicationContext(), ScreenTwoActivity.class);
//                nextScreen.putExtra("name", inputName.getText().toString());
//                nextScreen.putExtra("email", inputMail.getText().toString());
//                startActivity(nextScreen);
                addNewAccount(AccountGeneral.ACCOUNT_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
            }
        });
    }

    private void addNewAccount(String accountType, String authtokenType) {
        final AccountManagerFuture<Bundle> future = accountManager.addAccount(accountType, authtokenType,null,null,
                this, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try{
                    Bundle bundle = future.getResult();
                    Log.d("ScreenOnActivity", "addNewAccount bundle is "+ bundle);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },null);
    }
}
