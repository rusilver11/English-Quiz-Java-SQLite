package poltek_kediri.informatika.dashboarddasar;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class timertest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timertest);
        count.start();
    }

    CountDownTimer count = new CountDownTimer(1000000, 1000) {
        public void onTick(long millisUntilFinished) {

            // this will decrement 100 to 1
            int seconds = (int) (millisUntilFinished / 1000) % 100;

            int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
            int hours = (int) (((millisUntilFinished / (1000 * 60)) % 60))%60;

            // this will increment 1 to 100
            int incrementSeconds = 100 - seconds;

        }

        public void onFinish() {

        }
    };


}
