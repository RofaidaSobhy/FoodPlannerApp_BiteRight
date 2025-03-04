package com.example.biteright;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.BottomNavigationViewKt;
import androidx.navigation.ui.NavigationUI;

import com.example.biteright.utilits.ShowAlert;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);

        setupNavController();
        setupBottomNavigationBar();
        setupNavigationUI();
        setupActionBar();

        bottomNavigationView.setOnItemSelectedListener(
                item -> {
                    int destination = item.getItemId();

                    if (destination == R.id.favouriteFragment
                            || destination == R.id.planFragment
                            || destination == R.id.profileFragment) {
                        String currentUser = sharedPreferences.getString("email", null);
                        if (currentUser == null) {
                            showAlert();
                            return false; // Block navigation
                        }
                    }
                    // Proceed with navigation only if the user is logged in
                    return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.fragmentContainerView));
                }
        );



    }

    private void setupBottomNavigationBar() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

    }

    private void setupNavController(){
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();

    }

    private void setupNavigationUI(){
        //NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
    private void setupActionBar(){
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
//                if(navDestination.getId()==R.id.splashFragment || navDestination.getId()==R.id.welcomeFragment ||navDestination.getId()==R.id.loginFragment||navDestination.getId()==R.id.registrationFragment){
//                    getSupportActionBar().hide();
//                }else if(navDestination.getId()==R.id.homeFragment){
//                    //getSupportActionBar().show();
//                    //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                    getSupportActionBar().hide();
//                }else{
//                    getSupportActionBar().show();
//                }

                //bottomNavigationView
                if(navDestination.getId()==R.id.homeFragment
                        || navDestination.getId()==R.id.searchFragment
                        || navDestination.getId()==R.id.favouriteFragment
                        || navDestination.getId()==R.id.planFragment
                        || navDestination.getId()==R.id.profileFragment
                        || navDestination.getId()==R.id.recipeFragment
                )
                {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }else {
                    bottomNavigationView.setVisibility(View.GONE);
                }





            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private void showAlert(){

        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(this)
                .setTitle("Sign Up For More Features")
                .setMessage("Add your food preferences, plan your meals and more!")
                .setPositiveButton("Sign UP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Navigation.findNavController(MainActivity.this, R.id.fragmentContainerView)
                                .navigate(R.id.action_homeFragment_to_registrationFragment);


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        //.setIcon(R.drawable.biterightwithoutcalendar);

        builder.create();
        builder.show();


    }
}