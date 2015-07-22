package com.jsafaiyeh.fabricexampleapp.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.mopub.mobileads.MoPubView;

import com.jsafaiyeh.fabricexampleapp.R;

public class BannerAdActivity extends AppCompatActivity {

    // Replace this test id with your personal ad unit id
    private static final String MOPUB_BANNER_AD_UNIT_ID = "cc2c7c9f7da5488b8584ea9799c5da63";
    private MoPubView moPubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_ad);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("MoPub Banner Ad");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        moPubView = (MoPubView) findViewById(R.id.mopub_sample_ad);
        moPubView.setAdUnitId(MOPUB_BANNER_AD_UNIT_ID);
        moPubView.loadAd();
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
    protected void onDestroy() {
        moPubView.destroy();
        super.onDestroy();
    }
}
