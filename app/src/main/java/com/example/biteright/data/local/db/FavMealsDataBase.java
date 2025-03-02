package com.example.biteright.data.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.biteright.model.Meal;

@Database(entities = {Meal.class}, version = 1)
public abstract class FavMealsDataBase extends RoomDatabase {
    private static FavMealsDataBase instance=null;
    public abstract FavMealDAO getMealsDAO();
    public static synchronized FavMealsDataBase getInstance(Context context){
        if(instance==null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FavMealsDataBase.class, "favmealdb").build();
        }
        return instance;
    }

}
