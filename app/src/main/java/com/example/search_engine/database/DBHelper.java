package com.example.search_engine.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String Database_Name = "StudyStream.db";
    private static final int Database_Version = 1;

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;
        final String SQL_CREATE_USER_ENTRY = "CREATE TABLE " + SearchContract.SearchEntry.Table_Name
                + "( " + SearchContract.SearchEntry.Column_Suggestion + " TEXT PRIMARY KEY  "
                + ")";
        db.execSQL(SQL_CREATE_USER_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ SearchContract.SearchEntry.Table_Name);
        onCreate(db);
    }

    public Cursor Select(String query, String Argument[])
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cu= db.rawQuery(query, Argument);
        return cu;

    }
    public Boolean insert(String tablename, ContentValues content)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Long success= db.insert(tablename,null,content);
        return success!=-1;
    }
    public Boolean update(String tablename,ContentValues contentValues,String Argument[],String whreclause)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int NumberOfRowAffected= db.update(tablename, contentValues, whreclause, Argument);
        return NumberOfRowAffected!=0;
    }
    public Boolean deleteWithoutCascade(String tablename,String Argument[],String whereclause)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(tablename,whereclause,Argument)>0;
    }

}
