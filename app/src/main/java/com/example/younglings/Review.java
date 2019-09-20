package com.example.younglings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.younglings.Model.Common.Comm;
import com.example.younglings.Model.Scoreboard;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Review extends AppCompatActivity {

    FirebaseFirestore db;
    private ListView lst;
    int index = 0,size;
    TextView textView;
    String[] joo;
    String h;
    String data   = "";
    Scoreboard p;
    private ArrayAdapter<String> listAdapter ;

    ArrayList<String> planetList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mainn);
        textView = (TextView)findViewById(R.id.textView_Me);
       // lst = (ListView)findViewById(R.id.lstScore);

        db = FirebaseFirestore.getInstance();

       loadBoad();
       // getData();
        //populateList();
        //populateAdapetr();


       // populateArray();

       // Log.i("The value",ifo);

       // String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                //"Jupiter", "Saturn", "Uranus", "Neptune"};
        //ArrayList<String> planetList = new ArrayList<String>();
        //planetList.addAll( Arrays.asList(planets) );

      //  listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);
        //listAdapter.add( h+"" );
       // lst.setAdapter(listAdapter);



    }

    private void getData() {

        db.collection("Scoreboard")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    //    for (DataSnapshot ds : queryDocumentSnapshots.getDocuments()){

                        //}
                    }
                });
    }

    private void populateArray() {
        if (index<size){
            joo[index]= Comm.scoreList.get(index).getPerfomance().toString()+"";
            index++;
        }


    }

    private void populateAdapetr() {
       // listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);

    }

    private void populateList() {

       // planetList.addAll(Arrays.asList(Comm.scoreList));

       // planetList.add("Hiii");
       // planetList.add("Youu");
      /* if (index<size){
           planetList.add(Comm.scoreList.get(index).getDate()+" "+
                   Comm.scoreList.get(index).getPerfomance()+" "
                   +Comm.scoreList.get(index).getScore());
       }*/
        lst.setAdapter(listAdapter);
        //listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, );
    }

    private void loadBoad() {
        db.collection("Scoreboard")
                .orderBy("score", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    //    textView.setText("Perfomance:  \t\t\tScore: \t\t\tDate: "+"\n\n\n");

                       String topic = String.format("%12s\t%20s\t%34s\n\n\n", "Perfomance:","Score:","Date:" );

                       textView.setText(topic);
                            for (QueryDocumentSnapshot documentSnapshot  : queryDocumentSnapshots) {
                                 p = documentSnapshot.toObject(Scoreboard.class);
                                 String pere = p.getPerfomance();
                                 String da = p.getDate();
                                 int score = p.getScore();

                                //  ="\t\t\t" +pere+ "\t\t\t\t\t\t"+da + "\n\n";


                               data +=   String.format("%12s\t%28s\t%38s\n\n", pere, score+"",da );


                                Comm.scoreList.add(p);
                                size = Comm.scoreList.size();

                        }
                            textView.append(data);

                    }
                });


    }



}
