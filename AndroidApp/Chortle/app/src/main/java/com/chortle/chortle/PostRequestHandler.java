package com.chortle.chortle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Mitchell on 13/03/2016.
 */
public class PostRequestHandler{

    private Context context;
    private String url;
    private Object requestBodyObject;
    private String responseBodyString;
    private RequestQueue queue;

    public PostRequestHandler(Context context, String url, Object requestBodyObject, String responseBodyString, RequestQueue queue){
        this.context = context;
        this.url = url;
        this.requestBodyObject = requestBodyObject;
        this.responseBodyString = responseBodyString;
        this.queue = queue;
    }

    public void send() {
        Toast.makeText(context, ("Contacting " + url), Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        JSONObject json;
        try {
            json = new JSONObject(gson.toJson(requestBodyObject));
        } catch (JSONException e) {
            json = null;
        }
        final JsonObjectRequest request = new JsonObjectRequest(JsonObjectRequest.Method.POST,
                url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(context, ("Success - " + response.getString(responseBodyString)), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(context, ("Success - No response body given"), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String responseDesc = "";

                String errorMessage;

                try {
                    errorMessage = "Failure (" + error.networkResponse.statusCode + ")";
                } catch (NullPointerException e) {
                    errorMessage = "Failure with no response code given";
                }


                try {
                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    JSONObject jsonObject = new JSONObject(responseBody);
                    responseDesc = jsonObject.getString(responseBodyString);
                    errorMessage += " - " + responseDesc;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {

                }

                //Display error message as a toast
                Toast.makeText(context, (errorMessage), Toast.LENGTH_SHORT).show();
            }
        });

        //Display data to be sent as a toast
        Toast.makeText(context, ("Sending " + new String(request.getBody())), Toast.LENGTH_SHORT).show();

        //Add new user request to the queue
        queue.add(request);
    }
}
