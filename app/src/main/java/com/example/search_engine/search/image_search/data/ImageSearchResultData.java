package com.example.search_engine.search.image_search.data;

import com.example.search_engine.utilities.JsonNamesUtilities;
import com.google.gson.annotations.SerializedName;

public class ImageSearchResultData {
    @SerializedName(JsonNamesUtilities.IMAGE_RESULT_URL)
    private String mImageURL;
    @SerializedName(JsonNamesUtilities.IMAGE_RESULT_CAPTION)
    private  String mImageDescription;

    public ImageSearchResultData(String mImageURL, String mImageDescription) {
        this.mImageURL = mImageURL;
        this.mImageDescription = mImageDescription;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

    public String getmImageDescription() {
        return mImageDescription;
    }

    public void setmImageDescription(String mImageDescription) {
        this.mImageDescription = mImageDescription;
    }
}
