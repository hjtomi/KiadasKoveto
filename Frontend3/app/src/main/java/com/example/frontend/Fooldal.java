package com.example.frontend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Fooldal {

    Context context;
    int width;
    int height;

    public Fooldal(Context context, int x, int y) {
        this.context = context;
        width = x;
        height = y;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();

        paint.setColor(ContextCompat.getColor(context, R.color.white));
        canvas.drawRect(width /2-200, height /3-100, width /2+200, height /3+100, paint);
        canvas.drawRect(width /2-200, height /3*2-100, width /2+200, height /3*2+100, paint);

        paint.setTextSize(60);
        paint.setColor(ContextCompat.getColor(context, R.color.magenta));
        canvas.drawText("Főoldal", width /2-190, height /3+15, paint);
        canvas.drawText("asdfsdf", width /2-170, height /3*2+15, paint);
    }




}
