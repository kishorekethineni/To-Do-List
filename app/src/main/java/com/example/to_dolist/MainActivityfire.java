package com.example.to_dolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityfire extends AppCompatActivity {

    private CardView btnChangePassword, btnRemoveUser,
             signOut,home;
    private TextView email;

    private ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebaseprofile);
        // get firebase auth instance
        auth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.usermail);
        email.setSelected(true);
        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setDataToView(user);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivityfire.this, login.class));
                    finish();
                }
            }
        };


        btnChangePassword = (CardView) findViewById(R.id.passchange);

        btnRemoveUser = (CardView) findViewById(R.id.remove_user_button);

        signOut = (CardView) findViewById(R.id.sign_out);

        home=(CardView)findViewById(R.id.frirebasehome);

        progressBar = (ProgressBar) findViewById(R.id.progressBar3);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ifire=new Intent(MainActivityfire.this,MainActivity.class);
                startActivity(ifire);
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i= new Intent(MainActivityfire.this,passwordchange.class);
               startActivity(i);
                    }

        });


        btnRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (user != null) {
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivityfire.this, "Your profile is Removed from firebase!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivityfire.this, SignupActivity.class));
                                        finish();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(MainActivityfire.this, "Failed to delete your account check your connection!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        });

         signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user)
    {

        email.setText( "" + user.getEmail());


    }

    // this listener will be called when there is change in firebase user session
    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                startActivity(new Intent(MainActivityfire.this, login.class));
                finish();
            }
            else
             {
                setDataToView(user);
             }
        }
    };

    //sign out method
    public void signOut()
    {
        auth.signOut();
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivityfire.this, login.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (authListener != null)
        {
            auth.removeAuthStateListener(authListener);
        }
    }
}

