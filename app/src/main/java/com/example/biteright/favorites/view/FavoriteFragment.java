package com.example.biteright.favorites.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biteright.R;
import com.example.biteright.data.local.db.FavMealLocalDataSourceImpl;
import com.example.biteright.data.repo.MealRepositoryImpl;
import com.example.biteright.favorites.presenter.FavoritePresenter;
import com.example.biteright.favorites.presenter.FavoritePresenterImpl;
import com.example.biteright.home.view.HomeFragmentDirections;
import com.example.biteright.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements OnFavoriteClickListener ,FavView{


    RecyclerView recyclerView_favorite;
    FavMealsAdapter favMealsAdapter;
    FavoritePresenter favoritePresenter;
    LinearLayoutManager linearLayoutManager;
    View _v;

    public FavoriteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _v = view;

        favoritePresenter = new FavoritePresenterImpl(this,
                MealRepositoryImpl.getInstance(
                        new FavMealLocalDataSourceImpl(getContext())));

        initUI(view);
        setupRecyclerView();
        favoritePresenter.getMeals();


    }

    private void initUI(View view){
        recyclerView_favorite = view.findViewById(R.id.recyclerView_favorite);
        recyclerView_favorite.setHasFixedSize(true);

    }

    private void setupRecyclerView(){
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_favorite.setLayoutManager(linearLayoutManager);

        favMealsAdapter =new FavMealsAdapter(getContext(), new ArrayList<>(),this);
        recyclerView_favorite.setAdapter(favMealsAdapter);
    }



    @Override
    public void showData(List<Meal> meals) {
        favMealsAdapter.setMeals(meals);
        favMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void OnFavoriteClick(Meal meal) {


        FavoriteFragmentDirections.ActionFavouriteFragmentToRecipeFragment action =
                FavoriteFragmentDirections.actionFavouriteFragmentToRecipeFragment(meal.getIdMeal());
        Navigation.findNavController(_v).navigate(action);

    }

    @Override
    public void OnDeleteClick(Meal meal) {
        favoritePresenter.removeFromFav(meal);
    }
}