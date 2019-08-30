package com.example.younglings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Add_Ques extends AppCompatActivity {



    String option1,option2,option3,answer,question;
    EditText opt1,opt2,opt3,answr,quest;

    FirebaseFirestore db;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add = (Button)findViewById(R.id.btndAdd_Q);

        //firebase

        db = FirebaseFirestore.getInstance();

        opt1 = (EditText) findViewById(R.id.edtOpt1);
        opt2 = (EditText) findViewById(R.id.edtOpt2);
        opt3 = (EditText) findViewById(R.id.edtOpt3);
        answr = (EditText)findViewById(R.id.edtAnswer);
        quest = (EditText)findViewById(R.id.edtQuest);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                option1 = opt1.getText().toString();
                option2 = opt2.getText().toString();
                option3 = opt3.getText().toString();
                question = quest.getText().toString();
                answer = answr.getText().toString();

                Map<String,Object> data = new HashMap<>();
                data.put("answer",answer);
                data.put("option1",option1);
                data.put("option2",option2);
                data.put("option3",option3);
                data.put("title",question);

                db.collection("Questions")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                Toast.makeText(Add_Ques.this,"Done",Toast.LENGTH_LONG);
                                opt1.setText("");
                                opt2.setText("");
                                opt3.setText("");
                                answr.setText("");
                                quest.setText("");

                                Intent homeIntent = new Intent(Add_Ques.this, Home.class);
                                startActivity(homeIntent);
                                finish();


                            }
                        });

            }
        });


    }
            }
