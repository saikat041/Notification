package com.example.saika.notification;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private Button login_button,signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        login_button=(Button)findViewById(R.id.button2);
        signup_button=(Button)findViewById(R.id.button);
        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==login_button) {
            EditText email_editText=findViewById(R.id.editText);
            TextView password_editText=findViewById(R.id.editText3);
            String email=email_editText.getText().toString();
            String password=password_editText.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else {
                            Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
        else
        if(view==signup_button){
            EditText email_editText=findViewById(R.id.editText);
            TextView password_editText=findViewById(R.id.editText3);
            String email=email_editText.getText().toString();
            String password=password_editText.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            } else {
                                Toast.makeText(getApplicationContext(),"Signup failed",Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }
}
