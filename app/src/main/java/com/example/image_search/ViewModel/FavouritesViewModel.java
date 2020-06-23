package com.example.image_search.ViewModel;

import android.app.Application;

import com.example.image_search.Service.Entity.ImageDescription;
import com.example.image_search.Service.DataBase.DataBaseConnector;
import com.example.image_search.Service.Repository.QueryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavouritesViewModel extends AndroidViewModel {
    private QueryRepository queryRepository;
    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        queryRepository = QueryRepository.getInstance(application);
    }
    public void onToggleClicked(ImageDescription imageDescription){
            queryRepository.deleteFavourite(imageDescription);
    }

    public LiveData<List<ImageDescription>> getAllFavourites() {
        return queryRepository.getAllFavourites();
    }
}
