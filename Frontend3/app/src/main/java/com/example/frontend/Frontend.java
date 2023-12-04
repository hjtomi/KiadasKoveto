package com.example.frontend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class  Frontend extends SurfaceView implements SurfaceHolder.Callback {
    final Context context;
    public Loop loop;


    public Frontend(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        loop = new Loop(this, surfaceHolder);

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
        drawSzoveg(canvas);
    }

    public void drawSzoveg(Canvas canvas){
        String szoveg = Loop.getSzoveg();
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setTextSize(50);
        paint.setColor(color);
        canvas.drawText("Szöveg: "+szoveg, 100, 100, paint);
    }

    public void update() {
        return;
    }
}
