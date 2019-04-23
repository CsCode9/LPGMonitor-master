package com.cimcssc.lpgmonitor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.utils.Config;
import com.utils.SelectTask;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;

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


    private Context mContext;
    private SharedPreferences shared;
    private InputStream ttyS1InputStream;
    private OutputStream ttyS1OutputStream;
    private String title = "";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_safety);
        ButterKnife.bind(this);
        title_Tv.setText("密码与安全");
        mContext = SafetyActivity.this;
        shared = getSharedPreferences("Password",MODE_PRIVATE);
    }

    @OnClick({R.id.back_iv, R.id.clear_history, R.id.modify_administrator_password, R.id.modify_super_password})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back_iv:
                if(!isHasTwoPassword()){
                    finish();
                }
                break;
            case R.id.clear_history:
                Toast.makeText(this, "清除加液记录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.modify_administrator_password:
                title = "请输入新的管理员密码\n以确认修改管理员密码";
                Dialog(title,1);
                break;
            case R.id.modify_super_password:
                title = "请输入新的超级密码\n以确认修改超级密码";
                Dialog(title,2);
                break;
        }
    }

    public void Dialog(String title, final int number){

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
                dialog.dismiss();
                SharedPreferences.Editor editor = shared.edit();
                if (number == 1){
                    if (password_Et.getText().toString().trim().length() == 0){
                        Toast.makeText(SafetyActivity.this, "请输入管理员密码",Toast.LENGTH_SHORT).show();
                    }else {
                        editor.putString("AdminPassword",password_Et.getText().toString().trim());
                        editor.commit();
                        Toast.makeText(SafetyActivity.this, "管理员密码设置成功！",Toast.LENGTH_SHORT).show();
                    }
                }else if (number == 2){
                    if (password_Et.getText().toString().trim().length() == 0){
                        Toast.makeText(SafetyActivity.this, "请输入超级密码",Toast.LENGTH_SHORT).show();
                    }else {
                        editor.putString("SuperPassword",password_Et.getText().toString().trim());
                        editor.commit();
                        Toast.makeText(SafetyActivity.this, "超级密码设置成功！",Toast.LENGTH_SHORT).show();
                    }
                }

                if(isHasTwoPassword()){
                    startActivity(new Intent(SafetyActivity.this,LoginActivity.class));
                }

            }
        });
        exit_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public boolean isHasTwoPassword(){
        boolean b = false;
        if(shared.getString("AdminPassword","").length() != 0 &&
                shared.getString("SuperPassword","").length() != 0){
            b = true;
        }
        return  b;
    }
}
