package com.example.projectpraktikum.service;

import com.example.projectpraktikum.model.search.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchRepository {
    @GET("search?q=excited&key=3V1YT94GM0ZD&limit=8")
    Call<SearchResponse> getSearchDiscover();
}
