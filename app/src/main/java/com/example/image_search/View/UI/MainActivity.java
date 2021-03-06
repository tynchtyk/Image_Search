package com.example.image_search.View.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.image_search.R;
import com.example.image_search.ViewModel.FavouritesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    BottomNavigationView bottomNavigationView;
    FavouritesViewModel favouritesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navController = findNavController(this, R.id.nav_host_fragment);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //clearDagaBase();
    }

    public void clearDagaBase(){
        //favouritesViewModel = ViewModelProviders.of(this).get(FavouritesViewModel.class);
      //  favouritesViewModel.deleteAll();
    }

}