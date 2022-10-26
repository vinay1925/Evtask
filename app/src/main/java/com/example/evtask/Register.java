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
    EditText rname, remail, rmobileNo, rpassword, rconfirmPassword;
    Button register;
 SharedPreferences sharedPreferences;
 String newUser,newMail,newPassword,newPhNo,newCnFrmPass;
    public static final String MyPREFERENCES = "myPreferences";

    public static final String Name = "name";
    public static final String email = "mail";
    public static final String phone = "phone";
    public static final String Password = "passWord";
    public static final String ConfirmPassword = "confirmPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rname = findViewById(R.id.Rname);
        remail = findViewById(R.id.Remail);
        rmobileNo = findViewById(R.id.RphNo);
        rpassword = findViewById(R.id.RPassWord);
        rconfirmPassword = findViewById(R.id.RConfirmPassword);
        register = findViewById(R.id.Rbutton);

     sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
SharedPreferences.Editor editor=sharedPreferences.edit();
     if (!sharedPreferences.getString("confirmPassword","").isEmpty()){
         startActivity(new Intent(this,MainActivity.class));
     }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser = Objects.requireNonNull(rname.getText()).toString();
                newMail = Objects.requireNonNull(remail.getText()).toString();
                newPassword = Objects.requireNonNull(rpassword.getText()).toString();
                newCnFrmPass = Objects.requireNonNull(rconfirmPassword.getText()).toString();
                newPhNo = Objects.requireNonNull(rmobileNo.getText()).toString();

                checkDataEntered();
                editor.putString(Name, newUser);
                editor.putString(email, newMail);

                editor.putString(Password, newPassword);
                editor.putString(ConfirmPassword, newCnFrmPass);
                editor.putString(phone, newPhNo);
                final boolean commit=  editor.commit();
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
        if (isEmpty(rname)) {
                    rname.setError("Name is required");
                return;
              }

               else if (!isEmail(remail)) {
                  remail.setError("Enter valid email!");
                }

                else if(isEmpty(rmobileNo)){
                    rmobileNo.setError("phNo is required");
               return;
                }

                else if (isEmpty(rpassword)) {
                    rpassword.setError("Password is required");
                    return;
                }
                else if (isEmpty(rconfirmPassword)) {
                    rconfirmPassword.setError("ConfirmPassword is required");
                 return;
                }    else if (!rconfirmPassword.getText().toString().equals(rpassword.getText().toString())) {
            rconfirmPassword.setError("Your password does not matching");
        }else{

        }
                 Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }

        }

