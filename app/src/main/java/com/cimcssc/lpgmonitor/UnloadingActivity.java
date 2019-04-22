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
public class UnloadingActivity extends BaseActivity {
    @BindView(R.id.back_iv)
    ImageView back_Iv;
    @BindView(R.id.unit_name_et)
    EditText unit_name_Et;
    @BindView(R.id.unit_price_name_et)
    EditText unit_price_name_Et;
    @BindView(R.id.standard_coefficient_name_et)
    EditText standard_coefficient_name_Et;
    @BindView(R.id.compensation_coefficient_name_et)
    EditText compensation_coefficient_name_Et;
    @BindView(R.id.save_settings_bt)
    Button save_settings_Bt;
    @BindView(R.id.title_tv)
    TextView title_Tv;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unloading_parameter_setting);
        ButterKnife.bind(this);
        title_Tv.setText("卸液参数设置");

        sp = getSharedPreferences("configs",MODE_PRIVATE);


        addWatcher(unit_name_Et);
        addWatcher(unit_price_name_Et);
        addWatcher(standard_coefficient_name_Et);
        addWatcher(compensation_coefficient_name_Et);
        readConfigs();
    }

    public void readConfigs(){
        unit_name_Et.setText(sp.getString("unit_name",""));
        unit_price_name_Et.setText(sp.getString("unit_price_name",""));
        standard_coefficient_name_Et.setText(sp.getString("standard_coefficient_name",""));
        compensation_coefficient_name_Et.setText(sp.getString("compensation_coefficient_name",""));
    }

    public void addWatcher(TextView tv){
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(unit_name_Et.getText().toString().trim().length() != 0 &&
                        unit_price_name_Et.getText().toString().trim().length() != 0 &&
                        standard_coefficient_name_Et.getText().toString().trim().length() != 0 &&
                        compensation_coefficient_name_Et.getText().toString().trim().length() != 0){
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
                    editor.putString("port_name",unit_name_Et.getText().toString());
                    editor.putString("IP_dress",unit_price_name_Et.getText().toString());
                    editor.putString("APN_name",standard_coefficient_name_Et.getText().toString());
                    editor.putString("port_name",compensation_coefficient_name_Et.getText().toString());
                    editor.commit();
                    Toast.makeText(UnloadingActivity.this,"参数设置保存成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
