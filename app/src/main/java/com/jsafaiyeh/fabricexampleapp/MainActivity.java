package com.jsafaiyeh.fabricexampleapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "8WETgdWQSWKoyZneduxeQluWr";
    private static final String TWITTER_SECRET = "kYTUZ4C5ODiE85Cph32lOMuaKiIGaKv6Q7fOxCK92zvSErpJLm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        String[] fabricComponents = {
                "Embed Single Tweets",
                "Embed Multiple Tweets",
                "Log In With Twitter",
                "Digits",
                "MoPub Banner Ad",
                "MoPub FullScreen Ad",
                "MoPub Native Ad"
        };

        ListView mListView = (ListView) findViewById(R.id.list_view);
        ListAdapter listAdapter = new ListAdapter(this, fabricComponents);
        mListView.setAdapter(listAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent i = new Intent(getApplicationContext(), EmbedSingleTweetsActivity.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }

    private class ListAdapter extends ArrayAdapter<String>{

        public ListAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
    }
}
