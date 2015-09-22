package com.gofu.mathchallenge;

/**
 * Created by gofu on 2014-08-25.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Scoreboard.db";
    public static final String SCOREBOARD_TABLE_NAME = "scoreboard";
    public static final String SCOREBOARD_COLUMN_NAME = "name";
    public static final String SCOREBOARD_COLUMN_CORRECT = "correct";
    public static final String SCOREBOARD_COLUMN_TOTAL = "total";
    public static final String SCOREBOARD_COLUMN_TIME = "time";
    public static final String SCOREBOARD_COLUMN_LEVEL = "level";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table scoreboard " + "(_id integer primary key, name text, correct integer, total integer, time text, level text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + SCOREBOARD_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public boolean insertScoreboard  (String name, int correct, int total, String time, String level)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("correct", correct);
        contentValues.put("total", total);
        contentValues.put("time", time);
        contentValues.put("level", level);

        db.insert("scoreboard", null, contentValues);
        return true;
    }
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from scoreboard where _id="+id, null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SCOREBOARD_TABLE_NAME);
        return numRows;
    }

    public boolean updateScoreboard (Integer id, String name, int correct, int total, String time, String level)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("correct", correct);
        contentValues.put("total", total);
        contentValues.put("time", time);
        contentValues.put("level", level);
        db.update("scoreboard", contentValues, "_id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteScoreboard (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("scoreboard",
                "_id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getAllScoreboard()
    {
    //    ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM scoreboard ORDER BY correct DESC, time ASC", null );
        return res;
    }

    public Cursor getEasyScoreboard()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM scoreboard WHERE level LIKE 'easy' ORDER BY correct DESC, time ASC", null );
        return res;
    }

    public Cursor getIntermediateScoreboard()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM scoreboard WHERE level LIKE 'intermediate' ORDER BY correct DESC, time ASC", null );
        return res;
    }

    public Cursor getHardScoreboard()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM scoreboard WHERE level LIKE 'hard' ORDER BY correct DESC, time ASC", null );
        return res;
    }
}