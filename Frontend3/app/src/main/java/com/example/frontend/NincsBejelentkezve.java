package com.example.frontend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class NincsBejelentkezve {

    Context context;
    int width;
    int height;


    public NincsBejelentkezve(Context context, int x, int y) {
        this.context = context;
        width = x;
        height = y;
    }


    public int touch(double x, double y){

        if (x>width /2-200 && x < width /2+200 && y > height /3-100 && y < height /3+100){
            return 1;
        }
        if (x>width /2-200 && x < width /2+200 && y > height /3*2-100 && y < height /3*2+100){
            return 2;
        }
        return 0;

    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();

        paint.setColor(ContextCompat.getColor(context, R.color.white));
        canvas.drawRect(width /2-200, height /3-100, width /2+200, height /3+100, paint);
        canvas.drawRect(width /2-200, height /3*2-100, width /2+200, height /3*2+100, paint);

        paint.setTextSize(60);
        paint.setColor(ContextCompat.getColor(context, R.color.magenta));
        canvas.drawText("Bejelentkezés", width /2-190, height /3+15, paint);
        canvas.drawText("Regisztráció", width /2-170, height /3*2+15, paint);
    }


}
