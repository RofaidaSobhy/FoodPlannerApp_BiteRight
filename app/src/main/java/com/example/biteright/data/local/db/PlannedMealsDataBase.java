package com.example.biteright.data.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.biteright.model.PlannedMeal;

@Database(entities = {PlannedMeal.class}, version = 1)
public abstract class PlannedMealsDataBase extends RoomDatabase {
    private static PlannedMealsDataBase instance=null;
    public abstract PlannedMealDAO getMealsDAO();
    public static synchronized PlannedMealsDataBase getInstance(Context context){
        if(instance==null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PlannedMealsDataBase.class, "plannedmealdb").build();
        }
        return instance;
    }

}