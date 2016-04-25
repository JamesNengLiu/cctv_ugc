package com.cctv.ugc.uicomponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.cctv.ugc.R;


/**
 * Created by liuxin on 16/4/24.
 */
public class UploadingProgressView extends View {

    private Paint mPaint;
    private long interval = 500;
    private int curIndex = 0;

    //  保存绘制历史
    public CircleInfo[] mCircleInfos = new CircleInfo[3];

    public UploadingProgressView(Context context) {
        super(context);
    }

    public UploadingProgressView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public UploadingProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initOnce() {
        if (mPaint != null)
            return;
        mPaint = new Paint();
        float radius = getResources().getDimensionPixelSize(R.dimen.dp6);
        int startX = (int) radius;
        int startY = (int) radius;
        int offsetX = getResources().getDimensionPixelSize(R.dimen.dp13)+(int)radius*2;
        mCircleInfos[0] = new CircleInfo();
        mCircleInfos[0].setColor(getResources().getColor(R.color.lightBule));
        mCircleInfos[0].setX(startX);
        mCircleInfos[0].setY(startY);
        mCircleInfos[0].setRadius(radius);
        mCircleInfos[1] = new CircleInfo();
        mCircleInfos[1].setColor(getResources().getColor(R.color.lightBule));
        mCircleInfos[1].setX(startX + offsetX);
        mCircleInfos[1].setY(startY);
        mCircleInfos[1].setRadius(radius);
        mCircleInfos[2] = new CircleInfo();
        mCircleInfos[2].setColor(getResources().getColor(R.color.lightBule));
        mCircleInfos[2].setX(startX + offsetX * 2);
        mCircleInfos[2].setY(startY);
        mCircleInfos[2].setRadius(radius);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initOnce();
        //  根据保存的绘制历史重绘所有的实心圆
        for (int i = 0; i < mCircleInfos.length; i++) {
            CircleInfo circleInfo = mCircleInfos[i];
            //  设置画笔颜色
            if (i == curIndex) {
                mPaint.setColor(getResources().getColor(R.color.deepBule));
            } else {
                mPaint.setColor(getResources().getColor(R.color.lightBule));
            }
            //  绘制实心圆
            canvas.drawCircle(circleInfo.getX(), circleInfo.getY(), circleInfo.getRadius(), mPaint);
        }
    }

    private Handler timerHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            invalidate();
            curIndex++;
            curIndex %= mCircleInfos.length;
            sendEmptyMessageDelayed(0, interval);
        }
    };

    // 保存实心圆相关信息的类
    public static class CircleInfo {
        private float x;                //  圆心横坐标
        private float y;                //  圆心纵坐标
        private float radius;            //  半径
        private int color;            //  画笔的颜色

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getRadius() {
            return radius;
        }

        public void setRadius(float radius) {
            this.radius = radius;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }

    public void stop() {
        timerHandler.removeMessages(0);
    }

    public void start() {
        timerHandler.sendEmptyMessageDelayed(0, interval);
    }
}
