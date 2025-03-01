package com.example.biteright.planMeal.view;

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
import android.widget.CalendarView;

import com.example.biteright.R;
import com.example.biteright.data.local.db.MealLocalDataSourceImpl;
import com.example.biteright.data.network.MealRemoteDataSourceImp;
import com.example.biteright.data.repo.MealRepositoryImpl;

import com.example.biteright.model.PlannedMeal;
import com.example.biteright.planMeal.presenter.PlannedMealPresenter;
import com.example.biteright.planMeal.presenter.PlannedMealPresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment implements OnPlannedMealClickListener, PlannedMealsView {
    private CalendarView calendar;
    private RecyclerView recyclerView_plan;
    private PlannedMealsAdapter plannedMealsAdapter;
    private PlannedMealPresenter plannedMealPresenter;
    private LinearLayoutManager linearLayoutManager;
    private View _v;
    private String date;

    public PlanFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _v = view;

        plannedMealPresenter = new PlannedMealPresenterImpl(this,
                MealRepositoryImpl.getInstance(
                        new MealLocalDataSourceImpl(getContext())
                        , MealRemoteDataSourceImp.getInstance()
                )
        );

        initUI(view);
        setupRecyclerView();


        onClickListener(view);

    }

    private void initUI(View view){
        calendar = view.findViewById(R.id.calendar);
        recyclerView_plan = view.findViewById(R.id.recyclerView_plan);
        recyclerView_plan.setHasFixedSize(true);
    }
    private void setupRecyclerView(){
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_plan.setLayoutManager(linearLayoutManager);

        plannedMealsAdapter =new PlannedMealsAdapter(getContext(), new ArrayList<>(),this);
        recyclerView_plan.setAdapter(plannedMealsAdapter);
    }

    private void onClickListener(View view){

        calendar.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            date = dayOfMonth + "/" + (month + 1) + "/" + year;

            plannedMealPresenter.getPlannedMeals(date);
        });

    }

    @Override
    public void OnPlannedMealClick(PlannedMeal plannedMeal) {
        PlanFragmentDirections.ActionPlanFragmentToRecipeFragment action =
                PlanFragmentDirections.actionPlanFragmentToRecipeFragment(plannedMeal.getIdMeal());
        Navigation.findNavController(_v).navigate(action);
    }

    @Override
    public void OnDeleteClick(PlannedMeal plannedMeal) {
        plannedMealPresenter.removeFromPlanned(plannedMeal);
    }

    @Override
    public void showData(List<PlannedMeal> plannedMeals) {
        plannedMealsAdapter.setPlannedMeals(plannedMeals);
        plannedMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}