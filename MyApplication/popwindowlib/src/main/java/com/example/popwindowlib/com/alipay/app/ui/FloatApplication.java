package com.example.popwindowlib.com.alipay.app.ui;

import android.app.Application;
import android.view.WindowManager;

/**
 * Created by zhch0633 on 2015/1/26.
 */
public class FloatApplication  extends Application {
    private WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }
}