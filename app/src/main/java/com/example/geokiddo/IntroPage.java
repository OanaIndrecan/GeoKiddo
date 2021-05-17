package com.example.geokiddo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class IntroPage extends AppCompatActivity {

    //Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        ImageButton btn = (ImageButton) findViewById(R.id.continueButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(IntroPage.this, CategoryActivity.class);
                //startActivity(intent);
                openCategoryActivity();
            }
        });
    }

        public void openCategoryActivity() {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        }
    }
