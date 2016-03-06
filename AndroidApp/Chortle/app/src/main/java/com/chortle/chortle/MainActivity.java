package com.chortle.chortle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Instantiate the RequestQueue.
    RequestQueue queue;
    String url;

    User user = new User("a","b","c","d","e");

    JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST,
        url, user.toJson(),
        new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                TextView mTextView = (TextView) findViewById(R.id.output);
                mTextView.setText( mTextView.getText() + "Got a response");
            }
        }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            TextView mTextView = (TextView) findViewById(R.id.output);
            mTextView.setText(mTextView.getText() + "That didn't work!" + error.toString());
        }
    }
    );

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addUser:
                    // Add the request to the RequestQueue.*/
                    queue.add(request);
                default:
                    break;
            }
        }
    };

    private void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_mitchell:
                if (checked)
                    url = "http://" + UrlValues.MitchellIP + ":" + UrlValues.Port + "/chortleservice/users";
                    break;
            case R.id.radio_conor:
                if (checked)
                    url = "http://" + UrlValues.ConorIP + ":" + UrlValues.Port + "/chortleservice/users";
                    break;
            case R.id.radio_gianni:
                if (checked)
                    url = "http://" + UrlValues.GianniIP + ":" + UrlValues.Port + "/chortleservice/users";
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button addUser = (Button)findViewById(R.id.addUser);
        addUser.setOnClickListener(listener);
        url = "http://" + "192.168.20.5" + ":" + UrlValues.Port + "/chortleservice/users";
        RadioButton mitchellIP = (RadioButton)findViewById(R.id.radio_mitchell);
        mitchellIP.setChecked(true);
        RadioButton conorIP = (RadioButton)findViewById(R.id.radio_conor);
        mitchellIP.setChecked(true);
        RadioButton gianniIP = (RadioButton)findViewById(R.id.radio_gianni);
        mitchellIP.setChecked(true);
    }
}
