package com.zhushen.bluetoothcmdsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhushen.bluetoothcmdsender.base.BaseActivity;

public class SenderActivity extends BaseActivity implements View.OnClickListener {
    private Button fileSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);
        init();
    }

    private void init() {
        fileSelectBtn = (Button)findViewById(R.id.file_select_btn);
        fileSelectBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.file_select_btn:
                Intent intent = new Intent();
                intent.setClass(SenderActivity.this,FileActivity.class);
                startActivity(intent);
                break;
        }
    }
}
