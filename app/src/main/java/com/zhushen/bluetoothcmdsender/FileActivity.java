package com.zhushen.bluetoothcmdsender;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhushen.bluetoothcmdsender.adapter.FileAdapter;
import com.zhushen.bluetoothcmdsender.base.BaseActivity;

public class FileActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private TextView filePathTextView;
    private ListView fileList;
    FileAdapter fileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        init();
    }

    private void init() {
        filePathTextView = (TextView)findViewById(R.id.file_path_text);
        fileList = (ListView)findViewById(R.id.file_list);
        fileAdapter = new FileAdapter(this);
        fileList.setAdapter(fileAdapter);
        fileList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
