package com.example.StartActivitiResult;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/26/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParseComServerAuthenticate implements ServerAuthenticate {
    @Override
    public String userSignUp(String name, String email, String pass, String authType) throws Exception {
        String url = "https://api.parse.com/1/users";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("X-Parse-Application-Id", "XUafJTkPikD5XN5HxciweVuSe12gDgk2tzMltOhr");
        httpPost.addHeader("X-Parse-REST-API-Key", "8L9yTQ3M86O4iiucwWb4JS7HkxoSKo7ssJqGChWx");
        httpPost.addHeader("Content-Type", "application/json");
        String user = "{\"username\":\"" + email + "\",\"password\":\"" + pass + "\",\"phone\":\"415-392-0202\"}";

        HttpEntity entity = new StringEntity(user);
        httpPost.setEntity(entity);
        String authToken = null;

        try {
            HttpResponse response = httpClient.execute(httpPost);
            String responseString = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() != 201) {
                ParseComError error = new Gson().fromJson(responseString, ParseComError.class);
                throw new Exception("Error creating user[" + error.code + "] - " + error.error);
            }
            User createdUser = new Gson().fromJson(responseString, User.class);
            authToken = createdUser.sessionToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authToken;
    }

    @Override
    public String userSignIn(String user, String pass, String authType) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://api.parse.com/1/login";
        String query = null;
        try {
            query = String.format("%s=%s&%s=%s", "username", URLEncoder.encode(user, "UTF-8"), "password", pass);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url += "?" + query;
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("X-Parse-Application-Id", "XUafJTkPikD5XN5HxciweVuSe12gDgk2tzMltOhr");
        httpGet.addHeader("X-Parse-REST-API-Key", "8L9yTQ3M86O4iiucwWb4JS7HkxoSKo7ssJqGChWx");

        HttpParams params = new BasicHttpParams();
        params.setParameter("username", user);
        params.setParameter("password", pass);
        httpGet.setParams(params);

        String authtoken = null;
        try
        {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String responseString = EntityUtils.toString(httpResponse.getEntity());
            if (httpResponse.getStatusLine().getStatusCode() != 200){
                ParseComError error = new Gson().fromJson(responseString,ParseComError.class);
                throw new Exception("Error signing-in ["+error.code+"] - " + error.error);
            }
            User loggedUser = new Gson().fromJson(responseString, User.class);
            authtoken = loggedUser.sessionToken;
        }catch (IOException e){
            e.printStackTrace();
        }
        return  authtoken;
    }
}
