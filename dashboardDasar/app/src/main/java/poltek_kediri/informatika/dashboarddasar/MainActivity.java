package poltek_kediri.informatika.dashboarddasar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;


import poltek_kediri.informatika.dashboarddasar.dir_kuis.AwalActivity;
import poltek_kediri.informatika.dashboarddasar.dir_lesson.MateriActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardQuiz,cardMateri,cardScore,cardHelp;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardQuiz = (CardView) findViewById(R.id.cvQuiz);
        cardQuiz.setOnClickListener(this);
        cardMateri = (CardView) findViewById(R.id.cvMateri);
        cardMateri.setOnClickListener(this);
        cardScore = (CardView) findViewById(R.id.cvScore);
        cardScore.setOnClickListener(this);
        cardHelp = (CardView) findViewById(R.id.cvHelp);
        cardHelp.setOnClickListener(this);
        cardHelp = (CardView) findViewById(R.id.cvAbout);
        cardHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()){
            case R.id.cvQuiz : i = new Intent(this,FormActivity.class);startActivity(i);break;
            case R.id.cvMateri : i = new Intent(this,MateriActivity.class);startActivity(i);break;
            case R.id.cvScore : i = new Intent(this,ScoreActivity.class);startActivity(i);break;
            case R.id.cvHelp : i = new Intent(this,HelpActivity.class);startActivity(i);break;
            case R.id.cvAbout : i = new Intent(this,AboutActivity.class);startActivity(i);break;
            default:break;
        }

    }

}
