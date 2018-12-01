package com.zhushen.bluetoothcmdsender.adapter;

import android.bluetooth.BluetoothDevice;
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
 * Created by Zhushen on 2018/2/2.
 */

public class BlueToothDeviceAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInfalter;

    private List<BluetoothDevice> devices = new ArrayList<>();

    public BlueToothDeviceAdapter(Context context) {
        this.context = context;
        mInfalter = LayoutInflater.from(context);
    }

    public void setDevices(List<BluetoothDevice> devices) {
        this.devices = devices;
        notifyDataSetChanged();
    }

    public void addDevice(BluetoothDevice device){
        if(!devices.contains(device)){
            devices.add(device);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInfalter.inflate(R.layout.list_item_bt_device,null);
            holder  = new ViewHolder();
            holder.address = (TextView)convertView.findViewById(R.id.device_item_address);
            holder.name = (TextView)convertView.findViewById(R.id.device_item_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(devices.size() > 0){
            holder.name.setText(devices.get(position).getName());

//            ParcelUuid[] uuids = devices.get(position).getUuids();
//            String test = "";
//            for (ParcelUuid uuid:uuids) {
//                test += uuid + "\n";
//            }
            holder.address.setText(devices.get(position).getAddress());
        }


        return convertView;
    }

    private class ViewHolder{
        private TextView name,address;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getAddress() {
            return address;
        }

        public void setAddress(TextView address) {
            this.address = address;
        }
    }
}
