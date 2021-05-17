package com.example.geokiddo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton start = (ImageButton) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectFunction();
            }


        });
    }

    public void openSelectFunction() {
        Intent intent = new Intent(this, IntroPage.class);
        startActivity(intent);
    }
}
