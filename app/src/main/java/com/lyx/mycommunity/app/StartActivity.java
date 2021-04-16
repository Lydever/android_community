package com.lyx.mycommunity.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.lyx.shoppingcity.R;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_start);
        // 启动异步线程,5秒后开始跳转
        new UIAsyncTask().execute(null,null);
    }

    private class UIAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // 程序等待: 线程等待是一个比较耗时的操作,不能再主线程中操作
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = null;
            // 根据属性设置判断进入那个页面
            boolean isFirst = getSharedPreferences("Start",MODE_PRIVATE).getBoolean("isFirst",false);
            if (isFirst) {
                intent = new Intent(getBaseContext(),GuideActivity.class);
            } else {
                intent = new Intent(getBaseContext(), MainActivity.class);
            }
            startActivity(intent);
            // 把启动页面从视图中删除,或者不保存
            StartActivity.this.finish();
        }
    }
}

















