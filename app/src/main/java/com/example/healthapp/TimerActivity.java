package com.example.healthapp;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.icu.util.ULocale;
import android.R;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimerActivity {
//    Chronometer chronometer;
//    ImageButton btStart,btStop;
//
//    private boolean isResume;
//    long tMillisec, tStart,tBuff,tUpdate = 0L;
//    int sec,min,milliSec;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        chronometer = findViewById(R.id.chronometer);
//        btStart = findViewById(R.id.bt_start);
//        btStop = findViewById(R.id.bt_stop);
//
//        handler = new Handler();
//    }
//
//    Handler handler;
//    btStart.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v){
//            if(!isResume){
//                tStart = SystemClock.uptimeMillis();
//                handler.postDelayed(runnable, 0 );
//                chronometer.start();
//                isResume = true;
//                btStop.setVisibility(View.GONE);
//                btStart.setImageDrawable(getResources().getDrawable(
//                        R.drawable.ic_pause
//                ));
//            } else{
//                tBuff += tMillisec;
//                handler.removeCallbacks(runnable);
//                chronometer.stop();
//                isResume = false;
//                btStop.setVisibility(View.VISIBLE);
//                btStart.setImageDrawable(getResources().getDrawable(
//                        R.drawable.ic_play
//                ));
//            }
//        }
//    });
//
//    btStop.setOnCLickListener(new View.OnClickListener(){
//        @Override
//        public void onCLick(View v){
//            if(!isResume){
//                btStart.setImageDrawable(getResources().getDrawable(
//                        R.drawable.ic_play
//                ));
//                tMillisec = 0L;
//                tStart = 0L;
//                tBuff = 0L;
//                tUpdate = 0L;
//                sec = 0;
//                min = 0;
//                milliSec = 0;
//                chronometer.setText("00:00:00");
//        }
//    })
//
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            tMillisec = SystemClock.uptimeMillis() - tStart;
//            tUpdate = tBuff + tMillisec;
//            sec = (int) (tUpdate/1000);
//            min = sec/60;
//            sec = sec%60;
//            milliSec = (int) (tUpdate%100);
//            chronometer.setText(String.format("%02d", min)+":"+String.format("%02d",sec)+":"+String.format("%02d",milliSec));
//            handler.postDelayed(this,60);
//        }
//    };
//

}
