package tw.team5.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import tw.team5.Model.StringManipulation;
import tw.team5.fjuhacker.R;

public class FirebaseMethods {

    private static final String TAG = "FirebaseMethods";
    private boolean exist = false;

    private String userID;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirebaseFirestore;
    private CollectionReference colRef;
    private DocumentReference docRef;

    //vars
    private Context mContext;
    private double mPhotoUploadProgress = 0;

    private int imageCount;

    public FirebaseMethods(Context context){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();
        mContext = context;

        if (mAuth.getCurrentUser() != null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExists(String username){
        mFirebaseFirestore.collection("Users")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot document: task.getResult()){
                                Log.d(TAG, "onComplete: DocumentID:" + document.getId() + "=>" + document.getData());
                                exist = true;
                            }
                        }else{
                            exist = false;
                        }

                    }
                });

        return exist;

    }

    /**
     * Register a new email and password to Firebase Authentication
     * @param email
     * @param password
     * @param username
     */

    public void registerNewEmail (final String email, String password, final String username, final ProgressBar progressBar, final TextView textView){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete: "+ task.isSuccessful());

                        if (!task.isSuccessful()){
                            Toast.makeText(mContext, R.string.register_failed, Toast.LENGTH_SHORT).show();
                        }
                        else if(task.isSuccessful()){
                            //send verification email
                            sendVerificationEmail(progressBar, textView);

                        }
                    }
                });
    }

    public void sendVerificationEmail(final ProgressBar progressBar, final TextView textView){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(mContext, "註冊成功，請至信箱收取認證信", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                textView.setVisibility(View.GONE);
                            }else{
                                Toast.makeText(mContext, "couldn't send verification email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    /**
     * Add a information to Users collection
     * @param email
     * @param username
     * @param userID
     * @param random
     */

    public static void addNewUser(String email, String username, final String userID, boolean random){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference myRef = db.collection("Users").document(userID);

        // if username exists, then append the random ID to username
        if (random){
            String randomID = myRef.getId().substring(3);
            username = username + randomID;
        }

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("username", username);
        user.put("display_name", StringManipulation.condenseUsername(username));
        user.put("user_id", userID);
        user.put("phone_number", "");
        user.put("description", "");
        user.put("followers", 0);
        user.put("following", 0);
        user.put("posts", 0);
        user.put("profile_photo", "");
        user.put("website", "");

        myRef.set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error while writing document", e);
                    }
                });
    }



    private String getTimestamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'", Locale.TAIWAN);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return sdf.format(new Date());
    }



}
