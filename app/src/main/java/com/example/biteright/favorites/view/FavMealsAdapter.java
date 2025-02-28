package com.example.biteright.favorites.view;

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
import com.example.biteright.model.Meal;

import java.util.List;

public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsAdapter.ViewHolder> {
    private static final String Tag = "RecycerViewFav";
    private final Context context;

    List<Meal> meals;

    private OnFavoriteClickListener listener;


    public FavMealsAdapter(Context _context, List<Meal> data, OnFavoriteClickListener _listener){
        context=_context;
        meals=data;
        listener=_listener;

    }
    @NonNull
    @Override
    public FavMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.favorite_meal,parent,false);
        FavMealsAdapter.ViewHolder vh=new FavMealsAdapter.ViewHolder(v);
        Log.i(Tag, "onCreateViewHolder: ");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavMealsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.favMeal_name.setText(meals.get(position).getStrMeal());
        holder.favMeal_description.setText(meals.get(position)
                .getStrInstructions()
                .substring(0,meals.get(position).getStrInstructions().length()-1));

        holder.favMeal_area.setText(meals.get(position).getStrArea());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.favMeal_image);






        holder.favMeal_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnFavoriteClick(meals.get(position));
            }
        });

        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDeleteClick(meals.get(position));
            }
        });
        Log.i(Tag, "onBindViewHolder: ");
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
