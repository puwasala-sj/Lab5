package com.example.lab5;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class BackgroundService extends IntentService {
    public BackgroundService() { super("BackgroundService");}

    public static void startAction(Context context){
        Intent intent = new Intent(context,BackgroundService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null){
            for(int i = 0; i < 5; i++){
                Intent localBroadcast = new Intent(MainActivity.BROADCAST_ACTION);
                localBroadcast .putExtra("value","Broadcast"+(i + 1));
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                sendBroadcast(localBroadcast);
            }
        }
    }
}
