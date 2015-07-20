package com.jsafaiyeh.fabricexampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

public class EmbedSingleTweetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embed_single_tweets);

        final LinearLayout mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        //Dynamically add tweet
        final long tweetId = 623055543385591809L;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                mLinearLayout.addView(new TweetView(EmbedSingleTweetsActivity.this, result.data));
            }

            @Override
            public void failure(TwitterException exception) {
                //Handle failure to load tweet
            }
        });
    }

}
