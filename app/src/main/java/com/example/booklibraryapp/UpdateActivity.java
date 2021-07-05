package com.example.booklibraryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title,author,page;
    Button bt2,bt3;
    String id1,title1,author1,pages1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title=findViewById(R.id.title2);
        author=findViewById(R.id.author2);
        page=findViewById(R.id.page2);
        bt2=findViewById(R.id.bt2);
        bt3=findViewById(R.id.bt3);

        getAndSetIntentData();

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id1,title.getText().toString(),author.getText().toString(),page.getText().toString());
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }

        });



    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
          id1= getIntent().getStringExtra("id");
          title1= getIntent().getStringExtra("title");
          author1= getIntent().getStringExtra("author");
          pages1= getIntent().getStringExtra("pages");

          title.setText(title1);
          author.setText(author1);
          page.setText(pages1);
        }
        else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    private void confirmDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+title1+ " ?");
        builder.setMessage("Are you sure you want to delete "+title1+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id1);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}