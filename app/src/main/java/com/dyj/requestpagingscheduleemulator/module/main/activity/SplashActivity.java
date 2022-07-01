package com.dyj.requestpagingscheduleemulator.module.main.activity;


import android.graphics.Color;
import android.os.Handler;

import com.dyj.requestpagingscheduleemulator.base.BaseActivity;
import com.dyj.requestpagingscheduleemulator.databinding.ActivitySplashBinding;
import com.dyj.requestpagingscheduleemulator.module.main.presenter.SplashPresenter;
import com.dyj.requestpagingscheduleemulator.module.main.view.ISplashView;
import com.dyj.requestpagingscheduleemulator.util.ActivityUtil;

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
        new Handler().postDelayed(() -> ActivityUtil.startActivity(MainActivity.class,true),1000);
    }

    /**
     * 载入数据操作
     */
    @Override
    protected void initData() {

    }
}