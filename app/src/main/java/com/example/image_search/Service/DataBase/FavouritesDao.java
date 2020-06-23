package com.example.image_search.Service.DataBase;

import com.example.image_search.Service.Entity.ImageDescription;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FavouritesDao {

    @Insert
    void insertFavourite(ImageDescription imageDescription);

    @Delete
    void deleteFavourite(ImageDescription imageDescription);

    @Query("SELECT * FROM favourites")
    LiveData<List<ImageDescription>> getAllFavourites();

    @Query("DELETE FROM favourites")
    void deleteAllFavourites();

}
