package com.eqemp.materialdesgin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.eqemp.Config;

import static java.security.AccessController.getContext;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be
    }

    private void sendRegistrationToServer(final String token) {


        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        final SharedPreferences.Editor editor = pref.edit();

        Firebase ref = new Firebase("https://mysample-8e393.firebaseio.com/");
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        //Getting values to store
        String address = pref.getString("regId", null);

        //Creating Person object
        Person person = new Person();

        //Adding values
        person.setDeviceId(android_id);
        person.setPushid(address);

        //Storing values to firebase
        ref.child("Person").setValue(person, new Firebase.CompletionListener() {

            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {

                editor.putBoolean("sendtoserver", false);

            }
        });

    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }
}