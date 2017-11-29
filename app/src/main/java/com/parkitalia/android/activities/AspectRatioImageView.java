package com.parkitalia.android.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by vishal mahajan on 7/27/2017.
 */
public class AspectRatioImageView extends ImageView {
    public AspectRatioImageView(Context context) {
        super(context);
        this.setScaleType(ScaleType.FIT_XY);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setScaleType(ScaleType.FIT_XY);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setScaleType(ScaleType.FIT_XY);
    }

   /* public AspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        Drawable d = getDrawable();

        if (d != null && d.getIntrinsicWidth() > 0)
        {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            if (width <= 0)
                width = getLayoutParams().width;

            int height = 500;/*width * d.getIntrinsicHeight() / d.getIntrinsicWidth();*/
            setMeasuredDimension(width, height);
        }
        else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
