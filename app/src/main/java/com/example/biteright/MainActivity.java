package com.example.biteright;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavController();
        setupNavigationUI();
        setupActionBar();
    }

    private void setupNavController(){
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();

    }

    private void setupNavigationUI(){
        NavigationUI.setupActionBarWithNavController(this, navController);
    }
    private void setupActionBar(){
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId()==R.id.splashFragment || navDestination.getId()==R.id.welcomeFragment ||navDestination.getId()==R.id.loginFragment||navDestination.getId()==R.id.registrationFragment){
                    getSupportActionBar().hide();
                }else if(navDestination.getId()==R.id.homeFragment){
                    //getSupportActionBar().show();
                    //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().hide();
                }else{
                    getSupportActionBar().show();
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}