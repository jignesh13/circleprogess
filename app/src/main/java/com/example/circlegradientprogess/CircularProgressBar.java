package com.example.circlegradientprogess;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/**
 * Simple single android view component that can be used to showing a round progress bar.
 * It can be customized with size, stroke size, colors and text etc.
 * Progress change will be animated.
 * Created by Kristoffer, http://kmdev.se
 */
public class CircularProgressBar extends View {

    private int mViewWidth;
    private int mViewHeight;

    private final float mStartAngle = -90;      // Always start from top (default is: "3 o'clock on a watch.")
    private float mSweepAngle = 0;              // How long to sweep from mStartAngle
    private float mMaxSweepAngle = 360;         // Max degrees to sweep = full circle
    private float mStrokeWidth = 22;              // Width of outline
    private int mAnimationDuration = 400;       // Animation duration for progress change
    private int mMaxProgress = 100;             // Max progress to use
    private boolean mDrawText = true;           // Set to true if progress text should be drawn
    private boolean mRoundedCorners = true;     // Set to true if rounded corners should be applied to outline ends
    private int mProgressColor = Color.BLACK;   // Outline color
    private int mTextColor = Color.BLACK;       // Progress text color
    private int currentprogess;
    private  Paint mPaint;                 // Allocate paint outside onDraw to avoid unnecessary object creation
    private  Paint circle;
    private RectF mRect;
    private int cirlebackcolor;
    private int centerX, centerY, radius;
    private int startcolor,endcolor;
    private TextView textView;
    public CircularProgressBar(Context context) {
        this(context, null);
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyCustomView);
        mRoundedCorners = ta.getBoolean(R.styleable.MyCustomView_roundcorner, true);
        cirlebackcolor=ta.getColor(R.styleable.MyCustomView_circlebackgroundcolor,Color.parseColor("#ecedee"));
        mStrokeWidth=ta.getDimension(R.styleable.MyCustomView_circlestrokewidth,22);
        startcolor=ta.getColor(R.styleable.MyCustomView_startcolor,Color.RED);
        endcolor=ta.getColor(R.styleable.MyCustomView_endcolor,Color.BLUE);
        currentprogess=ta.getInt(R.styleable.MyCustomView_progess,0);
        if (textView!=null)textView.setText(currentprogess+"");

        mSweepAngle=calcSweepAngleFromProgress(currentprogess);
        ta.recycle();
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(attrs == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyCustomView);
        mRoundedCorners = ta.getBoolean(R.styleable.MyCustomView_roundcorner, true);
        cirlebackcolor=ta.getColor(R.styleable.MyCustomView_circlebackgroundcolor,Color.parseColor("#ecedee"));
        mStrokeWidth=ta.getDimension(R.styleable.MyCustomView_circlestrokewidth,22);
        startcolor=ta.getColor(R.styleable.MyCustomView_startcolor,Color.RED);
        endcolor=ta.getColor(R.styleable.MyCustomView_endcolor,Color.BLUE);
        currentprogess=ta.getInt(R.styleable.MyCustomView_progess,0);
        if (textView!=null)textView.setText(currentprogess+"");

        mSweepAngle=calcSweepAngleFromProgress(currentprogess);
        ta.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circle = new Paint();
        circle.setAntiAlias(true);
        circle.setStrokeWidth(mStrokeWidth - 1);
        circle.setColor(cirlebackcolor);
        circle.setStyle(Paint.Style.STROKE);
        circle.setStrokeJoin(Paint.Join.ROUND);
        circle.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initMeasurments();
        drawOutlineArc(canvas);

        if (mDrawText) {
            //drawText(canvas);
        }
    }
public void setTextView(TextView textView){
    this.textView=textView;
}
    private void initMeasurments() {
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
    }

    private void drawOutlineArc(Canvas canvas) {
        if (mRect == null) {
            // take the minimum of width and height here to be on he safe side:
            centerX = getMeasuredWidth() / 2;
            centerY = getMeasuredHeight() / 2;
            radius = Math.min(centerX, centerY);


            float startTop = mStrokeWidth;
            float startLeft = startTop;

            float endBottom = 2 * radius - startTop;
            float endRight = endBottom;
            mRect = new RectF(startTop, startLeft, endRight, endBottom);
        }

        final LinearGradient linearGradient = new LinearGradient(0, 0, 0, (float) (getMeasuredHeight()), startcolor, endcolor, Shader.TileMode.MIRROR);
        mPaint.setShader(linearGradient);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerY, radius - mStrokeWidth, circle);
        canvas.drawArc(mRect, mStartAngle, mSweepAngle, false, mPaint);
        // canvas.drawArc(0,0,getWidth()-mStrokeWidth,getHeight()-mStrokeWidth,mStartAngle,mSweepAngle,false,mPaint);
        // canvas.drawArc(outerOval, mStartAngle, mSweepAngle, false, mPaint);
    }

    private void drawText(Canvas canvas) {
        mPaint.setTextSize(Math.min(mViewWidth, mViewHeight) / 5f);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStrokeWidth(0);
        mPaint.setColor(mTextColor);

        // Center text
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2));

        canvas.drawText(calcProgressFromSweepAngle(mSweepAngle) + "%", xPos, yPos, mPaint);
    }

    private float calcSweepAngleFromProgress(int progress) {
        return (mMaxSweepAngle / mMaxProgress) * progress;
    }

    private int calcProgressFromSweepAngle(float sweepAngle) {
        return (int) ((sweepAngle * mMaxProgress) / mMaxSweepAngle);
    }

    /**
     * Set progress of the circular progress bar.
     *
     * @param progress progress between 0 and 100.
     */
    public void setProgress(int progress) {
        currentprogess = progress;
        ValueAnimator animator = ValueAnimator.ofFloat(mSweepAngle, calcSweepAngleFromProgress(progress));
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(mAnimationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSweepAngle = (float) valueAnimator.getAnimatedValue();
                if (textView!=null)textView.setText(calcProgressFromSweepAngle(mSweepAngle)+"");

                invalidate();
            }
        });
        animator.start();
    }

    public int getCurrentprogess() {
        return currentprogess;
    }

    public void setProgressColor(int color) {
        mProgressColor = color;
        invalidate();
    }


    public void setTextColor(int color) {
        mTextColor = color;
        invalidate();
    }

    public void showProgressText(boolean show) {
        mDrawText = show;
        invalidate();
    }

    /**
     * Toggle this if you don't want rounded corners on progress bar.
     * Default is true.
     *
     * @param roundedCorners true if you want rounded corners of false otherwise.
     */
    public void useRoundedCorners(boolean roundedCorners) {
        mRoundedCorners = roundedCorners;
        invalidate();
    }
}
