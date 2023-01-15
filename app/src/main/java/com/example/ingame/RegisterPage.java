package com.example.ingame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    private EditText editRegister_email, editRegister_password, editRegister_name;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Intent intent = getIntent();

        mAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.register);
        register.setOnClickListener(__ -> registerUser());

        editRegister_email = (EditText) findViewById(R.id.register_email);
        editRegister_password = (EditText) findViewById(R.id.register_password);
        editRegister_name = (EditText) findViewById(R.id.register_name);

    }

    private void registerUser() {
        String email = editRegister_email.getText().toString().trim();
        String password = editRegister_password.getText().toString().trim();
        String name = editRegister_name.getText().toString().trim();

        if (email.isEmpty()) {
            editRegister_email.setError("Email is required");
            editRegister_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editRegister_email.setError("Please provide valid email!");
            editRegister_email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editRegister_password.setError("Password is required");
            editRegister_password.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            editRegister_name.setError("Name is required");
            editRegister_name.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            PlayerModel playerModel = new PlayerModel("None", name, 0, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(playerModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterPage.this, "User registered successfully", Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(RegisterPage.this, "Failed to register user", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterPage.this, "Failed to register user", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {

    }
}