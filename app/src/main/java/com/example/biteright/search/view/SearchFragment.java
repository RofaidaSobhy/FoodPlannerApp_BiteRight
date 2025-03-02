package com.example.biteright.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.biteright.R;
import com.example.biteright.search.view.area.ListAreaFragment;
import com.example.biteright.search.view.categories.ListCategoriesFragment;
import com.example.biteright.search.view.ingredients.ListIngredientsFragment;
import com.google.android.material.chip.Chip;


public class SearchFragment extends Fragment {

    private SearchView searchView;
    private Chip chip_category;
    private Chip chip_ingredient;
    private Chip chip_country;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment existingFragment;

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

        initUI(view);
        onClickListener(view);

    }

    private void initUI(View view){

        searchView = view.findViewById(R.id.searchView);
        chip_category = view.findViewById(R.id.chip_category);
        chip_ingredient = view.findViewById(R.id.chip_ingredient);
        chip_country = view.findViewById(R.id.chip_country);

        fragmentManager = getActivity().getSupportFragmentManager();
    }

    private void onClickListener(View view){



        chip_category.setOnCheckedChangeListener((buttonView, isChecked) -> {
            handleFragmentTransaction(isChecked, "categories-fragment", new ListCategoriesFragment());
        });

        chip_ingredient.setOnCheckedChangeListener((buttonView, isChecked) -> {
            handleFragmentTransaction(isChecked, "ingredients-fragment", new ListIngredientsFragment());
        });

        chip_country.setOnCheckedChangeListener((buttonView, isChecked) -> {
            handleFragmentTransaction(isChecked, "countries-fragment", new ListAreaFragment());
        });


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                categoriesPresenter.onSearchQueryChanged(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                categoriesPresenter.onSearchQueryChanged(newText);  // Handle real-time search
//                return true;
//            }
//        });


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


}