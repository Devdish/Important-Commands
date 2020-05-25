package com.example.brighterlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {

    EditText name,email,contact,pass1,pass2,dateOfBirth;
    RadioGroup genGroup;
    RadioButton gender;
    Button Signup;
    FirebaseAuth mfire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.fnameR);
        email=(EditText)findViewById(R.id.emailR);
        contact=(EditText)findViewById(R.id.contactR);
        pass1=(EditText)findViewById(R.id.pass1);
        pass2=(EditText)findViewById(R.id.pass2);
        dateOfBirth=(EditText)findViewById(R.id.dob);
       genGroup=(RadioGroup)findViewById(R.id.gendergroup);
//       male=(RadioButton)findViewById(R.id.maleR);
//        female=(RadioButton)findViewById(R.id.femaleR);
//        other=(RadioButton)findViewById(R.id.othersR);

        Signup=(Button)findViewById(R.id.register);
        mfire= FirebaseAuth.getInstance();



        if(mfire.getCurrentUser()!= null){
            Intent i= new Intent(signup.this, home.class);
            startActivity(i);
            finish();


        }







        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int RadioId= genGroup.getCheckedRadioButtonId();
                gender = findViewById(RadioId);





                String  fullname= name.getText().toString();
                String  Email= email.getText().toString();
                String  Contact= contact.getText().toString();
                String  Pass1= pass1.getText().toString();
                String  Pass2= pass2.getText().toString();
                String  DOB= dateOfBirth.getText().toString();
                String Gender=gender.getText().toString();


                if (TextUtils.isEmpty(fullname)){
                    name.setError("Enter name Please");

                }
                else  if(TextUtils.isEmpty(Email)){
                    email.setError("Enter Email Please");
                }

                else  if(TextUtils.isEmpty(Contact)){
                    contact.setError("Enter Mobile Number Please");
                }


                else  if(Contact.length()<10){
                    contact.setError("Enter Valid Mobile Number");
                }

                else  if(TextUtils.isEmpty(DOB)){
                    dateOfBirth.setError("Enter Date of Birth Please");
                }

                else  if(TextUtils.isEmpty(Pass1)){
                    pass1.setError("Enter Password Please");
                }


                else  if(TextUtils.isEmpty(Pass2)){
                    pass2.setError("Enter Password again Please");
                }


                else  if(!Pass1.equals(Pass2)){
                    pass2.setError("Password Should be same");
                }





                else {
                    HashMap<String, Object> maps= new HashMap<>();
                    maps.put("Name", fullname);
                    maps.put("Email", Email);
                    maps.put("Mobile Number", Contact);
                    maps.put("Gender", Gender);
                    maps.put("Date of Birth", DOB);
                    maps.put("Password", Pass1);

                    FirebaseDatabase.getInstance().getReference().child("Users").child("Registration").child("New Users").child(fullname).updateChildren(maps);
                    Toast.makeText(signup.this,"Registered Successfully",Toast.LENGTH_LONG).show();

                    mfire.createUserWithEmailAndPassword(Email, Pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(signup.this,"Registered Successfully",Toast.LENGTH_LONG).show();
//                                Intent i= new Intent(signup.this,Home.class);
//                                startActivity(i);
                            }
                            else {

                                Toast.makeText(signup.this,"Try again later",Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }






            }
        });









    }
}
