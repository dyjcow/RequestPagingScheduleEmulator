package com.dyj.requestpagingscheduleemulator.base;

/**
 * @author ：Dyj
 * @date ：Created in 2022/4/15 19:57
 * @description：写入两个之后会使用到的加载view
 * @modified By：
 * @version: 1.0
 */
public interface BaseView {

    /**
     * 展示载入动画
     */
    void showLoading(boolean isShow);

    /**
     * 操作成功隐藏dialog和显示成功
     */
    void SuccessHideLoading(boolean isShow);

    /**
     * 操作失败隐藏dialog和显示失败
     */
    void FailedHideLoading(boolean isShow);
}
