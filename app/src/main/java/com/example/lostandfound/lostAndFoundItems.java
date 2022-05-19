package com.example.lostandfound;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class lostAndFoundItems extends AppCompatActivity {

    ListView lostFoundItemListView;
    List<Item> itemList;
    ArrayList<String> itemNameList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_items);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        lostFoundItemListView = findViewById(R.id.lostAndFoundItemsListView);

        DatabaseHelper db = new DatabaseHelper(this);

        itemList = db.fetchAllItem();
        itemNameList = new ArrayList<>();

        for (Item item:itemList) {
            itemNameList.add(item.getName());
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemNameList);
        lostFoundItemListView.setAdapter(adapter);

        lostFoundItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                intent.putExtra("name", itemList.get(i).getName());
                intent.putExtra("phone", itemList.get(i).getPhone());
                intent.putExtra("description", itemList.get(i).getDescription());
                intent.putExtra("date", itemList.get(i).getDate());
                intent.putExtra("location", itemList.get(i).getLocation());
                intent.putExtra("id", Integer.toString(itemList.get(i).getItemId()));
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if item was deleted, refresh list
        if(resultCode==RESULT_OK && requestCode==1) {
            lostFoundItemListView = findViewById(R.id.lostAndFoundItemsListView);

            DatabaseHelper db = new DatabaseHelper(this);

            itemList = db.fetchAllItem();

            itemNameList = new ArrayList<>();

            for (Item item:itemList) {
                itemNameList.add(item.getName());
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemNameList);
            lostFoundItemListView.setAdapter(adapter);
        }
    }
}