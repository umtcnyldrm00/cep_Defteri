package com.pulsetech.cepdefteri;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText editEmail, editPassword, editName;
    private TextView existingUserTextView;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mReference;

    private HashMap<String, Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();

        btnRegister = findViewById(R.id.btnRegister);
        editEmail = findViewById(R.id.editTextMail);
        editPassword = findViewById(R.id.editTextPass);
        editName = findViewById(R.id.editTextName);
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
        mData = new HashMap<>();
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();


        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !name.isEmpty()) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                mUser = mAuth.getCurrentUser();
                                mData = new HashMap<>();
                                mData.put("userName", name);
                                mData.put("Email", email);
                                mData.put("UID", mUser.getUid());

                                mReference.child(mUser.getUid()).setValue(mData)

                                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    Toast.makeText(RegisterActivity.this, "Kullanıcı başarıyla oluşturuldu!", Toast.LENGTH_SHORT).show();
                                                    Log.d("RegisterActivity", "Veritabanına veri başarıyla eklendi!");
                                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                                } else {
                                                    Log.e("RegisterActivity", "Veritabanına veri eklenirken hata oluştu: " + task.getException().getMessage());
                                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        });


                            } else
                                Log.e("TAG", "Firebase Authentication sırasında hata oluştu: " + task.getException().getMessage());
                            Toast.makeText(RegisterActivity.this, "Kullanıcı oluşturulurken bir hata oluştu", Toast.LENGTH_SHORT).show();

                        }
                    });

        } else {

            Toast.makeText(RegisterActivity.this, "lütfen istenilen tüm bilgileri doldurunuz..", Toast.LENGTH_SHORT).show();
        }


    }
}