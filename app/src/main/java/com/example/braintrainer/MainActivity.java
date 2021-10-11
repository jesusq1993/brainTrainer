package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.example.braintrainer.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    CountDownTimer countDownTimer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numOfQuestions = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = activityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        hideElements();
        newQuestion();
    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        activityMainBinding.problemTxv.setText(Integer.toString(a)+ " + " +Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for(int i = 0;i < 4; i++){
            if(i== locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(rand.nextInt(41));
            }

        }

        activityMainBinding.answerZeroButton.setText(Integer.toString(answers.get(0)));
        activityMainBinding.answerOneButton.setText(Integer.toString(answers.get(1)));
        activityMainBinding.answerTwoButton.setText(Integer.toString(answers.get(2)));
        activityMainBinding.answerThreeButton.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            activityMainBinding.resultTxv.setText("Correct!");
            score++;
        } else {
            activityMainBinding.resultTxv.setText("Wrong :(");
        }
        numOfQuestions++;

        activityMainBinding.problemAmounttxv.setText(Integer.toString(score) +"/"+Integer.toString(numOfQuestions));

         newQuestion();
    }

    public void hideElements(){
        activityMainBinding.timerTxv.setVisibility(View.GONE);
        activityMainBinding.problemTxv.setVisibility(View.GONE);
        activityMainBinding.problemAmounttxv.setVisibility(View.GONE);
        activityMainBinding.answerZeroButton.setVisibility(View.GONE);
        activityMainBinding.answerOneButton.setVisibility(View.GONE);
        activityMainBinding.answerTwoButton.setVisibility(View.GONE);
        activityMainBinding.answerThreeButton.setVisibility(View.GONE);
        activityMainBinding.resultTxv.setVisibility(View.GONE);
    }

    public void showElements(){
        activityMainBinding.timerTxv.setVisibility(View.VISIBLE);
        activityMainBinding.problemTxv.setVisibility(View.VISIBLE);
        activityMainBinding.problemAmounttxv.setVisibility(View.VISIBLE);
        activityMainBinding.answerZeroButton.setVisibility(View.VISIBLE);
        activityMainBinding.answerOneButton.setVisibility(View.VISIBLE);
        activityMainBinding.answerTwoButton.setVisibility(View.VISIBLE);
        activityMainBinding.answerThreeButton.setVisibility(View.VISIBLE);
        activityMainBinding.resultTxv.setVisibility(View.VISIBLE);

        activityMainBinding.goButton.setVisibility(View.INVISIBLE);

    }


    public void goButton(View view) {
        showElements();
        activityMainBinding.resultTxv.setText("");

        int seconds = 30;

        countDownTimer = new CountDownTimer(seconds*1000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                activityMainBinding.resultTxv.setText("Done!");
                activityMainBinding.playAgainButton.setVisibility(View.VISIBLE);
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


    public void playAgain(View view) {
        score = 0;
        numOfQuestions = 0;
        activityMainBinding.timerTxv.setText("30s");
        activityMainBinding.problemAmounttxv.setText(Integer.toString(score)+"/"+Integer.toString(numOfQuestions));
        activityMainBinding.playAgainButton.setVisibility(View.INVISIBLE);
        activityMainBinding.resultTxv.setText("");
        newQuestion();

        int seconds = 30;

        countDownTimer = new CountDownTimer(seconds*1000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                activityMainBinding.resultTxv.setText("Done!");
                activityMainBinding.playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
}