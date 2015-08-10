package com.goldminelabs.sparklegamestest.task2emements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by stephen on 09/08/15.
 */
public class Pieces {

    private Bitmap bitmap;
    private int x;
    private int y;
    private boolean touched;

    public Pieces(Bitmap original, int x, int y, int startX, int startY, Bitmap mask) {
        //this.bitmap = bitmap;
        this.x = x;
        this.y = y;

        this.bitmap = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(this.bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        Matrix matrix = new Matrix();
        Bitmap croppedBitmap = Bitmap.createBitmap(original, startX, startY, mask.getWidth(), mask.getHeight(), matrix, true);
        mCanvas.drawBitmap(croppedBitmap, 0 , 0, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,x- (bitmap.getWidth()/2), y - (bitmap.getHeight()/2),null);
    }

    public void handleActionDown(int eventX,int eventY){
        if (eventX >= x - (bitmap.getWidth()/2) && (eventX <= x + (bitmap.getWidth()/2))){
            if (eventY > y - (bitmap.getHeight()/2)&&(eventY <= y + (bitmap.getHeight()/2))){
                setTouched(true);
            }
            else{
                setTouched(false);
            }
        }else {
            setTouched(false);
        }
    }
}
