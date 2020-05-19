package com.example.search_engine.search.image_search;

import com.example.search_engine.search.image_search.data.ImageSearchResultData;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;

public interface ImageSearchModelPresenterContract {

    interface Model {
        void requestSearchResults(String searchQuery , String region);
    }

    interface Presenter {
        void hideLoadingScreen();
        void showLoadingScreen();
        void showResults(ArrayList<ImageSearchResultData> results);
        void showMessage(String message);
    }
}
