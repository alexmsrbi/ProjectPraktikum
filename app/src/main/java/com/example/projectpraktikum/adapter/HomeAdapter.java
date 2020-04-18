package com.example.projectpraktikum.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectpraktikum.R;
import com.example.projectpraktikum.model.home.HomeResult;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private static final String TAG = "HomeAdapter";
    private static String TRENDING_URL = "https://media.tenor.com/image/";
    private ArrayList<HomeResult> homeResults = new ArrayList<>();
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<HomeResult> item){
        homeResults.clear();
        homeResults.addAll(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder : called");
        Glide.with(context)
                .load(homeResults.get(position).getMedia().get(0).getGif().getUrl())
                .into(holder.ivImage);
        holder.tvTitle.setText(homeResults.get(position).getTitle());
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onCLick: clicked on "+homeResults.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImage;
        TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            this.ivImage = view.findViewById(R.id.ivIcon);
            this.tvTitle = view.findViewById(R.id.tvJudul);
        }
    }
}
