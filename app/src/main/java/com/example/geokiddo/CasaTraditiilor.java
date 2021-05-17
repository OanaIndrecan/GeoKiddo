package com.example.geokiddo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CasaTraditiilor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa_traditiilor);

        ImageButton btn = (ImageButton) findViewById(R.id.StartQuizButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(IntroPage.this, CategoryActivity.class);
                //startActivity(intent);
                openQuizActivity();
            }
        });
    }

    public void openQuizActivity() {
        Intent intent = new Intent(this, QuizCasaTraditiilor.class);
        startActivity(intent);
    }
    }
