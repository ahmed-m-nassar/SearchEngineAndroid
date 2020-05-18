package com.example.search_engine.search.text_search.data;

import com.example.search_engine.utilities.EndPointUtilities;
import com.example.search_engine.utilities.JsonNamesUtilities;
import com.google.gson.annotations.SerializedName;

public class TextSearchResultData {
    @SerializedName(JsonNamesUtilities.SEARCH_TEXT_RESULT_TITLE)
    private String mTitle;
    @SerializedName(JsonNamesUtilities.SEARCH_TEXT_RESULT_BRIEF)
    private String mBrief;
    @SerializedName(JsonNamesUtilities.SEARCH_TEXT_RESULT_URL)
    private String mUrl;

    public TextSearchResultData(String mTitle, String mBrief, String mUrl) {
        this.mTitle = mTitle;
        this.mBrief = mBrief;
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmBrief() {
        return mBrief;
    }

    public void setmBrief(String mBrief) {
        this.mBrief = mBrief;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
