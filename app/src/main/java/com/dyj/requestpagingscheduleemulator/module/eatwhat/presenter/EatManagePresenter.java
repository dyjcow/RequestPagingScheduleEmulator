package com.dyj.requestpagingscheduleemulator.module.eatwhat.presenter;

import com.dyj.requestpagingscheduleemulator.base.BasePresenter;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.view.IEatManageView;

/**
 * @author ：Dyj
 * @date ：Created in 2022/11/25 20:10
 * @description：菜单管理页的中间层
 * @modified By：
 * @version: 1.0
 */
public class EatManagePresenter extends BasePresenter<IEatManageView> {
    /**
     * 构造方法的同时绑定View接口
     *
     * @param baseView 传入的BaseView的子类
     */
    public EatManagePresenter(IEatManageView baseView) {
        super(baseView);
    }
}
