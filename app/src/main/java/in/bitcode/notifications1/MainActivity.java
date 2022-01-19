package in.bitcode.notifications1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify, btnCancel;
    private NotificationManager notificationManager;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        createChannel();

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(MainActivity.this);

                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

                Intent targetIntent = new Intent(MainActivity.this, HomeActivity.class);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                1,
                                targetIntent,
                                0
                        );

                @SuppressLint({"NewApi", "LocalSuppress"})
                Notification notification =
                        builder.setContentTitle("BitCode Updates...")
                                .setContentText("Excellent learning here...")
                                .setColor(Color.RED)
                                .setAutoCancel(true)
                                .setLargeIcon(largeIcon)
                                //.setSound(Uri.parse("file:///storage/audio/some.mp3"))
                                .setFlag(Notification.FLAG_INSISTENT, true)
                                //.setOngoing(true)
                                .setVisibility(Notification.VISIBILITY_PUBLIC)
                                .setVibrate(new long[]{200, 400, 300, 200, 300, 200, 300, 200})
                                .setColor(Color.YELLOW)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setChannelId("bitcode")
                                .setContentIntent(pendingIntent)
                                .build();
                notificationManager.notify(100, notification);
            }
        });
    }

    @SuppressLint("NewApi")
    private void init() {
        btnNotify = findViewById(R.id.btnNotify);
        btnCancel = findViewById(R.id.btnCancel);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager = getSystemService(NotificationManager.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel("bitcode", "BitCode Training Updates", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setShowBadge(true);

        notificationManager.createNotificationChannel(notificationChannel);

    }
}