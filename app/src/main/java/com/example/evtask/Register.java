package com.example.evtask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Register extends AppCompatActivity {
    EditText rName, rEmail, rMobileNo, rPassword, rConfirmPassword;
    Button register;
    SharedPreferences sharedPreferences;
    String newUser, newMail, newPassword, newPhNo, newCnFrmPass;
    public static final String MyPREFERENCES = "MyPreferences";

    public static final String Name = "Name";
    public static final String email = "Email";
    public static final String phone = "Phone";
    public static final String Password = "PassWord";
    public static final String ConfirmPassword = "ConfirmPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rName = findViewById(R.id.Rname);
        rEmail = findViewById(R.id.Remail);
        rMobileNo = findViewById(R.id.RphNo);
        rPassword = findViewById(R.id.RPassWord);
        rConfirmPassword = findViewById(R.id.RConfirmPassword);
        register = findViewById(R.id.Rbutton);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.getString("confirmPassword", "").isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser = Objects.requireNonNull(rName.getText()).toString();
                newMail = Objects.requireNonNull(rEmail.getText()).toString();
                newPassword = Objects.requireNonNull(rPassword.getText()).toString();
                newCnFrmPass = Objects.requireNonNull(rConfirmPassword.getText()).toString();
                newPhNo = Objects.requireNonNull(rMobileNo.getText()).toString();

                checkDataEntered();
                editor.putString(Name, newUser);
                editor.putString(email, newMail);

                editor.putString(Password, newPassword);
                editor.putString(ConfirmPassword, newCnFrmPass);
                editor.putString(phone, newPhNo);
                final boolean commit = editor.commit();
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    void checkDataEntered() {
        if (isEmpty(rName)) {
            rName.setError("Name is required");
        } else if (!isEmail(rEmail)) {
            rEmail.setError("Enter valid email!");
        } else if (isEmpty(rMobileNo)) {
            rMobileNo.setError("phNo is required");
        } else if (isEmpty(rPassword)) {
            rPassword.setError("Password is required");

        } else if (isEmpty(rConfirmPassword)) {
            rConfirmPassword.setError("ConfirmPassword is required");
        } else if (!rConfirmPassword.getText().toString().equals(rPassword.getText().toString())) {
            rConfirmPassword.setError(" Password does not matching");
        } else {

        Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);

    }

}}

