package com.example.biteright.mealDetails.view;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.biteright.R;
import com.example.biteright.mealDetails.model.MealDetailsRepositoryImpl;
import com.example.biteright.mealDetails.network.MealDetailsRemoteDataSourceImpl;
import com.example.biteright.mealDetails.presenter.MealDetailsPresenter;
import com.example.biteright.mealDetails.presenter.MealDetailsPresenterImpl;
import com.example.biteright.model.Meal;


public class RecipeFragment extends Fragment implements MealDetailsView {

    MealDetailsPresenter mealDetailsPresenter;

    public RecipeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mealDetailsPresenter = new MealDetailsPresenterImpl(this,
                MealDetailsRepositoryImpl.getInstance(MealDetailsRemoteDataSourceImpl.getInstance()));

        String mealId=RecipeFragmentArgs.fromBundle(getArguments()).getMealId();
        mealDetailsPresenter.getMealDetails(mealId);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void showMealDetails(Meal[] meals) {
        Log.i("TAG", "showMealDetails: "+meals[0].getStrArea());
        Toast.makeText(getContext(),meals[0].getStrMeal(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}