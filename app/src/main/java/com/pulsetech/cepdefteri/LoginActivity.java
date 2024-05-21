package com.pulsetech.cepdefteri;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.SQLOutput;

public class LoginActivity extends AppCompatActivity {


    private FirebaseUser mUser;
    private FirebaseAuth mAuth;


    private EditText loginMail, loginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        loginMail = findViewById(R.id.editTextLoginEmail);
        loginPass = findViewById(R.id.editTextLoginPassword);
        TextView newAccountText = findViewById(R.id.createNewAccountText);
        Button btnLogin = findViewById(R.id.LoginButton);
        mAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                girisYap();

            }
        });


        newAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Yeni hesap oluşturma intentine atar //
                Intent mainMenu = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mainMenu);
            }
        });


    }


    private void girisYap() {
        String emailText = loginMail.getText().toString();
        String passText = loginPass.getText().toString();


        if (!TextUtils.isEmpty(emailText) && !TextUtils.isEmpty(passText)) {
            mAuth.signInWithEmailAndPassword(emailText,passText)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            mUser=mAuth.getCurrentUser();
                            System.out.println("KullanıcıMail"+mUser.getEmail());
                            System.out.println("KullanıcıID"+mUser.getUid());

                            Intent mainMenu = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mainMenu);

                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });


        }else
            Toast.makeText(LoginActivity.this, "Email veya Şifre boş olamaz.", Toast.LENGTH_SHORT).show();


    }
}