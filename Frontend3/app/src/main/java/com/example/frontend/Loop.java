package com.example.frontend;

import static java.lang.Math.round;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.android.volley.toolbox.JsonObjectRequest;


public class Loop extends Thread {
    private boolean futas = false;
    private SurfaceHolder surfaceHolder;
    private Frontend frontend;
    public static String szoveg="Várom a lenyomást";
    public static String szoveg2="Várom a lenyomást";

    public Loop(Frontend frontend, SurfaceHolder surfaceHolder) {
        this.frontend = frontend;
        this.surfaceHolder = surfaceHolder;

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

    public void setSzoveg(double x, double y) {
        szoveg = Double.toString(round(x)) + "   " + Double.toString(round(y));
    }

    public void setSzoveg2(String szo) {
        szoveg2 = szo;
    }
    public static String getSzoveg() {
        return szoveg;
    }
    public static String getSzoveg2() {
        return szoveg2;
    }
}
