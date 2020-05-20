package com.example.search_engine.search.text_search;

import com.example.search_engine.search.suggestion.SuggestionData;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;

public interface TextSearchModelPresenterContract {
    interface Model {
        void requestSearchResults(String searchQuery , String region);
        void requestSearchSuggestions(String searchQuery);
    }

    interface Presenter {
        void hideLoadingScreen();
        void showLoadingScreen();
        void showResults(ArrayList<TextSearchResultData> results);
        void showSuggestions(ArrayList<SuggestionData> suggestions);
        void showMessage(String message);
    }
}
