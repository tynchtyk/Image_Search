package com.example.image_search.Service.DataBase;

import android.app.Application;
import android.os.AsyncTask;

import com.example.image_search.Service.DataBase.FavouritesDao;
import com.example.image_search.Service.DataBase.FavouritesDataBase;
import com.example.image_search.Service.Entity.ImageDescription;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DataBaseConnector {

    private FavouritesDao favouritesDao;

    private LiveData<List<ImageDescription>> allFavourites;
    private static DataBaseConnector instance;
    public DataBaseConnector(Application application) {
        FavouritesDataBase database = FavouritesDataBase.getInstance(application);
        favouritesDao = database.favouritesDao();
        allFavourites = favouritesDao.getAllFavourites();
    }
    public static DataBaseConnector getInstance(Application application){
        if(instance == null) {
            instance = new DataBaseConnector(application);
        }
        return instance;
    }

    public void insertFavourite(ImageDescription imageDescription) {
        new InsertNoteAsyncTask(favouritesDao).execute(imageDescription);
    }

    public void deleteFavourite(ImageDescription imageDescription) {
        new DeleteNoteAsyncTask(favouritesDao).execute(imageDescription);
    }
    public void deleteAllFavourites(){
        new DeleteAllAsyncTask(favouritesDao).execute();
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
    private static class DeleteAllAsyncTask extends AsyncTask < Void, Void, Void> {
        private FavouritesDao noteDao;
        private DeleteAllAsyncTask(FavouritesDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... images) {
            noteDao.deleteAllFavourites();
            return null;
        }
    }
}

