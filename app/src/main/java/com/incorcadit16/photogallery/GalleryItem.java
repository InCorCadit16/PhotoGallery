package com.incorcadit16.photogallery;

import android.net.Uri;

public class GalleryItem {
    private String mCaption;
    private String mId;
    private String mUrl;
    private String mOwnerId;

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(String mOwnerId) {
        this.mOwnerId = mOwnerId;
    }


    public Uri getPhotoPageUri() {
        return Uri.parse("https://www.flickr.com/photos/")
                .buildUpon()
                .appendPath(mOwnerId)
                .appendPath(mId)
                .build();
    }

    @Override
    public String toString() {
        return mCaption;

    }
}
