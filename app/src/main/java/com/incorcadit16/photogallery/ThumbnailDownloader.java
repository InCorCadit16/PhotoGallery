package com.incorcadit16.photogallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ThumbnailDownloader<T> extends HandlerThread {
    private final static String TAG = "ThumbnailDownloader";
    private final static int MESSAGE_DOWNLOADED = 0;

    private boolean mHasQuit = false;
    private Handler mRequestHandler;
    private ConcurrentMap<T,String> mRequestMap = new ConcurrentHashMap<>();
    private Handler mResponseHandler;
    private ThumbnailDownloadListener<T> mThumbnailDownloadListener;

    public interface ThumbnailDownloadListener<T> {
        void onThumbnailDownloaded(T target, Bitmap thumbnail);
    }

    public void setThumbnailDownloadListener(ThumbnailDownloadListener<T> listener) {
        mThumbnailDownloadListener = listener;
    }

    public ThumbnailDownloader(Handler handler) {
        super(TAG);
        mResponseHandler = handler;
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_DOWNLOADED) {
                    T target = (T) msg.obj;
                    Log.i(TAG,"Got a request for URL" + mRequestMap.get(target));

                    handleRequest(target);
                }
            }
        };
    }

    @Override
    public boolean quit() {
        mHasQuit = true;
        return super.quit();
    }

    void queueThumbnail(T target, String url) {
        Log.i(TAG,"Got a URL: " + url);
        if (url == null) {
            mRequestMap.remove(target);
        } else {
            mRequestMap.put(target,url);
            mRequestHandler.obtainMessage(MESSAGE_DOWNLOADED, target)
                    .sendToTarget();
        }
    }

    private void handleRequest(T target) {
            try {
                String url = mRequestMap.get(target);
                Bitmap bitmap;

                if (url == null) return;

                if (PhotoGalleryFragment.cache.get((PhotoGalleryFragment.PhotoHolder) target) != null) {
                    bitmap = PhotoGalleryFragment.cache.get((PhotoGalleryFragment.PhotoHolder) target);
                } else {
                    byte[] byteInfo = new FlickrFetchr().getUrlByte(url);
                    bitmap = BitmapFactory.decodeByteArray(byteInfo, 0, byteInfo.length);
                    Log.i(TAG, "Bitmap created");
                }

                mResponseHandler.post(() -> {
                    if (mRequestMap.get(target) != url || mHasQuit) return;

                    mRequestMap.remove(target);
                    mThumbnailDownloadListener.onThumbnailDownloaded(target,bitmap);
                });
            } catch (IOException ioe) {
                Log.e(TAG, "Can't load photo: " + ioe);
            }
    }

    void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_DOWNLOADED);
        mRequestMap.clear();
    }
}
