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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText editEmail, editPassword, editPhone;
    private TextView existingUserTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btnRegister);
        editEmail = findViewById(R.id.editTextMail);
        editPassword = findViewById(R.id.editTextPass);
        editPhone = findViewById(R.id.editTextPhone);
        existingUserTextView = findViewById(R.id.editTextExistedUser);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KayitOl();
            }
        });


        existingUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(mainMenu);
            }
        });
    }

    void KayitOl() {
        String emailtxt = editEmail.getText().toString();
        String passtxt = editPassword.getText().toString();

        if (!TextUtils.isEmpty(emailtxt) && !TextUtils.isEmpty(passtxt)) {
            mAuth.createUserWithEmailAndPassword(emailtxt, passtxt)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Kayıt işlemi tamamlandı!", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(RegisterActivity.this, "Email veya Şifre boş olamaz.", Toast.LENGTH_SHORT).show();
        }
    }
}