package com.example.search_engine.search.trends;

import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.example.search_engine.search.trends.data.TrendsData;

import java.util.ArrayList;

public interface TrendsViewPresenterContract {
    interface View {
        void showSearchResults(ArrayList<TrendsData> searchResults);
        void showLoadingScreen();
        void stopLoadingScreen();
        void showMessage(String message);
    }

    interface Presenter{
        void searchForResults(String region) ;
    }
}
