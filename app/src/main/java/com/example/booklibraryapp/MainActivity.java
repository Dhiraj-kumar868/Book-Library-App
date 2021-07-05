package com.example.booklibraryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab1;
    RecyclerView rv1;
    ImageView iv1;
    TextView tv1;

    MyDatabaseHelper myDB;
    ArrayList<String> book_id,book_title,book_author,book_pages;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab1=findViewById(R.id.fab1);
        rv1=findViewById(R.id.rv1);
        iv1=findViewById(R.id.iv1);
        tv1=findViewById(R.id.tv1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        myDB=new MyDatabaseHelper(MainActivity.this);
        book_id=new ArrayList<>();
        book_title=new ArrayList<>();
        book_author=new ArrayList<>();
        book_pages=new ArrayList<>();
        storeDataInArrays();

        customAdapter=new CustomAdapter(MainActivity.this,this,book_id,book_title,book_author,book_pages);
        rv1.setAdapter(customAdapter);
        rv1.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    private void storeDataInArrays() {
        Cursor cursor=myDB.readAllData();
        if(cursor.getCount()==0){
            iv1.setVisibility(View.VISIBLE);
            tv1.setVisibility(View.VISIBLE);
        }else{
            while(cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
            iv1.setVisibility(View.GONE);
            tv1.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.DeleteAll)
        {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    private void confirmDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete All"+ " ?");
        builder.setMessage("Are you sure you want to delete all data "+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                MyDatabaseHelper myDB=new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
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