package com.example.biteright.mealDetails.view;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biteright.R;
import com.example.biteright.data.local.db.MealLocalDataSourceImpl;
import com.example.biteright.data.network.MealRemoteDataSourceImp;
import com.example.biteright.data.repo.MealRepositoryImpl;
import com.example.biteright.mealDetails.model.MealDetailsRepositoryImpl;
import com.example.biteright.mealDetails.network.MealDetailsRemoteDataSourceImpl;
import com.example.biteright.mealDetails.presenter.MealDetailsPresenter;
import com.example.biteright.mealDetails.presenter.MealDetailsPresenterImpl;
import com.example.biteright.data.models.POJO.Details_Ingredient;
import com.example.biteright.model.Meal;
import com.example.biteright.model.PlannedMeal;

import java.util.Calendar;
import java.util.List;


public class RecipeFragment extends Fragment implements MealDetailsView {

    private MealDetailsPresenter mealDetailsPresenter;



    private TextView details_name;
    private TextView details_country;
    private TextView details_category;
    private TextView details_instructions;
    private ImageButton details_favorite;
    private ImageButton details_favorite_added;

    private ImageButton details_plan;

    private ImageView details_image;

    private WebView details_video;
    private ImageButton details_back;

    private RecyclerView recyclerView_details_ingredients;

    private Ingredients_MealDetailsAdapter ingredientsMealDetailsAdapter;

    private LinearLayoutManager linearLayoutManager;
    private NavController navController;

    private Meal meal;
    private PlannedMeal plannedMeal;




    public RecipeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mealDetailsPresenter = new MealDetailsPresenterImpl(this,
                MealDetailsRepositoryImpl.getInstance(MealDetailsRemoteDataSourceImpl.getInstance()),
                MealRepositoryImpl.getInstance(
                        new MealLocalDataSourceImpl(getContext())
                        , MealRemoteDataSourceImp.getInstance()
                )

        );

        String mealId=RecipeFragmentArgs.fromBundle(getArguments()).getMealId();
        mealDetailsPresenter.getMealDetails(mealId);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initUI(view);
        setupDetails_IngredientsAdapter();
        onClick();

    }
    private void onClick(){
        details_back.setOnClickListener(
                v->{
                    navController= Navigation.findNavController(v);
                    navController.popBackStack();
                }
        );

        details_favorite.setOnClickListener(
                v -> {
                    mealDetailsPresenter.addToFav(meal);
//                    details_favorite.setVisibility(View.GONE);
//                    details_favorite_added.setVisibility(View.VISIBLE);
                }
        );

        details_plan.setOnClickListener(
                v -> {
                    showDatePickerDialog();

                }
        );
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();

        calendar.add(Calendar.DAY_OF_MONTH, 7);
        long weekAhead = calendar.getTimeInMillis();
        calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;

                    plannedMeal=new PlannedMeal(meal, selectedDate);
                    mealDetailsPresenter.addToPlan(plannedMeal);



                },
                year, month, day


        );

        datePickerDialog.getDatePicker().setMinDate(today);
        datePickerDialog.getDatePicker().setMaxDate(weekAhead);

        datePickerDialog.show();
    }

    private void setupDetails_IngredientsAdapter(){
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView_details_ingredients.setLayoutManager(linearLayoutManager);

    }
    private void initUI(View view){
        details_name = view.findViewById(R.id.details_name);
        details_country = view.findViewById(R.id.details_country);
        details_category = view.findViewById(R.id.details_category);
        details_instructions = view.findViewById(R.id.details_instructions);
        details_back = view.findViewById(R.id.details_back);
        details_favorite = view.findViewById(R.id.details_favorite);
        details_favorite_added= view.findViewById(R.id.details_favorite_added);
        details_plan = view.findViewById(R.id.details_plan);

        details_image = view.findViewById(R.id.details_image);
        details_video= view.findViewById(R.id.details_video);
        recyclerView_details_ingredients = view.findViewById(R.id.recyclerView_details_ingredients);
        recyclerView_details_ingredients.setHasFixedSize(true);




    }

    @Override
    public void showMealDetails(Meal[] meals) {

        Log.i("TAG", "showMealDetails: "+meals[0].getStrArea());

        this.meal=meals[0];

        mealDetailsPresenter.getIngredients(meals[0]);

        Glide.with(this).load(meals[0].getStrMealThumb())
                .apply(new RequestOptions()
                        .override(356, 281)
                        .placeholder(R.drawable.dummy_food)
                        .error(R.drawable.error_food)
                        .centerCrop())
                .into(details_image);

        details_name.setText(meals[0].getStrMeal());
        details_country.setText(meals[0].getStrArea());
        details_category.setText(meals[0].getStrCategory());

        String[] instructions = meals[0].getStrInstructions().split("\\.");

        int counter=1;
        for (String instruction : instructions) {

            details_instructions.append(counter+". "+instruction+"\n \t\t_________________________________________\n\n");
            counter++;
        }



        loadYouTubeVideo(meals[0].getStrYoutube());



    }

    private void loadYouTubeVideo(String youtubeUrl) {
        details_video.getSettings().setJavaScriptEnabled(true);
        details_video.getSettings().setMediaPlaybackRequiresUserGesture(false);
        details_video.setWebChromeClient(new WebChromeClient());
        Uri uri = Uri.parse(youtubeUrl);
        String videoId = uri.getQueryParameter("v");

        if (videoId != null) {
            String iframe = "<iframe width=\"100%\" height=\"100%\" " +
                    "src=\"https://www.youtube.com/embed/" + videoId + "?autoplay=0&mute=0\" " +
                    "frameborder=\"0\" allowfullscreen></iframe>";

            details_video.loadData(iframe, "text/html", "utf-8");
        } else {
            details_video.setVisibility(View.GONE);
        }
    }
    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showIngredientsDetails(List<Details_Ingredient> ingredientList) {
        ingredientsMealDetailsAdapter = new Ingredients_MealDetailsAdapter(getContext(),ingredientList);
        recyclerView_details_ingredients.setAdapter(ingredientsMealDetailsAdapter);

    }
}