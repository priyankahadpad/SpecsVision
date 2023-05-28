package com.example.googleform_with_sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText,sloganText;
    TextInputLayout Username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //call sign_Up Screen
        callSignUp = findViewById(R.id.signup_screen);
        password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.Login_btn);
        Username = findViewById(R.id.login_Username);


        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent =new Intent(Login.this,SignUp.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private Boolean validateUsername(){

        String val = Username.getEditText().getText().toString();

        if (val.isEmpty()){
            Username.setError("Field cannot be empty");
            return false;
        }
        else{
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){
        if (!validatePassword() | !validateUsername()){
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {

        String userEnteredUsername = Username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    Username.setError(null);
                    Username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)){
                        Username.setError(null);
                        Username.setErrorEnabled(false);

                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(Login.this,Dashboard.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);
                        intent.putExtra("password",passwordFromDB);
                        intent.putExtra("username",usernameFromDB);
                        startActivity(intent);
                        finish();
                    }

                    else {
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                }
                else {
                    Username.setError("No such User exist");
                    Username.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }
