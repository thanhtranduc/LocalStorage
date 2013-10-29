package com.example.ContentProDemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/29/13
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */

//Creating the database class
public class TutListDatabase extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "TutListDatabase";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "tutorial_data";

    //defining the database schema
    public static final String TABLE_TUTORIALS = "tutorials";
    public static final String ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_URL = "url";
    public static final String CREATE_TABLE_TUTORIAL = "create table" + TABLE_TUTORIALS
        + "("+ ID + "integer primary key autoincrement, "+ COL_TITLE
        + "text not null, "+ COL_URL +"text not null;";
    private static final String DB_SCHEMA = CREATE_TABLE_TUTORIAL;

    public TutListDatabase(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DB_SCHEMA);//creating the database
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DEBUG_TAG,"Upgrading database. Existing contents will be lost. ["
        + oldVersion+"]->["+newVersion+"]");
        db.execSQL("DROP table if exists"+TABLE_TUTORIALS);
        onCreate(db);
    }
}
