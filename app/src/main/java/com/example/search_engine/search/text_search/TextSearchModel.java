package com.example.search_engine.search.text_search;

import com.example.search_engine.search.suggestion.SuggestionData;
import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.example.search_engine.utilities.EndPointUtilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TextSearchModel implements TextSearchModelPresenterContract.Model {
    public interface TextSearchCloudApi {

        @GET(EndPointUtilities.SEARCH_TEXT)
        Call<List<TextSearchResultData>> getSearchResults(
                @Query(EndPointUtilities.SEARCH_QUERY_PARAMETER) String searchQuery ,
                @Query(EndPointUtilities.REGION_PARAMETER) String region
        );

        @GET(EndPointUtilities.SEARCH_SUGGESTIONS)
        Call<List<SuggestionData>> getSuggestions(
                @Query(EndPointUtilities.SEARCH_QUERY_PARAMETER) String searchQuery
        );
    }

    private TextSearchModelPresenterContract.Presenter    mPresenter;

    public TextSearchModel(TextSearchModelPresenterContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void requestSearchResults(String searchQuery , String region) {
        mPresenter.showLoadingScreen();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPointUtilities.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TextSearchCloudApi textSearchCloudApi = retrofit.create(TextSearchCloudApi.class);
        Call<List<TextSearchResultData>> call = textSearchCloudApi.getSearchResults(searchQuery , region);
        call.enqueue(new Callback<List<TextSearchResultData>>() {
            @Override
            public void onResponse(Call<List<TextSearchResultData>> call, Response<List<TextSearchResultData>> response) {
                if (!response.isSuccessful()) {
                    mPresenter.hideLoadingScreen();
                    mPresenter.showResults(new ArrayList<TextSearchResultData>());
                    mPresenter.showMessage("Something went wrong");
                    return;
                }
                List<TextSearchResultData> results = response.body();
                mPresenter.hideLoadingScreen();
                mPresenter.showResults((ArrayList<TextSearchResultData>) results);

            }
            @Override
            public void onFailure(Call<List<TextSearchResultData>> call, Throwable t) {
                mPresenter.hideLoadingScreen();
                mPresenter.showResults(new ArrayList<TextSearchResultData>());
                mPresenter.showMessage("Something went wrong");
            }
        });
    }

    @Override
    public void requestSearchSuggestions(String searchQuery) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPointUtilities.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TextSearchModel.TextSearchCloudApi textSearchCloudApi = retrofit.create(TextSearchModel.TextSearchCloudApi.class);
        Call<List<SuggestionData>> call = textSearchCloudApi.getSuggestions(searchQuery);
        call.enqueue(new Callback<List<SuggestionData>>() {
            @Override
            public void onResponse(Call<List<SuggestionData>> call, Response<List<SuggestionData>> response) {
                if (!response.isSuccessful()) {
                    mPresenter.showSuggestions( new ArrayList<SuggestionData>());
                    return;
                }
                List<SuggestionData> results = response.body();
                mPresenter.showSuggestions( new ArrayList<SuggestionData>(results));

            }
            @Override
            public void onFailure(Call<List<SuggestionData>> call, Throwable t) {
                mPresenter.showSuggestions( new ArrayList<SuggestionData>());
            }
        });
    }
}

