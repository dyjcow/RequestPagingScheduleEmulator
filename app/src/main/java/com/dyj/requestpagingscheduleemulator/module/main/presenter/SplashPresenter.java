package com.dyj.requestpagingscheduleemulator.module.main.presenter;

import com.dyj.requestpagingscheduleemulator.base.BasePresenter;
import com.dyj.requestpagingscheduleemulator.module.main.view.ISplashView;

/**
 * @author ：Dyj
 * @date ：Created in 2022/7/1 17:52
 * @description：ps of Splash
 * @modified By：
 * @version: 1.0
 */
public class SplashPresenter extends BasePresenter<ISplashView> {
    /**
     * 构造方法的同时绑定View接口
     *
     * @param baseView 传入的BaseView的子类
     */
    public SplashPresenter(ISplashView baseView) {
        super(baseView);
    }
}
