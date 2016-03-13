package com.chortle.chortle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

/**
 * Created by Mitchell on 13/03/2016.
 */
public class CreateGroupActivity extends AppCompatActivity{
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

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


    public void submitGroup(View view){
        String groupName,groupDescription;
        groupName = ((EditText) findViewById(R.id.group_name)).getText().toString();
        groupDescription = ((EditText) findViewById(R.id.group_description)).getText().toString();

        Group group = new Group(groupName,groupDescription);
        new PostRequestHandler(getApplicationContext(),url,group,"addGroupResult",Volley.newRequestQueue(this)).send();

    }
}
