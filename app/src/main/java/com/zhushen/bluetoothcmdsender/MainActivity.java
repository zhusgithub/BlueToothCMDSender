package com.zhushen.bluetoothcmdsender;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zhushen.bluetoothcmdsender.adapter.BlueToothDeviceAdapter;
import com.zhushen.bluetoothcmdsender.base.BaseActivity;
import com.zhushen.bluetoothcmdsender.bluetooth.BlueToothManager;
import com.zhushen.bluetoothcmdsender.bluetooth.Constant.DeviceMessage;

public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button scan,bonded;
    private BlueToothDeviceAdapter bondedAdapter,scanAdapter;
    private TextView scanStateText;
    private LinearLayout scan_layout,bonded_layout;
    private ListView bondedList,scanList;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            switch (msg.what){
                case DeviceMessage.DEVICE_FOUNDED:
                    BluetoothDevice device = (BluetoothDevice) msg.obj;
                    if(blueToothManager.getBondedDevices().contains(device)){
                        bondedAdapter.addDevice(device);
                    }else {
                        scanAdapter.addDevice(device);
                    }
                    break;
                case DeviceMessage.DEVICE_SCAN_FINISHED:
                    scanStateText.setText("scan finished");
                    break;
                case DeviceMessage.DEVICE_SCAN_TIMEOUT:
                    scanStateText.setText("time out");
                    break;
                case DeviceMessage.DEVICE_PAIR_BONDED:
                    scanStateText.setText("bonded");
                    break;
                case DeviceMessage.DEVICE_PAIR_BONDING:
                    scanStateText.setText("bonding");
                    break;
                case DeviceMessage.DEVICE_PAIR_UNBONDED:
                    scanStateText.setText("unbond");
                    break;
                case DeviceMessage.DEVICE_PAIR_FAILED:
                    scanStateText.setText("pair failed");
                    break;
                case 100:
                    scanStateText.setText("connect success");
//                    Intent intent = new Intent(MainActivity.this,DataActivity.class);
//                    startActivity(intent);
                    break;
                case 101:
                    scanStateText.setText("connect failed");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blueToothManager = BlueToothManager.getInstance();
        init();
    }

    private void init() {
        scan = (Button)findViewById(R.id.bt_scan);
        bonded = (Button)findViewById(R.id.bt_bonded);
        scanStateText = (TextView)findViewById(R.id.scan_device_text);
        scan_layout = (LinearLayout)findViewById(R.id.scan_layout);
        bonded_layout = (LinearLayout)findViewById(R.id.bonded_layout);

        scanList = (ListView)findViewById(R.id.scan_device_list);
        scanAdapter = new BlueToothDeviceAdapter(this);
        scanList.setAdapter(scanAdapter);
        scanList.setOnItemClickListener(this);

        bondedList = (ListView)findViewById(R.id.bonded_device_list);
        bondedAdapter = new BlueToothDeviceAdapter(this);
        bondedList.setAdapter(bondedAdapter);
        bondedList.setOnItemClickListener(this);

        scan.setOnClickListener(this);
        bonded.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_scan:
                blueToothManager.startScan(this,handler);
                scan_layout.setVisibility(View.VISIBLE);
                bonded_layout.setVisibility(View.GONE);
                scanStateText.setText("scan start");
                break;
            case R.id.bt_bonded:
                scan_layout.setVisibility(View.GONE);
                bonded_layout.setVisibility(View.VISIBLE);
                bondedAdapter.setDevices(blueToothManager.getBondedDevices());
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device;
        switch (parent.getId()){
            case R.id.bonded_device_list:
                device = (BluetoothDevice) bondedAdapter.getItem(position);
                blueToothManager.startCommConnect(device,handler);
                break;
            case R.id.scan_device_list:
                device = (BluetoothDevice) scanAdapter.getItem(position);
                blueToothManager.autoPairByMac(this,device.getAddress(),handler);
                break;
        }
    }
}
