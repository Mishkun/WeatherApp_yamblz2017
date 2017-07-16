package com.mishkun.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.evernote.android.job.JobManager;
import com.mishkun.weatherapp.di.WeatherApplication;
import com.mishkun.weatherapp.domain.providers.CurrentWeatherProvider;
import com.mishkun.weatherapp.jobs.WeatherJob;
import com.mishkun.weatherapp.jobs.WeatherJobCreator;
import com.mishkun.weatherapp.view.AboutFragment;
import com.mishkun.weatherapp.view.HomeFragment;
import com.mishkun.weatherapp.view.SettingsFragment;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String KEY_JOB_ID = "JOB_ID";
    public int jobId;

    @Inject
    public CurrentWeatherProvider currentWeatherProvider;
    private Toolbar toolbar;

    @Override
    protected void onResume() {
        super.onResume();


        JobManager.create(this.getApplicationContext());
        JobManager.instance().addJobCreator(new WeatherJobCreator(currentWeatherProvider));

        if (JobManager.instance().getAllJobsForTag(WeatherJob.TAG).size() == 0) {
            jobId = WeatherJob.scheduleJob(
                    Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.time_preference_key), "15")));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (savedInstanceState == null) {
            transaction.replace(R.id.content, new HomeFragment(), HomeFragment.TAG).commit();
        } else {
            savedInstanceState.getInt(KEY_JOB_ID);
        }
        ((WeatherApplication) getApplication()).getAppComponent().inject(this);
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
    protected void onStop() {
        super.onStop();
        toolbar.getBackground().setAlpha(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();
        if (id == R.id.nav_home) {
            fm.beginTransaction().replace(R.id.content, new HomeFragment(), HomeFragment.TAG).commit();
        } else if (id == R.id.nav_settings) {
            fm.beginTransaction().replace(R.id.content, new SettingsFragment(), SettingsFragment.TAG).commit();
        } else if (id == R.id.nav_about) {
            fm.beginTransaction().replace(R.id.content, new AboutFragment(), AboutFragment.TAG).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_JOB_ID, jobId);
    }
}
