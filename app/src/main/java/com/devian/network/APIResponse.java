package com.devian.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse {

    @SerializedName("total")
    private int total;
    @SerializedName("totalHits")
    private int totalHits;
    @SerializedName("hits")
    private List<Hits> hits;

    public int getTotal() {
        return total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public List<Hits> getHits() {
        return hits;
    }
}
