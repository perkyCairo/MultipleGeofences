package com.sameeksha.multiplegeofences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText uname,upass;
    Button signup_bn;
    TextView tv;
    private FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        myFirebaseAuth = FirebaseAuth.getInstance();
        uname = findViewById(R.id.uname);
        upass = findViewById(R.id.upass);
        signup_bn = findViewById(R.id.signup_button);
        tv = findViewById(R.id.textView3);
        signup_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= uname.getText().toString();
                String pass= upass.getText().toString();
                if (email.isEmpty()) {
                    uname.setError("Please enter email id.");
                    uname.requestFocus();
                }
                else if (pass.isEmpty()) {
                    upass.setError("Please enter password.");
                    upass.requestFocus();
                }
                else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pass.isEmpty()))
                {
                    myFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(SignupActivity.this, "Signup Failed. Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                finish();
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Some error occurred.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });
    }
}