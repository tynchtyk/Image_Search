package com.example.image_search.Service.DataBase;

import android.content.Context;

import com.example.image_search.Service.Model.ImageDescription;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ImageDescription.class}, version = 1, exportSchema = false)
public abstract class FavouritesDataBase extends RoomDatabase {

    private static FavouritesDataBase favouritesDataBaseInstance;

    public abstract FavouritesDao favouritesDao();

    public static synchronized FavouritesDataBase getInstance(Context context){
        if(favouritesDataBaseInstance == null) {
            favouritesDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    FavouritesDataBase.class,
                    "favourites")
                    .fallbackToDestructiveMigration()
                    //.addCallback()
                    .build();
        }
        return favouritesDataBaseInstance;
    }

    private static FavouritesDataBase.Callback roomCallback = new FavouritesDataBase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
