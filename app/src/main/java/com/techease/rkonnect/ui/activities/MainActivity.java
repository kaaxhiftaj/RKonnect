package com.techease.rkonnect.ui.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.techease.rkonnect.FirebaseClasses.ForceUpdateChecker;
import com.techease.rkonnect.R;
import com.techease.rkonnect.ui.fragments.HistoyFragment;
import com.techease.rkonnect.ui.fragments.HomeFragment;
import com.techease.rkonnect.ui.fragments.ProfileFragment;
import com.techease.rkonnect.ui.fragments.ReportsFragment;
import com.techease.rkonnect.utils.Configuration;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String club_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        sharedPreferences = MainActivity.this.getSharedPreferences(Configuration.MY_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        fragment = new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_main, fragment).commit();
        setTitle("HOME");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment = new HomeFragment();
        } else if (id == R.id.nav_history) {
            fragment = new HistoyFragment();
        } else if (id == R.id.nav_reports) {
            fragment = new ReportsFragment();

        } else if (id == R.id.nav_profile) {
            fragment = new ProfileFragment();

        } else if (id == R.id.nav_logout) {
            editor.clear().commit();
            startActivity(new Intent(MainActivity.this, SplashActivity.class));

        }

        getFragmentManager().beginTransaction().replace(R.id.fragment_main, fragment).addToBackStack("tag").commit();
        item.setChecked(true);
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
