package com.example.booklibraryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    Animation translate_anim;
    private ArrayList book_id,book_title,book_author,book_pages;


    CustomAdapter(Activity activity,Context context,ArrayList book_id,ArrayList book_title,ArrayList book_author,ArrayList book_pages){
       this.activity=activity;
        this.context=context;
       this.book_id=book_id;
       this.book_title=book_title;
       this.book_author=book_author;
       this.book_pages=book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.idText.setText(String.valueOf(book_id.get(position)));
        holder.idTitle.setText(String.valueOf(book_title.get(position)));
        holder.idAuthor.setText(String.valueOf(book_author.get(position)));
        holder.idPage.setText(String.valueOf(book_pages.get(position)));
       holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(book_id.get(position)));
                intent.putExtra("title",String.valueOf(book_title.get(position)));
                intent.putExtra("author",String.valueOf(book_author.get(position)));
                intent.putExtra("pages",String.valueOf(book_pages.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idText,idTitle,idAuthor,idPage;
        RelativeLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idText=itemView.findViewById(R.id.idText);
            idTitle=itemView.findViewById(R.id.idTitle);
            idAuthor=itemView.findViewById(R.id.idAuthor);
            idPage=itemView.findViewById(R.id.idPage);
            mainLayout=itemView.findViewById(R.id.mainLayout);
            translate_anim= AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
