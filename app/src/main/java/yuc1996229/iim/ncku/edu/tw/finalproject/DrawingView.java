package yuc1996229.iim.ncku.edu.tw.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.io.OutputStream;

public class DrawingView extends View {

    private Paint mPaint = null;
    private Bitmap mBitmap = null;
    private Canvas mBitmapCanvas = null;
    private float startX;
    private float startY ;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Bitmap.createBitmap(1000,2000, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        mBitmapCanvas.drawColor(Color.WHITE);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float stopX = event.getX();
                float stopY = event.getY();
                mBitmapCanvas.drawLine(startX, startY, stopX, stopY, mPaint);
                startX = event.getX();
                startY = event.getY();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        }
    }

    public void saveBitmap(OutputStream stream) {
        if (mBitmap != null) {
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }
    }
}

