package com.example.biteright.home.view;

import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biteright.R;
import com.example.biteright.home.presenter.randommeal.RandomMealPresenter;
import com.example.biteright.home.presenter.randommeal.RandomMealPresenterImpl;

import com.example.biteright.home.view.randommeal.RandomMealView;

import com.example.biteright.model.Meal;
import com.example.biteright.home.model.randommeal.RandomMealRepositoryImpl;
import com.example.biteright.home.network.randommeal.RandomMealRemoteDataSourceImpl;

public class HomeFragment extends Fragment implements RandomMealView {




    private ImageView randomMeal_image;
    private TextView randomMeal_name;
    private TextView randomMeal_description;
    private TextView randomMeal_area;
    private CardView cardView_randomMeal;

    private String randomMealID;
    private ConstraintLayout constraintLayout_Network;
    private ConstraintLayout constrain_NetWorkExest;



    private RandomMealPresenter randomMealPresenter;



    public HomeFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        randomMealPresenter = new RandomMealPresenterImpl(this,
                RandomMealRepositoryImpl.getInstance(
                        RandomMealRemoteDataSourceImpl.getInstance()

                ));

            randomMealPresenter.getRandomMeal();





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
        onClick();




    }

    private void initUI(View view){


        randomMeal_image = view.findViewById(R.id.randomMeal_image);
        randomMeal_name = view.findViewById(R.id.randomMeal_name);
        randomMeal_description = view.findViewById(R.id.randomMeal_description);
        randomMeal_area = view.findViewById(R.id.randomMeal_area);
        cardView_randomMeal = view.findViewById(R.id.cardView_randomMeal);
        constraintLayout_Network=view.findViewById(R.id.constrain_Network);
        constrain_NetWorkExest=view.findViewById(R.id.constrain_NetWorkExest);


    }

    private void onClick(){
        cardView_randomMeal.setOnClickListener(
                v -> {
                    HomeFragmentDirections.ActionHomeFragmentToRecipeFragment2 action =
                            HomeFragmentDirections.actionHomeFragmentToRecipeFragment2(randomMealID);
                    Navigation.findNavController(v).navigate(action);
                }
        );
    }





    @Override
    public void showRandomMeal(Meal[] meals) {
        Log.i("TAG", "showRandomMeal: "+meals[0].getStrArea());

        Glide.with(this).load(meals[0].getStrMealThumb())
                .apply(new RequestOptions()
                    .override(350, 150)
                    .placeholder(R.drawable.dummy_food)
                    .error(R.drawable.error_food)
                    .centerCrop())
                .into(randomMeal_image);

        randomMeal_name.setText(meals[0].getStrMeal());
        randomMeal_area.setText(meals[0].getStrArea());
        randomMeal_description.setText(meals[0].getStrInstructions().substring(0,meals[0].getStrInstructions().length()-1) );
        randomMealID = meals[0].getIdMeal();
    }



    @Override
    public void showErrMsg(String error) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}