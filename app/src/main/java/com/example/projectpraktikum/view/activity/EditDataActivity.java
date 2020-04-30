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
import com.example.projectpraktikum.Session;

public class EditDataActivity extends AppCompatActivity {
    EditText etUserDaftar,etEmailDaftar;
    Button btnEdit,btnCancel;
    String userDaftar, emailDaftar;
    int id;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        etUserDaftar =  findViewById(R.id.user_edit);
        etEmailDaftar = findViewById(R.id.email_edit);
        btnEdit = findViewById(R.id.edit_activity_data);
        btnCancel = findViewById(R.id.edit_activity_cancel);
        appDatabase = AppDatabase.initDB(getApplicationContext());
        final Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        etUserDaftar.setText(intent.getStringExtra("User"));
        etEmailDaftar.setText(intent.getStringExtra("email"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDaftar = etUserDaftar.getText().toString();
                emailDaftar = etEmailDaftar.getText().toString();
                Register register = new Register();
                register.setId(id);
                register.setUser(userDaftar);
                register.setEmail(emailDaftar);
                register.setPassword(intent.getStringExtra("password"));
                appDatabase.dao().updateData(register);
                Session.setUser(getBaseContext(),userDaftar);
                finish();
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
