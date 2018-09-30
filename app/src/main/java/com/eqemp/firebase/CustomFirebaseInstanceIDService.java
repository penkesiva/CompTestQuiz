package com.eqemp.firebase;

import android.provider.Settings;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.eqemp.Config;

import static java.security.AccessController.getContext;

public class CustomFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = CustomFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token Value: " + refreshedToken);
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        sendTokenToFirease(android_id, refreshedToken);
    }


    public void sendTokenToFirease(String devicceid, String token) {
        Firebase ref = new Firebase(Config.FIREBASE_URL);


        //Creating Person object
        Person person = new Person();

        //Adding values
        person.setName(devicceid);
        person.setAddress(token);

        //Storing values to firebase
        ref.child("Person").setValue(person);


        //Value event listener for realtime data update
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    Person person = postSnapshot.getValue(Person.class);

                    //Adding it to a string
                    String string = "Name: " + person.getName() + "\nAddress: " + person.getAddress() + "\n\n";

                    //Displaying it on textview
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

}
