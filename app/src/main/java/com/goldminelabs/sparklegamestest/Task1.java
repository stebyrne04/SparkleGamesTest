package com.goldminelabs.sparklegamestest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by stephen on 07/08/15.
 */
public class Task1 extends Activity{

    public static Task1 instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        instance = this;
        setContentView(new SampleView(this));
    }

    private static class SampleView extends View{

        public SampleView(Context c){
            super(c);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            DisplayMetrics metrics = new DisplayMetrics();
            Task1.instance.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            Paint fontPaint = new Paint();
            fontPaint.setColor(Color.WHITE);
            fontPaint.setTextSize(45);

            Paint bitmapPaint = new Paint();

            canvas.drawColor(Color.BLUE);
            fontPaint.setFilterBitmap(true);
            //canvas.drawText();
            Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.test);
            Bitmap scaledImage = Bitmap.createScaledBitmap(bitmapImage, metrics.widthPixels - 10, metrics.heightPixels-10, true);

            canvas.drawBitmap(scaledImage, 5, 5, bitmapPaint);


            canvas.drawText(""+metrics.widthPixels+"x"+metrics.heightPixels, metrics.widthPixels/2, metrics.heightPixels/2, fontPaint);
            canvas.drawText(""+metrics.densityDpi+" Dpi",metrics.widthPixels/2, (metrics.heightPixels/2) + fontPaint.getTextSize() + 5, fontPaint);

        }
    }
}
