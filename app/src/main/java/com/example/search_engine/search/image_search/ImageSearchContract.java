package com.example.search_engine.search.image_search;

import com.example.search_engine.search.image_search.model.ImageSearchResult;

import java.util.ArrayList;

public interface ImageSearchContract {
    interface View {
        void showSearchResults(ArrayList<ImageSearchResult> searchResults);
        void showLoadingScreen();
        void stopLoadingScreen();
        void showMessage(String message);
    }

    interface Presenter{
        void searchForResults(String query);
    }
}
