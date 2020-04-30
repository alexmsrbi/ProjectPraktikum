package com.example.projectpraktikum.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectpraktikum.Database.AppDatabase;
import com.example.projectpraktikum.Database.Register;
import com.example.projectpraktikum.R;

public class EditPasswordActivity extends AppCompatActivity {
    EditText etPassBaru,etConfirmPass;
    Button btnEdit,btnCancel;
    String passBaru, konfirmasiPass,username,email;
    int id;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        etPassBaru = findViewById(R.id.new_password);
        etConfirmPass = findViewById(R.id.confirm_pass);
        btnCancel = findViewById(R.id.editPass_activity_cancel);
        btnEdit = findViewById(R.id.edit_activity_password);
        final Intent intent = getIntent();
        appDatabase = AppDatabase.initDB(getApplicationContext());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passBaru = etPassBaru.getText().toString();
                konfirmasiPass = etConfirmPass.getText().toString();
                if (passBaru==konfirmasiPass){
                    username =intent.getStringExtra("Username");
                    email = intent.getStringExtra("email");
                    id = intent.getIntExtra("id",0);
                    Register register = new Register();
                    register.setId(id);
                    register.setUser(username);
                    register.setPassword(passBaru);
                    register.setEmail(email);
                    appDatabase.dao().updateData(register);
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
}
