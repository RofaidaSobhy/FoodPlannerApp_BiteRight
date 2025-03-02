package com.example.biteright.search.view.area;

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
import com.example.biteright.data.models.POJO.Area;
import com.example.biteright.data.models.POJO.Category;
import com.example.biteright.data.network.MealRemoteDataSourceImp;
import com.example.biteright.data.repo.MealRepositoryImpl;
import com.example.biteright.search.presenter.area.AreasPresenter;
import com.example.biteright.search.presenter.area.AreasPresenterImpl;
import com.example.biteright.search.presenter.categories.CategoriesPresenter;
import com.example.biteright.search.presenter.categories.CategoriesPresenterImpl;
import com.example.biteright.search.view.categories.CategoriesAdapter;
import com.example.biteright.search.view.categories.CategoriesView;
import com.example.biteright.search.view.categories.OnCategoryClickListener;

import java.util.ArrayList;
import java.util.List;


public class ListAreaFragment extends Fragment implements OnAreaClickListener, AreaView {

    private AreasPresenter areasPresenter;
    private List<Area> areas;
    RecyclerView recyclerView_areas;
    AreasAdapter areasAdapter;
    GridLayoutManager gridLayoutManager;
    View _v;

    public ListAreaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getAreas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_area, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _v = view;
        initUI(view);
        setupRecyclerView();
    }

    private void initUI(View view){
        recyclerView_areas = view.findViewById(R.id.recyclerView_areas);
        recyclerView_areas.setHasFixedSize(true);

    }

    private void setupRecyclerView(){
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_areas.setLayoutManager(gridLayoutManager);

        areasAdapter =new AreasAdapter(getContext(), new ArrayList<>(),this);
        recyclerView_areas.setAdapter(areasAdapter);


    }

    private void getAreas(){
        areasPresenter = new AreasPresenterImpl(this,
                MealRepositoryImpl.getInstance(
                        new MealLocalDataSourceImpl(getContext())
                        , MealRemoteDataSourceImp.getInstance()
                ));

        areasPresenter.getAreas();
    }

    @Override
    public void showAreas(List<Area> areas) {

        Log.i("TAG", "showAreas: "+areas.get(0).getStrArea());
        areasAdapter.setAreas(areas);
        areasAdapter.notifyDataSetChanged();
        this.areas=areas;
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void OnAreaClick(Area area) {

    }
}