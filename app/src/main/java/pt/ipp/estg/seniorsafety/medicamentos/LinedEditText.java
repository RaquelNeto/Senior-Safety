package pt.ipp.estg.seniorsafety.medicamentos;


import androidx.appcompat.widget.AppCompatEditText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class LinedEditText extends AppCompatEditText {

    private Rect mRect;
    private Paint mPaint;


    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(0xFFFFD966);
    }

    @Override
    public void draw(Canvas canvas) {

        int height = ((View)this.getParent()).getHeight();

        int lineHeight = getLineHeight();
        int numberOfLines = height / lineHeight;

        Rect r = mRect;
        Paint paint = mPaint;

        int baseline = getLineBounds(0,r);

        for(int i= 0; i < numberOfLines; i++){
            canvas.drawLine(r.left, baseline +1, r.right, baseline +1, paint);
            baseline += lineHeight;

        }

        super.draw(canvas);
    }
}
