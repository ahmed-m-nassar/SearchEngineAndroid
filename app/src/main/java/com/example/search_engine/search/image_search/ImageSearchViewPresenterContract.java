package com.example.search_engine.search.image_search;

import com.example.search_engine.search.image_search.data.ImageSearchResultData;

import java.util.ArrayList;

public interface ImageSearchViewPresenterContract {
    interface View {
        void showSearchResults(ArrayList<ImageSearchResultData> searchResults);
        void showLoadingScreen();
        void stopLoadingScreen();
        void showMessage(String message);
    }

    interface Presenter{
        void searchForResults(String query , String region);
        ArrayList<String> getUserTypedQueries();
    }
}
