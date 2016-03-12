package com.chortle.chortle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Instantiate the RequestQueue.
    RequestQueue queue;
    String url, ip;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Define default url
        url = "http://" + getString(R.string.mitchell_ip) + ":" + getString(R.string.port) + "/chortleservice/users";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up add user button
        Button add_user = (Button)findViewById(R.id.add_user);
        add_user.setOnClickListener(buttonListener);

        //Create radio buttons
        RadioButton mitchellIP = (RadioButton)findViewById(R.id.radio_mitchell);
        RadioButton conorIP = (RadioButton)findViewById(R.id.radio_conor);
        RadioButton gianniIP = (RadioButton)findViewById(R.id.radio_gianni);
        RadioButton radioIP = (RadioButton)findViewById(R.id.radio_online_host);

        //Set radio button listeners
        mitchellIP.setOnClickListener(buttonListener);
        conorIP.setOnClickListener(buttonListener);
        gianniIP.setOnClickListener(buttonListener);
        radioIP.setOnClickListener(buttonListener);

        //Set radio button defualts
        mitchellIP.setChecked(true);
        conorIP.setChecked(false);
        gianniIP.setChecked(false);

        //set up output
        TextView mTextView = (TextView) findViewById(R.id.output);
        mTextView.setMovementMethod(new ScrollingMovementMethod());

        //Set up default IP
        ip = getString(R.string.mitchell_ip);
        print("IP set to " + ip);
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView mTextView = (TextView) findViewById(R.id.output);
            ip = getString(R.string.mitchell_ip);
            switch (v.getId()) {
                case R.id.add_user:
                    //addUser();

                    //Change activity to new user
                    startNewUserActivity();

                    break;
                case R.id.radio_mitchell:
                    ip = getString(R.string.mitchell_ip);
                    break;
                case R.id.radio_conor:
                    ip = getString(R.string.conor_ip);
                    break;
                case R.id.radio_gianni:
                    ip = getString(R.string.gianni_ip);
                    break;
                case R.id.radio_online_host:
                    ip = getString(R.string.online_host);
                    break;
                default:
                    break;
            }
            if(v.getId() == R.id.radio_mitchell
                    || v.getId() == R.id.radio_conor
                    || v.getId() == R.id.radio_gianni
                    || v.getId() == R.id.radio_online_host){
                url = "http://" + ip + ":" + getString(R.string.port) + "/chortleservice/users";
                print("IP set to " + ip);
            }
        }
    };


    private void print(String msg) {
        TextView mTextView = (TextView) findViewById(R.id.output);
        mTextView.append(count + ". " + msg + "\n");
        count++;
    }

    private void startNewUserActivity(){
        Intent intentNewUser = new Intent(this, CreateUserActivity.class);

        //Add Server URL to the new user activity
        intentNewUser.putExtra("URL", url);

        startActivity(intentNewUser);


    }

}
