package com.example.w4d2_as1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    ArrayList<BookItem> dataset;

    public BookAdapter(ArrayList<BookItem> results){
        this.dataset = results;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView coverIV;
        public TextView titleTV;
        public TextView authorTV;


        public ViewHolder(View itemView) {
            super(itemView);
            coverIV = itemView.findViewById(R.id.coverIV);
            titleTV = itemView.findViewById(R.id.titleTV);
            authorTV = itemView.findViewById(R.id.authorTV);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookItem bookItem = dataset.get(position);
        holder.titleTV.setText(bookItem.getTitle());
        holder.authorTV.setText(bookItem.getAuthors());
        Glide.with(holder.coverIV.getContext())
                .load(bookItem.getCoverURL())
                .into(holder.coverIV);

    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 :dataset.size();
    }
}
