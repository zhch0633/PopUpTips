package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;


public class DemoActivity extends ActionBarActivity implements View.OnClickListener {

    private Button testButton;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        testButton = (Button)findViewById(R.id.button);
        testButton.setOnClickListener(this);

        View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);

        //for the popupWindow and its buttons
        mPopupWindow = new PopupWindow(popupView, AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        ArrayList<Button> popUpButtonList = new ArrayList<>();
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
                break;
        }
    }

    public void windowPopUp(View v){
        ((TextView)findViewById(R.id.textView)).setText("calling popup");
        //offInOixels for adjust popup to the middle
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        View popContent = mPopupWindow.getContentView();
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        popContent.measure(w, h);
        //use getMeasuredHeight to get the heighe before show
        int height =popContent.getMeasuredHeight();
        int width =popContent.getMeasuredWidth();

        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]-width/2+v.getWidth()/2, location[1]-height/2-v.getHeight()/2);
    }
}
