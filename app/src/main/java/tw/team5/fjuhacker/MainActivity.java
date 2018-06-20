package tw.team5.fjuhacker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import tw.team5.Login.LoginActivity;
import tw.team5.Login.RegisterActivity;
import tw.team5.Model.Activity;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<Activity> activityArrayList;
    private String participant = "";
    private Button goDepartment;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        activityArrayList = new ArrayList<>();

        setUpRecyclerView();
        setUpFirebase();
        loadDataFromFirebase();
        setUpWidgets();
        }

    private void loadDataFromFirebase() {
        if (activityArrayList.size()>0){
            activityArrayList.clear();
        }

        db.collection("Activities")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot: task.getResult()){
                            Map<String, Object> map = documentSnapshot.getData();
                            for (Map.Entry<String, Object> entry: map.entrySet()){
                                if (entry.getKey().equals("act_participant")){
                                    participant = entry.getValue().toString();
                                }
                            }
                            Activity activity = new Activity(
                                    documentSnapshot.getString("act_category"),
                                    documentSnapshot.getString("act_name"),
                                    documentSnapshot.getString("act_organizer"),
                                    participant,
                                    documentSnapshot.getString("act_speaker"));
                            activityArrayList.add(activity);
                        }
                        mAdapter = new RecyclerViewAdapter(MainActivity.this, activityArrayList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Problem Detected", Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "onFailure: " + e.getMessage());
                    }
                });

    }

    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
    }

    private void setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpWidgets(){
        goDepartment = findViewById(R.id.UpdateBtn);
        goDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
