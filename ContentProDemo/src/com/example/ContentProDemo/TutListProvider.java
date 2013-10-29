package com.example.ContentProDemo;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: thanhtd
 * Date: 10/29/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */

//Creating the Content Provider class
public class TutListProvider extends ContentProvider{

    private TutListDatabase mDB;
    private static final String AUTHORITY = "com.mamlambo.tutorial.tutlist.data.TutListProvider";
    public static final int TUTORIALS = 100;
    public static final int TUTORIAL_ID = 110;
    private static final String TUTORIALS_BASE_PATH = "tutorials";
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+TUTORIALS_BASE_PATH)
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+"/mt-tutorial";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +"/mt-tutorial";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static
    {
        sURIMatcher.addURI(AUTHORITY,TUTORIALS_BASE_PATH,TUTORIALS);
        sURIMatcher.addURI(AUTHORITY,TUTORIALS_BASE_PATH+"/#",TUTORIAL_ID);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables();
    }

    @Override
    public boolean onCreate()
    {
        mDB = new TutListDatabase(getContext());
        return true;
    }
}
