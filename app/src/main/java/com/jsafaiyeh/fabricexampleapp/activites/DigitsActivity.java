package com.jsafaiyeh.fabricexampleapp.activites;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Contacts;
import com.digits.sdk.android.ContactsCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.digits.sdk.android.DigitsUser;
import com.digits.sdk.android.PhoneNumber;
import com.jsafaiyeh.fabricexampleapp.R;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

public class DigitsActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_digits);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Digits");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextView = (TextView) findViewById(R.id.phone_number);

        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                if (phoneNumber != null) {
                    mTextView.setText("Logged In with " + phoneNumber);
                    Digits.getInstance().getContactsClient().startContactsUpload(); //See DigitsResultReciever
                } else
                    mTextView.setText("Logged In already");
            }

            @Override
            public void failure(DigitsException exception) {
                // Do something on failure
            }
        });
        digitsButton.setAuthTheme(R.style.AppTheme);



        //Process Contact information
        Digits.getInstance().getContactsClient().lookupContactMatches(null, null,
                new ContactsCallback<Contacts>() {

                    @Override
                    public void success(Result<Contacts> result) {
                        if (result.data.users != null) {
                            // Process data
                        }
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Show error
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
