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

        //for test purpose
        ///////////////////////////////////////////////////////////////////////
       //ArrayList<ImageSearchResultData> resultData = new ArrayList<>();
       //resultData.add(new ImageSearchResultData("https://www.encodedna.com/images/theme/angularjs.png" ,
       //        "Angular"));

       //resultData.add(new ImageSearchResultData("https://picsum.photos/id/237/200/300.jpg" ,
       //        "dog"));

       //resultData.add(new ImageSearchResultData("https://i.picsum.photos/id/1001/200/300.jpg" ,
       //        "beach"));

       //mView.stopLoadingScreen();
       //mView.showSearchResults(resultData);
        //////////////////////////////////////////////////////////////////////
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
