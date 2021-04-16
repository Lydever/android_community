package com.lyx.mycommunity.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lyx.shoppingcity.R;

public class MessageCenterActivity extends AppCompatActivity {
    private ImageButton ib_login_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ib_login_back = (ImageButton) findViewById(R.id.ib_login_back);

        ib_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
