package com.example.search_engine.search.text_search;

import com.example.search_engine.search.suggestion.SuggestionData;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;

public interface TextSearchViewPresenterContract {
    interface View {
        void showSearchResults(ArrayList<TextSearchResultData> searchResults);
        void showLoadingScreen();
        void stopLoadingScreen();
        void showMessage(String message);
        void showSuggestions(ArrayList<String> suggestions);
    }

    interface Presenter{
        void searchForResults(String query , String region) ;
        ArrayList<String> getUserTypedQueries();
        void searchForQuerySuggestions(String query);
    }
}
