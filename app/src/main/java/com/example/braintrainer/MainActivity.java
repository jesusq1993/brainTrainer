package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.example.braintrainer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = activityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        hideElements();


    }

    public void hideElements(){
        activityMainBinding.timerTxv.setVisibility(View.GONE);
        activityMainBinding.problemTxv.setVisibility(View.GONE);
        activityMainBinding.problemAmounttxv.setVisibility(View.GONE);
        activityMainBinding.answerOneButton.setVisibility(View.GONE);
        activityMainBinding.answerTwoButton.setVisibility(View.GONE);
        activityMainBinding.answerThreeButton.setVisibility(View.GONE);
        activityMainBinding.answerfourButton.setVisibility(View.GONE);
    }

    public void showElements(){
        activityMainBinding.timerTxv.setVisibility(View.VISIBLE);
        activityMainBinding.problemTxv.setVisibility(View.VISIBLE);
        activityMainBinding.problemAmounttxv.setVisibility(View.VISIBLE);
        activityMainBinding.answerOneButton.setVisibility(View.VISIBLE);
        activityMainBinding.answerTwoButton.setVisibility(View.VISIBLE);
        activityMainBinding.answerThreeButton.setVisibility(View.VISIBLE);
        activityMainBinding.answerfourButton.setVisibility(View.VISIBLE);

        activityMainBinding.goButton.setVisibility(View.GONE);
    }


    public void goButton(View view) {
        showElements();

        int seconds = 30;

        countDownTimer = new CountDownTimer(seconds*1000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - minutes*60;

        String secondString = Integer.toString(seconds);

        if(seconds<=9){
            secondString = "0"+secondString;
        }

        Log.i("Timer:",Integer.toString(minutes)+":"+secondString);

        activityMainBinding.timerTxv.setText(Integer.toString(minutes)+":"+secondString);

    }


}