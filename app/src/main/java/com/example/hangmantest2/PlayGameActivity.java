package com.example.hangmantest2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PlayGameActivity extends AppCompatActivity {

    private TextView wordToBeGuessedTxt;
    private String wordToBeGuessed;
    private String wordDisplayedString;
    private char[] wordDisplayedCharArray;
    private ArrayList<String> listOfWords;
    private EditText inputEdtTxt;
    private TextView lettersTriedTxtView;
    private ImageView hangmanImageView;
    private int triesLeft = 0;
    private String lettersTried;
    private String correctLettersTried;
    private ArrayList<String> lettersTriedList = new ArrayList<>();
    private int[] imageIntArray = {R.drawable.hangman_1, R.drawable.hangman_2,R.drawable.hangman_3,R.drawable.hangman_4,R.drawable.hangman_5,
            R.drawable.hangman_6,R.drawable.hangman_7,R.drawable.hangman_8,R.drawable.hangman_9,R.drawable.hangman_10,R.drawable.hangman_11};
    private TextView showTriesLeftOnScreen;
    private int showTriesLeft = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        listOfWords = new ArrayList<String>();
        wordToBeGuessedTxt = findViewById(R.id.wordToBeGuessed);
        inputEdtTxt = findViewById(R.id.inputTxt);
        lettersTriedTxtView = findViewById(R.id.guessedCharTxt);
        hangmanImageView = findViewById(R.id.hangmanImage);
        showTriesLeftOnScreen = findViewById(R.id.triesLeftTxtView);
        InputStream inputStream = null;
        Scanner sc = null;
        String wordFromList = "";

        try {
            inputStream = getAssets().open("words_for_hangman.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc = new Scanner(inputStream);
            while(sc.hasNext()) {
                wordFromList = sc.next();
                listOfWords.add(wordFromList);
            }

        startGame();

        inputEdtTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lettersTried = s.toString();
                if (s.length() != 0) {
                    checkIfLetterIsInWord(s.charAt(0));
                }
            }@Override public void afterTextChanged(Editable s) {}});
    }
    void startGame() {
        Collections.shuffle(listOfWords);
        wordToBeGuessed = listOfWords.get(0);
        listOfWords.remove(0);
        wordDisplayedCharArray = wordToBeGuessed.toCharArray();

        for (int i = 1; i < wordDisplayedCharArray.length; i++){
            wordDisplayedCharArray[i] = '_';
        }
        showLetterInWord(wordDisplayedCharArray[0]);
        showLetterInWord(wordDisplayedCharArray[wordDisplayedCharArray.length-1]);

        wordDisplayedString = String.valueOf(wordDisplayedCharArray);
        showWordOnScreen();
    }

    void showLetterInWord(char letter) {
        int indexOfLetter = wordToBeGuessed.indexOf(letter);

        while (indexOfLetter >= 0) {
            wordDisplayedCharArray[indexOfLetter] = wordToBeGuessed.charAt(indexOfLetter);
            indexOfLetter = wordToBeGuessed.indexOf(letter, indexOfLetter+1);
        }
        wordDisplayedString = String.valueOf(wordDisplayedCharArray);
    }

    void showWordOnScreen() {
        String formattedString = "";
        for (char character : wordDisplayedCharArray) {
            formattedString += character + " ";
        }
        wordToBeGuessedTxt.setText(formattedString);

    }
    @SuppressLint("SetTextI18n")
    void checkIfLetterIsInWord(char letter) {

                 if (wordToBeGuessed.indexOf(letter) < 0) {
                     lettersTriedList.add(lettersTried);

                     correctLettersTried = lettersTriedList.toString().replace("[","").replace("]", "");

                     String LETTERS_TRIED_MESSAGE = getString(R.string.LETTERS_TRIED);
                     lettersTriedTxtView.setText(LETTERS_TRIED_MESSAGE+" "+correctLettersTried);
                     inputEdtTxt.setText("");
                     Log.d("BOB", "WRONG LETTER, GUESS AGAIN!");

                     showTriesLeft--;
                     String TRIES_LEFT_MESSAGE = getString(R.string.TRIES_LEFT);
                     showTriesLeftOnScreen.setText(showTriesLeft+" "+TRIES_LEFT_MESSAGE);

                     Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                     intent.putExtra("tries_left_message",showTriesLeft);

                     hangmanImageView.setImageResource(imageIntArray[triesLeft]);
                     triesLeft++;

                         if (triesLeft >= 11) {
                             Intent intent2 = new Intent(getApplicationContext(),ResultActivity.class);
                             intent2.putExtra("tries_left_message2",showTriesLeft);
                             intent2.putExtra("word_to_guess2",wordToBeGuessed);
                             startActivity(intent2);
                         }
                 }
                    if (wordToBeGuessed.indexOf(letter) >= 0) {
                        if(wordDisplayedString.indexOf(letter) < 0) {
                            showLetterInWord(letter);
                            showWordOnScreen();
                            inputEdtTxt.setText("");

                            if(!wordDisplayedString.contains("_")) {
                                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                                intent.putExtra("tries_left_message",showTriesLeft);
                                intent.putExtra("word_to_guess",wordToBeGuessed);
                                startActivity(intent);
                           }
                        }
                    }
         }

    public void infoBtn1(View view) {
        Intent infoAbout = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(infoAbout);
    }
    public void goBackBtn2(View view) {
        Intent goBacktoMenu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goBacktoMenu);
    }
}