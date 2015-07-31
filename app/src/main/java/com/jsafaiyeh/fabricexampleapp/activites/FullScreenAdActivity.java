package com.jsafaiyeh.fabricexampleapp.activites;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jsafaiyeh.fabricexampleapp.R;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class FullScreenAdActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {

    // Replace this test id with your personal ad unit id
    private static final String MOPUB_INTERSTITIAL_AD_UNIT_ID = "2be8f7ceb2c14c14b57910146144e80e";
    private MoPubInterstitial interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_ad);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Fullscreen Ad");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        interstitial = new MoPubInterstitial(this, MOPUB_INTERSTITIAL_AD_UNIT_ID);
        interstitial.setInterstitialAdListener(this);
        interstitial.load();
    }

    @Override
    protected void onDestroy() {
        interstitial.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // InterstitialAdListener methods
    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        if (interstitial.isReady()) {
            interstitial.show();
        } else {
            // Other code
        }
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {}

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {}

}
