package com.example.projectpraktikum.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectpraktikum.Database.AppDatabase;
import com.example.projectpraktikum.Database.Register;
import com.example.projectpraktikum.view.activity.EditDataActivity;
import com.example.projectpraktikum.R;
import com.example.projectpraktikum.Session;
import com.example.projectpraktikum.view.activity.EditPasswordActivity;
import com.example.projectpraktikum.view.activity.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    TextView tvUser,tvEmail,tvChangeProfile,tvChangePass,tvDeactivate;
    Context context;
    AppDatabase appDatabase;
    Button btnLogout;
    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase = AppDatabase.initDB(context);

        tvUser = view.findViewById(R.id.tvuser);
        tvEmail = view.findViewById(R.id.Email);
        tvChangeProfile = view.findViewById(R.id.tvProfile);
        tvChangePass = view.findViewById(R.id.tvPassword);
        tvDeactivate = view.findViewById(R.id.tvHapus);
        btnLogout = view.findViewById(R.id.LogOut);
        tvChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register[] register;
                String userName,Pass;
                userName = Session.getUser(getActivity());
                Pass = Session.getPass(getActivity());
                register = appDatabase.dao().getDataLogin(userName,Pass);
                Intent edit = new Intent(getActivity(), EditDataActivity.class);
                edit.putExtra("User", register[0].getUser());
                edit.putExtra("email",register[0].getEmail());
                edit.putExtra("id",register[0].getId());
                edit.putExtra("password",register[0].getPassword());
                startActivity(edit);
            }
        });

        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register[] register;
                String userName;
                userName = Session.getUser(getActivity());
                register = appDatabase.dao().getDataUser(userName);
                Intent edit = new Intent(getActivity(), EditPasswordActivity.class);
                edit.putExtra("User", register[0].getUser());
                edit.putExtra("email",register[0].getEmail());
                edit.putExtra("id",register[0].getId());
                startActivity(edit);
            }
        });

        tvDeactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus();
                Session.clearSession(getActivity());
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.clearSession(getActivity());
                Intent logout = new Intent(getActivity(),LoginActivity.class);
                startActivity(logout);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvUser.setText(Session.getUser(getActivity()));
    }

    public void hapus(){
        Register register = new Register();
        String password = Session.getPass(getActivity());
        String User = Session.getUser(getActivity());
        Register[] reg = appDatabase.dao().getDataLogin(User,password);
        register.setId(reg[0].getId());
        appDatabase.dao().deleteData(register);
    }
}
