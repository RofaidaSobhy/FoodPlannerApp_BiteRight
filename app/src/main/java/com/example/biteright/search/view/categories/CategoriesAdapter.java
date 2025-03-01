package com.example.biteright.search.view.categories;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biteright.R;
import com.example.biteright.data.models.POJO.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private final Context context;

    List<Category> categories;

    private OnCategoryClickListener listener;


    public CategoriesAdapter(Context _context, List<Category> data, OnCategoryClickListener _listener){
        context=_context;
        categories=data;
        listener=_listener;
        Log.i("TAG", "Adapter: constructor");


    }
    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_layout,parent,false);
        CategoriesAdapter.ViewHolder vh=new CategoriesAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Log.i("TAG", "showCategories Adapter: "+categories.get(position).getStrCategory());

        holder.category_name.setText(categories.get(position).getStrCategory());


        Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.dummy_food)
                        .error(R.drawable.error_food))
                .into(holder.category_image);


        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnCategoryClick(categories.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categories){
        Log.i("TAG", "setCategories: "+categories.get(0).getStrCategory());
        this.categories=categories;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView category_name;
        public ImageView category_image;

        public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            category_name = itemView.findViewById(R.id.category_name);
            category_image = itemView.findViewById(R.id.category_image);
            constraintLayout=itemView.findViewById(R.id.constrainLayout_category);

        }
    }



}