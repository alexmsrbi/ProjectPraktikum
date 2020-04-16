package com.example.projectpraktikum.service;

import com.example.projectpraktikum.model.home.HomeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeRepository {
    @GET("/trending?key=3V1YT94GM0ZD")
    Call<HomeResponse> getHomeRespone;
}
