package com.kingsley.payoneercheckout.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kingsley.payoneercheckout.R;
import com.kingsley.payoneercheckout.data.model.Result;

import java.util.List;


public class ResultAdapter  extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    private List<Result> listItems;

    public void setListDataItems(List<Result> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ResultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_result_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.MyViewHolder holder, int position) {
        holder.title.setText(listItems.get(position).getName());
        Glide.with(holder.imageView)
                .load(listItems.get(position).getLinks().getLogo())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(listItems == null)
            return 0;
        else
            return listItems.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.optionImage);
            title = itemView.findViewById(R.id.optionName);
        }
    }
}