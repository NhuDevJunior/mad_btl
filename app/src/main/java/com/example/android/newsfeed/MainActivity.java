/*
 * MIT License
 *
 * Copyright (c) 2018 Soojeong Shin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.android.newsfeed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newsfeed.adapter.CategoryFragmentPagerAdapter;
import com.example.android.newsfeed.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getName();
    private ViewPager viewPager;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.viewpager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        // Set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set the default fragment when starting the app
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));

        CategoryFragmentPagerAdapter pagerAdapter =
                new CategoryFragmentPagerAdapter(this, getSupportFragmentManager());
        // Set category fragment pager adapter
        viewPager.setAdapter(pagerAdapter);

        // Setup login button
        if (isLoggedIn()) {
            // Set the view as the user has logged in
            getUserInfo(AccessToken.getCurrentAccessToken());
        } else {
            // Set the login button function
            callbackManager = CallbackManager.Factory.create();
            View headerView = navigationView.getHeaderView(0);
            LoginButton loginButton = headerView.findViewById(R.id.login_button);
            loginButton.setPermissions(Arrays.asList(Constants.FACEBOOK_PERMISSION_PUBLIC_PROFILE, Constants.FACEBOOK_PERMISISON_EMAIL));
            loginButton.registerCallback(callbackManager, new CustomFacebookLoginCallback());
        }
    }

    /**
     * Check if the user is logged in or not
     *
     * @return checkResult
     */
    private boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Switch Fragments in a ViewPager on clicking items in Navigation Drawer
        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(Constants.HOME);
        } else if (id == R.id.nav_world) {
            viewPager.setCurrentItem(Constants.WORLD);
        } else if (id == R.id.nav_science) {
            viewPager.setCurrentItem(Constants.SCIENCE);
        } else if (id == R.id.nav_sport) {
            viewPager.setCurrentItem(Constants.SPORT);
        } else if (id == R.id.nav_environment) {
            viewPager.setCurrentItem(Constants.ENVIRONMENT);
        } else if (id == R.id.nav_society) {
            viewPager.setCurrentItem(Constants.SOCIETY);
        } else if (id == R.id.nav_fashion) {
            viewPager.setCurrentItem(Constants.FASHION);
        } else if (id == R.id.nav_business) {
            viewPager.setCurrentItem(Constants.BUSINESS);
        } else if (id == R.id.nav_culture) {
            viewPager.setCurrentItem(Constants.CULTURE);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    // Initialize the contents of the Activity's options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // This method is called whenever an item in the options menu is selected.
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Get the user information and update it to the view
     *
     * @param accessToken accessToken
     */
    private void getUserInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, (object, response) -> {
            JSONObject data = response.getJSONObject();
            try {
                // Get the data from response
                String id = data.getString(Constants.JSON_KEY_FACEBOOK_ID);
                String name = data.getString(Constants.JSON_KEY_FACEBOOK_NAME);

                // Update the data to the view
                NavigationView navigationView = findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                ImageView userAvatar = headerView.findViewById(R.id.user_avatar);
                TextView userName = headerView.findViewById(R.id.user_name);
                userName.setText(name);

                // Update the avatar
                if (data.has(Constants.JSON_KEY_FACEBOOK_PICTURE)) {
                    String pictureUrl = data.getJSONObject(Constants.JSON_KEY_FACEBOOK_PICTURE)
                            .getJSONObject(Constants.JSON_KEY_FACEBOOK_DATA)
                            .getString(Constants.JSON_KEY_FACEBOOK_URL);
                    Glide.with(getApplicationContext())
                            .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                            .load(Uri.parse(pictureUrl)).into(userAvatar);
                }
                headerView.findViewById(R.id.user_info).setVisibility(View.VISIBLE);
                headerView.findViewById(R.id.login_button).setVisibility(View.GONE);
                Log.i(LOG_TAG, response.getRawResponse());
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.toString());
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString(Constants.FACEBOOK_FIELD_PARAM, TextUtils.join(",", Arrays.asList(Constants.JSON_KEY_FACEBOOK_ID, Constants.JSON_KEY_FACEBOOK_NAME, "picture")));
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void getJwtFromServer() {
    }

    /**
     * The custom Facebook login button behavior
     */
    private class CustomFacebookLoginCallback implements FacebookCallback<LoginResult> {

        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            getUserInfo(accessToken);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    }

}
