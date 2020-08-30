package poltek_kediri.informatika.dashboarddasar.dir_kuis;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import poltek_kediri.informatika.dashboarddasar.R;
import poltek_kediri.informatika.dashboarddasar.ScoreDB;
import poltek_kediri.informatika.dashboarddasar.dir_lesson.MateriActivity;

public class QuizActivity extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView countLabel;
    private TextView countAnswer;
    private TextView questionLabel;
    private TextView answerBtn1;
    private TextView answerBtn2;
    private TextView answerBtn3;
    private TextView answerBtn4;
    int timeValue = 20;
    CountDownTimer countDownTimer;
    ScoreDB db;

    private TextView tvCountDown;
//    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ColorStateList textColorDefaultCd;

    private boolean answered;

    private String alertTitle;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;


    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            //{"Country","Right Answer","Choice1","Choice2","Choice3"}
            {"Long", "Panjang", "Pendek", "Luas", "Rendah"},
            {"Strong", "Kuat", "Lemah", "Tebal", "Kasar"},
            {"Big", "Besar", "Kecil", "Rendah", "Lemah"},
            {"Land", "Daratan", "Laut", "Batu", "Gunung"},
            {"Sea", "Laut", "Darat", "Salju", "Rumput"},
            {"Dangerous", "Berbahaya", "Berlebihan", "Berkabut", "Bersamaan"},
            {"Have/Has", "Memiliki", "Memilih", "Memelihara", "Meriam"},
            {"Jungle", "Hutan", "Laut", "Gunung", "Daratan"},
            {"Live", "Tinggal", "Hidup", "Teman", "Tinggi"},
            {"Rainy", "Hujan", "Dingin", "Salju", "Panas"},
            {"Cold", "Dingin", "Panas", "Salju", "Keren"},
            {"Share", "Berbagi", "Pembagian", "Seperti", "Terbagi"},
            {"Laugh", "Tertawa", "Menangis", "Benci", "Keras"},
            {"Often", "Sering", "Pernah", "Banyak", "Oven"}


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        countLabel = (TextView)findViewById(R.id.countLabel);
        tvCountDown = findViewById(R.id.tvCountDown);
        countAnswer = (TextView)findViewById(R.id.countAnswer);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        answerBtn4 = (Button)findViewById(R.id.answerBtn4);

        textColorDefaultCd = tvCountDown.getTextColors();

        //receive quizCategory from StartActivity
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        Log.v("CATEGORY_TAG", quizCategory + "");

        //Create quizArray from quizData
        for (int i = 0; i < quizData.length; i++){

            //prepare array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Country
            tmpArray.add(quizData[i][1]); //Rigth Answer
            tmpArray.add(quizData[i][2]); //Choice1
            tmpArray.add(quizData[i][3]); //Choice2
            tmpArray.add(quizData[i][4]); //Choice3

            //add tmpArray to quizArray
            quizArray.add(tmpArray);
        }
        if (!answered) {
//            startCountDown();
            countdowntimer();

            showNextQuiz();
        } else {
//            updateCountDownText();
//            checkAnswer();
        }
    }

//    private void startCountDown() {
//        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//
//            @Override
//            public void onFinish() {
//                timeLeftInMillis = 0;
//                updateCountDownText();
////                checkAnswer();
//            }
//        }.start();
//    }
//
//    private void updateCountDownText() {
//        int minutes = (int) (timeLeftInMillis / 1000) / 60;
//        int seconds = (int) (timeLeftInMillis / 1000) % 60;
//
//        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//
//        tvCountDown.setText(timeFormatted);
//
//        if (timeLeftI
// nMillis < 10000) {
//            tvCountDown.setTextColor(Color.RED);
//        } else {
//            tvCountDown.setTextColor(textColorDefaultCd);
//        }
//    }
/////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {

        moveTaskToBack(false);
    }

    public void  countdowntimer() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String mnama = getIntent().getStringExtra("mName");
        builder.setMessage("Time UP");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                intent.putExtra("mName",mnama);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);

        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
                tvCountDown.setText(String.valueOf(timeValue) + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

            }
            //Now user is out of time

            public void onFinish() {
                //We will navigate him to the result activity using below method
                builder.show();
            }
        };

    }

    public  void showNextQuiz(){
        //update quizCountLabel
        countLabel.setText("5/" + quizCount);


        //generate random number beetwen 0 and 14 (quizArray's size - 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //set question and right answer
        // Array Format : {"Country","Right Answer","Choice1","Choice2","Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        //remove "Country" from quiz and suffle choices
        quiz.remove(0);
        Collections.shuffle(quiz);

        //set Choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        //remove this quiz from quizArray
        quizArray.remove(randomNum);
        answered = false;

        timeValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();

//        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
//        startCountDown();
    }
    public void checkAnswer(View view) {
        answered = true;

        // get pushed button
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle ;

        if (btnText.equals(rightAnswer)){
            final String mnama = getIntent().getStringExtra("mName");
            //correct
            alertTitle = "Correct";
            rightAnswerCount++;
            countAnswer.setText("Score: "+rightAnswerCount);
            //CREATE DIALOG
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(alertTitle);
            builder.setMessage("Answer : " + rightAnswer);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (quizCount == QUIZ_COUNT){
                        //show result
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                        intent.putExtra("mName",mnama);
                        startActivity(intent);

                    } else {
                        quizCount++;
                        showNextQuiz();
                    }

                }
            });
            builder.setCancelable(false);
            builder.show();
        } else {
            //wrong
            alertTitle = "Wrong...";

            //go to result
//            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
//            startActivity(intent);

            //CREATE DIALOG
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(alertTitle);
            builder.setMessage("Increase Your Intelegence");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (rightAnswerCount <= 2){
                        //show result
                        Intent intent = new Intent(getApplicationContext(),MateriActivity.class);
                        startActivity(intent);

                    } else {
                        final String mnama = getIntent().getStringExtra("mName");
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                        intent.putExtra("mName",mnama);
                        startActivity(intent);
                    }

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String mnama = getIntent().getStringExtra("mName");

                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                        intent.putExtra("mName",mnama);
                        startActivity(intent);


                }
            });
            builder.setCancelable(false);
            builder.show();
        }



    }


}
