package com.lyx.mycommunity.utils;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/20 19:07
 * 包名： com.lyx.mycommunity.utils
 * 描述：监听手机电池电量变化
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class BatteryChangeReceiver extends BroadcastReceiver {

    private static final String TAG = BatteryChangeReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            int level = intent.getIntExtra("level",0);  // 获取电量等级
            int total = intent.getIntExtra("scale",0);   // 获取总电量
            int percent = level * 100 / total;   // 获取当前电量的百分比
            // Log.i(TAG,"battery: " + percent + "%");
            Toast.makeText(context, "手机还有"+percent+"%的电", Toast.LENGTH_SHORT).show();
        }


    }
}
