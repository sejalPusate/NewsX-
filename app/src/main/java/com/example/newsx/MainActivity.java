package com.example.newsx;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set default fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new HomeFragment())
                .commit();

        // Use if-else instead of switch
        bottomNavigationView.setOnItemSelectedListener(item -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                fragmentTransaction.replace(R.id.content, new HomeFragment());
            } else if (id == R.id.navigation_science) {
                fragmentTransaction.replace(R.id.content, new ScienceFragment());
            } else if (id == R.id.navigation_sports) {
                fragmentTransaction.replace(R.id.content, new SportsFragment());
            } else if (id == R.id.navigation_health) {
                fragmentTransaction.replace(R.id.content, new HealthFragment());
            } else if (id == R.id.navigation_entertainment) {
                fragmentTransaction.replace(R.id.content, new EnterFragment());
            } else {
                return false;
            }

            fragmentTransaction.commit();
            return true;
        });
    }
}
