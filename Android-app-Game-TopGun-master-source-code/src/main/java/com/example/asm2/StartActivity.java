package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class StartActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        progressBar = (ProgressBar)  findViewById(R.id.progressBar);

       new Thread(new Runnable() {
           @Override
           public void run() {
               while (progressStatus==0){
                   progressStatus +=1;
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           progressBar.setProgress(progressStatus);
                       }
                   });
                   try{
                       Thread.sleep(100);

                   } catch (InterruptedException e){
                       e.printStackTrace();
                   }finally {
                       Intent prepareIntent = new Intent(StartActivity.this, MenuActivity.class);
                       startActivity(prepareIntent);
                   }

               }
           }
           }).start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
