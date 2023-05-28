package com.example.googleform_with_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    TextInputLayout regFullName, regUsername, regEmail, regPassword, regPhoneNo;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regFullName = findViewById(R.id.SignUp_name);
        regUsername = findViewById(R.id.SignUp_Username);
        regEmail = findViewById(R.id.SignUp_Email);
        regPassword = findViewById(R.id.SignUp_Password);
        regPhoneNo = findViewById(R.id.SignUp_PhoneNo);
        regBtn = findViewById(R.id.SignUp_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);


        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private Boolean validateName() {
        String val = regFullName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regFullName.setError("Field cannot be empty");
            return false;

        } else {
            regFullName.setError(null);
            regFullName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;

        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;

        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePaasword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +
                "(?=\\S+$)" +
                "(?=.*[@#$%^&+=])" +
                ".{4,}" +
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;

        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            return true;
        }
    }


    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;

        }else if (!(val.length() == 10)) {
            regPhoneNo.setError("Incorrect Phone number");
            return false;

        } else {
            regPhoneNo.setError(null);
            return true;
        }
    }



    //Save data in FireBase on Button click

    public void registerUser(View view) {

        if (!validateName() | !validateUsername() | !validateEmail() | !validatePaasword() | !validatePhoneNo()) {
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String name = regFullName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);

        reference.child(username).setValue(helperClass);
        Intent intent =new Intent(SignUp.this,Dashboard.class);
        startActivity(intent);
        finish();


    }
    }
