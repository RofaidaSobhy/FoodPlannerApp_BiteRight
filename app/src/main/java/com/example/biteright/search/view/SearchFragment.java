package com.example.biteright.search.view;

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
import com.example.biteright.home.model.randommeal.RandomMealRepositoryImpl;
import com.example.biteright.home.network.randommeal.RandomMealRemoteDataSourceImpl;
import com.example.biteright.home.presenter.randommeal.RandomMealPresenter;
import com.example.biteright.home.presenter.randommeal.RandomMealPresenterImpl;
import com.example.biteright.home.view.randommeal.RandomMealView;
import com.example.biteright.model.Category;
import com.example.biteright.search.model.categories.CategoriesRepositoryImpl;
import com.example.biteright.search.network.categories.CategoriesRemoteDataSourceImpl;
import com.example.biteright.search.presenter.categories.CategoriesPresenter;
import com.example.biteright.search.presenter.categories.CategoriesPresenterImpl;
import com.example.biteright.search.view.categories.CategoriesView;


public class SearchFragment extends Fragment implements CategoriesView {

    private TextView txt_search;
    private TextView txt_categories;
    private TextView txt_ingredients;
    private TextView txt_area;
    private CategoriesPresenter categoriesPresenter;
    private Category[] categories;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getCategories();


    }

    private void getCategories(){
        categoriesPresenter = new CategoriesPresenterImpl(this,
                CategoriesRepositoryImpl.getInstance(
                        CategoriesRemoteDataSourceImpl.getInstance()
                ));

        categoriesPresenter.getCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        onClickListener(view);

    }

    private void initUI(View view){
        txt_search=view.findViewById(R.id.txt_search);
        txt_categories=view.findViewById(R.id.txt_categories);
        txt_ingredients=view.findViewById(R.id.txt_ingredients);
        txt_area=view.findViewById(R.id.txt_area);


    }

    private void onClickListener(View view){
        txt_search.setOnClickListener(
                v -> Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_searchByMealFragment)
        );

        txt_categories.setOnClickListener(
                v-> Toast.makeText(getContext(),categories.length+"",Toast.LENGTH_LONG).show()
        );

        txt_ingredients.setOnClickListener(
                v -> Log.i("TAG", "onClickListener: ")
        );

        txt_area.setOnClickListener(
                v -> Log.i("TAG", "onClickListener: ")
        );
    }

    @Override
    public void showCategories(Category[] categories) {
        Log.i("TAG", "showCategories: "+categories[0].getStrCategory());
        //Toast.makeText(getContext(),categories.length+"",Toast.LENGTH_LONG).show();
        this.categories=categories;

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}