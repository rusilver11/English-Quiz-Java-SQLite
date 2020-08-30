package poltek_kediri.informatika.dashboarddasar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ScoreDB  {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public ScoreDB(Context c) {
        this.context = c;
    }

    public ScoreDB open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String score, String category) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.NAME, name);
        contentValues.put(dbHelper.SCORE, score);
        contentValues.put(dbHelper.CATEGORY, category);
        database.insert(dbHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[]{dbHelper._ID, dbHelper.NAME, dbHelper.SCORE,dbHelper.CATEGORY};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
