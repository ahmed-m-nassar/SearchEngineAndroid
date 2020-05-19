package com.example.search_engine.search.image_search;

import com.example.search_engine.database.DatabaseServices;
import com.example.search_engine.database.DatabaseServicesImpl;
import com.example.search_engine.search.image_search.data.ImageSearchResultData;
import com.example.search_engine.search.text_search.TextSearchModel;
import com.example.search_engine.search.text_search.TextSearchModelPresenterContract;
import com.example.search_engine.search.text_search.TextSearchViewPresenterContract;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;

public class ImageSearchPresenter implements ImageSearchModelPresenterContract.Presenter , ImageSearchViewPresenterContract.Presenter {

    private ImageSearchViewPresenterContract.View   mView;
    private DatabaseServices mDataBaseServices;
    private ImageSearchModelPresenterContract.Model mModel;


    public ImageSearchPresenter(ImageSearchViewPresenterContract.View mView) {
        this.mView = mView;
        mDataBaseServices = new DatabaseServicesImpl();
        mModel = new ImageSearchModel(this);
    }

    @Override
    public void searchForResults(String query , String region) {
        mModel.requestSearchResults(query , region);
    }

    @Override
    public void showLoadingScreen() {
        mView.showLoadingScreen();
    }

    @Override
    public void showResults(ArrayList<ImageSearchResultData> results) {
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
