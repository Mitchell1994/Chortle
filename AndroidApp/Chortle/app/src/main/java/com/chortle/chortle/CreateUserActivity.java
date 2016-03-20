package com.chortle.chortle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

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

        UserDTO user = new UserDTO(username,"fname","lname", email, password);

        new PostRequestHandler(getApplicationContext(),url,user,"addUserResult",Volley.newRequestQueue(this)).send();
    }

}


