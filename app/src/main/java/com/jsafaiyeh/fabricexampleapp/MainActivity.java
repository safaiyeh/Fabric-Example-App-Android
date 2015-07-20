package com.jsafaiyeh.fabricexampleapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        String[] activities = {
                "Embed Tweets",
                "Log In With Twitter",
                "Digits",
                "MoPub Banner Ad",
                "MoPub FullScreen Ad",
                "MoPub Native Ad"
        };

        mListView = (ListView) findViewById(R.id.list_view);
        ListAdapter listAdapter = new ListAdapter(this, activities);
        mListView.setAdapter(listAdapter);

    }

    private class ListAdapter extends ArrayAdapter<String>{

        public ListAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
    }
}
