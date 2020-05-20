package com.example.search_engine.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class DatabaseServicesImpl extends DBHelper implements DatabaseServices {
    public DatabaseServicesImpl() {
        super(MyApp.getAppContext());
    }

    @Override
    public ArrayList<String> getSuggestions() {
        String query="Select * From "+ SearchContract.SearchEntry.Table_Name;
        String[] empt = {};
        Cursor cursor=getReadableDatabase().rawQuery(query,empt);

        ArrayList<String> suggestions = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String suggestion = cursor.getString(cursor.getColumnIndex(SearchContract.SearchEntry.Column_Suggestion));
            suggestions.add(suggestion);
        }

        return  suggestions;
    }

    @Override
    public void insertSuggestion(String suggestion) {
        ContentValues contentvalues= new ContentValues();
        contentvalues.put(SearchContract.SearchEntry.Column_Suggestion,suggestion);

        insert(SearchContract.SearchEntry.Table_Name,contentvalues);
    }
}
