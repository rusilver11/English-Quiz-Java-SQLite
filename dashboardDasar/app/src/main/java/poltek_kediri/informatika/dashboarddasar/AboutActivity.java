package poltek_kediri.informatika.dashboarddasar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView vwnama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        validateName2();
    }
    private void validateName2(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Intent i = getIntent();
            String iName = i.getStringExtra("mName");
            vwnama = (TextView)findViewById(R.id.textView9);
            vwnama.setText(String.format("Hi"+ iName.toUpperCase()));
        }else{
            //            Intent intent_form = new Intent(LihatActivity.this,
//                    StartActivity.class);
//            startActivity(intent_form);
        }
    }
}
