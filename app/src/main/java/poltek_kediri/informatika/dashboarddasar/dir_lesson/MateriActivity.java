package poltek_kediri.informatika.dashboarddasar.dir_lesson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import poltek_kediri.informatika.dashboarddasar.MainActivity;
import poltek_kediri.informatika.dashboarddasar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MateriActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        Button btn1 = (Button) findViewById(R.id.openpdf1);
        Button btn2 = (Button) findViewById(R.id.openpdf2);
        Button btn3 = (Button) findViewById(R.id.openpdf3);
//        final ViewPDF VPDF = new ViewPDF();

        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent ViewPDF = new Intent(MateriActivity.this, ViewPDF.class);
                startActivity(ViewPDF);

//                VPDF.Pdfview1();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent ViewPDF = new Intent(MateriActivity.this, ViewPDF2.class);
                startActivity(ViewPDF);

//                VPDF.Pdfview1();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent ViewPDF = new Intent(MateriActivity.this, ViewPDF3.class);
                startActivity(ViewPDF);

//                VPDF.Pdfview1();
            }
        });

    }
    public  void returnMenu2(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);}
}
//    public void ViewPDF1(View view){
//        startActivity(new Intent(MainActivity.this,ViewPDF.class));
//    }



