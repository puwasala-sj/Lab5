package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_ACTION = "" ;

    BackgroundService backgroundService;
    Receiver receiver;
    private TextView txtMsg;
    private Receiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView)findViewById(R.id.editText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){
        if(view.getId() == R.id.button){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = txtMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived "+message;
            txtMsg.setText(currentText);
        }
    }
}

