package com.example.search_engine.search.text_search.retrofit_api;

import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.example.search_engine.utilities.EndPointUtilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TextSearchCloudApi {

    @GET(EndPointUtilities.SEARCH_TEXT)
    Call<List<TextSearchResultData>> getSearchResults();
}
