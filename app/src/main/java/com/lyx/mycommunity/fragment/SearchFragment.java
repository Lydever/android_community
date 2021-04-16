package com.lyx.mycommunity.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lyx.mycommunity.base.BaseFragment;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/13 13:15
 * 包名：
 * 描述：
 */
public class SearchFragment extends BaseFragment {
    private static final String TAG = SearchFragment.class.getSimpleName();
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG,"搜索视图被初始化了");
        textView = new TextView(myContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"搜索数据被初始化了");
         textView.setText(" ");
    }
}
