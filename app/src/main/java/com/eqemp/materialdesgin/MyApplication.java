package com.eqemp.materialdesgin;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.firebase.client.Firebase;


public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Firebase.setAndroidContext(this);
        MultiDex.install(this);
        // register with parse
        //ParseUtils.registerParse(this);
        //  Parse.initialize(this);
        //  ParseInstallation.getCurrentInstallation().saveInBackground();

    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}