package com.zhushen.bluetoothcmdsender.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.medmod.simcprtest.R;
import com.medmod.simcprtest.command.SommandEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/3/28.
 */
public class CommandAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;

    private List<SommandEntity> cmds = new ArrayList<>();

    public CommandAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setCmds(List<SommandEntity> cmds) {
        this.cmds = cmds;
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
            convertView = mInflater.inflate(R.layout.list_item_command,null);
            viewHolder = new ViewHolder();
            viewHolder.cmd = (TextView)convertView.findViewById(R.id.list_item_command);
            viewHolder.detail = (TextView)convertView.findViewById(R.id.list_item_detail);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cmd.setText(cmds.get(position).getCommand());
        viewHolder.detail.setText(cmds.get(position).getDetail());
        return convertView;
    }


    private class ViewHolder{
        private TextView cmd,detail;

        public TextView getCmd() {
            return cmd;
        }

        public void setCmd(TextView cmd) {
            this.cmd = cmd;
        }

        public TextView getDetail() {
            return detail;
        }

        public void setDetail(TextView detail) {
            this.detail = detail;
        }
    }
}
