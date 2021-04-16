package com.lyx.mycommunity.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author create by liyingxia
 * 创建日期：2021/1/4 20:46
 * 包名： com.lyx.mycommunity.adapter
 * 描述：
 */
public class VpAdapter extends PagerAdapter {


    private final List<ImageView> imageViews;

    public VpAdapter(List<ImageView> imageViews) {
        this.imageViews= imageViews;
    }

    /**
     * 获取当前要显示对象的数量
     * @return
     */
    @Override
    public int getCount() {
        return imageViews.size();
    }

    /**
     * 判断是否用对象生成界面
     * @param view
     * @param o
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view== o;
    }

    /**
     * 从ViewGroup中移除当前对象
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViews.get(position));
    }


    /**
     * 当前要显示的对象
     * @param container
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }
}
