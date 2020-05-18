package com.example.search_engine.search.text_search;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.search_engine.R;
import com.example.search_engine.search.SearchMain;
import com.example.search_engine.search.text_search.adapter.TextSearchResultsAdapter;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class TextSearchView extends Fragment implements TextSearchViewPresenterContract.View, TextSearchResultsAdapter.OnLoadMoreItemsListener {
    private ProgressBar              mProgressBar;
    private ListView                 mListView;
    private AutoCompleteTextView     mTextView;
    private ImageButton              mSearchButton;
    private ImageButton              mVoiceSearchButton;

    private TextSearchResultsAdapter mAdapter;
    private ArrayList<TextSearchResultData> mSearchResults;
    private ArrayList<TextSearchResultData> mPaginatedSearchResults;
    private int mResults;

    private TextSearchPresenter      mPresenter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_text,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        ////////////////////////////////////////////////////////////////////
        mProgressBar = view.findViewById(R.id.SearchText_ProgressBar);
        mListView = view.findViewById(R.id.SearchText_ListView);
        mTextView = view.findViewById(R.id.SearchText_Text_TextView);
        mSearchButton = view.findViewById(R.id.SearchText_Search_Button);
        mVoiceSearchButton = view.findViewById(R.id.SearchText_Voice_Button);

        mPresenter = new TextSearchPresenter(this);
        //////////////////////////////////////////////////////////////////////

        //getting search query from bundle
        /////////////////////////////////////////////////////////////////////
        mTextView.setText(((SearchMain)getActivity()).getSearchQuery());
        /////////////////////////////////////////////////////////////////////


        //search button click listeners
        ///////////////////////////////////////////////////////////////////
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchButtonClicked();
            }
        });
        ///////////////////////////////////////////////////////////////////////

        //search by voice button click listeners
        ///////////////////////////////////////////////////////////////////
        mVoiceSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByVoiceButtonClicked();
            }
        });
        ///////////////////////////////////////////////////////////////////////



    }

    //View Interface methods
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void showSearchResults(ArrayList<TextSearchResultData> searchResults) {
        mSearchResults = searchResults;

        mPaginatedSearchResults = new ArrayList<>();
        int iterations = mSearchResults.size();
        if(iterations > 10)
            iterations = 10;
        mResults = 10;
        for(int i = 0 ; i < iterations ; i++) {
            mPaginatedSearchResults.add(mSearchResults.get(i));
        }


        mAdapter = new TextSearchResultsAdapter(getContext() , mPaginatedSearchResults , this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void showLoadingScreen() {
        mProgressBar.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
    }

    @Override
    public void stopLoadingScreen() {
        mProgressBar.setVisibility(View.GONE);
        mListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////


    //Utility functions
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    private void searchButtonClicked() {
        mPresenter.searchForResults(mTextView.getText().toString());
    }

    private void searchByVoiceButtonClicked() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        startActivityForResult(intent,10);

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onPause() {
        super.onPause();

        //sending search query to the main activity to send it to the other fragment about to be created
        ((SearchMain)getActivity()).setSearchQuery(mTextView.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if(resultCode == RESULT_OK && data !=null ) {
                    ArrayList<String> voiceText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mTextView.setText(voiceText.get(0));
                }
                break;
        }
    }

    public void displayMoreSearchResults() {
        if(mSearchResults.size() > mResults && mSearchResults.size() > 0) {
            int iterations ;
            if(mSearchResults.size() > (mResults + 10)) {
                iterations = 10;
            } else {
                iterations = mSearchResults.size() - mResults;
            }

            //adding the new search results
            for (int i = mResults ; i < mResults + iterations ; i++) {
                mPaginatedSearchResults.add(mSearchResults.get(i));
            }
            mResults += iterations;
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMoreItems() {
        displayMoreSearchResults();
    }
}
