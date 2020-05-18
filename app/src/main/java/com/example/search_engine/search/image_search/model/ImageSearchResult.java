package com.example.search_engine.search.image_search.model;

public class ImageSearchResult {
    private String mImageURL;
    private  String mImageDescription;

    public ImageSearchResult(String mImageURL, String mImageDescription) {
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
