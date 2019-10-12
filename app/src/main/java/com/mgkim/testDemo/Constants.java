package com.mgkim.testDemo;

import android.os.Environment;

public class Constants {
    public static final boolean IS_DEBUG = BuildConfig.DEBUG;

    public static final String	PACKAGE_NAME					= "com.mgkim.testDemo";

    public static final String LocalImgPath = Environment.getExternalStorageDirectory()+"/Android/data/" + PACKAGE_NAME + "/files/Pictures/";
    public static final String LocalGifPath = Environment.getExternalStorageDirectory()+"/Android/data/" + PACKAGE_NAME + "/files/Gifs/";

    public static final int 	CACHE_SIZE 					= 200;
    public static final int 	MAX_REQ_COUNT 				= 3;

}
