package com.example.frontend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Fooldal {

    Context context;
    int x;
    int y;

    public Fooldal(Context context, int x, int y) {
        this.context = context;
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();

        paint.setColor(ContextCompat.getColor(context, R.color.white));
        canvas.drawRect(x/2-200, y/3-100, x/2+200, y/3+100, paint);
        canvas.drawRect(x/2-200, y/3*2-100, x/2+200, y/3*2+100, paint);

        paint.setTextSize(60);
        paint.setColor(ContextCompat.getColor(context, R.color.magenta));
        canvas.drawText("Bejelentkezés", x/2-190, y/3+15, paint);
        canvas.drawText("Regisztráció", x/2-170, y/3*2+15, paint);
    }




}
