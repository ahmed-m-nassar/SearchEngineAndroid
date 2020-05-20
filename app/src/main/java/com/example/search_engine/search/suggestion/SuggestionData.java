package com.example.search_engine.search.suggestion;

import com.example.search_engine.utilities.JsonNamesUtilities;
import com.google.gson.annotations.SerializedName;

public class SuggestionData {
    @SerializedName(JsonNamesUtilities.SUGGEST_RESULT)
    private String mSuggestion;

    public String getmSuggestion() {
        return mSuggestion;
    }

    public void setmSuggestion(String mSuggestion) {
        this.mSuggestion = mSuggestion;
    }

    public SuggestionData(String mSuggestion) {

        this.mSuggestion = mSuggestion;
    }

}
