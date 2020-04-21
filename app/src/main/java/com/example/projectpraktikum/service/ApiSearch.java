package com.example.projectpraktikum.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSearch {
    private Retrofit retrofit;
    public SearchRepository getApiSearch(){
        String BASE_URL = "https://api.tenor.com/v1/";
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(SearchRepository.class);
    }
}
