package com.example.biteright;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.BottomNavigationViewKt;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavController();
        setupBottomNavigationBar();
        setupNavigationUI();
        setupActionBar();

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
                        || navDestination.getId()==R.id.searchByMealFragment
                        || navDestination.getId()==R.id.favouriteFragment
                        || navDestination.getId()==R.id.planFragment
                        || navDestination.getId()==R.id.profileFragment)
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
}