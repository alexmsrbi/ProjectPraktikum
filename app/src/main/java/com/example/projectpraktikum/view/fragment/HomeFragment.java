package com.example.projectpraktikum.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectpraktikum.R;
import com.example.projectpraktikum.adapter.HomeAdapter;
import com.example.projectpraktikum.model.home.HomeResult;
import com.example.projectpraktikum.view.viewmodel.HomeViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter;
    private RecyclerView rvHomeDiscover;
    private HomeViewModel homeViewModel;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeAdapter = new HomeAdapter(getContext());
        homeAdapter.notifyDataSetChanged();

        rvHomeDiscover = view.findViewById(R.id.rvMain);
        rvHomeDiscover.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setHomeDiscover();
        homeViewModel.getHomeDiscover().observe(this, getHomeDiscover);

        rvHomeDiscover.setAdapter(homeAdapter);
    }

    private Observer<ArrayList<HomeResult>> getHomeDiscover = new Observer<ArrayList<HomeResult>>() {
        @Override
        public void onChanged(ArrayList<HomeResult> homeResults) {
            if (homeAdapter != null){
                homeAdapter.setData(homeResults);
            }
        }
    };
}
