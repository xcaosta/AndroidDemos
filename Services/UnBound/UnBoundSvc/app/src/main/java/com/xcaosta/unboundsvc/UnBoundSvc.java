package com.xcaosta.unboundsvc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class UnBoundSvc extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("").setContentText("").build();
            startForeground(1, notification);
        }
        handler = new Handler(getMainLooper());
        showMessage("Service was Created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        thread.interrupt();
        showMessage("Service Destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        (thread = new Thread(() -> {
            for(int n = 0;;n++) { try {
                    Thread.sleep(2000);
                    final int nn = n;
                    handler.post(()->showMessage("Service Live: " + nn));
                } catch (Exception e) {
                    handler.post(()->showMessage("Service Interrupted"));
                    break;
                }
            }
        })).start();

        showMessage("Service Started");
        return START_STICKY; // recreate the service, call with null intent
    }

    private void showMessage(String message) {
        Log.d(TAG, message);
        Toast.makeText(UnBoundSvc.this, message, Toast.LENGTH_SHORT).show();
    }

    static final String TAG = UnBoundSvc.class.getSimpleName();

    MediaPlayer player;
    Handler handler;
    Thread thread;
}
