package com.zhushen.bluetoothcmdsender.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zhushen.bluetoothcmdsender.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/11/19.
 */
public class SimpleTextAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private List<String> cmds = new ArrayList<>();

    public SimpleTextAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void addCmd(String cmd){
        if(cmds.size()>5000){
            cmds.clear();
        }
        cmds.add(cmd);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cmds.size();
    }

    @Override
    public Object getItem(int position) {
        return cmds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_simple_command,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.simple_cmd_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(cmds.get(position));
        return convertView;
    }

    public void clear() {
        cmds.clear();
        notifyDataSetChanged();
    }

    private class ViewHolder{
        private TextView textView;


        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
