package com.chortle.chortle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Instantiate the RequestQueue.
    RequestQueue queue;
    String url, ip;
    int count = 1;
    User user = new User("a","b","c","d","e");

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView mTextView = (TextView) findViewById(R.id.output);
            ip = getString(R.string.mitchell_ip);
            switch (v.getId()) {
                case R.id.addUser:
                    print("Contacting " + url);
                    Gson gson = new Gson();
                    JSONObject json;
                    try {
                        json = new JSONObject(gson.toJson(user));
                    }catch(JSONException e){
                        json = null;
                    }
                    JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST,
                            url,json,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                TextView mTextView = (TextView) findViewById(R.id.output);
                                print("Success");
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                TextView mTextView = (TextView) findViewById(R.id.output);
                                print("Failure (" + error.toString() + ")");
                                count++;
                            }
                        }
                    );
                    print("Sending " + new String(request.getBody()));
                    queue.add(request);
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
                default:
                    break;
            }
            if(v.getId() == R.id.radio_mitchell
                    || v.getId() == R.id.radio_conor
                    || v.getId() == R.id.radio_gianni){
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
        Button addUser = (Button)findViewById(R.id.addUser);
        addUser.setOnClickListener(buttonListener);

        //Create radio buttons
        RadioButton mitchellIP = (RadioButton)findViewById(R.id.radio_mitchell);
        RadioButton conorIP = (RadioButton)findViewById(R.id.radio_conor);
        RadioButton gianniIP = (RadioButton)findViewById(R.id.radio_gianni);

        //Set radio button listeners
        mitchellIP.setOnClickListener(buttonListener);
        conorIP.setOnClickListener(buttonListener);
        gianniIP.setOnClickListener(buttonListener);

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
}
