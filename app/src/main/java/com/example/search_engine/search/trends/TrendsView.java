package com.example.search_engine.search.trends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.search_engine.R;
import com.example.search_engine.search.SearchMain;
import com.example.search_engine.search.text_search.TextSearchPresenter;
import com.example.search_engine.search.text_search.adapter.TextSearchResultsAdapter;
import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.example.search_engine.search.trends.data.TrendsData;

import java.util.ArrayList;
import java.util.List;

public class TrendsView extends Fragment implements TrendsViewPresenterContract.View{
    private Spinner             mSpinner;
    private ImageButton         mSearchButton;
    private ListView            mTrendsList;
    private ProgressBar         mProgressBar;

    private TrendsViewPresenterContract.Presenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_trends,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing private variables
        ////////////////////////////////////////////////////////////////////
        mSpinner = view.findViewById(R.id.Trends_Spinner);
        mSearchButton = view.findViewById(R.id.Trends_Search_Button);
        mTrendsList = view.findViewById(R.id.Trends_ListView);
        mProgressBar = view.findViewById(R.id.Trends_ProgressBar);

        mPresenter = new TrendsPresenter(this);
        ////////////////////////////////////////////////////////////////////


        //search button click listeners
        ///////////////////////////////////////////////////////////////////
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchButtonClicked();
            }
        });
        ///////////////////////////////////////////////////////////////////////


        //setting spinner data
        /////////////////////////////////////////////////////////////////////////
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext()
                                , R.array.countries_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        ////////////////////////////////////////////////////////////////////////
    }

    private void searchButtonClicked() {
        mPresenter.searchForResults(mSpinner.getSelectedItem().toString());
    }

    //View Interface methods
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void showSearchResults(ArrayList<TrendsData> searchResults) {


        ArrayList<String> names = new ArrayList<>();
        for(int i = 0 ; i < searchResults.size() ; i ++) {
            names.add(searchResults.get(i).getmPersonName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1,names);
        mTrendsList.setAdapter(adapter);
    }

    @Override
    public void showLoadingScreen() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTrendsList.setVisibility(View.GONE);
    }

    @Override
    public void stopLoadingScreen() {
        mProgressBar.setVisibility(View.GONE);
        mTrendsList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

}
