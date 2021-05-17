package com.example.geokiddo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizCasaTraditiilor extends AppCompatActivity {

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
                        Toast.makeText(QuizCasaTraditiilor.this, "Please Select an option", Toast.LENGTH_SHORT ).show();
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
            Intent intent = new Intent(QuizCasaTraditiilor.this, ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score)+ "/" + String.valueOf(questionsList.size()) );
            startActivity(intent);
            QuizCasaTraditiilor.this.finish();

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
        questionsList.add(new QuestionModel("1.\tÎn ce localitate se află Casa Tradițiilor?",
                "a.\tSântămăria Orlea",
                "b.\tSălașu de Sus",
                "c.\tOhaba Sibișel",
                "d.\tSânpetru",
                4));
        questionsList.add(new QuestionModel("2.\tCe este o tradiție? ",
                "a.\tun obicei transmis de la o generație la alta",
                "b.\tun obiect pentru prins pește",
                "c.\tun cântec",
                "d.\to fotografie",
                1));

        questionsList.add(new QuestionModel("3.\tCasa Tradițiilor spune povestea unei comunități multiculturale alcătuită din:",
                "a.\tromâni, italieni, maghiari și romi",
                "b.\tmaghiari, germani, spanioli, sârbi",
                "c.\tromâni, bulgari, maghiari, greci",
                "d.\ttransilvăneni, moldoveni, olteni, dobrogeni",
                1));

        questionsList.add(new QuestionModel("4.\tCare este ocupația tradițională a locuitorilor din Sînpetru? ",
                "a.\tgrădinăritul",
                "b.\tcreșterea puilor de rață",
                "c.\toieritul",
                "d.\tapicultura",
                3));

        questionsList.add(new QuestionModel("5.\tDin ce erau făcute, pe vremuri, hainele și covoarele? ",
                "a.\tdin bumbac",
                "b.\tdin lână",
                "c.\tdin materiale reciclabile",
                "d.\tdin poliester",
                2));

        questionsList.add(new QuestionModel("6.\tFemeile din Sânpetru vopseau lâna cu:",
                "a.\tcreioane colorate",
                "b.\tvopsea pe bază de apă",
                "c.\tculori pe bază de ulei",
                "d.\tplante tinctoriale ",
                4));

        questionsList.add(new QuestionModel("7.\tLa Casa Tradițiilor descoperi un tiglăzău care era folosit pentru: ",
                "a.\tcălcatul hainelor",
                "b.\taprins focul",
                "c.\tprins peștii",
                "d.\tobținerea untului",
                1));

        questionsList.add(new QuestionModel("8.\tLa ce se folosea un bădâni? ",
                "a.\tla vopsitul lânii",
                "b.\tla frământatul aluatului",
                "c.\tla obținerea untului",
                "d.\tla hrănitul animalelor",
                3));

        questionsList.add(new QuestionModel("9.\tLa ce se folosea tocila? ",
                "a.\tpentru ascuțit diverse obiecte",
                "b.\tpentru tocit obiectele ascuțite",
                "c.\tla croitorie",
                "d.\tla țesut lâna",
                1));

        questionsList.add(new QuestionModel("10.\tÎn expoziție este o fotografie cu o personalitate a României care a vizitat Sânpetru și anume:",
                "a.\tMihai Eminescu",
                "b.\tNicolae Iorga",
                "c.\tIoan Slavici",
                "d.\tRegele Ferdinand",
                2));



    }
}

