package com.goldminelabs.sparklegamestest.task2emements;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.goldminelabs.sparklegamestest.task2emements.Scene;

/**
 * Created by stephen on 09/08/15.
 */
public class MainThread extends Thread {

    private boolean running;
    private SurfaceHolder surfaceHolder;
    private Scene scene;

    public MainThread(SurfaceHolder surfaceHolder, Scene scene) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.scene = scene;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running){
            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){

                    this.scene.onDraw(canvas);
                }
            }finally {
                if (canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
