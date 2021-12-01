package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    protected ProgressDialog mProgressDialog;
    public static final int MSG_IND = 2;
    int elapsedTime = 0 ;

    String msg1 ;
    TextView textcount;
    Message msg;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textcount = (TextView) findViewById(R.id.start);
        setContentView(R.layout.activity_main);

        }


    public void stopserv(View view) {

    }

    public void getcount(View view) {
       mHandler.handleMessage(msg);

        return;


    }
    private final Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            msg1 = (msg.obj).toString() ;
            textcount.setText(msg1);//this is the textview
        }
    };

    private final Runnable sendcount = new Runnable(){
            @Override
            public void run() {
                boolean isTimerRunning = true;
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public int elapsedTime;

                    public void run() {
                        elapsedTime ++; //increase every sec
                        msg = mHandler.obtainMessage( 1, elapsedTime);
                        mHandler.sendMessage(msg);
                    }
                }, 0, 100000000);
    }};

    public void startserv(View view) {
        new Thread(sendcount).start();
    }



}


