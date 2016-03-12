package com.chortle.chortle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;

public class CreateUserActivity extends AppCompatActivity{

    private RequestQueue queue;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //Create request queue
        queue = Volley.newRequestQueue(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                url= null;
            } else {
                url= extras.getString("URL");
            }
        } else {
            url= (String) savedInstanceState.getSerializable("URL");
        }

        ((TextView) findViewById(R.id.url)).setText(url);
    }


    public void submitUser(View view){
        String username,fname,lname,email,password;
        username = ((EditText) findViewById(R.id.username)).getText().toString();
        fname = ((EditText) findViewById(R.id.firstname)).getText().toString();
        lname = ((EditText) findViewById(R.id.lastname)).getText().toString();
        email = ((EditText) findViewById(R.id.email)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();

        User user = new User(username,"fname","lname", email, password);

        Toast.makeText(getApplicationContext(),("Contacting " + url),Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        JSONObject json;
        try {
            json = new JSONObject(gson.toJson(user));
        }catch(JSONException e){
            json = null;
        }
        final JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST,
                url,json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView mTextView = (TextView) findViewById(R.id.output);
                        try {
                            Toast.makeText(getApplicationContext(),("Success - " + response.getString("addUserResult")),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),("Success - User added, no response body given"),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                TextView mTextView = (TextView) findViewById(R.id.output);
                String responseDesc = "";

                String errorMessage;

                try{
                    errorMessage = "Failure (" + error.networkResponse.statusCode + ")";
                }catch(NullPointerException e){
                    errorMessage = "Failure with no response code given";
                }


                try {
                    String responseBody = new String( error.networkResponse.data, "utf-8" );
                    JSONObject jsonObject = new JSONObject( responseBody );
                    responseDesc = jsonObject.getString("addUserResult");
                    errorMessage += " - " + responseDesc;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {

                }

                //Display error message as a toast
                Toast.makeText(getApplicationContext(),(errorMessage),Toast.LENGTH_SHORT).show();
            }
        });

        //Display data to be sent as a toast
        Toast.makeText(getApplicationContext(),("Sending " + new String(request.getBody())),Toast.LENGTH_SHORT).show();

        //Add new user request to the queue
        queue.add(request);
    }

}


