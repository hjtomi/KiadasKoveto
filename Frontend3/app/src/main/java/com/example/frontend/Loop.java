package com.example.frontend;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class Loop extends Thread {
    private boolean futas = false;
    private SurfaceHolder surfaceHolder;
    private Frontend frontend;

    public Loop(Frontend frontend, SurfaceHolder surfaceHolder) {
        this.frontend = frontend;
        this.surfaceHolder = surfaceHolder;

    }

    public static String getSzoveg() {
        return "Helló";
    }

    public void startLoop() {
        futas = true;
        start();
    }

    @Override
    public void run(){
        super.run();
        Canvas canvas;
        while (futas){
            try {
                canvas = surfaceHolder.lockCanvas();
                frontend.update();
                frontend.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }

        }
    }
}
