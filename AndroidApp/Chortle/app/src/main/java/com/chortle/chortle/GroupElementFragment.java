package com.chortle.chortle;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupElementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupElementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupElementFragment extends Fragment {

    private static final String ARG_DISPLAY_NAME = "displayName";
    private static final String ARG_USER_COUNT = "userCount";
    private static final String ARG_TASK_COUNT = "taskCount";

    private String displayName=null;
    private int userCount=-1;
    private int taskCount=-1;

    public GroupElementFragment(){}

    public static GroupElementFragment newInstance(String displayName, int userCount, int taskCount) {
        GroupElementFragment fragment = new GroupElementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DISPLAY_NAME, displayName);
        args.putInt(ARG_USER_COUNT, userCount);
        args.putInt(ARG_TASK_COUNT, taskCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            displayName = getArguments().getString(ARG_DISPLAY_NAME);
            userCount = getArguments().getInt(ARG_USER_COUNT);
            taskCount = getArguments().getInt(ARG_TASK_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_element, container, false);

        //Setup values

        //If displayname has been defined, use it
        if(displayName != null){
            ((TextView) view.findViewById(R.id.displayName)).setText(displayName);
        }

        //If a user count has been defined
        if(userCount >= 0){
            ((TextView) view.findViewById(R.id.userCount)).setText(""+userCount);
        }else{
            ((LinearLayout)view.findViewById(R.id.userCountLayout)).setVisibility(View.GONE);
        }

        //if a task count has been defined
        if(taskCount >= 0){
            ((TextView) view.findViewById(R.id.taskCount)).setText(""+taskCount);
        }else{
            ((LinearLayout)view.findViewById(R.id.taskCountLayout)).setVisibility(View.GONE);
        }

        //Randomly assign a background color
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ((ImageView)view.findViewById(R.id.groupElementImage)).setBackgroundColor(color);

        return view;
    }

}
