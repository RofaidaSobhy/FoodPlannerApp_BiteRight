package com.example.biteright.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biteright.R;
import com.example.biteright.home.model.suggestedmeals.SuggestedMealsRepositoryImpl;
import com.example.biteright.home.network.suggestedmeals.SuggestedMealsRemoteDataSourceImpl;
import com.example.biteright.home.presenter.randommeal.RandomMealPresenter;
import com.example.biteright.home.presenter.randommeal.RandomMealPresenterImpl;
import com.example.biteright.home.presenter.suggestedmeals.SuggestedMealsPresenter;
import com.example.biteright.home.presenter.suggestedmeals.SuggestedMealsPresenterImpl;
import com.example.biteright.home.view.randommeal.RandomMealView;
import com.example.biteright.home.view.suggestedmeals.SuggestedMealsView;
import com.example.biteright.model.Meal;
import com.example.biteright.home.model.randommeal.RandomMealRepositoryImpl;
import com.example.biteright.home.network.randommeal.RandomMealRemoteDataSourceImpl;

public class HomeFragment extends Fragment implements RandomMealView, SuggestedMealsView {


    private TextView txt_from_home_to_plan;
    private TextView txt_from_home_to_favourite;
    private TextView txt_from_home_to_profile;
    private TextView txt_from_home_to_search;
    private TextView txt_from_home_to_notification;

    RandomMealPresenter randomMealPresenter;
    SuggestedMealsPresenter suggestedMealsPresenter;



    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        randomMealPresenter = new RandomMealPresenterImpl(this,
                RandomMealRepositoryImpl.getInstance(
                        RandomMealRemoteDataSourceImpl.getInstance()
                ));

        randomMealPresenter.getRandomMeal();

        suggestedMealsPresenter = new SuggestedMealsPresenterImpl(this,
                SuggestedMealsRepositoryImpl.getInstance(
                        SuggestedMealsRemoteDataSourceImpl.getInstance()
                ));

        suggestedMealsPresenter.getSuggestedMeals();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        onClickListener(view);

    }

    private void initUI(View view){
        txt_from_home_to_plan=view.findViewById(R.id.txt_from_home_to_plan);
        txt_from_home_to_favourite=view.findViewById(R.id.txt_from_home_to_favourite);
        txt_from_home_to_search=view.findViewById(R.id.txt_from_home_to_search);
        txt_from_home_to_profile=view.findViewById(R.id.txt_from_home_to_profile);
        txt_from_home_to_notification=view.findViewById(R.id.txt_from_home_to_notification);
    }

    private void onClickListener(View view){
        txt_from_home_to_plan.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_planFragment)
        );

        txt_from_home_to_favourite.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_favouriteFragment)
        );

        txt_from_home_to_search.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment)
        );

        txt_from_home_to_profile.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_profileFragment)
        );

        txt_from_home_to_notification.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_notificationFragment)
        );
    }

    @Override
    public void showRandomMeal(Meal[] meals) {
        Log.i("TAG", "showRandomMeal: "+meals[0].getStrArea());
        Toast.makeText(getContext(),meals[0].getIdMeal(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuggestedMeals(Meal[] meals) {
        Log.i("TAG", "showSuggestedMeals: "+meals[0].getStrArea());
        Toast.makeText(getContext(),meals.length+"",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}