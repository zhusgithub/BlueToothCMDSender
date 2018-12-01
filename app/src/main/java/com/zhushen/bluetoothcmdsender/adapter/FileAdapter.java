package com.zhushen.bluetoothcmdsender.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhushen.bluetoothcmdsender.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/12/1.
 */
public class FileAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;

    private List<File> fileList = new ArrayList<>();

    public FileAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fileList.size();
    }

    @Override
    public Object getItem(int position) {
        return fileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_file,null);
            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.file_item_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(fileList.get(position).getName());

        return convertView;
    }

    class ViewHolder{
        private TextView name;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }
    }
}
