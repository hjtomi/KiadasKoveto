package com.example.frontend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class  Frontend extends SurfaceView implements SurfaceHolder.Callback {
    final Context context;
    private final Fooldal fooldal;
    public Loop loop;
    private UrlKezelo urlKezelo;
    private UrlKetelo2 urlKetelo2;
    private UrlKezelo3 urlKezelo3;
    private NincsBejelentkezve nincsBejelentkezve;
    private boolean nincsBejelentkezve_=true;
    private boolean fooldal_ = false;
    private boolean bejelentkezes_ = false;
    private boolean regisztracio_ = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                double x = event.getX();
                double y = event.getY();
                //loop.setSzoveg(x, y);


                if (nincsBejelentkezve_){
                    int c=nincsBejelentkezve.touch(x, y);
                    if (c==1) {
                        nincsBejelentkezve_ = false;
                        //bejelentkezes_ = true;
                        fooldal_ = true;
                    }
                    else if(c==2){
                        nincsBejelentkezve_ = false;
                        regisztracio_ = true;
                    }
                }

                if (fooldal_){

                    urlKezelo3.fuggveny();

                    /*try {
                        loop.setSzoveg2("Talán");
                        loop.setSzoveg2(UrlKetelo2.UrlMeghivo(""));
                    } catch (IOException e) {
                        loop.setSzoveg2("Nem sikerült");
                        throw new RuntimeException(e);

                    }*/

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
        urlKetelo2 = new UrlKetelo2(context);
        urlKezelo3 = new UrlKezelo3(context, loop);

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
        drawSzoveg2(canvas);
        if (nincsBejelentkezve_)
            nincsBejelentkezve.draw(canvas);
        if (fooldal_)
            fooldal.draw(canvas);
    }

    public void drawSzoveg2(Canvas canvas){
        String szoveg = Loop.getSzoveg2();
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setTextSize(50);
        paint.setColor(color);
        canvas.drawText("Szöveg: "+szoveg, 100, 100, paint);
    }

    public void drawSzoveg(Canvas canvas){
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
