package com.example.search_engine.search.image_search;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.search_engine.R;
import com.example.search_engine.search.SearchMain;
import com.example.search_engine.search.image_search.adapter.ImageSearchResultsAdapter;
import com.example.search_engine.search.image_search.data.ImageSearchResultData;
import com.example.search_engine.search.text_search.TextSearchPresenter;
import com.example.search_engine.search.text_search.adapter.TextSearchResultsAdapter;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class ImageSearchView extends Fragment implements ImageSearchViewPresenterContract.View , ImageSearchResultsAdapter.OnLoadMoreItemsListener {

    private ProgressBar mProgressBar;
    private ListView mListView;
    private AutoCompleteTextView mTextView;
    private ImageButton mSearchButton;
    private ImageButton mVoiceSearchButton;

    private ImageSearchResultsAdapter mAdapter;
    private ArrayList<ImageSearchResultData> mSearchResults;
    private ArrayList<ImageSearchResultData> mPaginatedSearchResults;
    private int mResults;

    private ImageSearchPresenter mPresenter;

    private int ACCESS_COARSE_LOCATION_CODE = 100;
    private int ACCESS_FINE_LOCATION_CODE = 101;
    private int ACCESS_NETWORK_STATE_CODE = 102;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_image,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        ////////////////////////////////////////////////////////////////////
        mProgressBar = view.findViewById(R.id.SearchImage_ProgressBar);
        mListView = view.findViewById(R.id.SearchImage_ListView);
        mTextView = view.findViewById(R.id.SearchImage_Text_TextView);
        mSearchButton = view.findViewById(R.id.SearchImage_Search_Button);
        mVoiceSearchButton = view.findViewById(R.id.SearchImage_Voice_Button);

        mPresenter = new ImageSearchPresenter(this);
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

    @Override
    public void onPause() {
        super.onPause();

        //sending search query to the main activity to send it to the other fragment about to be created
        ((SearchMain)getActivity()).setSearchQuery(mTextView.getText().toString());
    }

    @Override
    public void showSearchResults(ArrayList<ImageSearchResultData> searchResults) {
        mSearchResults = searchResults;

        mPaginatedSearchResults = new ArrayList<>();
        int iterations = mSearchResults.size();
        if (iterations > 10)
            iterations = 10;
        mResults = 10;
        for (int i = 0; i < iterations; i++) {
            mPaginatedSearchResults.add(mSearchResults.get(i));
        }


        mAdapter = new ImageSearchResultsAdapter(getContext(), mPaginatedSearchResults, this);
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

    //Utility functions
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    private void searchButtonClicked() {
        String country = getCurrentLocation();
        if(country == null)
            return;

        mPresenter.searchForResults(mTextView.getText().toString() , country);
    }

    private void searchByVoiceButtonClicked() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        startActivityForResult(intent, 10);

    }

    private void requestLocationPermissions() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, ACCESS_COARSE_LOCATION_CODE);
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE);
        requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, ACCESS_NETWORK_STATE_CODE);
    }

    private String getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getContext(), "Permission not granted", Toast.LENGTH_SHORT).show();
            requestLocationPermissions();
            return null;
        }
        Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location network = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        double latitude;
        double logitude;
        Location finalLoc = null;
        if(gpsLocation != null) {
            finalLoc = gpsLocation;
            latitude = gpsLocation.getLatitude();
            logitude = gpsLocation.getLongitude();
        } else if (network != null) {
            finalLoc = network;
            latitude = network.getLatitude();
            logitude = network.getLongitude();
        } else {
            latitude = 0.0;
            logitude =0.0;
        }

        try {
            Geocoder geocoder = new Geocoder(getContext() , Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude,logitude,1);
            if(addresses != null && addresses.size() > 0) {
                String country = addresses.get(0).getCountryName();
                return country;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
}
