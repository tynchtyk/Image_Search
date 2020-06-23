package com.example.image_search.Service.Entity;

import com.google.gson.annotations.SerializedName;

public class Meta_Data {
    @SerializedName("totalCount")
    private int total_count;

    @SerializedName("pageableCount")
    private int pageable_count;

    @SerializedName("isEnd")
    private boolean is_end;

    public boolean isEnd() {
        return is_end;
    }
}
