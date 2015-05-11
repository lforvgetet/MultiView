package com.fis.www.multiview;

import android.app.Service;
import android.content.Intent;


import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;


public class MyService extends Service {
    public MyService() {
    }

    private Handler handler=new Handler();
    private Runnable clock = new Runnable() {
        @Override
        public void run() {
            Log.d("times", new Date().toString());
            handler.postDelayed(this,1000);
        }
    };

    private  final IBinder localBinder = new LocalBinder();
    public class LocalBinder extends Binder {
        MyService getService(){
            return MyService.this;

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;

    }
    //預備使用的service
    public void startClock(){

        handler.postDelayed(clock,1000);
    }
    @Override
    public void onCreate() {
         super.onCreate();
        Log.d("MyService","onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy");
       handler.removeCallbacks(clock);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand");
        handler.postDelayed(clock,1000);
        return super.onStartCommand(intent, flags, startId);
    }
}
