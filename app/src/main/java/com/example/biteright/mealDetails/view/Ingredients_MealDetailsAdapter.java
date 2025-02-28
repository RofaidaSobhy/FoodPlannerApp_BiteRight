package com.example.biteright.mealDetails.view;


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
import com.example.biteright.model.Details_Ingredient;

import java.util.List;

public class Ingredients_MealDetailsAdapter extends RecyclerView.Adapter<Ingredients_MealDetailsAdapter.ViewHolder> {
    private final Context context;

    List<Details_Ingredient> ingredient;



    public Ingredients_MealDetailsAdapter(Context _context, List<Details_Ingredient> data){
        context=_context;
        ingredient=data;

    }

    public void setList(List<Details_Ingredient> updateIngredients){
        this.ingredient = updateIngredients;
    }
    @NonNull
    @Override
    public Ingredients_MealDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.ingredient,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Log.i("TAG", "onBindViewHolder: "+ingredient.get(position).getName());

        Log.i("TAG", "onBindViewHolder: "+ingredient.get(position).getMeasure());

        holder.details_ingredient_name.setText(ingredient.get(position).getName());
        holder.details_ingredient_measure.setText(ingredient.get(position).getMeasure());


        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.get(position).getImage()+".png")
                .apply(new RequestOptions()
                        .override(120, 120)
                        .placeholder(R.drawable.dummy_food)
                        .error(R.drawable.error_food)
                        .centerCrop())
                .into(holder.details_ingredient_image);

    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView details_ingredient_name;
        public TextView details_ingredient_measure;

        public ImageView details_ingredient_image;;

        public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;

            details_ingredient_name = itemView.findViewById(R.id.details_ingredient_name);
            details_ingredient_measure = itemView.findViewById(R.id.details_ingredient_measure);
            details_ingredient_image= itemView.findViewById(R.id.details_ingredient_image);

            constraintLayout=itemView.findViewById(R.id.ingredient);
        }
    }



}
