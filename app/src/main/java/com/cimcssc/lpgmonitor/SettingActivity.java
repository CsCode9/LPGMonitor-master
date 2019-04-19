package com.cimcssc.lpgmonitor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
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
public class SettingActivity extends BaseActivity {
    @BindView(R.id.gateway_setting)
    TextView gateway_setting_Tv;
    @BindView(R.id.unloading_setting)
    TextView unloading_setting_Tv;
    @BindView(R.id.downtime_setting)
    TextView downtime_setting_Tv;
    @BindView(R.id.password_safety)
    TextView password_safety_Tv;
    @BindView(R.id.back_iv)
    ImageView back_Iv;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_iv,R.id.gateway_setting,R.id.unloading_setting,R.id.downtime_setting,
            R.id.password_safety})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.gateway_setting:
                startActivity(new Intent(SettingActivity.this, GatewayActivity.class));
                break;
            case R.id.unloading_setting:
                Intent intent1 = new Intent(SettingActivity.this, UnloadingActivity.class);
                startActivity(intent1);
                break;
            case R.id.downtime_setting:
                Intent intent2 = new Intent(SettingActivity.this, SettingSubActivity.class);
                startActivity(intent2);
                break;
            case R.id.password_safety:
                Intent intent3 = new Intent(SettingActivity.this, SafetyActivity.class);
                startActivity(intent3);
                break;
        }

    }
   /* public void Dialog( String title){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view1 = inflater.inflate(R.layout.ready_dailog_content,null);
        Button login_Bt = view1.findViewById(R.id.login_bt);
        ImageView back_Iv = (ImageView) view1.findViewById(R.id.back_iv);
        ImageView exit_Iv = (ImageView) view1.findViewById(R.id.exit_iv);
        TextView title_Tv = (TextView) view1.findViewById(R.id.title_tv);
        title_Tv.setText(title);
        final EditText password_Et = (EditText) view1.findViewById(R.id.password_et);
        password_Et.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        final Dialog dialog = new AlertDialog.Builder(mContext)
                //.setTitle("提示")
                .setView(view1)
                //.setMessage(mContext.getResources().getString(R.string
                //.add_oil_dialog_title))
                .setCancelable(false)
                .create();
        dialog.show();

        login_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = password_Et.getText().toString().trim();
                if(password.equals("")){
                    Toast.makeText(mContext,"请输入密码！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("1234")){
                    dialog.dismiss();
                    Toast.makeText(mContext,"登入成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SettingActivity.this, GatewayActivity.class);
                    startActivity(intent);
                }else{
                    password_Et.setText("");
                    Toast.makeText(mContext,"密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        back_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        exit_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }*/
}
