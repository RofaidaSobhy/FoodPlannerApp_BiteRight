package com.example.biteright.planMeal.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biteright.R;
import com.example.biteright.model.PlannedMeal;

import java.util.List;

public class PlannedMealsAdapter extends RecyclerView.Adapter<PlannedMealsAdapter.ViewHolder> {
    private final Context context;

    List<PlannedMeal> plannedMeals;

    private OnPlannedMealClickListener listener;


    public PlannedMealsAdapter(Context _context, List<PlannedMeal> data, OnPlannedMealClickListener _listener){
        context=_context;
        plannedMeals=data;
        listener=_listener;

    }
    @NonNull
    @Override
    public PlannedMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.favorite_meal,parent,false);
        PlannedMealsAdapter.ViewHolder vh=new PlannedMealsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlannedMealsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.favMeal_name.setText(plannedMeals.get(position).getStrMeal());
        holder.favMeal_description.setText(plannedMeals.get(position)
                .getStrInstructions()
                .substring(0,plannedMeals.get(position).getStrInstructions().length()-1));

        holder.favMeal_area.setText(plannedMeals.get(position).getStrArea());
        Glide.with(context).load(plannedMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.dummy_food)
                        .error(R.drawable.error_food))
                .into(holder.favMeal_image);






        holder.favMeal_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnPlannedMealClick(plannedMeals.get(position));
            }
        });

        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDeleteClick(plannedMeals.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return plannedMeals.size();
    }

    public void setPlannedMeals(List<PlannedMeal> plannedMeals){
        this.plannedMeals=plannedMeals;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageButton btn_remove;
        public TextView favMeal_name;
        public TextView favMeal_description;
        public TextView favMeal_area;


        public ImageView favMeal_image;

        public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            favMeal_name = itemView.findViewById(R.id.favMeal_name);
            favMeal_description = itemView.findViewById(R.id.favMeal_description);
            favMeal_area = itemView.findViewById(R.id.favMeal_area);

            favMeal_image = itemView.findViewById(R.id.favMeal_image);
            constraintLayout=itemView.findViewById(R.id.favMeal_card);
            btn_remove=itemView.findViewById(R.id.btn_remove);
        }
    }



}
