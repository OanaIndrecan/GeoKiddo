package com.example.geokiddo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore, tvQuestionNo, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnNext;

    int totalQuestions;
    int qCounter = 0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;

   // CountDownTimer countDownTimer;

    //private int score_up;


    private QuestionModel currentQuestion;

    private List<QuestionModel> questionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_casa_geo);

        questionsList = new ArrayList<>();
        tvQuestion = findViewById(R.id.textQuestion);
        tvScore = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        //tvTimer = findViewById(R.id.textTimer);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();





        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answered == false){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                        //countDownTimer.cancel();
                    } else{
                        Toast.makeText(QuizActivity.this, "Please Select an option", Toast.LENGTH_SHORT ).show();
                    }

                }else{
                    showNextQuestion();
                }
            }
        });
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) +1;
        if(answerNo == currentQuestion.getCorrectAnsNo()){
            score++;
            tvScore.setText(("Score: "+score +"/"+totalQuestions));
        }
        rb1.setTextColor((Color.RED));
        rb2.setTextColor((Color.RED));
        rb3.setTextColor((Color.RED));
        rb4.setTextColor((Color.RED));
        switch ( currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }
        if(qCounter < totalQuestions){
            btnNext.setText("Next");
        }
        else {
            btnNext.setText("Finish");
        }

    }

    private void showNextQuestion() {
        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if (qCounter < totalQuestions){

            //timer();
            currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            qCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: " +qCounter+ "/"+totalQuestions);
            answered = false;
        }
        else{
            Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score)+ "/" + String.valueOf(questionsList.size()) );
            startActivity(intent);
            QuizActivity.this.finish();

        }
    }

//    private void timer() {
//        countDownTimer = new CountDownTimer(20000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                tvTimer.setText("00:" + 1/1000);
//            }
//
//            @Override
//            public void onFinish() {
//                showNextQuestion();
//
//            }
//        }.start();
//    }

    private void addQuestions() {
        questionsList.add(new QuestionModel("1.\tCe este un Geoparc? ",
                "a.\tun teritoriu foarte bine delimitat",
                "b.\tun parc de distrac??ii",
                "c.\tun muzeu",
                "d.\tun dinozaur ",
                1));
        questionsList.add(new QuestionModel("2.\t??n ce localitate se afl?? Casa Geoparcului? ",
                "a.\tS??npetru",
                "b.\tDensu??",
                "c.\tHa??eg",
                "d.\tPui",
                3));
        questionsList.add(new QuestionModel("3.\tCare dintre dinozaurii care au tr??it pe fosta Insul?? a Ha??egului este vedeta expozi??iei din Casa Geoparcului? ",
                "a.\tMagyarosaurus dacus",
                "b.\tBalaurul bondoc",
                "c.\tStruthiosaurus transilvanicus",
                "d.\tElopteryx nopcsai",
                2));
        questionsList.add(new QuestionModel("4.\tCu ce era acoperit corpul Balaurului bondoc?",
                "a.\tPene",
                "b.\tSolzi",
                "c.\tBlan??",
                "d.\tPiele",
                1));
        questionsList.add(new QuestionModel("5.\t??n func??ie de modul de hr??nire, Balaur bondoc era: ",
                "a.\tErbivor",
                "b.\tCarnivor",
                "c.\tOmnivor",
                "d.\tInsectivor",
                2));
        questionsList.add(new QuestionModel("6.\t??n ce perioad?? geologic?? a tr??it Balaurul bondoc? ",
                "a.\tCretacic superior",
                "b.\tJurasic superior",
                "c.\tTriasic ",
                "d.\tCretacic inferior",
                1));
        questionsList.add(new QuestionModel("7.\tDinozaurii de pe fosta Insul?? a Ha??egului au tr??it ??n urm?? cu:",
                "a.\t30 ??? 40 de milioane de ani",
                "b.\t100 -150 de ani",
                "c.\t4,6 - 4,9 miliarde de ani",
                "d.\t65 - 70 de milioane de ani",
                4));
        questionsList.add(new QuestionModel("8.\tOasele de dinozauri s-au p??strat p??n?? ??n zilele noastre prin: ",
                "a.\tmumificare",
                "b.\tfosilizare",
                "c.\tosificare",
                "d.\tsalinizare",
                2));
        questionsList.add(new QuestionModel("9.\tPe vremea dinozaurilor, Insula Ha??egului avea o clim??:",
                "a.\ttropical??",
                "b.\ttemperat??",
                "c.\tsubpolar??",
                "d.\tmediteranian??",
                1));
        questionsList.add(new QuestionModel("10.\tCum se nume??te expozi??ia din Casa Geoparcului din Ha??eg? ",
                "a.\t???Pove??ti, Legende, Dinozauri??? ",
                "b.\t???Dinozauri pitici???",
                "c.\t???Balauri, Dragoni, Dinozauri??? ",
                "d.\t???Insula Ha??eg??? ",
                3));

    }
}