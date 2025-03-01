package com.example.biteright.data.local.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.biteright.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
@Dao
public interface PlannedMealDAO {
    @Query("SELECT * FROM plannedmeal WHERE plannedData = :date")
    Observable<List<PlannedMeal>> getAllPlannedMeals(String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlannedMeal(PlannedMeal plannedMeal);
    @Delete
    Completable deletePlannedMeal(PlannedMeal plannedMeal);
}
