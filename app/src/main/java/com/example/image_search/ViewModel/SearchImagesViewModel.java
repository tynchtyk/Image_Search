package com.example.image_search.ViewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.image_search.Service.Model.ImageDescription;
import com.example.image_search.Service.Model.Response_Data;
import com.example.image_search.Service.Repository.SearchImageRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchImagesViewModel extends ViewModel {
    //private MutableLiveData<Response_Data> mutableLiveData = new MutableLiveData<Response_Data>();
    private SearchImageRepository imageRepository;

    public SearchImagesViewModel() {
        imageRepository = SearchImageRepository.getInstance();
    }

    public void onsubmitQuery(String query){
 //       mutableLiveData.postValue(imageRepository.query(query).getValue());
          imageRepository.query(query);

    }

    public LiveData<Response_Data> getImageRepository() {
        //return mutableLiveData;
        return imageRepository.getLiveData();
    }
}