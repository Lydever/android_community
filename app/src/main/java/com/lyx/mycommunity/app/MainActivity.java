package com.lyx.mycommunity.app;


import android.content.Intent;
import android.os.Bundle;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.lyx.mycommunity.adapter.HomeRecycleAdapter;
import com.lyx.mycommunity.base.BaseFragment;
import com.lyx.mycommunity.fragment.ServiceFragment;
import com.lyx.mycommunity.fragment.NewsFragment;
import com.lyx.mycommunity.fragment.HomeFragment;
import com.lyx.mycommunity.fragment.UserFragment;
import com.lyx.mycommunity.utils.BatteryChangeReceiver;
import com.lyx.shoppingcity.R;
import com.lyx.mycommunity.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_category)
    RadioButton rbType;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_search)
    RadioButton rbSearch;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Intent intent;
    private String struser;
    private TextView tv_user;
    /**
     * 缓存的Fragemnt或者上次显示的Fragment
     */
    private Fragment tempFragemnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgMain = findViewById(R.id.rg_main);
        ButterKnife.bind(this);

        initFragment();
        initListener();

    }
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ServiceFragment());
        fragments.add(new NewsFragment());
        fragments.add(new SearchFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_category:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        /*intent = new Intent(MainActivity.this,NoteShowActivity.class);
                        startActivity(intent);*/
                        break;
                    case R.id.rb_search:
                        position = 3;
                        intent = new Intent(MainActivity.this,NoteShowActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }

                // 获取相应的fragment位置
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragemnt, baseFragment);
            }
        });

        rgMain.check(R.id.rb_home);

    }


    /**
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    // 隐藏上一个fragment
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}
