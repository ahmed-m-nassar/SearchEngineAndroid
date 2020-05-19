package com.example.search_engine.search.trends;

import com.example.search_engine.database.DatabaseServices;
import com.example.search_engine.database.DatabaseServicesImpl;
import com.example.search_engine.search.text_search.TextSearchModel;
import com.example.search_engine.search.text_search.TextSearchModelPresenterContract;
import com.example.search_engine.search.text_search.TextSearchViewPresenterContract;
import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.example.search_engine.search.trends.data.TrendsData;

import java.util.ArrayList;

public class TrendsPresenter implements TrendsModelPresenterContract.Presenter , TrendsViewPresenterContract.Presenter {
    private TrendsViewPresenterContract.View   mView;
    private DatabaseServices mDataBaseServices;
    private TrendsModelPresenterContract.Model mModel;

    public TrendsPresenter(TrendsViewPresenterContract.View view) {
        mView = view;
        mDataBaseServices = new DatabaseServicesImpl();
        mModel = new TrendsModel(this);
    }


    @Override
    public void searchForResults(String region) {
        mModel.requestSearchResults(region);

        //For test purpose
        ////////////////////////////////////////////////////////
        //ArrayList<TrendsData> results = new ArrayList<>();
        //results.add(new TrendsData("Ahmed" , 10));
        //results.add(new TrendsData("ta7a" , 10));
        //results.add(new TrendsData("mokhtar" , 10));
        //results.add(new TrendsData("sofyan" , 10));
        //results.add(new TrendsData("khaled" , 10));
        //results.add(new TrendsData("waleed" , 10));
        //results.add(new TrendsData("geddo" , 10));
        //results.add(new TrendsData("ayman" , 10));
        //results.add(new TrendsData("shady" , 10));
        //results.add(new TrendsData("nassar" , 10));
//
        //mView.stopLoadingScreen();
        //mView.showSearchResults(results);
        ////////////////////////////////////////////////////////
    }


    @Override
    public void showLoadingScreen() {
        mView.showLoadingScreen();
    }

    @Override
    public void showResults(ArrayList<TrendsData> results) {
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
