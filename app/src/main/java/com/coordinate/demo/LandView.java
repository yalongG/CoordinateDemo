package com.coordinate.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

public class LandView extends View {
    // 点集合
    private List<Point> mPointList = new ArrayList<>();
    // 线宽
    private float mLandWidth;
    // 线颜色
    private int mLandColor;
    // 内部填充色
    private int mFillColor;

    private Paint mLandPaint;
    private Paint mFillPaint;

    public LandView(Context context) {
        this(context, null);
    }

    public LandView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LandView);
        mLandWidth = typedArray.getDimension(R.styleable.LandView_land_width, 2f);
        mLandColor = typedArray.getColor(R.styleable.LandView_land_color, Color.LTGRAY);
        mFillColor = typedArray.getColor(R.styleable.LandView_fill_color, Color.BLUE);
        typedArray.recycle();

        mLandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLandPaint.setStrokeWidth(mLandWidth);
        mLandPaint.setColor(mLandColor);
        mLandPaint.setStyle(Paint.Style.STROKE);
        mLandPaint.setStrokeCap(Paint.Cap.ROUND);

        mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFillPaint.setColor(mFillColor);
        mFillPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPointList == null || mPointList.size() <= 0) {
            return;
        }
        Path path = new Path();
        path.reset();
        path.moveTo((float) mPointList.get(0).getX(), (float) mPointList.get(0).getY());
        for (int i = 1, j = mPointList.size(); i < j; i++) {
            path.lineTo((float) mPointList.get(i).getX(), (float) mPointList.get(i).getY());
        }
        path.close();
        canvas.drawPath(path, mFillPaint);
        canvas.drawPath(path, mLandPaint);
        setRotationX(180);
    }


    public void setPointList(List<LatLng> pointList) {
        if (pointList == null || pointList.size() <= 0) {
            return;
        }
        mPointList.clear();
        // 为了能够使所有的点都显示出来，设置最大的宽和高都减4，后面宽和高的位置加2
        mPointList.addAll(CoordinateUtil.getPoints(pointList,
                getHeight() - 4, getWidth() - 4));
        invalidate();
    }
}
