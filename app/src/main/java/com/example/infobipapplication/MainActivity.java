package com.example.infobipapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.infobip.mobile.messaging.MobileMessaging;
import org.infobip.mobile.messaging.User;
import org.infobip.mobile.messaging.UserAttributes;
import org.infobip.mobile.messaging.UserIdentity;
import org.infobip.mobile.messaging.mobileapi.MobileMessagingError;
import org.infobip.mobile.messaging.mobileapi.Result;

import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MobileMessaging
                .Builder(getApplication())
                .build();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> saveUser());
    }

    private void saveUser() {
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setPhones(new HashSet<>(Arrays.asList("+573188887654")));
        userIdentity.setEmails(new HashSet<>(Arrays.asList("b@testinfobip.com")));

        UserAttributes userAttributes = new UserAttributes();
        userAttributes.setFirstName("BJohn");
        userAttributes.setLastName("BDoe");

        MobileMessaging.getInstance(this).personalize(userIdentity, userAttributes, new MobileMessaging.ResultListener<User>() {
            @Override
            public void onResult(Result<User, MobileMessagingError> result) {
                Log.e("ACR", " result " + result.isSuccess());
            }
        });
    }
}