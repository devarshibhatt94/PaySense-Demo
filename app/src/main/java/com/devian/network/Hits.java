package com.devian.network;

import com.google.gson.annotations.SerializedName;

public class Hits {

    @SerializedName("id")
    private int id;
    @SerializedName("pageURL")
    private String pageURL;
    @SerializedName("type")
    private String type;
    @SerializedName("tags")
    private String tags;
    @SerializedName("previewURL")
    private String previewURL;
    @SerializedName("previewWidth")
    private int previewWidth;
    @SerializedName("previewHeight")
    private int previewHeight;
    @SerializedName("webformatURL")
    private String webformatURL;
    @SerializedName("webformatWidth")
    private int webformatWidth;
    @SerializedName("webformatHeight")
    private int webformatHeight;
    @SerializedName("largeImageURL")
    private String largeImageURL;
    @SerializedName("fullHDURL")
    private String fullHDURL;
    @SerializedName("imageURL")
    private String imageURL;
    @SerializedName("imageWidth")
    private int imageWidth;
    @SerializedName("imageHeight")
    private int imageHeight;
    @SerializedName("imageSize")
    private int imageSize;
    @SerializedName("views")
    private int views;
    @SerializedName("downloads")
    private int downloads;
    @SerializedName("favorites")
    private int favorites;
    @SerializedName("likes")
    private int likes;
    @SerializedName("comments")
    private int comments;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("user")
    private String user;
    @SerializedName("userImageURL")
    private String userImageURL;

    public int getId() {
        return id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getType() {
        return type;
    }

    public String getTags() {
        return tags;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public String getFullHDURL() {
        return fullHDURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageSize() {
        return imageSize;
    }

    public int getViews() {
        return views;
    }

    public int getDownloads() {
        return downloads;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser() {
        return user;
    }

    public String getUserImageURL() {
        return userImageURL;
    }
}
