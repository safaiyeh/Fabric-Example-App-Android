package com.jsafaiyeh.fabricexampleapp.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jsafaiyeh.fabricexampleapp.R;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mopub.nativeads.MoPubAdAdapter;
import com.mopub.nativeads.MoPubNativeAdRenderer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;
import java.util.ArrayList;

public class NativeAdActivity extends AppCompatActivity {

    //Replace this test id with your personal ad unit id
    private static final String MOPUB_NATIVE_AD_UNIT_ID = "317835218ff34057aa5999191befeecd";
    private MoPubAdAdapter adAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("MoPub Native Ad");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView sampleListView = (ListView) findViewById(R.id.mopub_sample_list_view);
        ArrayList<String> sampleItems = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            sampleItems.add("Item " + i);
        }
        ArrayAdapter<String> sampleAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                sampleItems
        );
        //TODO: Set up native ads

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        // Optional targeting parameters
        RequestParameters parameters = new RequestParameters.Builder()
                //.keywords("your target words here")
                .build();

        // Request ads when the user returns to this activity
        adAdapter.loadAds(MOPUB_NATIVE_AD_UNIT_ID, parameters);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        adAdapter.destroy();
        super.onDestroy();
    }
}
