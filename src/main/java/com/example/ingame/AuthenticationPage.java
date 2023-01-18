package com.example.ingame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.UUID;

public class AuthenticationPage extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextView register, forgotPassword;
    EditText editLoginEmail, editLoginPassword;

    //private SharedViewModel sharedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_page);
        mAuth = FirebaseAuth.getInstance();
        //sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        editLoginEmail = findViewById(R.id.login_email);
        editLoginPassword = findViewById(R.id.login_password);


        register = findViewById(R.id.login_register);
        register.setOnClickListener(__ -> registerPage());

        Button signInButton = findViewById(R.id.login);
        signInButton.setOnClickListener(__ -> authUser());

        forgotPassword = findViewById(R.id.forgotpassword);
        forgotPassword.setOnClickListener(__ -> forgotPassword());

    }

    private void forgotPassword() {
        Intent intentChangeForgotPasswordPage = new Intent(this, ForgotPassword.class);
        startActivity(intentChangeForgotPasswordPage);
    }


    private void authUser() {
        String email = editLoginEmail.getText().toString().trim();
        String password = editLoginPassword.getText().toString().trim();
        Log.v("teste",""+email+password);
        if(email.isEmpty()){
            editLoginEmail.setError("Email is required");
            editLoginEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editLoginEmail.setError("Please provide valid email!");
            editLoginEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editLoginPassword.setError("Password is required");
            editLoginPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Log.v("datass", "" + email);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userID = user.getUid();
                if(user.isEmailVerified()){
                    //sharedViewModel.addPlayer(userID);
                    mainPage(userID);

                }
                else {
                    user.sendEmailVerification();
                    Toast.makeText(AuthenticationPage.this,"Check your email to verify your account",Toast.LENGTH_LONG).show();

                }
            }else {
                Toast.makeText(AuthenticationPage.this,"Check your credentials",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void registerPage() {
        Intent intentChangeRegisterPage = new Intent(this, RegisterPage.class);
        startActivity(intentChangeRegisterPage);
        finish();

    }

    private void mainPage(String playerUUid) {
        Intent intentChangeMainPage = new Intent(this, MainActivity.class);
        intentChangeMainPage.putExtra("playerID", playerUUid);
        startActivity(intentChangeMainPage);
        finish();

    }



}