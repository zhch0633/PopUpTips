package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;


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

        mPopupWindow = new PopupWindow(popupView, AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
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
        mPopupWindow.showAsDropDown(v);
    }
}
