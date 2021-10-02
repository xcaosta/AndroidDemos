package com.xcaosta.boundsvc;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
public class BoundSvc extends Service {
    static final String TAG = BoundSvc.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        showMessage("Service was Created");
    }

    @Override
    public IBinder onBind(Intent intent) {
        showMessage("onBind()");
        return new IAdditionService.Stub() {
            public int add(int a, int b) throws RemoteException {
                Log.d(TAG, String.format("AdditionService.add(%d, %d)",a, b));
                return a + b;
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showMessage("Service Destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showMessage("onStartCommand called");
        return START_STICKY; // recreate the service, call with null intent
    }

    private void showMessage(String message) {
        Log.d(TAG, message);
        Toast.makeText(BoundSvc.this, message, Toast.LENGTH_SHORT).show();
    }
}
