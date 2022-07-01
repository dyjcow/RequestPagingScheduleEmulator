package com.dyj.requestpagingscheduleemulator.base;

/**
 * @author ：Dyj
 * @date ：Created in 2022/4/17 13:05
 * @description：Base presenter层
 * @modified By：
 * @version: 1.0
 */
public class BasePresenter <V extends BaseView>{

    /**
     * 初始化接口View
     */
    public V baseView;


    /**
     * 构造方法的同时绑定View接口
     *
     * @param baseView 传入的BaseView的子类
     */
    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }


    /**
     * 解除与接口View的绑定
     */
    public void detachView(){
        baseView = null;
    }


    /**
     * 获取当前View
     *
     * @return 返回当前接口View
     */
    public V getBaseView() {
        return baseView;
    }
}
