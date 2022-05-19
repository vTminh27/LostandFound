package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class newAdvert extends AppCompatActivity {

    RadioGroup lostFoundRadioGroup;
    RadioButton selectedRadioButton;
    EditText newNameEditText;
    EditText newPhoneEditText;
    EditText newDescriptionEditText;
    EditText newDateEditText;
    EditText newLocationEditText;
    Button newSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        lostFoundRadioGroup = findViewById(R.id.lostFoundRadioGroup);
        newNameEditText = findViewById(R.id.newNameEditText);
        newPhoneEditText = findViewById(R.id.newPhoneEditText);
        newDescriptionEditText = findViewById(R.id.newDescriptionEditText);
        newDateEditText = findViewById(R.id.newDateEditText);
        newLocationEditText = findViewById(R.id.newLocationEditText);
        newSaveButton = findViewById(R.id.newSaveButton);


        DatabaseHelper db = new DatabaseHelper(this);

        newSaveButton.setOnClickListener(view -> {
            int selectedId = lostFoundRadioGroup.getCheckedRadioButtonId();
            String name = newNameEditText.getText().toString();
            String phone = newPhoneEditText.getText().toString();
            String description = newDescriptionEditText.getText().toString();
            String date = newDateEditText.getText().toString();
            String location = newLocationEditText.getText().toString();

            if (name.equals("") || phone.equals("") || description.equals("") || date.equals("") || location.equals("")) { // check for empty fields
                Toast.makeText(this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
            } else if (selectedId == -1) { // check if radio button selected
                Toast.makeText(this, "Please select post type", Toast.LENGTH_SHORT).show();
            } else {
                selectedRadioButton = findViewById(selectedId);
                name = selectedRadioButton.getText().toString() + " " + name;

                Item item = new Item(name, phone, description, date, location);
                db.insertItem(item);
                Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(newAdvert.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}