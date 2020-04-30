package com.example.projectpraktikum.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectpraktikum.Database.AppDatabase;
import com.example.projectpraktikum.Database.Register;
import com.example.projectpraktikum.R;
import com.example.projectpraktikum.Session;

public class LoginActivity extends AppCompatActivity {
    EditText etUser,etPassword;
    public String user,pass;
    Button btnSignIn, btnSignUp;
    Register[] reg;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        appDatabase = AppDatabase.initDB(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUser=findViewById(R.id.Username);
        etPassword=findViewById(R.id.Password);
        btnSignIn = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnDaftar);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    user = etUser.getText().toString();
                    pass = etPassword.getText().toString();
                    reg = appDatabase.dao().getDataLogin(user,pass);
                    if (reg[0]!=null){
                        Session.setUser(getBaseContext(),user);
                        Session.setPass(getBaseContext(),pass);
                        Session.setStatus(getBaseContext(),true);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getBaseContext(),"username/password salah",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Username/Password belum terisi",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambah = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(tambah);
            }
        });
    }

}
