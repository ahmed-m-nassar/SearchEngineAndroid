package com.example.search_engine.database;

import java.util.ArrayList;

public interface DatabaseServices {
    ArrayList<String>  getSuggestions();
    void insertSuggestion(String suggestion);
}
