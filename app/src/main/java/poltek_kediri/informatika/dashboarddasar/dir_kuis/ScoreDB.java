package poltek_kediri.informatika.dashboarddasar.dir_kuis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ScoreDB extends SQLiteOpenHelper  {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "leaderboard_db";


    public ScoreDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create  table
        db.execSQL(ScoreModel.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ScoreModel.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
//////////////INSERT//////////
    public long insertNote(String name, int score) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id`  will be inserted automatically.
        // no need to add them
        values.put(ScoreModel.COLUMN_NAME, name);
        values.put(ScoreModel.COLUMN_SCORE, score);

        // insert row
        long id = db.insert(ScoreModel.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
/////////VIEW//////////
    public ScoreModel getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ScoreModel.TABLE_NAME,
                new String[]{ScoreModel.COLUMN_ID, ScoreModel.COLUMN_NAME, ScoreModel.COLUMN_SCORE},
                ScoreModel.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        ScoreModel note = new ScoreModel(
                cursor.getInt(cursor.getColumnIndex(ScoreModel.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ScoreModel.COLUMN_NAME)),
                cursor.getInt(cursor.getColumnIndex(ScoreModel.COLUMN_SCORE)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<ScoreModel> getAllNotes() {
        List<ScoreModel> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + ScoreModel.TABLE_NAME + " ORDER BY " +
                ScoreModel.COLUMN_SCORE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ScoreModel note = new ScoreModel();
                note.setId(cursor.getInt(cursor.getColumnIndex(ScoreModel.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(ScoreModel.COLUMN_NAME)));
                note.setScore(cursor.getInt(cursor.getColumnIndex(ScoreModel.COLUMN_SCORE)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + ScoreModel.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

}
