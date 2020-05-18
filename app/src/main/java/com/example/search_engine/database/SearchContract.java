package com.example.search_engine.database;

import android.provider.BaseColumns;

public class SearchContract {

    private SearchContract(){}

    public static class SearchEntry implements BaseColumns {
        public static final String Table_Name = "SEARCH_SUGGESTIONS";
        public static final String Column_Suggestion = "SUGGESTION";
    }

}
