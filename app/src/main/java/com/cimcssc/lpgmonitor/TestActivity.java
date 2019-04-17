package com.cimcssc.lpgmonitor;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import junit.framework.Test;

import butterknife.ButterKnife;

public class TestActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_safety);
    }
}
