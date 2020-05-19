package com.example.search_engine.search.trends;

import com.example.search_engine.search.text_search.TextSearchModel;
import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.example.search_engine.search.trends.data.TrendsData;
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

public class TrendsModel implements TrendsModelPresenterContract.Model {

    public interface TrendsSearchCloudApi {

        @GET(EndPointUtilities.SEARCH_TRENDS)
        Call<List<TrendsData>> getSearchResults(
                @Query(EndPointUtilities.REGION_PARAMETER) String region
        );
    }
    private TrendsModelPresenterContract.Presenter mPresenter;

    public TrendsModel(TrendsModelPresenterContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void requestSearchResults( String region) {
        mPresenter.showLoadingScreen();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPointUtilities.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrendsSearchCloudApi trendsSearchCloudApi = retrofit.create(TrendsSearchCloudApi.class);
        Call<List<TrendsData>> call = trendsSearchCloudApi.getSearchResults(region);
        call.enqueue(new Callback<List<TrendsData>>() {
            @Override
            public void onResponse(Call<List<TrendsData>> call, Response<List<TrendsData>> response) {
                if (!response.isSuccessful()) {
                    mPresenter.hideLoadingScreen();
                    mPresenter.showResults(new ArrayList<TrendsData>());
                    mPresenter.showMessage("Something went wrong");
                    return;
                }
                List<TrendsData> results = response.body();
                mPresenter.hideLoadingScreen();
                mPresenter.showResults((ArrayList<TrendsData>) results);

            }
            @Override
            public void onFailure(Call<List<TrendsData>> call, Throwable t) {
                mPresenter.hideLoadingScreen();
                mPresenter.showResults(new ArrayList<TrendsData>());
                mPresenter.showMessage("Something went wrong");
            }
        });
    }
}
