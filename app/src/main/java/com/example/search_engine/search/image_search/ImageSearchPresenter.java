package com.example.search_engine.search.image_search;

import com.example.search_engine.database.DatabaseServices;
import com.example.search_engine.database.DatabaseServicesImpl;
import com.example.search_engine.search.image_search.data.ImageSearchResultData;
import com.example.search_engine.search.suggestion.SuggestionData;
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
        mDataBaseServices.insertSuggestion(query);
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
        try {
            mView.showLoadingScreen();
        } catch (Exception e) {

        }
    }

    @Override
    public void showResults(ArrayList<ImageSearchResultData> results) {
        try{
            mView.showSearchResults(results);
        } catch (Exception e) {

        }

    }

    @Override
    public void showMessage(String message) {
        try {
            mView.showMessage(message);
        } catch (Exception e) {

        }
    }

    @Override
    public void hideLoadingScreen() {
        try {
            mView.stopLoadingScreen();
        } catch (Exception e) {

        }

    }

    @Override
    public ArrayList<String> getUserTypedQueries() {
        return mDataBaseServices.getSuggestions();
    }

    @Override
    public void showSuggestions(ArrayList<SuggestionData> suggestions) {
        ArrayList<String> suggestionsList = new ArrayList<>();
        for(int i = 0 ; i < suggestions.size() ; i++) {
            suggestionsList.add(suggestions.get(i).getmSuggestion()) ;
        }
        try{
            mView.showSuggestions(suggestionsList);
        } catch (Exception e) {
        }

    }
    @Override
    public void searchForQuerySuggestions(String query) {
        mModel.requestSearchSuggestions(query);

        //for test purpose
        //////////////////////////////////////////////////////////
        //ArrayList<String> suggestions =new ArrayList<String> ();
        //suggestions.add("Egypt is beautiful");
        //suggestions.add("Egypt is good");
        //suggestions.add("Egypt is awesome");
        //mView.showSuggestions(suggestions);
        ///////////////////////////////////////////////////////////
    }
}
