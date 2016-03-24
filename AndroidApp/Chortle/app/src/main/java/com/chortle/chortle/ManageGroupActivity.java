package com.chortle.chortle;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;

public class ManageGroupActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int userCount,taskGroupCount,taskCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //End the activity
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateCounts();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addUser(View v){
        Random rnd = new Random();

        addNewUserElement("User " + ++userCount, (new Random()).nextInt(taskCount + 1));
    }

    public void addTaskGroup(View v){

        addNewTaskGroupElement("TaskGroup " + ++taskGroupCount, (new Random()).nextInt(taskGroupCount + 1), (new Random()).nextInt(taskCount + 1));
    }

    public void addTask(View v){

        addNewTaskElement("Task " + ++taskCount, (new Random()).nextInt(userCount + 1));
    }

    public void editGroupName(View v){

    }

    public void addNewUserElement(String displayName, int numberOfTasks){
        //TODO Make this method take a a user object

        if(findViewById(R.id.fragment_container_members) != null){
            GroupElementFragment user = GroupElementFragment.newInstance(displayName, -1, numberOfTasks, GroupElementFragment.ICON_USER);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_members,user).commit();
        }

        updateCounts();
    }

    public void addNewTaskGroupElement(String displayName, int numberOfUsers, int numberOfTasks){
        //TODO Make this method take a a user object

        if(findViewById(R.id.fragment_container_taskgroups) != null){
            GroupElementFragment taskGroup = GroupElementFragment.newInstance(displayName, numberOfUsers, numberOfTasks, GroupElementFragment.ICON_GROUP);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_taskgroups,taskGroup).commit();
        }

        updateCounts();
    }

    public void addNewTaskElement(String displayName, int numberOfUsers){
        //TODO Make this method take a a user object

        if(findViewById(R.id.fragment_container_tasks) != null){
            GroupElementFragment task = GroupElementFragment.newInstance(displayName, numberOfUsers, -1,GroupElementFragment.ICON_TASK);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_tasks,task).commit();
        }

        updateCounts();
    }

    private void updateCounts(){
        ((TextView)findViewById(R.id.membersTitle)).setText(getString(R.string.members) + " ("+ userCount + ") ");
        ((TextView)findViewById(R.id.taskGroupsTitle)).setText(getString(R.string.task_groups) + " ("+ taskGroupCount + ") ");
        ((TextView)findViewById(R.id.tasksTitle)).setText(getString(R.string.tasks) + " ("+ taskCount + ") ");
    }
}
