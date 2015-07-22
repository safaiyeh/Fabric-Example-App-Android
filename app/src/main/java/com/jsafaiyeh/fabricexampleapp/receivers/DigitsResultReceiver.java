package com.jsafaiyeh.fabricexampleapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.digits.sdk.android.ContactsUploadResult;
import com.digits.sdk.android.ContactsUploadService;

public class DigitsResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ContactsUploadService.UPLOAD_COMPLETE.equals(intent.getAction())) {
            ContactsUploadResult result = intent
                    .getParcelableExtra(ContactsUploadService.UPLOAD_COMPLETE_EXTRA);

            // Post success notification
        } else {
            // Post failure notification
        }
    }
}
