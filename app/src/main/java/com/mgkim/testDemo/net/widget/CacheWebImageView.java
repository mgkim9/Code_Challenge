package com.mgkim.testDemo.net.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.mgkim.testDemo.net.IRequest;
import com.mgkim.testDemo.net.ImageCache;
import com.mgkim.testDemo.net.RequestImage;
import com.mgkim.testDemo.utils.FormatUtil;

public class CacheWebImageView extends WebImageView {

    public CacheWebImageView(Context context) {
        super(context);
    }

    public CacheWebImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CacheWebImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void checkRequestImage(String url) {
        Bitmap bitmap = ImageCache.getInstance().getBitmap(FormatUtil.getFileName(url));
        if(bitmap != null) {
            setImageBitmap(bitmap);
        } else {
            super.checkRequestImage(url);
        }
    }

    @Override
    protected void applyImage(Bitmap bitmap, String url) {
        if(bitmap != null) {
            ImageCache.getInstance().setBitmap(FormatUtil.getFileName(url), bitmap);
        }
        setImageBitmap(bitmap);
    }

}
