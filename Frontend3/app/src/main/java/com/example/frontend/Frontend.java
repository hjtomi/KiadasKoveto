package com.example.frontend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class  Frontend extends SurfaceView implements SurfaceHolder.Callback {
    final Context context;
    private final Fooldal fooldal;
    public Loop loop;
    private UrlKezelo urlKezelo;
    private NincsBejelentkezve nincsBejelentkezve;
    private boolean nincsBejelentkezve_=true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                double x = event.getX();
                double y = event.getY();
                loop.setSzoveg(x, y);

                if (x > 400 && y < 400){
                    urlKezelo.nyugtasKiadas();
                }

                return true;
        }
        return super.onTouchEvent(event);
    }


    public Frontend(Context context, int width, int height) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);


        this.context = context;
        loop = new Loop(this, surfaceHolder);
        urlKezelo = new UrlKezelo(context, loop);

        nincsBejelentkezve = new NincsBejelentkezve(context, width, height);
        fooldal = new Fooldal(context, width, height);

        loop.setSzoveg2(Float.toString(width)+"   "+Float.toString(height));



        setFocusable(true);

    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        loop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        //drawSzoveg(canvas);
        //drawSzoveg2(canvas);
        if (nincsBejelentkezve_)
            nincsBejelentkezve.draw(canvas);
    }

    public void drawSzoveg(Canvas canvas){
        String szoveg = Loop.getSzoveg2();
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setTextSize(50);
        paint.setColor(color);
        canvas.drawText("Szöveg: "+szoveg, 100, 100, paint);
    }

    public void drawSzoveg2(Canvas canvas){
        String szoveg = Loop.getSzoveg();
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setTextSize(50);
        paint.setColor(color);
        canvas.drawText("Szöveg: "+szoveg, 100, 200, paint);
    }

    public void update() {
        return;
    }
}
