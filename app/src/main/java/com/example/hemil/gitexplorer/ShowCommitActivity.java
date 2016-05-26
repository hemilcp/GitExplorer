package com.example.hemil.gitexplorer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import RestAPI.RestApiClass;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by hemil on 5/26/2016.
 */
public class ShowCommitActivity extends MainActivity {

    ListView commit_listview;
    JsonArray response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.mainActivity_layout).setVisibility(View.GONE);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.commit_activity, null, false);
        drawer.addView(contentView, 0);

        TextView textView = (TextView) findViewById(R.id.repo_name);
        textView.setText(getIntent().getStringExtra("name")+" commits");

      //  Log.d("NAME",getIntent().getStringExtra("name"));
        String str = getIntent().getStringExtra("name");
        Log.d("Name",str.substring(1,str.length()-1));

        commit_listview = (ListView) findViewById(R.id.commit_listView);
        new CommitAsync().execute();
    }

    public void showCommits(JsonArray list){
        commit_listview.setAdapter(new PopulateCommitAdapter(this,list));
    }

    private class CommitAsync extends AsyncTask<Void,Void,Void>{

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
            String str = getIntent().getStringExtra("name");

            response = restApiClass.getCommitList(str.substring(1,str.length()-1));

            Log.d("Commit",response.toString());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//stuff that updates ui
                    if (response.size() > 0)
                        showCommits(response);

                }
            });

            return null;
        }
    }
}
