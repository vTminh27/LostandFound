package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button createNewAdvertButton = findViewById(R.id.createNewAdvertButton);
        Button showAllItemsButton = findViewById(R.id.ShowAllItemsButton);

        createNewAdvertButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, newAdvert.class);
            startActivity(intent);
        });

        showAllItemsButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, lostAndFoundItems.class);
            startActivity(intent);
        });
    }
}