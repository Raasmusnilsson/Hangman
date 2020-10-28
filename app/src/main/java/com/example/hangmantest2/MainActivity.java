package com.example.hangmantest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void playBtn(View view) {
        Intent PlayGame = new Intent(getApplicationContext(), PlayGameActivity.class);
        startActivity(PlayGame);
    }
    public void infoBtn(View view) {
        Intent infoAbout = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(infoAbout);
    }
    public void startGameBtn(View view) {
        Intent startGame = new Intent(getApplicationContext(), PlayGameActivity.class);
        startActivity(startGame);
    }
    public void infoBigBtn(View view) {
        Intent infoAbout = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(infoAbout);
    }
}