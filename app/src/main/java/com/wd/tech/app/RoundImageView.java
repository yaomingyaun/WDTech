package com.wd.tech.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


@SuppressLint("ViewConstructor")
public class RoundImageView extends View {

    Paint mpaint;

    public RoundImageView(Context context) {
        super(context);
        init();

    }

    public RoundImageView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public RoundImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    private void init() {
        mpaint=new Paint();
        mpaint.setColor(Color.GREEN);
        mpaint.setStyle(Paint.Style.FILL);




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mpaint.setColor(Color.BLACK);
        canvas.drawCircle(100,100,3,mpaint);

    }
}
