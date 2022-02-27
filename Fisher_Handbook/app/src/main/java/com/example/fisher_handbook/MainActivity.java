package com.example.fisher_handbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fisher_handbook.databinding.ActivityMainBinding;
import com.example.fisher_handbook.settings.SettingsActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView list;
    private String[] array;
    private ArrayAdapter<String> adapter;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private Toolbar myToolbar;
    private int category_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = findViewById(R.id.listView);
        array = getResources().getStringArray(R.array.fishes_array);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array)));

        list.setAdapter(adapter);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, Text_Content_Activity.class);
                intent.putExtra("category", category_index);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        myToolbar.setTitle(R.string.menu_fishes);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_fishes) {
            fillArray(R.string.menu_fishes, R.string.menu_fishes, R.array.fishes_array, 0);
        } else if (id == R.id.nav_bait) {
            fillArray(R.string.menu_bait, R.string.menu_bait, R.array.bait_array, 1);
        } else if (id == R.id.nav_tackle) {
            fillArray(R.string.menu_tackle, R.string.menu_tackle, R.array.tackle_array, 2);
        } else if (id == R.id.nav_food) {
            fillArray(R.string.menu_food, R.string.menu_food, R.array.food_array, 3);
        } else if (id == R.id.nav_history) {
            fillArray(R.string.other_history, R.string.other_history, R.array.history_array, 4);
        } else if (id == R.id.nav_advice) {
            fillArray(R.string.other_advice, R.string.other_advice, R.array.advice_array, 5);
        }

        drawer = binding.drawerLayout;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void fillArray(int toast, int title, int arrayList, int index) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();

        myToolbar.setTitle(title);
        array = getResources().getStringArray(arrayList);
        adapter.clear();
        adapter.addAll(array);
        adapter.notifyDataSetChanged();
        category_index = index;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.listView);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setmAppBarConfiguration(AppBarConfiguration mAppBarConfiguration) {
        this.mAppBarConfiguration = mAppBarConfiguration;
    }
}