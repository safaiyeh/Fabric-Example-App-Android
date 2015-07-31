package com.jsafaiyeh.fabricexampleapp.activites;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;
import com.jsafaiyeh.fabricexampleapp.R;
import com.mopub.common.MoPub;
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
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Components");

        String[] fabricComponents = {
                "Embed Tweets",
                "Timelines",
                "Log In With Twitter",
                "Digits",
                "Banner Ad",
                "FullScreen Ad",
                "Native Ad"
        };

        ListView mListView = (ListView) findViewById(R.id.list_view);
        ListAdapter listAdapter = new ListAdapter(this, fabricComponents);
        mListView.setAdapter(listAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position) {
                    case 0:
                        i = new Intent(getApplicationContext(), EmbedTweetsActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(getApplicationContext(), TimelineActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(getApplicationContext(), DigitsActivity.class);
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent(getApplicationContext(), BannerAdActivity.class);
                        startActivity(i);
                        break;
                    case 5:
                        i = new Intent(getApplicationContext(), FullScreenAdActivity.class);
                        startActivity(i);
                        break;
                    case 6:
                        i = new Intent(getApplicationContext(), NativeAdActivity.class);
                        startActivity(i);
                }
            }
        });
    }

    private class ListAdapter extends ArrayAdapter<String> {

        public ListAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
    }
}
