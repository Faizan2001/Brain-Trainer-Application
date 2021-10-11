package com.example.faiza.braintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {

    TextView TimerView;
    TextView QuestionView;
    TextView ResultView;
    TextView ScoreView;
    GridLayout GridLayout;
    Button StartButton;
    Button OptionButtons;
    Button OptionButtons2;
    Button OptionButtons3;
    Button OptionButtons4;
    Button PlayAgainButton;
    RelativeLayout GameRelativeLayout;
    Boolean gameIsActive = false;


    ArrayList<Integer> Answers = new ArrayList<Integer>();
    int LocationOfCorrectAnswer;
    int Score = 0;
    int NumberOfQuestions = 0;





   public void StartGame(View view) {

       StartButton.setVisibility(View.INVISIBLE);
       GameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
       PlayAgain(findViewById(R.id.PlayAgainButton));
       gameIsActive = true;
   }




  public void ChosenAnswer(View view) {

      if (gameIsActive) {
          if (view.getTag().toString().equals(Integer.toString(LocationOfCorrectAnswer))) {
              Score++;
              ResultView.setText("Correct!");


          } else {
              ResultView.setText("Wrong!");

          }

          NumberOfQuestions++;
          ScoreView.setText(Integer.toString(Score) + "/" + Integer.toString(NumberOfQuestions));
          GenerateQuestion();
      }


  }

    public void GenerateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);

        int b = rand.nextInt(21);


        QuestionView.setText(Integer.toString(a) + " + " +  Integer.toString(b));

        LocationOfCorrectAnswer = rand.nextInt(4);

        Answers.clear();

        int IncorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == LocationOfCorrectAnswer) {
                Answers.add(a + b);
            } else {
                IncorrectAnswer = rand.nextInt(41);

                while (IncorrectAnswer == a + b) {

                    IncorrectAnswer = rand.nextInt(41);
                }


                Answers.add(IncorrectAnswer);
            }


        }


        OptionButtons.setText(Integer.toString(Answers.get(0)));
        OptionButtons2.setText(Integer.toString(Answers.get(1)));
        OptionButtons3.setText(Integer.toString(Answers.get(2)));
        OptionButtons4.setText(Integer.toString(Answers.get(3)));


    }




  public void PlayAgain(View view) {
      Score = 0;
      NumberOfQuestions = 0;
      TimerView.setText("30s");
      ScoreView.setText("0/0");
      ResultView.setText("");
      PlayAgainButton.setVisibility(View.INVISIBLE);
      GenerateQuestion();
      gameIsActive = true;


      new CountDownTimer(30100, 1000) {
          @Override
          public void onTick(long millisUntilFinished) {

              TimerView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

          }

          @Override
          public void onFinish() {
              TimerView.setText("0s");
              ResultView.setText("Your Score is: " + Integer.toString(Score) + "/" + Integer.toString(NumberOfQuestions));
              PlayAgainButton.setVisibility(View.VISIBLE);
              gameIsActive = false;

          }
      }.start();



  }












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResultView = (TextView) findViewById(R.id.ResultView);
        QuestionView = (TextView) findViewById(R.id.QuestionView);
        TimerView = (TextView) findViewById(R.id.TimerView);
        ScoreView = (TextView) findViewById(R.id.ScoreView);
        GridLayout = (GridLayout) findViewById(R.id.GridLayout);
        StartButton = (Button) findViewById(R.id.StartButton);
        OptionButtons = (Button) findViewById(R.id.OptionButtons);
        OptionButtons2 = (Button) findViewById(R.id.OptionButtons2);
        OptionButtons3 = (Button) findViewById(R.id.OptionButtons3);
        OptionButtons4 = (Button) findViewById(R.id.OptionButtons4);
        PlayAgainButton = (Button) findViewById(R.id.PlayAgainButton);
        GameRelativeLayout = (RelativeLayout) findViewById(R.id.GameRelativeLayout);





    }
}
