package com.lyx.mycommunity.utils;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/20 19:06
 * 包名： com.lyx.mycommunity.utils
 * 描述：
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/12 20:15
 * 包名： com.example.sqlite.BroadcastReceiver
 * 描述：广播接收监听网络变化
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    private static final String TAG = NetworkStateReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"网络状态发生改变~");
        if (!isNetworkAvaiable(context)) {
            Toast.makeText(context, "网络不可用~", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 网络是否可用
     * @param context
     * @return
     */
    private boolean isNetworkAvaiable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

}