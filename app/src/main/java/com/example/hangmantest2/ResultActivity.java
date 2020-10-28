package com.example.hangmantest2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView resultWindow;
    Intent intent;
    TextView showTriesLeft;
    TextView theWord;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultWindow = findViewById(R.id.resultTxt);
        showTriesLeft = findViewById(R.id.showTriesLeftTxtView);
        theWord = findViewById(R.id.theWord);
        showResultWindow();
    }

    @SuppressLint("SetTextI18n")
    public void showResultWindow() {
    intent = getIntent();
    intent2 = getIntent();
    String AMOUNT_OF_TRIES_LEFT_MESSAGE = getString(R.string.AMOUNT_OF_TRIES_LEFT_MESSAGE);
    String THE_WORD_IS_MESSAGE = getString(R.string.THE_WORD_MESSAGE);
    int triesLeftToInt = intent.getIntExtra("tries_left_message",0);

    if (triesLeftToInt > 0) {

        String wordToGuess = intent.getStringExtra("word_to_guess");
        String YOU_WON_MESSAGE = getString(R.string.YOU_WON_MESSAGE);
        resultWindow.setText(YOU_WON_MESSAGE);
        theWord.setText(THE_WORD_IS_MESSAGE+wordToGuess);
    } else {
        String wordToGuess2 = intent2.getStringExtra("word_to_guess2");
        String YOU_LOST_MESSAGE = getString(R.string.YOU_LOST_MESSAGE);
        resultWindow.setText(YOU_LOST_MESSAGE);
        theWord.setText(THE_WORD_IS_MESSAGE+wordToGuess2);
    }
        showTriesLeft.setText(AMOUNT_OF_TRIES_LEFT_MESSAGE+ triesLeftToInt);

    }

    public void goBackBtn2(View view) {
        Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goBack);
    }

    public void infoBtn1(View view) {
        Intent infoAbout = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(infoAbout);
    }

    public void playAgainBtn(View view) {
        Intent playAgain = new Intent(getApplicationContext(), PlayGameActivity.class);
        startActivity(playAgain);
    }
}