package com.mersocarlin.notificationapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mersocarlin.notificationapp.R;
import com.mersocarlin.notificationapp.ui.fragment.HomeFragment;
import com.mersocarlin.notificationapp.ui.fragment.ItemFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener {

    private final String HOME_FRAGMENT_TAG = "homeFragment";
    private final String ITEM_FRAGMET_TAG = "itemFragment";

    private final String SIDE_MENU_IMPORT_FRAGMENT_TAG = "sideMenuImport";
    private final String SIDE_MENU_GALLERY_FRAGMENT_TAG = "sideMenuGallery";
    private final String SIDE_MENU_SLIDESHOW_FRAGMENT_TAG = "sideMenuSlideShow";
    private final String SIDE_MENU_TOOLS_FRAGMENT_TAG = "sideMenuTools";
    private final String SIDE_MENU_SHARE_FRAGMENT_TAG = "sideMenuShare";
    private final String SIDE_MENU_SEND_FRAGMENT_TAG = "sideMenuSend";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(this.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        this.actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        this.drawerLayout.setDrawerListener(this.actionBarDrawerToggle);

        this.actionBarDrawerToggle.syncState();

        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.addFragment(HomeFragment.newInstance("home", 50), HOME_FRAGMENT_TAG);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();

        if (fragmentCount > 1) {
            getSupportFragmentManager().popBackStack();
        }
        else {
            finish();
            //super.onBackPressed();
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
        int id = item.getItemId();

        int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();

        while (fragmentCount > 1) {
            getSupportFragmentManager().popBackStack();
            fragmentCount--;
        }

        Fragment fragment = null;
        String fragmentTag = null;

        switch (id) {
            case R.id.nav_camara:
                fragment = HomeFragment.newInstance(SIDE_MENU_IMPORT_FRAGMENT_TAG, 20);
                fragmentTag = SIDE_MENU_IMPORT_FRAGMENT_TAG;
                break;
            case R.id.nav_gallery:
                fragment = HomeFragment.newInstance(SIDE_MENU_GALLERY_FRAGMENT_TAG, 30);
                fragmentTag = SIDE_MENU_GALLERY_FRAGMENT_TAG;
                break;
            case R.id.nav_slideshow:
                fragment = HomeFragment.newInstance(SIDE_MENU_SLIDESHOW_FRAGMENT_TAG, 40);
                fragmentTag = SIDE_MENU_SLIDESHOW_FRAGMENT_TAG;
                break;
            case R.id.nav_manage:
                fragment = HomeFragment.newInstance(SIDE_MENU_TOOLS_FRAGMENT_TAG, 50);
                fragmentTag = SIDE_MENU_TOOLS_FRAGMENT_TAG;
                break;
            case R.id.nav_share:
                fragment = HomeFragment.newInstance(SIDE_MENU_SHARE_FRAGMENT_TAG, 60);
                fragmentTag = SIDE_MENU_SHARE_FRAGMENT_TAG;
                break;
            case R.id.nav_send:
                fragment = HomeFragment.newInstance(SIDE_MENU_SEND_FRAGMENT_TAG, 70);
                fragmentTag = SIDE_MENU_SEND_FRAGMENT_TAG;
                break;
        }

        this.addFragment(fragment, fragmentTag);

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String id) {
        this.addFragment(ItemFragment.newInstance("Item " + id, "other param2"), ITEM_FRAGMET_TAG);
    }

    private void addFragment(Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
    }
}
