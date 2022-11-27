package com.dyj.requestpagingscheduleemulator.module.eatwhat.view;

import com.dyj.requestpagingscheduleemulator.base.BaseView;
import com.tamsiree.rxui.view.RxRotateBarBasic;

import java.util.ArrayList;

/**
 * @author ：Dyj
 * @date ：Created in 2022/11/23 10:55
 * @description：Eat的接口
 * @modified By：
 * @version: 1.0
 */
public interface IEatView extends BaseView {

    /**
     * 设置展示对应的驱动结果
     * @param rotateList 传入设置的标签list
     */
    void setRotateView(ArrayList<RxRotateBarBasic> rotateList);

}
