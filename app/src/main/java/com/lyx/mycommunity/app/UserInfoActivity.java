package com.lyx.mycommunity.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lyx.shoppingcity.R;

public class UserInfoActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageView ibImg;
    private EditText etNicke;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etPsw;
    private RadioGroup rgSex;
    private RadioButton rbMan;
    private RadioButton rbWoman;
    private Button btnUpdate;
    private Button btnClear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        ibImg = (ImageView) findViewById(R.id.ib_img);
        etNicke = (EditText) findViewById(R.id.et_nicke);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPsw = (EditText) findViewById(R.id.etPsw);
        rgSex = (RadioGroup) findViewById(R.id.rg_sex);
        rbMan = (RadioButton) findViewById(R.id.rb_man);
        rbWoman = (RadioButton) findViewById(R.id.rb_woman);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnClear = (Button) findViewById(R.id.btn_clear);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences sp = getSharedPreferences("register_date",MODE_PRIVATE);
        String name = sp.getString("username","");
        String psw = sp.getString("userpassword","");
        String phone = sp.getString("uphone","");
        String email = sp.getString("useremail","");
        etNicke.setText(name);
        etPhone.setText(phone);
        etPsw.setText(psw);
        etEmail.setText(email);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个Shareference 对象,确定打开文件名及文件
                SharedPreferences sp = getSharedPreferences("register_date",MODE_PRIVATE);
                // 创建一个Shareference.Editor的编辑器editor
                SharedPreferences.Editor editor = sp.edit();
                if (!etNicke.getText().toString().equals("")&&!etPsw.getText().toString().equals("")&&
                    !etPhone.getText().toString().equals("") &&!etEmail.getText().toString().equals("")){
                    // 通过editor编辑器的putXXX方法进行数据的提交
                    editor.putString("username",etNicke.getText().toString());
                    editor.putString("userpassword",etPsw.getText().toString());
                    editor.putString("uphone",etPhone.getText().toString());
                    editor.putString("useremail",etEmail.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "请输入完整信息", Toast.LENGTH_SHORT).show();
                }
                editor.apply();
                Toast.makeText(getApplicationContext(),"信息保存成功~", Toast.LENGTH_SHORT).show();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEmail.setText("");
                etNicke.setText("");
                etPhone.setText("");
                etPsw.setText("");
            }
        });
    }
}
