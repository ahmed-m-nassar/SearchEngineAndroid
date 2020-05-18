package com.example.search_engine.search.text_search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.search_engine.R;
import com.example.search_engine.search.text_search.data.TextSearchResultData;

import java.util.List;

public class TextSearchResultsAdapter extends ArrayAdapter<TextSearchResultData> {

    public TextSearchResultsAdapter(@NonNull Context context, List<TextSearchResultData> textSearchResultData) {
        super(context, 0, textSearchResultData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final TextSearchResultData Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.search_text_item,parent,false);
        }

        TextView title =    ListItemView.findViewById(R.id.SearchTextItem_Title_TextView);
        TextView url =      ListItemView.findViewById(R.id.SearchTextItem_URL_TextView);
        TextView brief =    ListItemView.findViewById(R.id.SearchTextItem_Brief_TextView);

        title.setText(Item.getmTitle().toString());
        url.setText(Item.getmUrl().toString());
        brief.setText(Item.getmBrief().toString());

        return ListItemView;
    }

}
