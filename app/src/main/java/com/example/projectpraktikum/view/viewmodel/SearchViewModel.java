package com.example.projectpraktikum.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.projectpraktikum.model.search.SearchResponse;
import com.example.projectpraktikum.model.search.SearchResultsItem;
import com.example.projectpraktikum.service.ApiSearch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    private ApiSearch apiSearch;
    private MutableLiveData<ArrayList<SearchResultsItem>> listDiscoverSearch = new MutableLiveData<>();

    public void setSearchDiscover(){
        if (this.apiSearch == null){
            apiSearch = new ApiSearch();
        }
        apiSearch.getApiSearch().getSearchDiscover().enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                if (searchResponse != null && searchResponse.getResults() != null){
                    ArrayList<SearchResultsItem> searchResultsItems = searchResponse.getResults();
                    listDiscoverSearch.postValue(searchResultsItems);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }
    public LiveData<ArrayList<SearchResultsItem>> getSearchDiscover(){
        return listDiscoverSearch;
    }
}
