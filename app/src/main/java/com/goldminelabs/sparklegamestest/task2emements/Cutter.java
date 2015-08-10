package com.goldminelabs.sparklegamestest.task2emements;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by stephen on 10/08/15.
 */
public class Cutter {

    private Bitmap bitmap;
    private Bitmap mask;
    private int x;
    private int y;
    private boolean touched;

    public Cutter(Bitmap bitmap, int x, int y, Bitmap mask) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.mask = mask;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getMask() {
        return mask;
    }

    public void setMask(Bitmap mask) {
        this.mask = mask;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }
    public void handleActionDown(int eventX,int eventY){
        if (eventX >= x - (bitmap.getWidth()) && (eventX <= x + (bitmap.getWidth()))){
            if (eventY > y - (bitmap.getHeight())&&(eventY <= y + (bitmap.getHeight()))){
                setTouched(true);
            }
            else{
                setTouched(true);
            }
        }else {
            setTouched(true);
        }
    }

}
