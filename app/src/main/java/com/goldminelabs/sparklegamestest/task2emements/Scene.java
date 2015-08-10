package com.goldminelabs.sparklegamestest.task2emements;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import  com.goldminelabs.sparklegamestest.Task2;
import com.goldminelabs.sparklegamestest.R;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by stephen on 09/08/15.
 */
public class Scene extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;
    private MainImage mainImage;
    private Cutter cutter;
    private HashMap<String, Pieces> pieces = new HashMap<String, Pieces>();

    private String lastTouched;
    private int pieceCount = 0;
    int min = 65;
    int max = 80;
    DisplayMetrics metrics;
    Random r = new Random();

    private int oldState;

    public Scene(Context context) {
        super(context);
        getHolder().addCallback(this);

        metrics = new DisplayMetrics();
        Task2.instance.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mainImage = new MainImage(BitmapFactory.decodeResource(getResources(), R.drawable.mainimage), 10, 10, metrics);

        cutter = new Cutter(BitmapFactory.decodeResource(getResources(),R.drawable.cutter), 20, 20, BitmapFactory.decodeResource(getResources(), R.drawable.mask));

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry){
            try{
                thread.join();
                retry = false;
            }
            catch (InterruptedException e){}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            if(event.getY() > getHeight()-50){
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            }
            else {

            }
            if((int)event.getX() > mainImage.getX()+(cutter.getBitmap().getWidth()/3-3)
                    && (int)event.getY() > mainImage.getY()+(cutter.getBitmap().getHeight()/3-3)
                    && (int)event.getX() < mainImage.getBitmap().getWidth()- (cutter.getBitmap().getWidth()/3+3)
                    && (int)event.getY() < mainImage.getBitmap().getHeight()- (cutter.getBitmap().getHeight()/3+3)){

                cutter.setX((int)event.getX() - cutter.getBitmap().getWidth()/2);
                cutter.setY((int)event.getY()- cutter.getBitmap().getHeight()/2);
                cutter.setTouched(true);
            }
            if(cutter.isTouched()){
                int x = r.nextInt(metrics.widthPixels - (mainImage.getY()+ mainImage.getWidth() + 100) + 1) + (mainImage.getY()+ mainImage.getWidth() + 100);
                int y = r.nextInt((metrics.heightPixels-100) - 100 + 1) + 100;
                Pieces newPiece = new Pieces(mainImage.getBitmap(), x, y , cutter.getX() ,cutter.getY() ,cutter.getMask());
                pieces.put("piece" + pieceCount++, newPiece);
                cutter.setTouched(false);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLUE);
        mainImage.draw(canvas);

        cutter.draw(canvas);

        for (Pieces value : pieces.values()) {
            value.draw(canvas);
        }

    }

    public void stopThread(){
        thread.setRunning(false);
    }


}
