package com.example.rememberwithnotify;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class HomeActivity extends AppCompatActivity {

    EditText message, title;
    Button button;

    String CHANNEL_ID = "notify_channel";
    NotificationManager notificationManager;
    int id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        message = findViewById(R.id.messageNotify);
        title = findViewById(R.id.titleNotify);
        button = findViewById(R.id.button);

        createNotificationChannel();

    }

    public void rememberClick(View v){
        if(message.getText().length()==0){ return; }
        Log.d("RECIEVED->", message.getText().toString());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title.getText().toString())
                .setSmallIcon(R.drawable.notify_icon_transparent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message.getText().toString()))
                .setContentText(message.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(id++, builder.build());
        message.setText("");
        title.setText("");
    }

    private void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CANNAL PRINCIPAL";
            int importance = NotificationManager.IMPORTANCE_MAX;
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription("Sample Channel Description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }
    }

    //LANGUAGES

    public void languageSpanish(View v){
        title.setHint(R.string.placeholder_titulo);
        message.setHint(R.string.placeholder_recordar);
        button.setText(R.string.notify_titulo);
    }

    public void languageEnglish(View v){
        title.setHint(R.string.placeholder_title);
        message.setHint(R.string.placeholder_remember);
        button.setText(R.string.notify_title);
    }
}