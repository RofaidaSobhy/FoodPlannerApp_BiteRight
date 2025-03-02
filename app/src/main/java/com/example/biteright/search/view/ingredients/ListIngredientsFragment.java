package com.example.biteright.search.view.ingredients;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.biteright.R;
import com.example.biteright.data.local.db.MealLocalDataSourceImpl;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.models.POJO.Ingredient;
import com.example.biteright.data.network.MealRemoteDataSourceImp;
import com.example.biteright.data.repo.MealRepositoryImpl;
import com.example.biteright.search.presenter.categories.CategoriesPresenter;
import com.example.biteright.search.presenter.categories.CategoriesPresenterImpl;
import com.example.biteright.search.presenter.ingredients.IngredientsPresenter;
import com.example.biteright.search.presenter.ingredients.IngredientsPresenterImpl;
import com.example.biteright.search.view.categories.CategoriesAdapter;
import com.example.biteright.search.view.categories.CategoriesView;
import com.example.biteright.search.view.categories.OnCategoryClickListener;

import java.util.ArrayList;
import java.util.List;


public class ListIngredientsFragment extends Fragment implements OnIngredientClickListener, IngredientsView {


    private IngredientsPresenter ingredientsPresenter;
    private List<Ingredient> ingredients;
    RecyclerView recyclerView_ingredients;
    IngredientsAdapter ingredientsAdapter;
    GridLayoutManager gridLayoutManager;
    View _v;
    public ListIngredientsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIngredients();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _v = view;
        initUI(view);
        setupRecyclerView();
    }

    private void initUI(View view){
        recyclerView_ingredients = view.findViewById(R.id.recyclerView_ingredients);
        recyclerView_ingredients.setHasFixedSize(true);

    }

    private void setupRecyclerView(){
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_ingredients.setLayoutManager(gridLayoutManager);

        ingredientsAdapter =new IngredientsAdapter(getContext(), new ArrayList<>(),this);
        recyclerView_ingredients.setAdapter(ingredientsAdapter);


    }
    private void getIngredients(){
        ingredientsPresenter = new IngredientsPresenterImpl(this,
                MealRepositoryImpl.getInstance(
                        new MealLocalDataSourceImpl(getContext())
                        , MealRemoteDataSourceImp.getInstance()
                ));

        ingredientsPresenter.getIngredients();
    }


    @Override
    public void showIngredients(List<Ingredient> ingredients) {

        ingredientsAdapter.setIngredients(ingredients);
        ingredientsAdapter.notifyDataSetChanged();
        this.ingredients=ingredients;

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void OnIngredientClick(Ingredient ingredient) {

    }
}