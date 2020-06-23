package com.example.image_search.ViewModel;

import android.app.Application;

import com.example.image_search.Service.Entity.ImageDescription;
import com.example.image_search.Service.Entity.Response_Data;
import com.example.image_search.Service.Network.NetworkConnector;
import com.example.image_search.Service.Repository.QueryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SearchImagesViewModel extends AndroidViewModel {
    //private MutableLiveData<Response_Data> mutableLiveData = new MutableLiveData<Response_Data>();
    private QueryRepository queryRepository;

    public SearchImagesViewModel(@NonNull Application application) {
        super(application);
        queryRepository = QueryRepository.getInstance(application);
    }

    public void onsubmitQuery(String query){
 //       mutableLiveData.postValue(imageRepository.query(query).getValue());
        queryRepository.NewQuery(query);

    }
    public void onToggleClicked(ImageDescription imageDescription){
        if(imageDescription.getIsFavourite()) {
            queryRepository.deleteFavourite(imageDescription);
        }
        else {
            queryRepository.insertFavourite(imageDescription);
        }
    }
    public LiveData<List<ImageDescription>> liveQueryImages() {
        //return mutableLiveData;
        return queryRepository.getQueryImages();
    }
}