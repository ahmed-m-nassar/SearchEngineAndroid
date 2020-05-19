package com.example.search_engine.search.text_search;

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
}

