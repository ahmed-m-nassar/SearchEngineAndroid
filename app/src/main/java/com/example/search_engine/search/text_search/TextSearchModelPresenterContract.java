package com.example.search_engine.search.text_search;

import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;

public interface TextSearchModelPresenterContract {
    interface Model {
        void requestSearchResults();
    }

    interface Presenter {
        void hideLoadingScreen();
        void showLoadingScreen();
        void showResults(ArrayList<TextSearchResultData> results);
        void showMessage(String message);
    }
}
