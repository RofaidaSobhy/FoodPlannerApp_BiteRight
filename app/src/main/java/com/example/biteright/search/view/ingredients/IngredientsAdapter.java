package com.example.biteright.search.view.ingredients;

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
import com.example.biteright.data.models.POJO.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private final Context context;

    List<Ingredient> ingredients;

    private OnIngredientClickListener listener;


    public IngredientsAdapter(Context _context, List<Ingredient> data, OnIngredientClickListener _listener){
        context=_context;
        ingredients=data;
        listener=_listener;


    }
    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.ingredient_layout,parent,false);
        IngredientsAdapter.ViewHolder vh=new IngredientsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        holder.ingredient_name.setText(ingredients.get(position).getStrIngredient());


        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredients.get(position).getStrIngredient()+".png")
                .apply(new RequestOptions()
                        .placeholder(R.drawable.dummy_food)
                        .error(R.drawable.error_food))
                .into(holder.ingredient_image);


        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnIngredientClick(ingredients.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setIngredients(List<Ingredient> ingredients){
        this.ingredients=ingredients;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ingredient_name;
        public ImageView ingredient_image;

        public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_image = itemView.findViewById(R.id.ingredient_image);
            constraintLayout=itemView.findViewById(R.id.constrainLayout_ingredient);

        }
    }



}