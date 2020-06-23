package com.example.image_search.Service.Entity;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//import androidx.room.PrimaryKey
@Entity(tableName = "favourites")

public class ImageDescription {
    @SerializedName("display_sitename")
    private String display_sitename;

    @SerializedName("image_url")
    private String image;

    @SerializedName("datetime")
    private String datetime;

    @SerializedName("doc_url")
    private String doc_url;

    @SerializedName("height")
    private int height;

    @SerializedName("thumbnail_url")
    private String thumbnail_url;

    @SerializedName("width")
    private int width;

    @PrimaryKey @NonNull
    private String id;

    private boolean isFavourite;

    public String getDisplay_sitename() {
        return display_sitename;
    }
    public void setDisplay_sitename(String display_sitename) {this.display_sitename = display_sitename;}

    public String getImage() { return image; }
    public void setImage(String image) {this.image = image;}

    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) {this.datetime = datetime;}

    public String getDoc_url(){return thumbnail_url;}
    public void setDoc_url(String thumbnail_url) {this.thumbnail_url = thumbnail_url;}

    public int getHeight() { return height; }
    public void setHeight(int height) {this.height = height;}

    public String getThumbnail_url() { return thumbnail_url; }
    public void setThumbnail_url(String thumbnail_url) {this.thumbnail_url = thumbnail_url;}

    public int getWidth() { return width; }
    public void setWidth(int width) {this.width = width;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public boolean getIsFavourite(){return this.isFavourite;}
    public void setIsFavourite(boolean isFavourite) {this.isFavourite = isFavourite;}
}
