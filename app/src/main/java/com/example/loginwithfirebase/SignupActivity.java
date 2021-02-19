package com.example.loginwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText mUsename,mPassword,mconfopass;
    Button mSignup;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsename = (EditText)findViewById(R.id.user);
        mPassword = (EditText)findViewById(R.id.pass);
        mconfopass =(EditText)findViewById(R.id.conf_pass);
        mSignup = (Button)findViewById(R.id.btn);

        fAuth= FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() !=  null){
            startActivity(new Intent(getApplicationContext() ,MainActivity.class));
        }

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUsename.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();
                String conpass =mconfopass.getText().toString().trim();

                if(TextUtils.isEmpty(user)){
                    mUsename.setError("Enter Username");
                }

                if(TextUtils.isEmpty(pass)){
                    mPassword.setError("Enter Password");
                }

                if (TextUtils.isEmpty(conpass)){
                    mconfopass.setError("Enter Confirmation password");
                }

                if(pass.length() <6 ){
                    mPassword.setError("Password Must be >=6 Charaters");
                }
                if (pass != conpass){
                    mPassword.setError("Pls Enter valid password");
                }

                //register user in firebase

                fAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext() ,MainActivity.class));
                        }else{
                            Toast.makeText(SignupActivity.this ,"Error "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}