package com.lyx.mycommunity.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lyx.shoppingcity.R;


import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    private EditText edt_username;
    private EditText edt_password;
    private EditText edt_email;
    private EditText et_confirmpws;
    private CheckBox cb_agree;
    private Button btn_login;
    private Button btn_register;
    private Intent intent;
    private EditText et_phone;
    private RadioGroup rg_gender;
    private RadioButton rb_male,rb_female;
    private ImageButton ibLoginVisible;
    private int count;
    private String sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        initListener();
    }


    private void initView() {
        // 获取布局文件中控件
        edt_username = findViewById(R.id.et_username);
        edt_password = findViewById(R.id.et_password);
        edt_password = findViewById(R.id.et_password);
        cb_agree = findViewById(R.id.checkbox_save);
        edt_email = findViewById(R.id.etEmail);
        et_confirmpws = findViewById(R.id.et_confirmpws);
        et_phone = findViewById(R.id.et_phone);
        rg_gender = findViewById(R.id.rg_gender);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);
        cb_agree = findViewById(R.id.cb_agree);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        ibLoginVisible = findViewById(R.id.ibLoginVisible);

    }

    private void initListener() {
        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rb_female.isChecked()) {
                    sex = "女";
                } else {
                    sex = "男";
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }
                String password = edt_password.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String password1 = et_confirmpws.getText().toString().trim();
                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(getApplicationContext(), "请输入确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "请输入电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = edt_email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "请输入邮箱", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 创建一个Shareference 对象,确定打开文件名及文件
                SharedPreferences sp = getSharedPreferences("register_date",MODE_PRIVATE);
                // 创建一个Shareference.Editor的编辑器editor
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("agree",cb_agree.isChecked());
                editor.putString("username",username);
                editor.putString("phone",et_phone.getText().toString());
                editor.putString("userpassword",edt_password.getText().toString());
                editor.putString("confirmpws",et_confirmpws.getText().toString());
                editor.putString("useremail",edt_email.getText().toString());
                editor.putString("male",sex );
                editor.apply();
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                Toast.makeText(getApplicationContext(),"注册成功啦,快去登陆吧~", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        /**
         * 密码是否隐藏监听
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
