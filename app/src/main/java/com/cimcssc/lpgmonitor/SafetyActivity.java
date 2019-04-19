package com.cimcssc.lpgmonitor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafetyActivity extends BaseActivity {

    @BindView(R.id.back_iv)
    ImageView back_Iv;
    @BindView(R.id.title_tv)
    TextView title_Tv;
    @BindView(R.id.clear_history)
    TextView clear_history_Tv;
    @BindView(R.id.modify_administrator_password)
    TextView modify_administrator_password_Tv;
    @BindView(R.id.modify_super_password)
    TextView modify_super_password_Tv;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_safety);
        ButterKnife.bind(this);
        title_Tv.setText("密码与安全");
    }

    @OnClick({R.id.back_iv, R.id.clear_history, R.id.modify_administrator_password, R.id.modify_super_password})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.clear_history:
                Toast.makeText(this, "清除加液记录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.modify_administrator_password:
                Toast.makeText(this, "修改管理员密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.modify_super_password:
                Toast.makeText(this, "修改超级密码", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
