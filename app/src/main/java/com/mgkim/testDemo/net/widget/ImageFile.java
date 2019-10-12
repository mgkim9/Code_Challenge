package com.mgkim.testDemo.net.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mgkim.testDemo.Constants;
import com.mgkim.testDemo.utils.FormatUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.mgkim.testDemo.Constants.LocalImgPath;

public class ImageFile {
    private String url;
    private File imagefile;
    private Bitmap bitmap;
    private int reqWidth = 0, reqHeight = 0;


    public ImageFile(String url) {
        this.url = url;
        File storageDir = new File(Constants.LocalImgPath);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        imagefile = new File(storageDir, FormatUtil.getFileName(url));
        if(imagefile.exists()) {
            decodeBitmap();
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public boolean writeFile(InputStream is) {
        boolean isSuccessed = false;
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(imagefile));
            int current = 0;
            byte[] data = new byte[2048];
            while ((current = is.read(data, 0, data.length)) != -1) {
                bos.write(data, 0, current);
            }
            decodeBitmap();
            if(bitmap != null) {
                isSuccessed = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null) {
                    is.close();
                }
                if(bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuccessed;
    }

    private boolean decodeBitmap() {
        try {
            Bitmap bitmap = null;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;

            if (reqHeight != 0 || reqWidth != 0) {
                // //here only bounds are being decoded so no real memory
                // allocatiion
                options.inJustDecodeBounds = true;
                //BitmapFactory.decodeFile(imagefile.getAbsolutePath(), options);

                // Calculate inSampleSize
                options.inSampleSize = calculateInSampleSize(options);

                // Decode bitmap with inSampleSize set
                options.inJustDecodeBounds = false;
                // allocating memory now as per the required size of bit map
                bitmap = BitmapFactory.decodeFile(imagefile.getAbsolutePath(),
                        options);
            } else {
                bitmap = BitmapFactory.decodeFile(imagefile.getAbsolutePath(),
                        options);
            }

            if (bitmap != null) {
                this.bitmap = bitmap;
                return true;
            }

            imagefile.delete();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int calculateInSampleSize(BitmapFactory.Options options) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                if (reqHeight == 0) {
                    inSampleSize = 1;
                } else {
                    inSampleSize = Math.round((float) height
                            / (float) reqHeight);
                }
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }
}
