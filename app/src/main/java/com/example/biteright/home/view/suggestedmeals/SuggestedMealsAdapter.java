package com.example.biteright.home.view.suggestedmeals;

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
import com.example.biteright.model.Meal;

public class SuggestedMealsAdapter extends RecyclerView.Adapter<SuggestedMealsAdapter.ViewHolder> {
    private static final String Tag = "RecycerView";
    private final Context context;

    Meal[] meals;

    private OnMealClickListener listener;


    public SuggestedMealsAdapter(Context _context, Meal[] data, OnMealClickListener _listener){
        context=_context;
        meals=data;
        listener=_listener;

    }

    public void setList(Meal[] updateMeals){
        this.meals= updateMeals;
    }
    @NonNull
    @Override
    public SuggestedMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.meal,parent,false);
        ViewHolder vh=new ViewHolder(v);
        Log.i(Tag, "onCreateViewHolder: ");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.meal_name.setText(meals[position].getStrMeal());
        holder.meal_description.setText(meals[position].getStrInstructions().substring(0,25)+"...");
        holder.meal_area.setText(meals[position].getStrArea());


        Glide.with(context).load(meals[position].getStrMealThumb())
                .apply(new RequestOptions()
                    .override(150, 160)
                    .placeholder(R.drawable.dummy_food)
                    .error(R.drawable.error_food)
                    .centerCrop())
                .into(holder.meal_image);








        holder.meal_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnMealClick(meals[position]);
            }
        });
        Log.i(Tag, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return meals.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView meal_name;
        public TextView meal_description;
        public TextView meal_area;

        public ImageView meal_image;
        public ConstraintLayout meal_card;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            meal_name = itemView.findViewById(R.id.meal_name);
            meal_description=itemView.findViewById(R.id.meal_description);
            meal_area=itemView.findViewById(R.id.meal_area);
            meal_image=itemView.findViewById(R.id.meal_image);
            meal_card=itemView.findViewById(R.id.meal_card);
        }
    }



}
