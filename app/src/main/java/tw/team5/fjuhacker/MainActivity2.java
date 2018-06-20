package tw.team5.fjuhacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import tw.team5.Login.RegisterActivity;
import tw.team5.Model.Activity;
import tw.team5.Model.Facebook;

public class MainActivity2 extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter2 mAdapter;
    ArrayList<Facebook> activityArrayList;
    private String participant = "";
    private Button goDepartment;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


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

        db.collection("Department")
                .document("輔仁大學資管系學會")
                .collection("Post")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot: task.getResult()){
                            Facebook facebook = new Facebook(
                                    documentSnapshot.getString("post_ID"),
                                    documentSnapshot.getString("post_content"),
                                    documentSnapshot.getString("post_createTime"));
                            Log.d(TAG, "postID is " + documentSnapshot.getString("post_ID"));
                            Log.d(TAG, "postcontent is " + documentSnapshot.getString("post_content"));
                            activityArrayList.add(facebook);
                        }
                        mAdapter = new RecyclerViewAdapter2(MainActivity2.this, activityArrayList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity2.this, "Problem Detected", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
