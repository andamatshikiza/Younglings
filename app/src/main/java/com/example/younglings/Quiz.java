package com.example.younglings;


import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.younglings.Model.Common.Comm;
import com.example.younglings.Model.Questionz;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz extends AppCompatActivity implements View.OnClickListener {


    int index = 0, score = 0, corrctAnswer = 0, totalQuest = 0, currentQuest;

    String perf,date;
    TextView quest,mark;
    Button btnA, btnB, btnC, btnDone;
    DocumentReference docreF;
    FirebaseFirestore db;
    public   List<Questionz> questionsL = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = FirebaseFirestore.getInstance();

        quest = (TextView) findViewById(R.id.txtQuestion);
        mark = (TextView) findViewById(R.id.edtmark);
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);

        btnDone = (Button) findViewById(R.id.btnDone);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd  'at' HH:mm ");
        date = df.format(c);
        LoadQuestion();
        //showQuestion(index);
        btnA.setOnClickListener((View.OnClickListener) this);
        btnB.setOnClickListener((View.OnClickListener) this);
        btnC.setOnClickListener((View.OnClickListener) this);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index< 5){
                    index++;
                    showQuest();
                }else{


                   Intent homeIntent = new Intent(Quiz.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });

    }

    private void addScore() {
        perf = mark.getText().toString();
        Map<String,Object> data = new HashMap<>();
        data.put("score",score);
        data.put("perfomance",perf);
        data.put("date",date);

        db.collection("Scoreboard")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Intent homeIntent = new Intent(Quiz.this, Home.class);
                        startActivity(homeIntent);
                        finish();

                    }
                });
    }


    private void LoadQuestion() {


        db.collection("Questions")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> lis = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : lis) {
                                Questionz p = d.toObject(Questionz.class);
                                Comm.questionsList.add(p);
                                totalQuest = Comm.questionsList.size();
                                Collections.shuffle(Comm.questionsList);
                                mark.setText("0/"+5);
                                showQuest();

                            }

                        }
                    }
                });


    }

    private void showQuest() {
if (index<5){
    quest.setText(Comm.questionsList.get(index).getTitle());
    btnA.setText(Comm.questionsList.get(index).getOption1());
    btnB.setText(Comm.questionsList.get(index).getOption2());
    btnC.setText(Comm.questionsList.get(index).getOption3());
}else {

    Comm.questionsList.clear();

    //totalQuest = 0;
   // index = 0;
    addScore();

    Intent homeIntent = new Intent(Quiz.this, Good.class);
    startActivity(homeIntent);
    finish();
}




    }


    @Override
    public void onClick(View v) {

        if (index < 5){
            Button clickedButton = (Button) v;

            if (clickedButton.getText().equals(Comm.questionsList.get(index).getAnswer())){
                score += 50;

                clickedButton.setBackgroundColor(Color.GREEN);
                corrctAnswer++;
                mark.setText(corrctAnswer+"/"+5);
               // setContentView(R.layout.good_activity);
               // index++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                       // corrctAnswer++;
                       /// setContentView(R.layout.activity_quiz);
                        clickedButton.setBackgroundColor(Color.parseColor("#7BC17E"));

                        clickedButton.setBackground(getResources().getDrawable(R.drawable.buttonshap2));

                        index++;
                        showQuest();

                    }
                }, 1500);

                //showQuestion(++index);
            }else{

                clickedButton.setBackgroundColor(Color.RED);
               // index++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                       // corrctAnswer++;
                      //  clickedButton.setBackgroundColor(Color.parseColor("#7BC17E"));
                        clickedButton.setBackground(getResources().getDrawable(R.drawable.buttonshap2));
                        index++;
                        showQuest();


            }
        }, 1500);


            }
            //index++;
       // showQuest();

        }else{

            Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();

        }
    }




}
