package com.example.image_search.Service.Entity;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Response_Data {
    @SerializedName("meta")
    private Meta_Data metaData;

    @SerializedName("documents")
    private ArrayList<ImageDescription> images;

    public ArrayList<ImageDescription> getImaghes() {
        return images;
    }

    public Meta_Data getMetaData() {
        return metaData;
    }
}
