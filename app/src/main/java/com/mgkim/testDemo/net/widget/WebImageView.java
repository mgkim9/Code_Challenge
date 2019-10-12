package com.mgkim.testDemo.net.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.mgkim.testDemo.net.IRequest;
import com.mgkim.testDemo.net.IResultReceiver;
import com.mgkim.testDemo.net.NetAPI;
import com.mgkim.testDemo.net.RequestImage;

public class WebImageView extends AppCompatImageView implements IResultReceiver {

    private final int LOAD_SUCCSESS = 1;
    private final int LOAD_FAIL = -1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == LOAD_SUCCSESS) {
                if (msg.obj instanceof RequestImage) {
                    RequestImage requestImage = (RequestImage) msg.obj;
                    Bitmap bitmap = requestImage.getBitmap();
                    if (bitmap != null) {
                        applyImage(bitmap, requestImage.getUrl());
                    }
                    requestImage.released();
                }
            } else if (msg.what == LOAD_FAIL) {
                RequestImage requestImage = (RequestImage) msg.obj;
                requestImage.released();
                setImageBitmap(null);   //TODO fail image
            }
            super.handleMessage(msg);
        }
    };

    public WebImageView(Context context) {
        super(context);
    }

    public WebImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public final void setUrl(String url) {
        checkRequestImage(url);
    }

    protected void checkRequestImage(String url) {
        ImageFile imageFile = new ImageFile(url);
        Bitmap bitmap = imageFile.getBitmap();
        if(bitmap != null) {
            applyImage(bitmap, url);
        } else {
            RequestImage requestImage = new RequestImage(url);
            requestImage.setIResultReceiver(this);
            NetAPI.getInstance().addReq(requestImage);
        }
    }

    protected void applyImage(Bitmap bitmap, String url) {
        setImageBitmap(bitmap);
    }

    @Override
    public void onResult(IRequest obj, boolean bSuccess) {
        Message msg = handler.obtainMessage();
        if(bSuccess) {
            msg.what = LOAD_SUCCSESS;
        } else {
            msg.what = LOAD_FAIL;
        }
        msg.obj = obj;
        handler.sendMessage(msg);
    }
}
