package com.example.projectpraktikum.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectpraktikum.R;
import com.example.projectpraktikum.adapter.HomeAdapter;
import com.example.projectpraktikum.adapter.SearchAdapter;
import com.example.projectpraktikum.model.home.HomeResult;
import com.example.projectpraktikum.model.search.SearchResponse;
import com.example.projectpraktikum.model.search.SearchResultsItem;
import com.example.projectpraktikum.view.viewmodel.HomeViewModel;
import com.example.projectpraktikum.view.viewmodel.SearchViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private SearchAdapter searchAdapter;
    private RecyclerView rvSearchDiscover;
    private SearchViewModel searchViewModel;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchAdapter = new SearchAdapter(getContext());
        searchAdapter.notifyDataSetChanged();

        rvSearchDiscover = view.findViewById(R.id.rvSearch);
        rvSearchDiscover.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.setSearchDiscover();
        searchViewModel.getSearchDiscover().observe(this, getHomeDiscover);

        rvSearchDiscover.setAdapter(searchAdapter);
    }

    private Observer<ArrayList<SearchResultsItem>> getHomeDiscover = new Observer<ArrayList<SearchResultsItem>>() {
        @Override
        public void onChanged(ArrayList<SearchResultsItem> searchResultsItems) {
            if (searchAdapter != null){
                searchAdapter.setData(searchResultsItems);
            }
        }
    };

}
