package com.example.projectpraktikum.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectpraktikum.R;
import com.example.projectpraktikum.model.search.SearchResultsItem;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchResultsItem> searchItems = new ArrayList<>();

    public SearchAdapter(Context context) {
        this.context = context;
    }
    public void setData(ArrayList<SearchResultsItem> items){
        searchItems.clear();
        searchItems.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder : called");
        Glide.with(context)
                .load(searchItems.get(position).getMedia().get(0).getGif().getUrl())
                .into(holder.ivSearch);
        holder.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onCLick: clicked on "+searchItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivSearch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivSearch = itemView.findViewById(R.id.ivIconSearch);

        }
    }
}
