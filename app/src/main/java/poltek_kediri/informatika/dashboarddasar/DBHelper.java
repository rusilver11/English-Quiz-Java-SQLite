package poltek_kediri.informatika.dashboarddasar;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {

    // Initialize Database Information
    private static final String DB_NAME = "quiz.db";
    // Initialize Database version
    private static final int DB_VERSION = 1;
    // Initialize Table Name
    static final String TABLE_NAME = "leaderboard";
    // Initialize Table from(Columns)
    static final String _ID = "_id";
    static final String NAME = "Name";
    static final String SCORE = "Score";
    static final String CATEGORY = "Category";



    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, "
            + SCORE + " TEXT,"
            + CATEGORY + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
