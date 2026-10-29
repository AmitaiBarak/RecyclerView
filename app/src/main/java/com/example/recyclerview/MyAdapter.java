package com.example.recyclerview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ArrayList<Item> mData;
    public MyAdapter(ArrayList<Item> data) {
        this.mData = data;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView tvText1;
        public final TextView tvText2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvText1 = itemView.findViewById(R.id.tvText1);
            tvText2 = itemView.findViewById(R.id.tvText2);
        }
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custome_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Item item = mData.get(position);
        holder.imageView.setImageResource(R.drawable.dog);
        holder.tvText1.setText(item.getName());
        holder.tvText2.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
