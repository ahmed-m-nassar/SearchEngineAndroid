package com.example.search_engine.search.trends.data;

import com.example.search_engine.utilities.JsonNamesUtilities;
import com.google.gson.annotations.SerializedName;

public class TrendsData {
    @SerializedName(JsonNamesUtilities.TRENDS_RESULT_PERSON_NAME)
    private String mPersonName;
    @SerializedName(JsonNamesUtilities.TRENDS_RESULT_SEARCH_COUNT)
    private int mCount;

    public TrendsData(String mPersonName, int mCount) {
        this.mPersonName = mPersonName;
        this.mCount = mCount;
    }

    public String getmPersonName() {
        return mPersonName;
    }

    public void setmPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }
}
