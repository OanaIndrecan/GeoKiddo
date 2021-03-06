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
        questionsList.add(new QuestionModel("1.\t??n ce localitate se afl?? Casa Tradi??iilor?",
                "a.\tS??nt??m??ria Orlea",
                "b.\tS??la??u de Sus",
                "c.\tOhaba Sibi??el",
                "d.\tS??npetru",
                4));
        questionsList.add(new QuestionModel("2.\tCe este o tradi??ie? ",
                "a.\tun obicei transmis de la o genera??ie la alta",
                "b.\tun obiect pentru prins pe??te",
                "c.\tun c??ntec",
                "d.\to fotografie",
                1));

        questionsList.add(new QuestionModel("3.\tCasa Tradi??iilor spune povestea unei comunit????i multiculturale alc??tuit?? din:",
                "a.\trom??ni, italieni, maghiari ??i romi",
                "b.\tmaghiari, germani, spanioli, s??rbi",
                "c.\trom??ni, bulgari, maghiari, greci",
                "d.\ttransilv??neni, moldoveni, olteni, dobrogeni",
                1));

        questionsList.add(new QuestionModel("4.\tCare este ocupa??ia tradi??ional?? a locuitorilor din S??npetru? ",
                "a.\tgr??din??ritul",
                "b.\tcre??terea puilor de ra????",
                "c.\toieritul",
                "d.\tapicultura",
                3));

        questionsList.add(new QuestionModel("5.\tDin ce erau f??cute, pe vremuri, hainele ??i covoarele? ",
                "a.\tdin bumbac",
                "b.\tdin l??n??",
                "c.\tdin materiale reciclabile",
                "d.\tdin poliester",
                2));

        questionsList.add(new QuestionModel("6.\tFemeile din S??npetru vopseau l??na cu:",
                "a.\tcreioane colorate",
                "b.\tvopsea pe baz?? de ap??",
                "c.\tculori pe baz?? de ulei",
                "d.\tplante tinctoriale ",
                4));

        questionsList.add(new QuestionModel("7.\tLa Casa Tradi??iilor descoperi un tigl??z??u care era folosit pentru: ",
                "a.\tc??lcatul hainelor",
                "b.\taprins focul",
                "c.\tprins pe??tii",
                "d.\tob??inerea untului",
                1));

        questionsList.add(new QuestionModel("8.\tLa ce se folosea un b??d??ni? ",
                "a.\tla vopsitul l??nii",
                "b.\tla fr??m??ntatul aluatului",
                "c.\tla ob??inerea untului",
                "d.\tla hr??nitul animalelor",
                3));

        questionsList.add(new QuestionModel("9.\tLa ce se folosea tocila? ",
                "a.\tpentru ascu??it diverse obiecte",
                "b.\tpentru tocit obiectele ascu??ite",
                "c.\tla croitorie",
                "d.\tla ??esut l??na",
                1));

        questionsList.add(new QuestionModel("10.\t??n expozi??ie este o fotografie cu o personalitate a Rom??niei care a vizitat S??npetru ??i anume:",
                "a.\tMihai Eminescu",
                "b.\tNicolae Iorga",
                "c.\tIoan Slavici",
                "d.\tRegele Ferdinand",
                2));



    }
}

