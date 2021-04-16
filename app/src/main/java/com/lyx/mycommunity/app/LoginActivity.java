package com.lyx.mycommunity.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lyx.shoppingcity.R;


import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    private EditText edt_username;
    private EditText edt_password;
    private CheckBox checkbox_save;
    private Button btn_login;
    private Button btn_register;
    private Intent intent;
    private String struser;
    private ImageButton ibLoginVisible;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化视图
        initView();
        // 事件监听
        initListener();

    }

    private void initView() {
        // 获取布局文件中控件
        edt_username = findViewById(R.id.et_username);
        edt_password = findViewById(R.id.et_password);
        checkbox_save = findViewById(R.id.checkbox_save);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        ibLoginVisible = (ImageButton) findViewById(R.id.ib_login_visible);
        SharedPreferences sp = getSharedPreferences("logindate",MODE_PRIVATE);
        boolean auto = sp.getBoolean("autosave",false);
        String uname = sp.getString("username","");
        String upassword = sp.getString("userpassword","");
        if (auto){
            checkbox_save.setChecked(true);
            edt_username.setText(uname);
            edt_password.setText(upassword);
        }
    }

    private void initListener() {

        /**
         * 登录按钮事件监听
         */
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个Shareference 对象,确定打开文件名及文件
                SharedPreferences sp = getSharedPreferences("logindate",MODE_PRIVATE);
                // 创建一个Shareference.Editor的编辑器editor
                SharedPreferences.Editor editor = sp.edit();
                // 通过editor编辑器的putXXX方法进行数据的提交
                editor.putBoolean("autosave",checkbox_save.isChecked());
                editor.putString("username",edt_username.getText().toString());
                editor.putString("userpassword",edt_password.getText().toString());
                editor.apply();
                // 报数据传给主页面
                intent = new Intent(LoginActivity.this,MainActivity.class);
                Toast.makeText(getApplicationContext(),"登录成功", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        /**
         * 注册按钮事件监听
         */
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 密码隐藏监听
         */
        ibLoginVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == ibLoginVisible) {
                    count++;
                    if (count % 2 == 0) {
                        ibLoginVisible.setBackgroundResource(R.mipmap.new_password_drawable_invisible1);
                        edt_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    } else {
                        ibLoginVisible.setBackgroundResource(R.mipmap.new_password_drawable_visible1);
                        edt_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                }
            }
        });
    }


}
