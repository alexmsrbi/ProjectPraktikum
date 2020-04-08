package com.example.projectpraktikum;

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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {
    private static final String TAG = "AppAdapter";
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> gambar =  new ArrayList<>();
    private Context context;

    public AppAdapter(ArrayList<String> name, ArrayList<String> gambar, Context context) {
        this.name = name;
        this.gambar = gambar;
        this.context = context;
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
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);
        Glide.with(context)
                .load(gambar.get(position))
                .apply(requestOptions)
                .into(holder.ivImage);
        holder.tvTitle.setText(name.get(position));
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onCLick: clicked on "+name.get(position));
                Toast.makeText(context,name.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gambar.size();
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
