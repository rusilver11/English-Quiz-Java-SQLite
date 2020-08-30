package poltek_kediri.informatika.dashboarddasar.dir_kuis;


import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import poltek_kediri.informatika.dashboarddasar.R;


public class AwalActivity extends AppCompatActivity {
private TextView countdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
    }

    public void startQuiz(View view){
        final String mnama = getIntent().getStringExtra("mName");
        int quizCategory = 0;
        switch (view.getId()){
            case R.id.asia:
                quizCategory = 1;
                Intent intent = new Intent(getApplicationContext(), Quiz1.class);
                intent.putExtra("QUIZ_CATEGORY", quizCategory);
                intent.putExtra("mName",mnama);
                startActivity(intent);
                break;
            case R.id.america:
                quizCategory = 2;
                Intent intent2 = new Intent(getApplicationContext(), Quiz2.class);
                intent2.putExtra("QUIZ_CATEGORY", quizCategory);
                intent2.putExtra("mName",mnama);
                startActivity(intent2);
                break;
            case R.id.europe:
                quizCategory = 3;
                Intent intent3 = new Intent(getApplicationContext(), Quiz3.class);
                intent3.putExtra("QUIZ_CATEGORY", quizCategory);
                intent3.putExtra("mName",mnama);
                startActivity(intent3);
                break;
            case R.id.asia2:
                quizCategory = 1;
                Intent intent4 = new Intent(getApplicationContext(),PQuiz1.class);
                intent4.putExtra("QUIZ_CATEGORY", quizCategory);
                intent4.putExtra("mName",mnama);
                startActivity(intent4);
                break;
            case R.id.america2:
                quizCategory = 2;
                Intent intent5 = new Intent(getApplicationContext(), PQuiz2.class);
                intent5.putExtra("QUIZ_CATEGORY", quizCategory);
                intent5.putExtra("mName",mnama);
                startActivity(intent5);
                break;
            case R.id.europe2:
                quizCategory = 3;
                Intent intent6 = new Intent(getApplicationContext(), PQuiz3.class);
                intent6.putExtra("QUIZ_CATEGORY", quizCategory);
                intent6.putExtra("mName",mnama);
                startActivity(intent6);
                break;
        }

    }

}

