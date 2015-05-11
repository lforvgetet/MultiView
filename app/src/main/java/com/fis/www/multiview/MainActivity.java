package com.fis.www.multiview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button activity_1;
    Button startButton;
    Button stopButton;
    Button startBind;
    Button stopBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("GoTo1 onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_1 = (Button) findViewById(R.id.activity_1);
        activity_1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Activity2.class);
                startActivity(intent);

            }
        });
        //StartButton 的動作
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                startService(intent);
                //indService(intent,connection, Context.BIND_AUTO_CREATE);

            }
        });
        stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                stopService(intent);
                //unbindService(connection);
            }

        });
        startBind = (Button) findViewById(R.id.startBind);
        startBind.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);

                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
        stopBind = (Button) findViewById(R.id.stopBind);
        stopBind.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                unbindService(connection);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        System.out.println("GoTo1 onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("GoTo1 onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("GoTo1 onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("GoTo1 onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("GoTo1 onDestroy");
        unbindService(connection);
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        System.out.println("GoTo1 onRestart");
        super.onRestart();
    }

    private MyService myService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.LocalBinder) service).getService();
            myService.startClock();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
