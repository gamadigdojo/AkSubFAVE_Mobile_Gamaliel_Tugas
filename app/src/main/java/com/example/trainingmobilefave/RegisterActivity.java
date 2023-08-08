package com.example.trainingmobilefave;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user-entered username and email from the registration form
                EditText editTextUsername = findViewById(R.id.Username);
                EditText editTextEmail = findViewById(R.id.Email);
                String username = editTextUsername.getText().toString();
                String email = editTextEmail.getText().toString();

                // Save data to Shared Preferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", username);
                editor.putString("email", email);
                editor.apply();

                // Show AlertDialog for successful registration
                showSuccessDialog();
            }
        });
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registration Successful");
        builder.setMessage("Congratulations! Your registration is successful.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
