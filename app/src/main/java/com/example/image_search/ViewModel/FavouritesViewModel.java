package com.example.image_search.ViewModel;

import android.app.Application;

import com.example.image_search.Service.Model.ImageDescription;
import com.example.image_search.Service.Repository.FavouriteImagesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FavouritesViewModel extends AndroidViewModel {
    private FavouriteImagesRepository repository;
    private LiveData<List<ImageDescription>> allFavouriteImages;
    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        repository = new FavouriteImagesRepository(application);
        allFavouriteImages = repository.getAllFavourites();
    }
    public void insert(ImageDescription note) {
        repository.insertFavourite(note);
    }
    public void delete(ImageDescription note) {
        repository.deleteFavourite(note);
    }
    public void deleteAll(){repository.deleteAllFavourites();}

    public LiveData<List<ImageDescription>> getAllFavourites() {
        return allFavouriteImages;
    }
}
