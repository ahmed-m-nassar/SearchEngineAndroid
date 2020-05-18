package com.example.search_engine.search;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.search_engine.R;
import com.example.search_engine.search.image_search.ImageSearchView;
import com.example.search_engine.search.text_search.TextSearchView;
import com.example.search_engine.search.trends.TrendsView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SearchMain extends AppCompatActivity {
    private String               mSearchQuery;
    private FragmentManager mFragmentManager;
    private BottomNavigationView mBottomNav;

    private Bundle                mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        //getting extras
        Intent intent = getIntent();

        //fragment manager
        mFragmentManager = getSupportFragmentManager();

        //setting bottom navigation
        mBottomNav = findViewById(R.id.SearchMain_Navigation_View);



        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            Fragment fragment = new TextSearchView();

            getSupportFragmentManager().beginTransaction().replace(R.id.SearchMain_Container_FragmentLayout,
                    fragment).commit();
        }

    mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.SearchMainMenu_TextSearch_MenuItem:
                selectedFragment = new TextSearchView();
                break;
            case R.id.SearchMainMenu_ImageSearch_MenuItem:
                selectedFragment = new ImageSearchView();
                break;
            case R.id.SearchMainMenu_Trends_MenuItem:
                selectedFragment = new TrendsView();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.SearchMain_Container_FragmentLayout,
                selectedFragment).commit();

        return true;
    }
    });
    }

    public void setSearchQuery(String searchQuery) {
        mSearchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return mSearchQuery;
    }



}
