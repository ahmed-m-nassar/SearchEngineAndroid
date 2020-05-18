package com.example.search_engine.search.trends;

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

public class TrendsView extends Fragment {
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
        ////////////////////////////////////////////////////////////////////

        //getting search query from bundle
        /////////////////////////////////////////////////////////////////////
        Bundle bundle = this.getArguments();
        if(bundle != null) {
           // mTextView.setText(bundle.getString("searchQuery"));
        }
        /////////////////////////////////////////////////////////////////////
    }


}
