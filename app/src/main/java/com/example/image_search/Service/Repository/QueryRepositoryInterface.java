package com.example.image_search.Service.Repository;

import android.app.Application;

import com.example.image_search.Service.DataBase.DataBaseConnector;
import com.example.image_search.Service.Entity.ImageDescription;
import com.example.image_search.Service.Network.NetworkConnector;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface QueryRepositoryInterface {

    public void NewQuery(String query);

    public void deleteFavourite(ImageDescription imageDescription);
    public void insertFavourite(ImageDescription imageDescription);

    public LiveData<List<ImageDescription>> getQueryImages();
    public LiveData<List<ImageDescription>> getAllFavourites();
}
