package com.example.saika.notification;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button logout_button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
      //  startActivity(intent);
        mAuth = FirebaseAuth.getInstance();
        logout_button=(Button)findViewById(R.id.button3);
        if(mAuth.getCurrentUser()==null)
        {
            Intent login_activity=new Intent(this,Login.class);
            startActivity(login_activity);
        }
        else
        {
            ProgressBar progressBar= (ProgressBar)findViewById(R.id.progressBar3);
            progressBar.setVisibility(View.INVISIBLE);
            TextView textView=(TextView)findViewById(R.id.textView);
            textView.setVisibility(View.VISIBLE);
            Button button=(Button)findViewById(R.id.button3);
            button.setVisibility(View.VISIBLE);
        }
        logout_button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
      if(view==logout_button){
          mAuth.signOut();
          startActivity(new Intent(getApplicationContext(),Login.class));
      }
    }
}
