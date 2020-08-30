package poltek_kediri.informatika.dashboarddasar.dir_kuis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import poltek_kediri.informatika.dashboarddasar.MainActivity;
import poltek_kediri.informatika.dashboarddasar.R;
import poltek_kediri.informatika.dashboarddasar.ScoreDB;

public class ResultActivity extends AppCompatActivity {
    ScoreDB db;
 private TextView  vwnama;
    private  TextView CategoryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Our database helper class
        db = new ScoreDB(this);
        //Make db writable
        db.open();
        validateName();

//        TextView resultLabel = (TextView) findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView) findViewById(R.id.totalScoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.ScoreLabel);
        CategoryLabel = (TextView) findViewById(R.id.CategoryLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        String nama = getIntent().getStringExtra("mName");
        String category = getIntent().getStringExtra("CATEGORY_TAG");

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore",0);

        totalScore += score;

//        resultLabel.setText(score + " / 5");
        totalScoreLabel.setText("Total Score : "+ totalScore);
        highScoreLabel.setText("High Score : "+score);
        CategoryLabel.setText(category);

        //insert into db score
        int scores = score;
        String core = String.valueOf(scores);
        db.insert(nama,core,category);
        //update total score
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalScore", totalScore);
        editor.commit();

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    public  void returnTop(View view){
        String mnama = getIntent().getStringExtra("mName");
        String category = getIntent().getStringExtra("CATEGORY_TAG");
        Intent intent = new Intent(getApplicationContext(),AwalActivity.class);
        intent.putExtra("mName",mnama);
        intent.putExtra("CATEGORY_TAG",category);
        startActivity(intent);
    }
    public  void returnMenu(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    private void validateName(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Intent i = getIntent();
            String iName = i.getStringExtra("mName");
            vwnama = (TextView)findViewById(R.id.textView8);
            vwnama.setText(String.format(iName.toUpperCase()));
        }else{
            //            Intent intent_form = new Intent(LihatActivity.this,
//                    StartActivity.class);
//            startActivity(intent_form);
        }
    }

}


