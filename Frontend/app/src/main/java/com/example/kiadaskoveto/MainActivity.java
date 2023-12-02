import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class RectangleView extends View {

    private Paint paint;

    public RectangleView(Context context) {
        super(context);
        init();
    }

    public RectangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectangleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.GREEN); // Téglalap színe, itt zöld lesz
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int left = 100; // Bal felső sarok x koordinátája
        int top = 100; // Bal felső sarok y koordinátája
        int right = 500; // Jobb alsó sarok x koordinátája
        int bottom = 400; // Jobb alsó sarok y koordinátája

        canvas.drawRect(left, top, right, bottom, paint); // Téglalap rajzolása a canvasra
    }
}
