package com.example.search_engine.search.image_search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.search_engine.R;
import com.example.search_engine.search.SearchMain;

public class ImageSearchView extends Fragment {

    private AutoCompleteTextView     mTextView;

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
        mTextView = view.findViewById(R.id.SearchImage_Text_TextView);
        ////////////////////////////////////////////////////////////////////

        //getting search query from bundle
        /////////////////////////////////////////////////////////////////////

        mTextView.setText(((SearchMain)getActivity()).getSearchQuery());

        /////////////////////////////////////////////////////////////////////
    }

    @Override
    public void onPause() {
        super.onPause();

        //sending search query to the main activity to send it to the other fragment about to be created
        ((SearchMain)getActivity()).setSearchQuery(mTextView.getText().toString());
    }
}
