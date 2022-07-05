package com.dyj.requestpagingscheduleemulator.module.main.activity;

import android.graphics.Color;
import android.os.Handler;

import com.dyj.requestpagingscheduleemulator.base.BaseActivity;
import com.dyj.requestpagingscheduleemulator.common.GlobalConstant;
import com.dyj.requestpagingscheduleemulator.databinding.ActivityMainBinding;
import com.dyj.requestpagingscheduleemulator.module.main.presenter.MainPresenter;
import com.dyj.requestpagingscheduleemulator.module.main.view.IMainView;
import com.dyj.requestpagingscheduleemulator.module.result.activity.ResultActivity;
import com.dyj.requestpagingscheduleemulator.util.ActivityUtil;

public class MainActivity extends BaseActivity<MainPresenter, ActivityMainBinding> implements IMainView {

    /**
     * 初始化presenter，也是与Activity的绑定
     *
     * @return 返回new的Presenter层的值
     */
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    /**
     * 载入view的一些操作
     */
    @Override
    protected void initView() {
        getBinding().button1LRU.setOnClickListener(v -> ActivityUtil.actionStart(ResultActivity.class, GlobalConstant.LRU));
        getBinding().button2OPT.setOnClickListener(v -> ActivityUtil.actionStart(ResultActivity.class,GlobalConstant.OPT));
        getBinding().button3FIFO.setOnClickListener(v -> ActivityUtil.actionStart(ResultActivity.class,GlobalConstant.FIFO));
        getBinding().button4Exit.setOnClickListener(v -> ActivityUtil.finishActivity(this));
    }

    /**
     * 载入数据操作
     */
    @Override
    protected void initData() {

    }
}