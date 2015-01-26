package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.popwindowlib.com.alipay.app.ui.DensityUtil;
import com.example.popwindowlib.com.alipay.app.ui.PopList;
import com.example.popwindowlib.com.alipay.app.ui.popListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DemoActivity extends ActionBarActivity implements View.OnClickListener {

    private Button testButton;
    private PopList mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        testButton = (Button) findViewById(R.id.button);
        testButton.setOnClickListener(this);

        popListAdapter adapter = new popListAdapter(this, getData(), this);
        View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);
        ListView newView = (ListView) popupView.findViewById(R.id.dynamicList);
        LinearLayout lay = (LinearLayout) popupView.findViewById(R.id.linearLay);
        newView.setAdapter(adapter);
        //for the popupWindow and its buttons
        mPopupWindow = new PopList(popupView, DensityUtil.dip2px(this, 50), DensityUtil.dip2px(this, 50), true);
        //mPopupWindow = new PopList(popupView, AbsoluteLayout.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(this, 200), true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.setDragable(true);
        //ArrayList<Button> popUpButtonList = new ArrayList<>();
        adapter.setDragListener(mPopupWindow);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int screenWidth = dm.widthPixels;
        final int screenHeight = dm.heightPixels - 50;

        testButton.setOnTouchListener(new View.OnTouchListener() {
            int lastX, lastY; //记录移动的最后的位置
            public boolean onTouch(View v, MotionEvent event) {//获取Action
                  int ea = event.getAction();
                  Log.i("TAG", "Touch:" + ea);
                  switch (ea) {
                                                 case MotionEvent.ACTION_DOWN:   //按下
                                                     lastX = (int) event.getRawX();
                                                     lastY = (int) event.getRawY();
                                                     break;
                                                 /**
                                                  * layout(l,t,r,b)
                                                  * l  Left position, relative to parent
                                                  t  Top position, relative to parent
                                                  r  Right position, relative to parent
                                                  b  Bottom position, relative to parent
                                                  * */
                                                 case MotionEvent.ACTION_MOVE:  //移动
                                                     //移动中动态设置位置
                                                     int dx = (int) event.getRawX() - lastX;
                                                     int dy = (int) event.getRawY() - lastY;
                                                     int left = v.getLeft() + dx;
                                                     int top = v.getTop() + dy;
                                                     int right = v.getRight() + dx;
                                                     int bottom = v.getBottom() + dy;
                                                     if (left < 0) {
                                                         left = 0;
                                                         right = left + v.getWidth();
                                                     }
                                                     if (right > screenWidth) {
                                                         right = screenWidth;
                                                         left = right - v.getWidth();
                                                     }
                                                     if (top < 0) {
                                                         top = 0;
                                                         bottom = top + v.getHeight();
                                                     }
                                                     if (bottom > screenHeight) {
                                                         bottom = screenHeight;
                                                         top = bottom - v.getHeight();
                                                     }
                                                     v.layout(left, top, right, bottom);
                                                     Log.i("", "position：" + left + ", " + top + ", " + right + ", " + bottom);
                                                     //将当前的位置再次设置
                                                     lastX = (int) event.getRawX();
                                                     lastY = (int) event.getRawY();
                                                     break;
                                                 case MotionEvent.ACTION_UP:   //脱离
                                                     break;
                                             }
                                             return false;
                                         }
                                     });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    //set the onclick listener and call method popUpWindow!
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                windowPopUp(v);
                break;
            default:
                ((TextView) findViewById(R.id.textView)).setText(((Button) v).getText());
                break;
        }
    }

    public void windowPopUp(View v) {
        ((TextView) findViewById(R.id.textView)).setText("calling popup");
        //offInOixels for adjust popup to the middle
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        View popContent = mPopupWindow.getContentView();
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        popContent.measure(w, h);
        //use getMeasuredHeight to get the heighe before show
        //int height =popContent.getMeasuredHeight()*(((ListView)popContent.findViewById(R.id.dynamicList)).getAdapter().getCount()+1);
        //int width =popContent.getMeasuredWidth();

        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] + v.getHeight());
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "G1");
        map.put("info", "google 1");
        map.put("img", R.drawable.i1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("info", "google 2");
        map.put("img", R.drawable.i2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.i3);
        list.add(map);

        return list;
    }
}