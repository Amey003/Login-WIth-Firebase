package com.example.loginwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth MAuth;
    EditText editTextEmail1 ,getEditTextPassword;
    Button login,signup;
    TextView newuser;
    private static final String TAG="login";
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MAuth = FirebaseAuth.getInstance();
        editTextEmail1 = (EditText) findViewById(R.id.user);
        getEditTextPassword = (EditText) findViewById(R.id.pass);
        newuser = (TextView) findViewById(R.id.newuser);
        login = (Button) findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextEmail1.getText().toString().trim();
                String pass = getEditTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(user)){
                    editTextEmail1.setError("Enter Username");
                }

                if(TextUtils.isEmpty(pass)){
                    getEditTextPassword.setError("Enter Password");
                }


                if(pass.length() <6 ){
                   getEditTextPassword.setError("Password Must be >=6 Charaters");
                }


                MAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Was Succesful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext() ,Homepage.class));
                            }else{
                                Toast.makeText(MainActivity.this ,"Error "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    }
                });


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() ,SignupActivity.class));

            }
        });

}}