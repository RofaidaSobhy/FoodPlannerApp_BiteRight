package com.example.biteright.search.view.area;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biteright.R;
import com.example.biteright.data.models.POJO.Area;

import java.util.List;

public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.ViewHolder> {
    private final Context context;

    List<Area> areas;

    private OnAreaClickListener listener;


    public AreasAdapter(Context _context, List<Area> data, OnAreaClickListener _listener){
        context=_context;
        areas=data;
        listener=_listener;


    }
    @NonNull
    @Override
    public AreasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.area_layout,parent,false);
        AreasAdapter.ViewHolder vh=new AreasAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AreasAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        holder.area_name.setText(areas.get(position).getStrArea());





        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnAreaClick(areas.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "getItemCount: "+areas.size());
        return areas.size();
    }

    public void setAreas(List<Area> areas){
        this.areas=areas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView area_name;
        public ConstraintLayout constraintLayout;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            area_name = itemView.findViewById(R.id.area_name);
            constraintLayout=itemView.findViewById(R.id.constraintLayout_area);
        }
    }



}