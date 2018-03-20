package com.example.testapp.myapplication;


import android.util.Log;

public class Question {
    private int questionTextID;
    private boolean isTrue;

    public Question(int questionRID, boolean answer) {
        questionTextID = questionRID;
        isTrue = answer;
    }

    public int getQuestionRID() {
        return questionTextID;
    }

    public boolean isAnswer() {
        return isTrue;
    }

    public void printQuestion() {
        Log.d("QuestionData", "ID: " + questionTextID + ", answer: " + isTrue);
    }
}




