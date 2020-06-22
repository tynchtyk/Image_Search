package com.example.image_search.Service.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.image_search.Service.DataBase.FavouritesDao;
import com.example.image_search.Service.DataBase.FavouritesDataBase;
import com.example.image_search.Service.Model.ImageDescription;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FavouriteImagesRepository {

    private FavouritesDao favouritesDao;

    private LiveData<List<ImageDescription>> allFavourites;

    public FavouriteImagesRepository(Application application) {
        FavouritesDataBase database = FavouritesDataBase.getInstance(application);
        favouritesDao = database.favouritesDao();
        allFavourites = favouritesDao.getAllFavourites();
    }

    public void insertFavourite(ImageDescription imageDescription) {
        new InsertNoteAsyncTask(favouritesDao).execute(imageDescription);
    }

    public void deleteFavourite(ImageDescription imageDescription) {
        new DeleteNoteAsyncTask(favouritesDao).execute(imageDescription);
    }

    public LiveData<List<ImageDescription>> getAllFavourites() {
        return allFavourites;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<ImageDescription, Void, Void> {
        private FavouritesDao favouritesDao;
        private InsertNoteAsyncTask(FavouritesDao favouritesDao) {
            this.favouritesDao = favouritesDao;
        }
        @Override
        protected Void doInBackground(ImageDescription... images) {
            favouritesDao.insertFavourite(images[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<ImageDescription, Void, Void> {
        private FavouritesDao noteDao;
        private DeleteNoteAsyncTask(FavouritesDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(ImageDescription... images) {
            noteDao.deleteFavourite(images[0]);
            return null;
        }
    }
}

