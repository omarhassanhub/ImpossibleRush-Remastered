package com.game.impossible.rush.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.game.impossible.rush.R;


public class Square extends FrameLayout {

    private int SIDE;

    private Paint mLeftTrianglePaint;
    private Paint mTopTrianglePaint;
    private Paint mRightTrianglePaint;
    private Paint mBottomTrianglePaint;

    private Path mLPath = new Path();
    private Path mTPath = new Path();
    private Path mRPath = new Path();
    private Path mBPath = new Path();

    private int mRed;
    private int mGreen;
    private int mBlu;
    private int mYellow;

    public Square(Context context, AttributeSet attrs) {

        super(context, attrs);
        SIDE = (int) getResources().getDimension(R.dimen.square_side_size);
        init();


    }


    private void init() {

        setWillNotDraw(false);

        getRes();
        initPaints();
        initPaths();
    }

    private void getRes() {
        mRed = getResources().getColor(R.color.red);
        mGreen = getResources().getColor(R.color.green);
        mBlu = getResources().getColor(R.color.blu);
        mYellow = getResources().getColor(R.color.yellow);
    }


    private void initPaints() {

        mLeftTrianglePaint = new Paint();
        mLeftTrianglePaint.setStrokeWidth(4);

        mLeftTrianglePaint.setColor(mRed);

        mLeftTrianglePaint.setStyle(Paint.Style.FILL);
        mLeftTrianglePaint.setAntiAlias(true);

        mTopTrianglePaint = new Paint();
        mTopTrianglePaint.set(mLeftTrianglePaint);
        mTopTrianglePaint.setColor(mBlu);

        mRightTrianglePaint = new Paint();
        mRightTrianglePaint.set(mLeftTrianglePaint);
        mRightTrianglePaint.setColor(mGreen);

        mBottomTrianglePaint = new Paint();
        mBottomTrianglePaint.set(mLeftTrianglePaint);
        mBottomTrianglePaint.setColor(mYellow);
    }

    private void initPaths() {

        mLPath.lineTo(0, 0);
        mLPath.lineTo(0, SIDE);
        mLPath.lineTo(SIDE / 2, SIDE / 2);
        mLPath.close();

        mTPath.lineTo(0, 0);
        mTPath.lineTo(SIDE / 2, SIDE / 2);
        mTPath.lineTo(SIDE, 0);
        mTPath.close();

        mRPath.moveTo(SIDE, 0);
        mRPath.lineTo(SIDE, SIDE);
        mRPath.lineTo(SIDE / 2, SIDE / 2);
        mRPath.close();

        mBPath.moveTo(0, SIDE);
        mBPath.lineTo(SIDE / 2, SIDE / 2);
        mBPath.lineTo(SIDE, SIDE);
        mBPath.close();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawPath(mLPath, mLeftTrianglePaint);
        canvas.drawPath(mTPath, mTopTrianglePaint);
        canvas.drawPath(mRPath, mRightTrianglePaint);
        canvas.drawPath(mBPath, mBottomTrianglePaint);


    }

}
