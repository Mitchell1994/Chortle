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

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

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

        new PostRequestHandler(getApplicationContext(),url,user,"addUserResult",Volley.newRequestQueue(this)).send();
    }

}


