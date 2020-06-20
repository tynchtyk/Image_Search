package com.example.image_search.Service.Repository;

import android.util.Log;

import com.example.image_search.Service.Model.Response_Data;
import com.example.image_search.Service.Network.ApiFactory;
import com.example.image_search.Service.Network.ApiInterface;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchImageRepository {
    private ApiInterface searchService;


    private SearchImageRepository() {
        searchService = ApiFactory.createSearchService();
    }

    private static SearchImageRepository instance;

    public static SearchImageRepository getInstance() {
        if (instance == null) {
            synchronized (SearchImageRepository.class) {
                if (instance == null) {
                    instance = new SearchImageRepository();
                }
            }
        }
        return instance;
    }

    public  MutableLiveData<Response_Data> getImages(String query) {
        MutableLiveData<Response_Data> imageData = new MutableLiveData<>();
        searchService.getSearchImages(query).enqueue(new Callback<Response_Data>() {
            @Override
            public void onResponse(Call<Response_Data> call, Response<Response_Data> response) {
                if (response.isSuccessful()){
//                    Log.v("SearchImageRepository1:", String.valueOf(response.));
                    imageData.setValue(response.body());
                  //  for(int i=0; i<imageData.s)
                    Log.e("ImageData", String.valueOf(imageData));
                }
            }

            @Override
            public void onFailure(Call<Response_Data> call, Throwable t) {
                Log.e("SearchImageRepository2:", " NULLLLLLL ");
                imageData.setValue(null);
            }
        });
        return imageData;
    }

}
