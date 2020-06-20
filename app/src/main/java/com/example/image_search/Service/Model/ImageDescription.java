package com.example.image_search.Service.Model;

import com.google.gson.annotations.SerializedName;

public class ImageDescription {
    @SerializedName("collection")
    private String collection;

    @SerializedName("image_url")
    private String image;

    @SerializedName("doc_url")
    private String url;

    @SerializedName("datetime")
    private String datetime;

    public String getCollection() {
        return collection;
    }

    public String getImage() {
        return image;
    }

    public String getDatetime() {
        return datetime;
    }
}
