package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButtton;
    TextView resultTextView;
    TextView resultTextView2;
    TextView gameOverTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    RelativeLayout resultRelativeLayout;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrrectAnswer;
    int score=0;
    int numberOfQuestions=0;

    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView2.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestions();

        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000 + "s"));
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");
                resultRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
                gameOverTextView.setText("GAME OVER!");
                resultTextView2.setText("Your Score: " + Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
                gameRelativeLayout.setVisibility(RelativeLayout.INVISIBLE);
            }
        }.start();
        resultRelativeLayout.setVisibility(RelativeLayout.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
    }
    public void generateQuestions(){
        Random rand=new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);
        sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));

        locationOfCorrrectAnswer=rand.nextInt(4);
        answers.clear();
        int inCorrectAnswer;
        for(int i=0;i<4;i++){
            if(i==locationOfCorrrectAnswer){
                answers.add(a+b);
            }else{
                inCorrectAnswer=rand.nextInt(71);
                while(inCorrectAnswer==a+b){
                    inCorrectAnswer=rand.nextInt(71);
                }
                answers.add(inCorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrrectAnswer))){
            score++;
            //resultTextView.setHighlightColor(Color.green(1));
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        generateQuestions();


    }
    public void start(View view){

        startButtton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButtton=(Button) findViewById(R.id.startButton);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        button0=(Button) findViewById(R.id.button0);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        resultTextView=(TextView) findViewById(R.id.resultTextView);
        resultTextView2=(TextView) findViewById(R.id.resultTextView2);
        pointsTextView=(TextView) findViewById(R.id.pointsTextView);
        timerTextView=(TextView) findViewById(R.id.timerTextView);
        playAgainButton=(Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout=(RelativeLayout) findViewById(R.id.gameRelativeLayout);
        resultRelativeLayout=(RelativeLayout) findViewById(R.id.resultRelativeLayout);
        gameOverTextView=(TextView) findViewById(R.id.gameOverTextView);



    }
}