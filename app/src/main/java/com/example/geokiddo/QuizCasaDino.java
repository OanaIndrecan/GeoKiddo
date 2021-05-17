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

public class QuizCasaDino extends AppCompatActivity {
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
                        Toast.makeText(QuizCasaDino.this, "Please Select an option", Toast.LENGTH_SHORT ).show();
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
            Intent intent = new Intent(QuizCasaDino.this, ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score)+ "/" + String.valueOf(questionsList.size()) );
            startActivity(intent);
            QuizCasaDino.this.finish();

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
        questionsList.add(new QuestionModel("1.\tAl câtelea punct de vizitare deschis în Geoparc a fost Casa Dinozaurilor Pitici? ",
                "a.\tultimul",
                "b.\tal doilea",
                "c.\tprimul",
                "d.\tal șaselea",
                3));
        questionsList.add(new QuestionModel("2.\tCare dintre dinozaurii descoperiți în Țara Hațegului este expus aici? ",
                "a.\tElopteryx nopcsai",
                "b.\tBalaur bondoc",
                "c.\tStruthiosaurus transilvanicus",
                "d.\tZalmoxes robustus",
                1));

        questionsList.add(new QuestionModel("3.\tElopteryx nopcsai era un dinozaur",
                "a.\terbivor",
                "b.\tcarnivor",
                "c.\tinsectivor",
                "d.\tfrugivor",
                2));

        questionsList.add(new QuestionModel("4.\tSavantul care a descoperit primele fosile de dinozauri în Țara Hațegului se numea: ",
                "a.\tFranz Nopcsa",
                "b.\tGeneralul Berthelot",
                "c.\tHenri Coandă",
                "d.\tEmil Racoviță",
                1));

        questionsList.add(new QuestionModel("5.\tÎn Casa Dinozaurilor Pitici este amenajat, în miniatură un: ",
                "a.\tcuib cu ouă de dinozaur",
                "b.\tculcuș de dinozaur ",
                "c.\tun șantier paleontologic",
                "d.\tdeal cu fosile",
                3));

        questionsList.add(new QuestionModel("6.\tCasa Dinozaurilor Pitici se află în satul Sânpetru din comuna:",
                "a.\tSântămăria Orlea",
                "b.\tDensuș",
                "c.\tRăchitova",
                "d.\tPui",
                1));

        questionsList.add(new QuestionModel("7.\tCum se numesc dinozaurii făcuți de elevii școlii din Sântămăria Orlea? ",
                "a.\tRex și T-rex",
                "b.\tZalmi și Magy",
                "c.\tDina și Dinuța",
                "d.\tDinel și Dorel",
                3));

        questionsList.add(new QuestionModel("8.\tCe era Hatzegopteryx thambema, a cărui miniatură este expusă la Casa Dinozaurilor Pitici?",
                "a.\tun mamifer ",
                "b.\tun dinozaur",
                "c.\tun ihtiozaur",
                "d.\tun pterozaur",
                4));

        questionsList.add(new QuestionModel("9.\tHatzegopteryx tambema putea ajunge la dimensiunea unui: ",
                "a.\tavion planor",
                "b.\telicopter",
                "c.\tautoturism ",
                "d.\ttractor ",
                1));

        questionsList.add(new QuestionModel("10.\tCum este considerată dispariția dinozaurilor de către oamenii de știință? ",
                "a.\to pandemie",
                "b.\to catastrofă biologică",
                "c.\to epidemie",
                "d.\tun fapt obișnuit",
                2));



    }
}

