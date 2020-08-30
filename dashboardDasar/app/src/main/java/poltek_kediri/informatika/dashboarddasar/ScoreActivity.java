package poltek_kediri.informatika.dashboarddasar;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {


    private ScoreDB db;

    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{DBHelper._ID, DBHelper.NAME, DBHelper.SCORE, DBHelper.CATEGORY};
    final int[] to = new int[]{R.id.id, R.id.listName, R.id.listScore, R.id.listCategory};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        listView = (ListView) findViewById(R.id.myListView);
        TextView tvtotalscore = (TextView) findViewById(R.id.tvtotal);
        db = new ScoreDB(this);
        db.open();
        Cursor cursor = db.fetch();

        adapter = new SimpleCursorAdapter(this, R.layout.row_list_adapter, cursor, from, to, 0);
        listView.setAdapter(adapter);

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore",0);

        tvtotalscore.setText("TOTAL SCORE : "+ totalScore);
    }
}
