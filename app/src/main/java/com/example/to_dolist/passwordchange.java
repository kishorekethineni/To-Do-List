package com.example.to_dolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class passwordchange extends AppCompatActivity {
    private EditText email;
    private FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final ImageButton imd_changepass=findViewById(R.id.img_save);
        final ImageButton imd_home=findViewById(R.id.img_home);
        final EditText newpassword = findViewById(R.id.cp_newpass);
        final EditText oldpassword=findViewById(R.id.cp_oldpass);
        email=findViewById(R.id.cp_email);
        progressBar=findViewById(R.id.passchangepb);
        progressBar.setVisibility(View.VISIBLE);

        imd_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ihome=new Intent(passwordchange.this,MainActivity.class);
                startActivity(ihome);
            }
        });
        imd_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setVisibility(View.GONE);

                oldpassword.setVisibility(View.GONE);
                newpassword.setVisibility(View.VISIBLE);

                imd_changepass.setVisibility(View.VISIBLE);

            }
        });

        imd_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user != null && ! newpassword.getText().toString().trim().equals(""))
                {
                    if (newpassword.getText().toString().trim().length() < 6) {
                        newpassword.setError("Password too short, enter minimum 6 characters");
                        progressBar.setVisibility(View.GONE);
                    } else {
                        user.updatePassword(newpassword.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(passwordchange.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                            signOut();
                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            Toast.makeText(passwordchange.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    }
                } else if (newpassword.getText().toString().trim().equals("")) {
                    newpassword.setError("Enter password");
                    progressBar.setVisibility(View.GONE);
                }
            }

        });

    }

    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(passwordchange.this, login.class));
                finish();
            }

        }
    };

    //sign out method
    public void signOut()
    {
        auth.signOut();
// this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(passwordchange.this, login.class));
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
