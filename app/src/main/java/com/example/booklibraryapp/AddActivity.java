package com.example.booklibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText title,author,page;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title=findViewById(R.id.title);
        author=findViewById(R.id.author);
        page=findViewById(R.id.page);
        bt1=findViewById(R.id.bt1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().equals("") || author.getText().toString().equals("") || page.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "Please enter the all fields", Toast.LENGTH_SHORT).show();
                } else {
                    MyDatabaseHelper mdb = new MyDatabaseHelper(AddActivity.this);
                    mdb.addBook(title.getText().toString(), author.getText().toString(), Integer.parseInt(page.getText().toString()));
                }
            }
        });

    }
}