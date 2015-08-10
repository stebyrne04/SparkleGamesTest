package com.goldminelabs.framework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by stephen on 09/08/15.
 */
public class RenderView extends View {

    DisplayMetrics metrics;
    public RenderView(Context context, Activity instance) {
        super(context);
        metrics = new DisplayMetrics();
        instance.getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        invalidate();
    }
}
