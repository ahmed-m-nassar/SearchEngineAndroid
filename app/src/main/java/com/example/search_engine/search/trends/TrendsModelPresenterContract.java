package com.example.search_engine.search.trends;

import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.example.search_engine.search.trends.data.TrendsData;

import java.util.ArrayList;

public interface TrendsModelPresenterContract {
    interface Model {
        void requestSearchResults(String region);
    }

    interface Presenter {
        void hideLoadingScreen();
        void showLoadingScreen();
        void showResults(ArrayList<TrendsData> results);
        void showMessage(String message);
    }
}
