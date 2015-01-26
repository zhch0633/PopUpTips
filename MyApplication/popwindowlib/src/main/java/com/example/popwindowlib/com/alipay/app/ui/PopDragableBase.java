package com.example.popwindowlib.com.alipay.app.ui;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

import com.example.popwindowlib.R;

/**
 * Created by zhch0633 on 2015/1/26.
 */
public class PopDragableBase extends PopupWindow implements View.OnTouchListener{
    protected View popupView;
    protected boolean isDragalbe = false;
    protected int lastX;
    protected int lastY;
    protected int mScreenX,mScreenY,dx,dy;

        public PopDragableBase(View view, int w,int h,boolean focusable){
            super(view,w,h,focusable);
            popupView = view;
        }

        public void initiate(){
            //use this to realize drag&drop popupwindow
            //http://blog.csdn.net/wangjinyu501/article/details/24697229 refer this to get more information about this realization
        }

        //use this to realize drag&drop popupwindow
        //http://blog.csdn.net/wangjinyu501/article/details/24697229 refer this to get more information about this realization
        //chen.zhang 2015.1.23
        public void setDragable(boolean drag){
            if(drag == true) {
                isDragalbe = true;
                if( getContentView().findViewById(R.id.dragacher)!=null) {
                    getContentView().findViewById(R.id.dragacher).setOnTouchListener(this);
                }
            }
            else {
                isDragalbe = false;
                if( getContentView().findViewById(R.id.dragacher)!=null) {
                    getContentView().findViewById(R.id.dragacher).setOnTouchListener(null);
                }
            }
            return;
        }

        public boolean getDragable(){
            return isDragalbe;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //touch form view and drag the window
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    int[] location = new int[2];
                    getContentView().getLocationOnScreen(location);
                    mScreenX = location[0];
                    mScreenY = location[1];
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    Log.w("lastX:", String.valueOf(dx));
                    Log.w("lastY:",String.valueOf(dy));
                    break;
                case MotionEvent.ACTION_UP:
                    mScreenX = dx;
                    mScreenY = dy;
                    Log.i("dx:",String.valueOf(dx));
                    Log.i("dy:",String.valueOf(dy));
                    break;
                case MotionEvent.ACTION_MOVE:
                    dx = (int) event.getRawX() - lastX + mScreenX;
                    dy = (int) event.getRawY() - lastY + mScreenY;
                    update(dx, dy, -1, -1);
                    break;
            }
            return true;
        }
}
