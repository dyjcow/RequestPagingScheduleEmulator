package com.dyj.requestpagingscheduleemulator.module.result.view;

import com.dyj.requestpagingscheduleemulator.base.BaseView;
import com.dyj.requestpagingscheduleemulator.bean.PageData;

import java.util.List;

/**
 * @author ：Dyj
 * @date ：Created in 2022/7/1 19:31
 * @description：view of result
 * @modified By：
 * @version: 1.0
 */
public interface IResult extends BaseView {
    /**
     * 展示背景图
     */
    void showPic(String url);

    /**
     * 展示页面串
     */
    void showInstructions(List<Integer> datas);

    /**
     * 展示页面变化
     */
    void showPages(List<PageData> list);

    /**
     * 展示缺页率
     */
    void showMissingPageRate(int lost);

    void showReInit();
}
