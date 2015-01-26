package com.example.popwindowlib.com.alipay.app.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.example.popwindowlib.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by zhch0633 on 2015/1/26.
 * this will be used to contruct a new round view form popupwindow that can drag and click
 */
public class PopFloatButton extends PopDragableBase{
    private RoundImageView mRoundImageView;

    public PopFloatButton(View view, int w,int h,boolean focusable){
        super(view,w,h,focusable);
        mRoundImageView = (RoundImageView)view.findViewById(R.id.dragacher);
        popupView = view;
    }

    public PopFloatButton(Activity activity){
        super(activity.getLayoutInflater().inflate(R.layout.round_pop_layout, null), WRAP_CONTENT, WRAP_CONTENT,false);
        mRoundImageView = (RoundImageView)popupView.findViewById(R.id.dragacher);
        setTouchable(true);
        setOutsideTouchable(false);
        setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap) null));
        setDragable(true);
    }
}
