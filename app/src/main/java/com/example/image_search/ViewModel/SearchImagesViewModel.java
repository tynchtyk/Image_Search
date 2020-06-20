package com.example.image_search.ViewModel;

import com.example.image_search.Service.Model.Response_Data;
import com.example.image_search.Service.Repository.SearchImageRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchImagesViewModel extends ViewModel {
    private MutableLiveData<Response_Data> mutableLiveData;
    private SearchImageRepository imageRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        imageRepository = SearchImageRepository.getInstance();
        mutableLiveData = imageRepository.getImages("cat");

    }

    public LiveData<Response_Data> getImageRepository() {
        return mutableLiveData;
    }
}
