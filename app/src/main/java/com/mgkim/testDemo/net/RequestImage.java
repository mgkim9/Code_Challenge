package com.mgkim.testDemo.net;

import android.graphics.Bitmap;

import com.mgkim.testDemo.net.widget.ImageFile;
import com.mgkim.testDemo.utils.FormatUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;

public class RequestImage extends RequestGet {
    private String url;
    private HttpGet httpGet;
    private ImageFile imgfile;

    public RequestImage(String url) {
        this.url = url;
        httpGet = new HttpGet(url);
        imgfile = new ImageFile(url);
        needRetry = 3;
    }

    @Override
    public boolean needRetry() {
        return super.needRetry();
    }

    @Override
    public HttpGet getHttpGet() {
        return httpGet;
    }

    @Override
    public void onResult(HttpResponse res) {
        InputStream is = null;
        boolean isSuccess = false;
        try {
            is = res.getEntity().getContent();
            isSuccess = imgfile.writeFile(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(isSuccess) {
            notifyResult(isSuccess);
        }
    }

    @Override
    public void released() {
        imgfile = null;
        httpGet = null;
        super.released();
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return FormatUtil.getFileName(url);
    }

    public Bitmap getBitmap() {
        return imgfile.getBitmap();
    }}
