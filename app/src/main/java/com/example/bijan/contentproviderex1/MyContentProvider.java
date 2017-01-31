package com.example.bijan.contentproviderex1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private MyHelper myHelper;

    //URI - TO - TABLE MAPPING LOGIC
    private static UriMatcher uriMatcher = new UriMatcher(-1);
    static {
        uriMatcher.addURI("com.techpalle.com", "studenttable", 1);
    }
    //end mapping logic

    public class MyHelper extends SQLiteOpenHelper{

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table student(_id integer primary key, sname text, ssub text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case 1:
                //means client is asking to insert into student table
                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                sqLiteDatabase.insert("student", null, values);
                break;
            case 2:
                //means invalid table
                break;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        myHelper = new MyHelper(getContext(), "techpalle.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder){
        switch (uriMatcher.match(uri)){
            case 1:
                //means client is requesting to read from student table
                Cursor cursor = null;
                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                cursor = sqLiteDatabase.query("student", null, null, null, null, null, null);
                return cursor;
            default:
                //means invalid table name
                break;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs){
        return 0;
        }
}
