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

public class QuizCasaVulcanilor extends AppCompatActivity {

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
                        Toast.makeText(QuizCasaVulcanilor.this, "Please Select an option", Toast.LENGTH_SHORT ).show();
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
            Intent intent = new Intent(QuizCasaVulcanilor.this, ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score)+ "/" + String.valueOf(questionsList.size()) );
            startActivity(intent);
            QuizCasaVulcanilor.this.finish();

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
        questionsList.add(new QuestionModel("1.\tÎn ce localitate se află Casa Vulcanilor?",
                "a.\tHațeg",
                "b.\tClopotiva",
                "c.\tGeneral Berthelot",
                "d.\tDensuș",
                4));
        questionsList.add(new QuestionModel("2.\tCa să ajungi la Casa Vulcanilor trebuie să străbați:",
                "a.\tPuntea peste Timp",
                "b.\tPuntea suspinelor",
                "c.\tTunelul călătoriei în timp",
                "d.\tPădurea fermecată",
                1));
        questionsList.add(new QuestionModel("3.\tÎn drum spre Casa Vulcanilor, descoperi niște roci cu urme de viețuitoare fosile numite: ",
                "a.\thieroglife",
                "b.\tbioglife ",
                "c.\tfosile de dinozauri",
                "d.\tcoprolite",
                2));
        questionsList.add(new QuestionModel("4.\tDin ce a fost construită Casa Vulcanilor? ",
                "a.\tlemn",
                "b.\tcob",
                "c.\tpiatră",
                "d.\tplastic",
                2));
        questionsList.add(new QuestionModel("5.\tAici este locul în care poți descoperi informații despre",
                "a.\ttradiții populare",
                "b.\tmări și oceane",
                "c.\tvulcani",
                "d.\tplante carnivore",
                3));
        questionsList.add(new QuestionModel("6.\tCine este mascota Casei Vulcanilor? ",
                "a.\tAndi Andezit",
                "b.\tGabi Granit",
                "c.\tBogdi Bazalt",
                "d.\tRobi Riolit",
                1));
        questionsList.add(new QuestionModel("7.\tLa Casa Vulcanilor au loc:",
                "a.\tture cu bicicletele",
                "b.\tactivități educaționale",
                "c.\tcompetiții sportive",
                "d.\tactivități gospodărești",
                2));
        questionsList.add(new QuestionModel("8.\tGrădina în care se află Casa Vulcanilor se numește:",
                "a.\tGrădina lui Andi Andezit",
                "b.\tGrădina din margine",
                "c.\tGrădina din nori",
                "d.\tGrădina de pe deal",
                3));
        questionsList.add(new QuestionModel("9.\tÎn grădina Casei Vulcanilor poți săpa pentru a descoperi:",
                "a.\treproduceri de plante aromate",
                "b.\tpietre de râu",
                "c.\tfosile originale",
                "d.\treproduceri de fosile",
                4));
        questionsList.add(new QuestionModel("10.\tLa construirea Casei Vulcanilor au participat:",
                "a.\televi de la liceele din Hunedoara",
                "b.\tvoluntarii pentru Geoparc",
                "c.\tstudenți",
                "d.\tturiștii din Țara Hațegului",
                2));



    }
}
