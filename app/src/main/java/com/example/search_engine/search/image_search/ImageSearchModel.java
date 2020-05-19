package com.example.search_engine.search.image_search;

import com.example.search_engine.search.image_search.data.ImageSearchResultData;
import com.example.search_engine.search.text_search.TextSearchModel;
import com.example.search_engine.search.text_search.TextSearchModelPresenterContract;
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

public class ImageSearchModel implements ImageSearchModelPresenterContract.Model {

    public interface ImageSearchCloudApi {

        @GET(EndPointUtilities.SEARCH_IMAGE)
        Call<List<ImageSearchResultData>> getSearchResults(
                @Query(EndPointUtilities.SEARCH_QUERY_PARAMETER) String searchQuery ,
                @Query(EndPointUtilities.REGION_PARAMETER) String region
        );
    }

    private ImageSearchModelPresenterContract.Presenter    mPresenter;

    public ImageSearchModel(ImageSearchModelPresenterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestSearchResults(String searchQuery , String region) {
        mPresenter.showLoadingScreen();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPointUtilities.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageSearchCloudApi imageSearchCloudApi = (ImageSearchCloudApi) retrofit.create(ImageSearchCloudApi.class);
        Call<List<ImageSearchResultData>> call = imageSearchCloudApi.getSearchResults(searchQuery , region);
        call.enqueue(new Callback<List<ImageSearchResultData>>() {
            @Override
            public void onResponse(Call<List<ImageSearchResultData>> call, Response<List<ImageSearchResultData>> response) {
                if (!response.isSuccessful()) {
                    mPresenter.hideLoadingScreen();
                    mPresenter.showResults(new ArrayList<ImageSearchResultData>());
                    mPresenter.showMessage("Something went wrong");
                    return;
                }
                List<ImageSearchResultData> results = response.body();
                mPresenter.hideLoadingScreen();
                mPresenter.showResults((ArrayList<ImageSearchResultData>) results);

            }

            @Override
            public void onFailure(Call<List<ImageSearchResultData>> call, Throwable t) {
                mPresenter.hideLoadingScreen();
                mPresenter.showResults(new ArrayList<ImageSearchResultData>());
                mPresenter.showMessage("Something went wrong");
            }
        });
    }
}
