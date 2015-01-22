package com.example.popwindowlib.com.alipay.app.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popwindowlib.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zhch0633 on 2015/1/22.
 */
public class popListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;
    private View.OnClickListener mbtnListener;

    public popListAdapter(Context context,List<Map<String, Object>> data,View.OnClickListener btnListener){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mbtnListener = btnListener;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.poplist_layout, null);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.img);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView info = (TextView) convertView.findViewById(R.id.info);
        Button btn = (Button) convertView.findViewById(R.id.view_btn);

        image.setBackgroundResource((Integer) mData.get(position).get("img"));
        title.setText((String) mData.get(position).get("title"));
        info.setText((String) mData.get(position).get("info"));
        btn.setOnClickListener(this.mbtnListener);
        btn.setText((String) mData.get(position).get("title"));
        return convertView;
    }
    public void showInfo(){
        return;
    }
}
