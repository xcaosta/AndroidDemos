package com.xcaosta.boundsvctester;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xcaosta.boundsvc.IAdditionService;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "BoundSvcTester";

    IAdditionService service;
    ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IAdditionService.Stub.asInterface((IBinder) boundService);
            Log.d(TAG, "onServiceConnected()");
        }
        public void onServiceDisconnected(ComponentName name) {
            service = null;
            Log.d(TAG, "onServiceDisconnected()");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        value1 = (EditText) findViewById(R.id.value1);
        value2 = (EditText) findViewById(R.id.value2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent();
        i.setClassName("com.xcaosta.boundsvc", "com.xcaosta.boundsvc.BoundSvc");
        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "bindService() => " + ret);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(connection);
        Log.d(TAG, "unbindService()");
    }

    TextView result;
    EditText value1;
    EditText value2;

    public void onClickCalc(View view) {
        int res = 0;

        try {
            if (service != null) {
                int v1 = Integer.parseInt(value1.getText().toString());
                int v2 = Integer.parseInt(value2.getText().toString());
                res = service.add(v1, v2);
            }
        } catch (Exception e) {
            Log.d(TAG, "onClick failed with: " + e);
            e.printStackTrace();
        }
        result.setText(new Integer(res).toString());
    }
}
