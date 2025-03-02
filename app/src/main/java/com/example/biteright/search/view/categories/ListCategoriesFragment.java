package com.example.biteright.search.view.categories;

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

import com.example.biteright.R;
import com.example.biteright.data.local.db.MealLocalDataSourceImpl;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.network.MealRemoteDataSourceImp;
import com.example.biteright.data.repo.MealRepositoryImpl;
import com.example.biteright.search.presenter.categories.CategoriesPresenter;
import com.example.biteright.search.presenter.categories.CategoriesPresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class ListCategoriesFragment extends Fragment implements OnCategoryClickListener,CategoriesView{

    private CategoriesPresenter categoriesPresenter;
    private List<Category> categories;
    RecyclerView recyclerView_category;
    CategoriesAdapter categoriesAdapter;
    GridLayoutManager gridLayoutManager;
    View _v;

    public ListCategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getCategories();


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _v = view;
        initUI(view);
        setupRecyclerView();
    }

    private void initUI(View view){
        recyclerView_category = view.findViewById(R.id.recyclerView_categories);
        recyclerView_category.setHasFixedSize(true);

    }

    private void setupRecyclerView(){
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_category.setLayoutManager(gridLayoutManager);

        categoriesAdapter =new CategoriesAdapter(getContext(), new ArrayList<>(),this);
        recyclerView_category.setAdapter(categoriesAdapter);


    }
    private void getCategories(){
        categoriesPresenter = new CategoriesPresenterImpl(this,
                MealRepositoryImpl.getInstance(
                        new MealLocalDataSourceImpl(getContext())
                        , MealRemoteDataSourceImp.getInstance()
                ));

        categoriesPresenter.getCategories();
    }
    @Override
    public void showCategories(List<Category> categories) {
        Log.i("TAG", "showCategories: "+categories.get(0).getStrCategory());
        categoriesAdapter.setCategories(categories);
        categoriesAdapter.notifyDataSetChanged();
        this.categories=categories;

    }



    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void OnCategoryClick(Category category) {

    }
}