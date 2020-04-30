package com.example.projectpraktikum.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectpraktikum.Database.AppDatabase;
import com.example.projectpraktikum.Database.Register;
import com.example.projectpraktikum.R;

public class RegisterActivity extends AppCompatActivity {
    EditText etUserDaftar,etPassDaftar,etEmailDaftar;
    String username,password,email;
    Button btnnDaftar, btnCancel;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = AppDatabase.initDB(getApplicationContext());
        setContentView(R.layout.activity_register);

        etUserDaftar = findViewById(R.id.user_daftar);
        etPassDaftar = findViewById(R.id.pass_daftar);
        etEmailDaftar = findViewById(R.id.email_daftar);

        btnnDaftar = findViewById(R.id.register_activity_daftar);
        btnCancel = findViewById(R.id.register_activity_cancel);


        btnnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    username = etUserDaftar.getText().toString();
                    password = etPassDaftar.getText().toString();
                    email = etEmailDaftar.getText().toString();
                    if (!username.equals("") || !password.equals("")){
                        Register[] reg = appDatabase.dao().getDataUser(username);
                        if (reg[0] != null){
                            etUserDaftar.setError("Userame telah terdaftar");
                        }
                    }
                    else if (!username.equals("")){
                        etPassDaftar.setError("This Field is required");
                    }
                    else if (!password.equals("")){
                        etUserDaftar.setError("This Field is Required");
                    }
                    else {
                        etPassDaftar.setError("This Field is required");
                        etUserDaftar.setError("This Field is Required");
                    }
                }catch (Exception e){
                    inserData();
                    finish();
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void inserData(){
        Register register = new Register();
        register.setUser(username);
        register.setPassword(password);
        register.setEmail(email);
        appDatabase.dao().insertData(register);
        finish();
    }
}
