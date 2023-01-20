package com.example.ingame;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText email;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.forgot_email);
        Button resetPasswordButton = findViewById(R.id.reset);

        auth = FirebaseAuth.getInstance();
        resetPasswordButton.setOnClickListener(v -> resetPassword());

    }

    private void resetPassword() {
        String userEmail = email.getText().toString().trim();
        if ( userEmail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.setError("Please provide valid email");
        }
        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(ForgotPassword.this,"Check your email", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ForgotPassword.this,"Something went wrong, try again later", Toast.LENGTH_LONG).show();

            }
        });
    }
}