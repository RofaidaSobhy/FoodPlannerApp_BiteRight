package com.example.biteright;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.biteright.home.model.suggestedmeals.SuggestedMealsRepositoryImpl;
import com.example.biteright.home.network.suggestedmeals.SuggestedMealsRemoteDataSourceImpl;
import com.example.biteright.home.presenter.suggestedmeals.SuggestedMealsPresenter;
import com.example.biteright.home.presenter.suggestedmeals.SuggestedMealsPresenterImpl;
import com.example.biteright.home.view.suggestedmeals.OnMealClickListener;
import com.example.biteright.home.view.suggestedmeals.SuggestedMealsAdapter;
import com.example.biteright.home.view.suggestedmeals.SuggestedMealsView;
import com.example.biteright.model.Meal;


public class MealsFragment extends Fragment implements SuggestedMealsView, OnMealClickListener {

    private RecyclerView recyclerView_meals;
    private SuggestedMealsAdapter suggestedMealsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SuggestedMealsPresenter suggestedMealsPresenter;

    public MealsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        setupSuggestedMealsAdapter();
    }
    private void initUI(View view){
        recyclerView_meals=view.findViewById(R.id.recyclerView_meals);
            recyclerView_meals.setHasFixedSize(true);

    }

    private void setupSuggestedMealsAdapter(){
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_meals.setLayoutManager(linearLayoutManager);

        suggestedMealsAdapter=new SuggestedMealsAdapter(getContext(),new Meal[]{},this);
        recyclerView_meals.setAdapter(suggestedMealsAdapter);


    }

    @Override
    public void showSuggestedMeals(Meal[] meals) {
        Log.i("TAG", "showSuggestedMeals: "+meals[0].getStrArea());
        Toast.makeText(getContext(),meals.length+"",Toast.LENGTH_LONG).show();

        suggestedMealsAdapter.setList(meals);
        suggestedMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void OnMealClick(Meal meal) {
        //go to details fragment
    }
}