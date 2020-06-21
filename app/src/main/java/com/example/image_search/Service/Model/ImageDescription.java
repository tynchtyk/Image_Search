package com.example.image_search.Service.Model;

import com.google.gson.annotations.SerializedName;
//import androidx.room.PrimaryKey
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

    public String getDisplay_sitename() {
        return display_sitename;
    }

    public String getImage() { return image; }

    public String datetime() { return datetime; }

    public String doc_url() { return doc_url; }

    public int height() { return height; }

    public String thumbnail_url() { return thumbnail_url; }

    public int width() { return width; }


}
