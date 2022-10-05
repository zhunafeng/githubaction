package com.example.indigenous;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.indigenous.databinding.ActivityGardenBinding;
import com.readystatesoftware.systembartint.SystemBarTintManager;


public class GardenActivity extends AppCompatActivity {


    private NavController navController;
    private AppBarConfiguration configuration;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGardenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_garden);
        this.drawerLayout = binding.drawLayout;
        navController = Navigation.findNavController(this, R.id.nav_host_view);
        configuration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(binding.drawLayout).build();

        setSupportActionBar(binding.toolBar);
        NavigationUI.setupActionBarWithNavController(this, navController, configuration);


        NavigationUI.setupWithNavController(binding.drawerNavigation, navController);

        // TODO: 5/10/2022 need to fix sub item destinations
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.sub_1 || navDestination.getId() == R.id.sub_2 || navDestination.getId() == R.id.sub_3) {
                    String toast = "";
                    switch (navDestination.getId()) {
                        case R.id.sub_1:
                            toast = "sub 1";
                            break;
                        case R.id.sub_2:
                            toast = "sub 2";
                            break;
                        case R.id.sub_3:
                            toast = "sub 3";
                            break;
                    }
                    Toast.makeText(GardenActivity.this, "toast", Toast.LENGTH_SHORT).show();
                }
            }
        });

        drawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, binding.toolBar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        drawerLayout.addDrawerListener(drawerToggle);

        initSystemBar();
    }

    private void initSystemBar() {
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        tintManager.setTintColor(Color.RED);
        tintManager.setNavigationBarTintColor(Color.RED);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, configuration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
