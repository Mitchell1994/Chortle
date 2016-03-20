package com.chortle.chortle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

    private String displayName;
    private int userCount;
    private int taskCount;

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
        return inflater.inflate(R.layout.fragment_group_element, container, false);
    }

}
