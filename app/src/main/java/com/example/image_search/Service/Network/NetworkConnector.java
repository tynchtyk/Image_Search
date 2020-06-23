package com.example.image_search.Service.Network;

import android.util.Log;

import com.example.image_search.Service.Entity.ImageDescription;
import com.example.image_search.Service.Entity.Response_Data;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkConnector {
    private ApiInterface searchService;

    private int page_count = 1;

    private int Default_Size = 10;

    MutableLiveData<List< ImageDescription >> liveQueryResponse;

    private NetworkConnector() {
        liveQueryResponse = new MutableLiveData<List<ImageDescription>>();
        searchService = ApiFactory.createSearchService();
    }

    private static NetworkConnector instance;

    public static NetworkConnector getInstance() {
        if (instance == null) {
            synchronized (NetworkConnector.class) {
                if (instance == null) {
                    instance = new NetworkConnector();
                }
            }
        }
        return instance;
    }

    public void fetchQueryImages(String query, List<ImageDescription> localImages) {
        List< ImageDescription > fetchQueryResponse = new ArrayList<ImageDescription>();
        searchService.getSearchImages(query,page_count,Default_Size).enqueue(new Callback<Response_Data>() {
            @Override
            public void onResponse(Call<Response_Data> call, Response<Response_Data> response) {
                if (response.isSuccessful()){
                    fetchQueryResponse.addAll(response.body().getImaghes());
//                    Log.v("SearchImageRepository1:", String.valueOf(response.));
                    for(ImageDescription i : fetchQueryResponse){
                        i.setIsFavourite(false);
                        i.setId(i.getDoc_url() + i.getImage() + i.getThumbnail_url());
                    }
                    if(localImages != null){
                        for(ImageDescription i : fetchQueryResponse){
                            Log.e("FetchedQuery", i.getId());
                            for(ImageDescription j : localImages) {
                                Log.e("FetchedFav", i.getId() + "\n" + j.getId());
                                if(i.getId().equals(j.getId())){
                                    Log.e("IDMATCH", j.getImage());
                                    i.setIsFavourite(j.getIsFavourite());
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        Log.e("localimages", "NULL");
                    }
                    liveQueryResponse.postValue(fetchQueryResponse);
                    //  for(int i=0; i<imageData.s)
                    //Log.e("ImageData", String.valueOf(imageData));

                }
            }

            @Override
            public void onFailure(Call<Response_Data> call, Throwable t) {
                Log.e("SearchImageRepository2:", " NULLLLLLL ");
                //liveQueryResponse.postValue(fetchQueryResponse);
//                imageData.postValue(null);
            }
        });
        //return fetchQueryResponse;
    }
    public MutableLiveData<List<ImageDescription>> getAllImages(){
        return liveQueryResponse;
    }
}