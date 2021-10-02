package com.xcaosta.unboundsvctester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.xcaosta.unboundsvc", "com.xcaosta.unboundsvc.UnBoundSvc");
        // https://stackoverflow.com/questions/46445265/android-8-0-java-lang-illegalstateexception-not-allowed-to-start-service-inten
        // starting from API 26, startService call is limited. Must use startForegroundService
        // startService(intent);
        startForegroundService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.xcaosta.unboundsvc", "com.xcaosta.unboundsvc.UnBoundSvc");
        stopService(intent);
    }
}