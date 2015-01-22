package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.popwindowlib.com.alipay.app.ui.popListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DemoActivity extends ActionBarActivity implements View.OnClickListener {

    private Button testButton;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        testButton = (Button)findViewById(R.id.button);
        testButton.setOnClickListener(this);

        popListAdapter adapter = new popListAdapter(this, getData(),this);
        View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);
        ListView newView = (ListView)popupView.findViewById(R.id.dynamicList);
        LinearLayout lay = (LinearLayout)popupView.findViewById(R.id.linearLay);
        newView.setAdapter(adapter);
        //for the popupWindow and its buttons
        mPopupWindow = new PopupWindow(popupView, 900, 600, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        //ArrayList<Button> popUpButtonList = new ArrayList<>();
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
        switch (v.getId()){
            case R.id.button:
                windowPopUp(v);
                break;
            default:
                ((TextView)findViewById(R.id.textView)).setText(((Button)v).getText());
                break;
        }
    }

    public void windowPopUp(View v){
        ((TextView)findViewById(R.id.textView)).setText("calling popup");
        //offInOixels for adjust popup to the middle
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        View popContent = mPopupWindow.getContentView();
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        popContent.measure(w, h);
        //use getMeasuredHeight to get the heighe before show
        //int height =popContent.getMeasuredHeight()*(((ListView)popContent.findViewById(R.id.dynamicList)).getAdapter().getCount()+1);
        //int width =popContent.getMeasuredWidth();

        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]+v.getHeight());
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
