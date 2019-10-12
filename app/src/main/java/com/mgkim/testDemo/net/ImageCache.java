package com.mgkim.testDemo.net;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageCache {
    private LruCache<String, Bitmap> _Cache;
    private int _CacheSize = 500;

    private static class SingleTonHolder {
        public final static ImageCache instance = new ImageCache(500);
    }

    private ImageCache(int cacheSize) {
        this._CacheSize = cacheSize;
        _Cache = new LruCache<>(_CacheSize);
    }


    public static ImageCache getInstance() {
        return SingleTonHolder.instance;
    }

    public boolean findCacheBitmap(String url) {
        return _Cache.get(url) != null;
    }

    public Bitmap getBitmap(String url) {
        return _Cache.get(url);
    }

    public void setBitmap(String key, Bitmap bitmap) {
        _Cache.put(key, bitmap);
    }
}
