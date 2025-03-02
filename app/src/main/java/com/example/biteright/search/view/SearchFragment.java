package com.example.biteright.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.biteright.R;
import com.example.biteright.data.local.db.MealLocalDataSourceImpl;
import com.example.biteright.data.network.MealRemoteDataSourceImp;
import com.example.biteright.data.repo.MealRepositoryImpl;

import com.example.biteright.model.Meal;
import com.example.biteright.search.presenter.SearchPresenter;
import com.example.biteright.search.presenter.SearchPresenterImpl;
import com.example.biteright.search.view.area.ListAreaFragment;
import com.example.biteright.search.view.categories.ListCategoriesFragment;
import com.example.biteright.search.view.ingredients.ListIngredientsFragment;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;


public class SearchFragment extends Fragment implements OnSearchMealClickListener, SearchingView {

    private SearchView searchView;
    private Chip chip_category;
    private Chip chip_ingredient;
    private Chip chip_country;
    private Group Chips;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment existingFragment;


    private RecyclerView recyclerView_Search;
    SearchingAdapter searchingAdapter;
    SearchPresenter searchPresenter;
    LinearLayoutManager linearLayoutManager;
    View _v;


    public SearchFragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _v = view;

        initUI(view);
        onClickListener(view);



        searchPresenter = new SearchPresenterImpl(this,
                MealRepositoryImpl.getInstance(
                        new MealLocalDataSourceImpl(getContext())
                        , MealRemoteDataSourceImp.getInstance()
                )
        );

        setupRecyclerView();

    }

    private void setupRecyclerView(){
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_Search.setLayoutManager(linearLayoutManager);

        searchingAdapter =new SearchingAdapter(getContext(), new ArrayList<>(),this);
        recyclerView_Search.setAdapter(searchingAdapter);
    }
    private void initUI(View view){

        searchView = view.findViewById(R.id.searchView);
        chip_category = view.findViewById(R.id.chip_category);
        chip_ingredient = view.findViewById(R.id.chip_ingredient);
        chip_country = view.findViewById(R.id.chip_country);
        Chips = view.findViewById(R.id.chipsFragment);
        recyclerView_Search = view.findViewById(R.id.recyclerView_Seach);
        recyclerView_Search.setHasFixedSize(true);

        fragmentManager = getActivity().getSupportFragmentManager();
    }

    private void onClickListener(View view){



        chip_category.setOnCheckedChangeListener((buttonView, isChecked) -> {
            recyclerView_Search.setVisibility(View.GONE);
            Chips.setVisibility(View.VISIBLE);

            handleFragmentTransaction(isChecked, "categories-fragment", new ListCategoriesFragment());
        });

        chip_ingredient.setOnCheckedChangeListener((buttonView, isChecked) -> {
            recyclerView_Search.setVisibility(View.GONE);
            Chips.setVisibility(View.VISIBLE);
            handleFragmentTransaction(isChecked, "ingredients-fragment", new ListIngredientsFragment());
        });

        chip_country.setOnCheckedChangeListener((buttonView, isChecked) -> {
            recyclerView_Search.setVisibility(View.GONE);
            Chips.setVisibility(View.VISIBLE);
            handleFragmentTransaction(isChecked, "countries-fragment", new ListAreaFragment());
        });


        Observable<String> search_observable = Observable.create(emitter -> {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    recyclerView_Search.setVisibility(View.VISIBLE);
                    Chips.setVisibility(View.GONE);

                    emitter.onNext(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    recyclerView_Search.setVisibility(View.VISIBLE);
                    Chips.setVisibility(View.GONE);
                    emitter.onNext(newText);
                    return true;
                }
            });

        });

        search_observable
                .debounce(300, TimeUnit.MICROSECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(text -> searchPresenter.onSearchQueryChanged(text));





    }

    private void handleFragmentTransaction(boolean isChecked, String fragmentTag, Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        if (isChecked) {
            existingFragment = fragmentManager.findFragmentByTag(fragmentTag);
            if (existingFragment == null) {
                fragmentTransaction.replace(R.id.searchFragmentContainerView, fragment, fragmentTag);
                fragmentTransaction.commit();
            }
        } else {
            existingFragment = fragmentManager.findFragmentByTag(fragmentTag);
            if (existingFragment != null) {
                fragmentTransaction.remove(existingFragment);
                fragmentTransaction.commit();
            }
        }
    }


    @Override
    public void OnMealClick(Meal meal) {
        SearchFragmentDirections.ActionSearchFragmentToRecipeFragment action =
               SearchFragmentDirections.actionSearchFragmentToRecipeFragment(meal.getIdMeal());
        Navigation.findNavController(_v).navigate(action);
    }

    @Override
    public void showData(List<Meal> meals) {
        searchingAdapter.setMeals(meals);
        searchingAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}