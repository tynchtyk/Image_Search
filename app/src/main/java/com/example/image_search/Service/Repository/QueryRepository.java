package com.example.image_search.Service.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.image_search.Service.DataBase.DataBaseConnector;
import com.example.image_search.Service.DataBase.FavouritesDao;
import com.example.image_search.Service.Entity.ImageDescription;
import com.example.image_search.Service.Network.NetworkConnector;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class QueryRepository {
    private  MutableLiveData<List<ImageDescription>> queryImages;

    private  LiveData<List<ImageDescription>> favouriteImages;

    private NetworkConnector networkConnector;

    private DataBaseConnector dataBaseConnector;

    private static QueryRepository instance;

    public QueryRepository(@NonNull Application application){
        networkConnector = NetworkConnector.getInstance();
        dataBaseConnector = DataBaseConnector.getInstance(application);
        queryImages = networkConnector.getAllImages();
        //queryImages = new MutableLiveData<List<ImageDescription>>();

        favouriteImages = dataBaseConnector.getAllFavourites();
        /*if(favouriteImages.getValue() == null){
            Log.e("DB", "null");
        }*/
    }

    public static QueryRepository getInstance(@NonNull Application application){
        if(instance == null){
            instance = new QueryRepository(application);
        }
        return instance;
    }

    public void NewQuery(String query){
        if(favouriteImages.getValue() == null){
            favouriteImages = dataBaseConnector.getAllFavourites();
            Log.e("localImagesValue", "NULL");
        }
        networkConnector.fetchQueryImages(query, favouriteImages.getValue());
        //List<ImageDescription> localImages = dataBaseConnector.getAllFavourites().getValue();
        /*for(ImageDescription j : localImages) {
            Log.e("FetchedQuery", j.getId());
                for(ImageDescription i : fetchedQueryImages){
                Log.e("FetchedFav", i.getId());
                if(i.getId() == j.getId()){
                    i.setIsFavourite(j.getIsFavourite());
                }
            }
        }
        for(int i=0; i<1000000000; i++){

        }
        Log.e("fetchresp", fetchedQueryImages.toString());
        queryImages.postValue(fetchedQueryImages);*/
    }

    public void deleteFavourite(ImageDescription imageDescription){
        dataBaseConnector.deleteFavourite(imageDescription);
        updateQueryImages(imageDescription, "delete");
    }

    public void insertFavourite(ImageDescription imageDescription){
        dataBaseConnector.insertFavourite(imageDescription);
        updateQueryImages(imageDescription,  "insert");
    }

    public LiveData<List<ImageDescription>> getQueryImages(){
        return queryImages;
    }

    public LiveData<List<ImageDescription>> getAllFavourites(){
        return favouriteImages;
    }

    private void updateQueryImages(ImageDescription imageDescription, String action){
        List<ImageDescription> _queryImages = queryImages.getValue();
        if(_queryImages == null) {
            Log.e("updresponsequeryimages", "NULLLLL");
            return ;
        }
        if(_queryImages.isEmpty()){
            Log.e("updresponsequeryimages", "EMPTY");
            return ;
        }

        for(ImageDescription i : _queryImages) {
            if(i.getId().equals(imageDescription.getId())){
                boolean flag = action.equals("delete")  ? false : true;
                Log.e("Updateequal", i.getImage());
                i.setIsFavourite(flag);
            }
        }
        queryImages.postValue(_queryImages);
    }
}
