package com.example.testapp.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button trueButton, falseButton;
    TextView questionView;
    int curQuestionIndex, score;
    Question currentQuestion;

    // the list of questions
    Question[] questions = new Question[] {
            new Question(R.string.question_1, false),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // update state
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("OurScore");
            curQuestionIndex = savedInstanceState.getInt("CurIndex");
        }
        else {
            score = 0;
            curQuestionIndex = 0;
        }

        trueButton = (Button) findViewById(R.id.trueButton);
        falseButton = (Button) findViewById(R.id.falseButton);
        questionView = (TextView) findViewById(R.id.display_question);

        // set the current question
        currentQuestion = questions[curQuestionIndex];
        questionView.setText(currentQuestion.getQuestionRID());

        trueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                answerQuestion(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                answerQuestion(false);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("OurScore", score);
        outState.putInt("CurIndex", curQuestionIndex);
    }

    public void answerQuestion(boolean answer) {

        // resolve the question and update the answer if so
        if (answer == currentQuestion.isAnswer()) {

            // answer is right, show a toast!
            Toast toastMessage = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
            toastMessage.show();
            score++;
        }
        else {
            // answer is wrong, show a toast!
            Toast toastMessage = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
            toastMessage.show();
        }

        // update the question
        curQuestionIndex++;
        if (curQuestionIndex == questions.length) {
            AlertDialog.Builder completedAlert = new AlertDialog.Builder(MainActivity.this);
            completedAlert.setTitle("Quiz Over!");
            completedAlert.setCancelable(false);
            completedAlert.setMessage("Your Scored " + score + "/" + questions.length);
            completedAlert.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            completedAlert.show();
        }
        else {
            currentQuestion = questions[curQuestionIndex];
            questionView.setText(currentQuestion.getQuestionRID());
        }
    }
}
