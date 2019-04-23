package com.cimcssc.lpgmonitor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.utils.Config;
import com.utils.SelectTask;

import java.util.Timer;

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
    TextView unit_name_Et;
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

    private SharedPreferences share;
    private Context mContext;
    private String unit = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unloading_parameter_setting);
        ButterKnife.bind(this);
        title_Tv.setText("卸液参数设置");
        mContext = UnloadingActivity.this;

        share = getSharedPreferences("configs",MODE_PRIVATE);

        addWatcher(unit_name_Et);
        addWatcher(unit_price_name_Et);
        addWatcher(standard_coefficient_name_Et);
        addWatcher(compensation_coefficient_name_Et);
        readConfigs();
    }

    public void readConfigs(){
        unit_name_Et.setText(share.getString("unit_name",""));
        unit_price_name_Et.setText(share.getString("unit_price_name",""));
        standard_coefficient_name_Et.setText(share.getString("standard_coefficient_name",""));
        compensation_coefficient_name_Et.setText(share.getString("compensation_coefficient_name",""));
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

    @OnClick({R.id.back_iv,R.id.save_settings_bt,R.id.unit_name_et})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.unit_name_et:
                Dialog();
            case R.id.save_settings_bt:
                if(save_settings_Bt.isClickable()){
                    SharedPreferences.Editor editor = share.edit();
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

    public void Dialog(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view1 = inflater.inflate(R.layout.unloading_dailog_content,null);
        Button sure_Bt = view1.findViewById(R.id.unit_sure);
        ImageView exit_Iv = (ImageView) view1.findViewById(R.id.exit_iv);
        final Button unit_L =  (Button) view1.findViewById(R.id.unit_L);
        final Button unit_Kg =  (Button) view1.findViewById(R.id.unit_Kg);
        final Button unit_M =  (Button) view1.findViewById(R.id.unit_M);
        final Dialog dialog = new AlertDialog.Builder(mContext)
                //.setTitle("提示")
                .setView(view1)
                //.setMessage(mContext.getResources().getString(R.string
                //.add_oil_dialog_title))
                .setCancelable(false)
                .create();
        dialog.show();

        unit_L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unit = "L";
                unit_L.setBackgroundColor(Color.parseColor("#D11414"));
                unit_Kg.setBackgroundColor(Color.parseColor("#8D8787"));
                unit_M.setBackgroundColor(Color.parseColor("#8D8787"));
            }
        });

        unit_Kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unit = "Kg";
                unit_Kg.setBackgroundColor(Color.parseColor("#D11414"));
                unit_L.setBackgroundColor(Color.parseColor("#8D8787"));
                unit_M.setBackgroundColor(Color.parseColor("#8D8787"));
            }
        });

        unit_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unit = "M³";
                unit_M.setBackgroundColor(Color.parseColor("#D11414"));
                unit_Kg.setBackgroundColor(Color.parseColor("#8D8787"));
                unit_L.setBackgroundColor(Color.parseColor("#8D8787"));
            }
        });

        sure_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unit_name_Et.setText(unit);
                dialog.dismiss();
            }
        });

        exit_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}

