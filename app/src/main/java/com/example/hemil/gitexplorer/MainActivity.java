package com.example.hemil.gitexplorer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;


import com.google.gson.*;
import java.util.List;

import RestAPI.RestApiClass;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  //  JSONObject[] response;
   JsonArray response;

 //  List<JSONObject> response;
    ListView repoListView;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        repoListView = (ListView) findViewById(R.id.repoListView);
        new FetchRepoListAsync().execute();
    }

    public void showListView(JsonArray list){
        repoListView.setAdapter(new PopulateRepoList(this,list));

        repoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ShowCommitActivity.class);
                intent.putExtra("name",response.get(position).getAsJsonObject().get("name").toString());
                startActivity(intent);
            }
        });
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

private class FetchRepoListAsync extends AsyncTask<Void,Void,Void>{

    RestAdapter restAdapter;

    @Override
    protected void onPreExecute(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.github.com";
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient))
                .build();
    }

    @Override
    protected Void doInBackground(Void... params) {
        final RestApiClass restApiClass = restAdapter.create(RestApiClass.class);
        response = restApiClass.getRepoList();

     //   Log.d("Response", String.valueOf(restApiClass.getRepoList().get(0)));
//        try {
          Log.d("Repo", response.get(0).toString());

        Log.d("Image",response.get(0).getAsJsonObject().get("owner").getAsJsonObject().get("avatar_url").toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//stuff that updates ui
                if (response.size() > 0)
                    showListView(response);

            }
        });

        return null;
    }
}

}
