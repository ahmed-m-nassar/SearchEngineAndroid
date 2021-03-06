package com.example.search_engine.search.text_search;

import com.example.search_engine.database.DatabaseServices;
import com.example.search_engine.database.DatabaseServicesImpl;
import com.example.search_engine.search.suggestion.SuggestionData;
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
    public void searchForResults(String query , String region) {
        mDataBaseServices.insertSuggestion(query);
        mModel.requestSearchResults(query , region);

        //for testing purpose
        /////////////////////////////////////////////////////////////////////
        //ArrayList<TextSearchResultData> results = new ArrayList<>();
        //results.add(new TextSearchResultData("reddit:the front page on the internet",
        //        "Reddit is a network of communities based on people's interests Find communities you're interested in," +
        //                " and become part of an online community",
        //        "www.reddit.com"));
//
        //results.add(new TextSearchResultData("Reddit: Home page",
        //        "he Conversation Starts Here. Reddit is home to thousands of communities, endless" +
        //                " conversation, and authentic hu",
        //        "www.redditinc.com"));
//
        //results.add(new TextSearchResultData("Reddit: Wikipedia",
        //        "Reddit is an American social news aggregation, web content rating, and discussion website." +
        //                " Registered members submit content to the site such as links, text ...",
        //        "www.wikipedia.com"));
//
        //results.add(new TextSearchResultData("Reddit: Apps on google play",
        //        "What is Reddit? Reddit is where topics or ideas are arranged in communities. Start off with what you like and go from there. " +
        //                "There are 100K active ones to ...",
        //        "play.google.com"));
//
//
        //mView.stopLoadingScreen();
        //mView.showSearchResults(results);
        /////////////////////////////////////////////////////////////////////
    }


    @Override
    public void showLoadingScreen() {
        try {
            mView.showLoadingScreen();
        } catch (Exception e) {

        }
    }

    @Override
    public void showResults(ArrayList<TextSearchResultData> results) {
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
