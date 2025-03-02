package com.example.biteright.search.view;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.biteright.model.Meal;

import java.util.List;

public class SearchingAdapter extends RecyclerView.Adapter<SearchingAdapter.ViewHolder> {
    private final Context context;

    List<Meal> meals;

    private OnSearchMealClickListener listener;


    public SearchingAdapter(Context _context, List<Meal> data, OnSearchMealClickListener _listener){
        context=_context;
        meals=data;
        listener=_listener;

    }
    @NonNull
    @Override
    public SearchingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.meal,parent,false);
        SearchingAdapter.ViewHolder vh=new SearchingAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.meal_name.setText(meals.get(position).getStrMeal());
        holder.meal_description.setText(meals.get(position)
                .getStrInstructions()
                .substring(0,meals.get(position).getStrInstructions().length()-1));

        holder.meal_area.setText(meals.get(position).getStrArea());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.dummy_food)
                        .error(R.drawable.error_food))
                .into(holder.meal_image);






        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnMealClick(meals.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setMeals(List<Meal> meals){
        this.meals=meals;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView meal_name;
        public TextView meal_description;
        public TextView meal_area;


        public ImageView meal_image;

        public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
           meal_name = itemView.findViewById(R.id.meal_name);
            meal_description= itemView.findViewById(R.id.meal_description);
            meal_area = itemView.findViewById(R.id.meal_area);

            meal_image = itemView.findViewById(R.id.meal_image);
            constraintLayout=itemView.findViewById(R.id.meal_card);
        }
    }



}
