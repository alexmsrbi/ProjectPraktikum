package com.example.projectpraktikum.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectpraktikum.model.home.HomeResponse;
import com.example.projectpraktikum.model.home.HomeResult;
import com.example.projectpraktikum.service.ApiHome;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private ApiHome apiHome;
    private MutableLiveData<ArrayList<HomeResult>> listDiscoverHome = new MutableLiveData<>();

    public void setHomeDiscover(){
        if (this.apiHome == null){
            apiHome = new ApiHome();
        }
        apiHome.getApiHome().getHomeDiscover().enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                HomeResponse responseHome = response.body();
                if (responseHome != null && responseHome.getResults() != null){
                    ArrayList<HomeResult> homeResultItem = responseHome.getResults();
                    listDiscoverHome.postValue(homeResultItem);
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {

            }
        });
    }
    public LiveData<ArrayList<HomeResult>> getHomeDiscover(){
        return listDiscoverHome;
    }

}
