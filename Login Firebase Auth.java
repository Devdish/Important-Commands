package com.example.brighterlearning;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth mfire;
    EditText Email,Passwor;
    Button loginbtn;
    TextView forgots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Email= (EditText)findViewById(R.id.emailL);
        Passwor=(EditText)findViewById(R.id.PassL);
        loginbtn=(Button)findViewById(R.id.loginNow);
         forgots=(TextView)findViewById(R.id.forgot);



        mfire= FirebaseAuth.getInstance();

        if(mfire.getCurrentUser()!= null){
            Intent i= new Intent(Login.this, profile.class);
            startActivity(i);
            finish();
        }



        forgots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(Login.this,ForgotPass.class);
                startActivity(j);
            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= Email.getText().toString().trim();
                String pass= Passwor.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    Email.setError("Enter Email Please");
                }
                else if (TextUtils.isEmpty(pass)){

                    Passwor.setError("Enter Password Please");
                }
                else {



                    mfire.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();

                                Intent i=new Intent(Login.this,profile.class);
                                startActivity(i);
                            }
                            else {

                                Toast.makeText(Login.this,"Please Check details",Toast.LENGTH_LONG).show();
                            }


                        }
                    });


                }

            }
        });



    }
}
