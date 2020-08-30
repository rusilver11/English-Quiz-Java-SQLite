package poltek_kediri.informatika.dashboarddasar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import poltek_kediri.informatika.dashboarddasar.dir_kuis.AwalActivity;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }
    public  void lihat(View view){

        EditText mNameView = (EditText)findViewById(R.id.txName);
        String mName = mNameView.getText().toString().trim();

        if (mName.equals("")){
            //If empty string, make a toast message
            String enter_name = getString(R.string.text_masukin_nama);
            Toast.makeText(FormActivity.this,
                    enter_name,
                    Toast.LENGTH_SHORT).show();
        }else{
            //Go to quiz

            goToQuiz(mName);
        }
    }
    private void goToQuiz(String mname) {
        Intent intent_main = new Intent(FormActivity.this, AwalActivity.class);
        intent_main.putExtra("mName", mname);
        startActivity(intent_main);
    }

}
