package com.example.search_engine.search.text_search;

import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;

public interface TextSearchViewPresenterContract {
    interface View {
        void showSearchResults(ArrayList<TextSearchResultData> searchResults);
        void showLoadingScreen();
        void stopLoadingScreen();
        void showMessage(String message);
    }

    interface Presenter{
        void searchForResults(String query , String region) ;
    }
}
