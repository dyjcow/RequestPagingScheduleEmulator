package com.dyj.requestpagingscheduleemulator.module.main.activity;


import android.graphics.Color;
import android.os.Handler;

import com.dyj.requestpagingscheduleemulator.base.BaseActivity;
import com.dyj.requestpagingscheduleemulator.bean.DishName;
import com.dyj.requestpagingscheduleemulator.common.GlobalConstant;
import com.dyj.requestpagingscheduleemulator.databinding.ActivitySplashBinding;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.activity.EatActivity;
import com.dyj.requestpagingscheduleemulator.module.main.presenter.SplashPresenter;
import com.dyj.requestpagingscheduleemulator.module.main.view.ISplashView;
import com.dyj.requestpagingscheduleemulator.util.ActivityUtil;
import com.dyj.requestpagingscheduleemulator.util.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplashActivity extends BaseActivity<SplashPresenter, ActivitySplashBinding> implements ISplashView {

    /**
     * 初始化presenter，也是与Activity的绑定
     *
     * @return 返回new的Presenter层的值
     */
    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    /**
     * 载入view的一些操作
     */
    @Override
    protected void initView() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        new Handler().postDelayed(() -> ActivityUtil.startActivity(EatActivity.class,true),1000);
    }

    /**
     * 载入数据操作
     */
    @Override
    protected void initData() {
        boolean isNotFirstUse = SpUtil.getBoolean(GlobalConstant.IS_NOT_FIRST_USE);
        if (!isNotFirstUse){
            SpUtil.setBoolean(GlobalConstant.IS_NOT_FIRST_USE,true);
            String[] nameArray = {"鸭腿","手撕鸡","F+咖喱鸡","红烧排骨","大众餐","猪杂汤","渔粉","早茶"};
            List<String> nameList;
            nameList = Stream.of(nameArray).collect(Collectors.toList());
            for (String name : nameList){
                DishName dishName = new DishName();
                dishName.setName(name);
                dishName.save();
            }
        }
    }
}