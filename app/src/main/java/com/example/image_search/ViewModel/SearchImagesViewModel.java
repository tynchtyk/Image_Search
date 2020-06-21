package com.example.image_search.ViewModel;

import com.example.image_search.Service.Model.Response_Data;
import com.example.image_search.Service.Repository.SearchImageRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchImagesViewModel extends ViewModel {
    private MutableLiveData<Response_Data> mutableLiveData;
    private SearchImageRepository imageRepository;

    public void onsubmitQuery(String query) {

        mutableLiveData = imageRepository.getImages(query);
    }
    public void init(){
        if(mutableLiveData != null){
            return;
        }
        imageRepository = SearchImageRepository.getInstance();
        mutableLiveData = new MutableLiveData<Response_Data>();
    }

    public LiveData<Response_Data> getImageRepository() {
        return mutableLiveData;
    }
}
