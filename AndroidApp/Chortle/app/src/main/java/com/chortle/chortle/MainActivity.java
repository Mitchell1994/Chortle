package com.chortle.chortle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    final String url = "http://192.168.1.66:8080/chortleservice/users";

    final User user = new User("a","b","c","d","e");

    JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST,
        url, user.toJson(),
        new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                TextView mTextView = (TextView) findViewById(R.id.textView2);
                mTextView.setText( mTextView.getText() + "Got a response");
            }
        }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            TextView mTextView = (TextView) findViewById(R.id.textView2);
            mTextView.setText(mTextView.getText() + "That didn't work!" + error.toString());
        }
    }
    );

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    // Add the request to the RequestQueue.*/
                    queue.add(request);
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(listener);
    }
}
