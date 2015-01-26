package com.example.popwindowlib.com.alipay.app.ui;

import android.view.View;
import android.widget.ListView;

import com.example.popwindowlib.R;

/**
 * Created by zhch0633 on 2015/1/22.
 */
public class PopList extends PopDragableBase implements View.OnTouchListener{
    private ListView list;

    public PopList(View view, int w,int h,boolean focusable){
        super(view,w,h,focusable);
        popupView = view;
        list =  (ListView) popupView.findViewById(R.id.dynamicList);
    }

    public ListView getList(){
        return list;
    }
}

