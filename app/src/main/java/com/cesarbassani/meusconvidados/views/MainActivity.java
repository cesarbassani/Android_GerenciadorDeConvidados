package com.cesarbassani.meusconvidados.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;

import com.cesarbassani.meusconvidados.R;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        this.mViewHolder.mFloatAddGuest = findViewById(R.id.float_add_guest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        this.setListeners();

        this.startDefaultFragment();

        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });
        addContentView(crashButton,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void setListeners() {
        mViewHolder.mFloatAddGuest.setOnClickListener(this);
    }

    private void startDefaultFragment() {
        Fragment fragment = null;
        Class fragmentClass = AllInvitedFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass = null;


        int id = item.getItemId();

        if (id == R.id.nav_all_guests) {
            fragmentClass = AllInvitedFragment.class;

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "all_invited_id");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "all_invited");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "menu_click");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            bundle = new Bundle();
            bundle.putString("menu_content", "all_invited");
            mFirebaseAnalytics.logEvent("menu_content_custon", bundle);


        } else if (id == R.id.nav_present) {
            fragmentClass = PresentFragment.class;
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "present_id");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "present");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "menu_click");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            bundle = new Bundle();
            bundle.putString("menu_content", "present");
            mFirebaseAnalytics.logEvent("menu_content_custon", bundle);

        } else if (id == R.id.nav_absent) {
            fragmentClass = AbsentFragment.class;

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "absent_id");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "absent");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "menu_click");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            bundle = new Bundle();
            bundle.putString("menu_content", "absent");
            mFirebaseAnalytics.logEvent("menu_content_custon", bundle);
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.float_add_guest) {
            Intent guestForm = new Intent(this, GuestFormActivity.class);
            this.startActivity(guestForm) ;
        }
    }

    private static class ViewHolder {
        FloatingActionButton mFloatAddGuest;
    }
}
