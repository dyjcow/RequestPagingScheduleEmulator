package com.dyj.requestpagingscheduleemulator.module.main.presenter;

import com.dyj.requestpagingscheduleemulator.base.BasePresenter;
import com.dyj.requestpagingscheduleemulator.module.main.activity.MainActivity;
import com.dyj.requestpagingscheduleemulator.module.main.view.IMainView;

/**
 * @author ：Dyj
 * @date ：Created in 2022/7/1 17:20
 * @description：P Of Main
 * @modified By：
 * @version: 1.0
 */
public class MainPresenter extends BasePresenter<IMainView> {
    /**
     * 构造方法的同时绑定View接口
     *
     * @param baseView 传入的BaseView的子类
     */
    public MainPresenter(IMainView baseView) {
        super(baseView);
    }
}
