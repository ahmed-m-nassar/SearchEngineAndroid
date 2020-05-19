package com.example.search_engine.search.image_search.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.search_engine.R;
import com.example.search_engine.search.image_search.data.ImageSearchResultData;
import com.example.search_engine.search.text_search.data.TextSearchResultData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageSearchResultsAdapter extends ArrayAdapter<ImageSearchResultData> {
    String TAG = "TextSearchResultsAdapter";
    public interface OnLoadMoreItemsListener{
        void onLoadMoreItems();
    }
    private OnLoadMoreItemsListener mOnLoadMoreItemsListener;


    public ImageSearchResultsAdapter(@NonNull Context context, List<ImageSearchResultData> imageSearchResultData
            , OnLoadMoreItemsListener onLoadMoreItemsListener) {
        super(context, 0, imageSearchResultData);
        mOnLoadMoreItemsListener = onLoadMoreItemsListener;
    }

    private boolean reachedEndOfList(int position){
        return position == getCount() - 1;
    }
    private void loadMoreData() {
        try {
            mOnLoadMoreItemsListener.onLoadMoreItems();
        } catch (NullPointerException e) {
            Log.d(TAG, "loadMoreData:Null ptr exc " + e.getMessage());
        }

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ImageSearchResultData Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.search_image_item,parent,false);
        }

        ImageView image = ListItemView.findViewById(R.id.SearchImageItem_Image);
        TextView caption = ListItemView.findViewById(R.id.SearchImageItem_Caption);

        Picasso.get().load(Item.getmImageURL()).into(image);
        caption.setText(Item.getmImageDescription());

        if(reachedEndOfList(position)) {
            loadMoreData();
        }
        return ListItemView;
    }

}
