package com.example.search_engine.search.text_search;

import com.example.search_engine.database.DatabaseServices;
import com.example.search_engine.database.DatabaseServicesImpl;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;

public class TextSearchPresenter implements TextSearchViewPresenterContract.Presenter , TextSearchModelPresenterContract.Presenter {

    private TextSearchViewPresenterContract.View   mView;
    private DatabaseServices                       mDataBaseServices;
    private TextSearchModelPresenterContract.Model mModel;

    public TextSearchPresenter(TextSearchViewPresenterContract.View view) {
        mView = view;
        mDataBaseServices = new DatabaseServicesImpl();
        mModel = new TextSearchModel(this);
    }

    @Override
    public void searchForResults(String query) {
        mModel.requestSearchResults();
    }


    @Override
    public void showLoadingScreen() {
        mView.showLoadingScreen();
    }

    @Override
    public void showResults(ArrayList<TextSearchResultData> results) {
        mView.showSearchResults(results);
    }

    @Override
    public void showMessage(String message) {
        mView.showMessage(message);
    }

    @Override
    public void hideLoadingScreen() {
        mView.stopLoadingScreen();
    }
}
