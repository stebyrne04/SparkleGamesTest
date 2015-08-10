package com.goldminelabs.sparklegamestest.task2emements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by stephen on 09/08/15.
 */
public class MainImage {

    private Bitmap bitmap;
    private int x;
    private int y;

    public MainImage(Bitmap org, int x, int y, DisplayMetrics metrics) {
        //this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        //v.getWidth()
        int width, height;
        width = org.getWidth();
        height = org.getHeight();
        double scaleX, scaleY;
        scaleX = metrics.widthPixels / org.getWidth();
        scaleY = metrics.heightPixels / org.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale((float)scaleX, (float)scaleY);
        double aspectRatio = metrics.heightPixels/ metrics.widthPixels;
        //this.bitmap = Bitmap.createScaledBitmap(org, width * (int)scaleX, height * (int)scaleY, true);
        this.bitmap = Bitmap.createBitmap(org, 0, 0, width, height, matrix, true);


        //metrics.
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public int getWidth(){
        return bitmap.getWidth();
    }

    public int getHeight(){
        return bitmap.getHeight();
    }
}
