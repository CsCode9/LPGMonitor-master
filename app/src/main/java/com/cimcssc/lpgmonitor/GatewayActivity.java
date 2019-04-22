package com.cimcssc.lpgmonitor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author october
 */
public class GatewayActivity extends BaseActivity {
    @BindView(R.id.back_iv)
    ImageView back_Iv;
    @BindView(R.id.port_name_et)
    EditText port_name_Et;
    @BindView(R.id.IP_dress_et)
    EditText IP_dress_Et;
    @BindView(R.id.APN_name_et)
    EditText APN_name_Et;
    @BindView(R.id.save_settings_bt)
    Button save_settings_Bt;
    @BindView(R.id.title_tv)
    TextView title_Tv;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gatway_parameter_setting);
        ButterKnife.bind(this);
        title_Tv.setText("网关参数设置");

        sp = getSharedPreferences("configs",MODE_PRIVATE);


        addWatcher(port_name_Et);
        addWatcher(IP_dress_Et);
        addWatcher(APN_name_Et);
        readConfigs();
    }

    public void readConfigs(){
        port_name_Et.setText(sp.getString("port_name",""));
        IP_dress_Et.setText(sp.getString("IP_dress",""));
        APN_name_Et.setText(sp.getString("APN_name",""));
    }

    public void addWatcher(TextView tv){
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(port_name_Et.getText().toString().trim().length() != 0 &&
                        IP_dress_Et.getText().toString().trim().length() != 0 &&
                        APN_name_Et.getText().toString().trim().length() != 0 ){
                    save_settings_Bt.setClickable(true);
                    save_settings_Bt.setBackground(getResources().getDrawable(R.drawable.shape_login_button_on));
                }else{
                    save_settings_Bt.setClickable(false);
                    save_settings_Bt.setBackground(getResources().getDrawable(R.drawable.shape_login_button_off));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.back_iv,R.id.save_settings_bt})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.save_settings_bt:
                if(save_settings_Bt.isClickable()){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("port_name",port_name_Et.getText().toString());
                    editor.putString("IP_dress",IP_dress_Et.getText().toString());
                    editor.putString("APN_name",APN_name_Et.getText().toString());
                    editor.commit();
                    Toast.makeText(GatewayActivity.this,"参数设置保存成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
