package com.lyx.mycommunity.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lyx.mycommunity.app.LoginActivity;
import com.lyx.mycommunity.app.MessageCenterActivity;
import com.lyx.mycommunity.app.UserInfoActivity;
import com.lyx.mycommunity.utils.BatteryChangeReceiver;
import com.lyx.shoppingcity.R;
import com.lyx.mycommunity.base.BaseFragment;

import java.io.File;

public class UserFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG =  UserFragment.class.getSimpleName();
    private TextView textView,tv_all_order;
    private Context myContent;
    private ImageButton ibUserIconAvator;
    private TextView tvUsercenter;
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;
    private ScrollView scrollView;
    private ScrollView scrollview;
    private LinearLayout llWeiji;
    private LinearLayout llMsg;
    private LinearLayout llPhoto;
    private LinearLayout llCall;
    private LinearLayout llFriend;
    private LinearLayout llVersion;




    @Override
    public View initView() {
        Log.e(TAG, "用户中心的Frament的UI被初始化");
        View view = View.inflate(myContext, R.layout.fragment_user,null);
        findViews(view);
        tvUsercenter.setAlpha(0);

        return view;

    }

    public void findViews(View view) {
        ibUserIconAvator = (ImageButton) view.findViewById(R.id.ib_user_icon_avator);
        tvUsercenter = (TextView) view.findViewById(R.id.tv_usercenter);
        ibUserMessage = (ImageButton) view.findViewById(R.id.ib_user_message);
        scrollView = (ScrollView) view.findViewById(R.id.scrollview);
        tv_all_order =(TextView) view.findViewById(R.id.tv_all_order);

        scrollview = (ScrollView) view.findViewById(R.id.scrollview);
        llWeiji = (LinearLayout) view.findViewById(R.id.ll_weiji);
        llMsg = (LinearLayout) view.findViewById(R.id.ll_msg);
        llPhoto = (LinearLayout) view.findViewById(R.id.ll_photo);
        llCall = (LinearLayout) view.findViewById(R.id.ll_call);
        llFriend = (LinearLayout) view.findViewById(R.id.ll_friend);
        llVersion = (LinearLayout) view.findViewById(R.id.ll_version);

        llMsg.setOnClickListener(this);
        llPhoto.setOnClickListener(this);
        llCall.setOnClickListener(this);
        llFriend.setOnClickListener(this);
        ibUserIconAvator.setOnClickListener(this);
        llVersion.setOnClickListener(this);
        ibUserSetting.setOnClickListener(this);
        tv_all_order.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == ibUserIconAvator) {
            Intent intent = new Intent(myContext, LoginActivity.class);
            startActivity(intent);

        } else if (v == ibUserSetting) {
            Toast.makeText(myContext, "设置", Toast.LENGTH_SHORT).show();
        } else if (v == ibUserMessage) {
            Intent intent = new Intent(myContext, MessageCenterActivity.class);
            startActivity(intent);
        }else if (v == llCall){
            //隐式Intent 由android系统帮助匹配
            //匹配规则，清单文件中的 Intent_filter 标签中的action
            Uri uri = Uri.parse("tel:10086");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        }else if (v == llMsg){
            Intent intent2 = new Intent(Intent.ACTION_SEND);
            intent2.setAction("android.intent.action.SENDTO");
            intent2.addCategory("android.intent.category.DEFAULT");
            intent2.setData(Uri.parse("sms:110"));
            startActivity(intent2);
        }else if (v == llPhoto){
            Intent it4 =new Intent(Intent.ACTION_VIEW);
            File file2=new File("/storage/emulated/0/Pictures/");
            it4.setDataAndType(Uri.fromFile(file2),"image/*");
            startActivity(it4);
        }else if (v == tv_all_order){
            Intent myinfo = new Intent(getContext(), UserInfoActivity.class);
            startActivity(myinfo);
        }
    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "用户中心的Fragment的数据被初始化了");

    }

}
