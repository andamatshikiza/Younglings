package com.example.younglings;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Home extends AppCompatActivity {
    Button btnReview, btnQuiz, btnAdd;
    //FirebaseDatabase database;
    //DatabaseReference questions;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnReview = (Button) findViewById(R.id.btnReview);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnQuiz = (Button) findViewById(R.id.btnQuiz);
        db = FirebaseFirestore.getInstance();

btnQuiz.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent homeIntent = new Intent(Home.this, Quiz.class);
        startActivity(homeIntent);
        finish();
    }



});

btnAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent sc = new Intent(Home.this, Add_Ques.class);
        startActivity(sc);
        finish();
    }
});

btnReview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent sc = new Intent(Home.this, Review.class);
        startActivity(sc);
        finish();    }
});

    }

}
