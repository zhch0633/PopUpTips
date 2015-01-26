package com.example.popwindowlib.com.alipay.app.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * Created by zhch0633 on 2015/1/23.
 */

public class DragableHub extends RoundImageView {
    private float x;
    private float y;

    private float mTouchX;
    private float mTouchY;

    private float mStartX;
    private float mStartY;

    private OnClickListener mClickListener;

    private WindowManager windowManager = (WindowManager) getContext()
            .getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    private WindowManager.LayoutParams windowManagerParams = ((FloatApplication) getContext()
            .getApplicationContext()).getWindowParams();


    public DragableHub(Context context){
        super(context);
    }

    public DragableHub(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public DragableHub(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        Rect frame = new Rect();
        getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        System.out.println("statusBarHeight:"+statusBarHeight);

        x = event.getRawX();
        y = event.getRawY() - statusBarHeight;
        Log.i("tag", "currX" + x + "====currY" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                mTouchY = event.getY();
                mStartX = x;
                mStartY = y;
                Log.i("tag", "startX" + mTouchX + "====startY"
                        + mTouchY);
                break;
            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                updateViewPosition();
                mTouchX = mTouchY = 0;
                if ((x - mStartX) < 5 && (y - mStartY) < 5) {
                    if(mClickListener!=null) {
                        mClickListener.onClick(this);
                    }
                }
                break;
        }
        return false;

    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.mClickListener = l;
    }

    private void updateViewPosition() {
        windowManagerParams.x = (int) (x - mTouchX);
        windowManagerParams.y = (int) (y - mTouchY);
        windowManager.updateViewLayout(this, windowManagerParams);
    }
}
