package com.dyj.requestpagingscheduleemulator.module.eatwhat.activity;

import android.os.Handler;
import android.view.View;

import com.dyj.requestpagingscheduleemulator.base.BaseActivity;
import com.dyj.requestpagingscheduleemulator.databinding.ActivityEatBinding;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.presenter.EatPresenter;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.view.IEatView;
import com.dyj.requestpagingscheduleemulator.util.ActivityUtil;
import com.dyj.requestpagingscheduleemulator.util.LogUtil;
import com.tamsiree.rxui.view.RxRotateBar;
import com.tamsiree.rxui.view.RxRotateBarBasic;

import java.util.ArrayList;

public class EatActivity extends BaseActivity<EatPresenter, ActivityEatBinding> implements IEatView {

    private RxRotateBar rotateBar;
    private RxRotateBarBasic rotateBarBasicTmp;
//    private Handler handler;

    /**
     * 初始化presenter，也是与Activity的绑定
     *
     * @return 返回new的Presenter层的值
     */
    @Override
    protected EatPresenter createPresenter() {
        return new EatPresenter(this);
    }

    /**
     * 载入view的一些操作
     */
    @Override
    protected void initView() {
        rotateBar = getBinding().rotate;
        rotateBar.setAnimatorListener(new RxRotateBar.AnimatorListener() {
            @Override
            public void onRotateStart() {
                LogUtil.d("Start");
//                if (handler != null){
//                    handler.removeCallbacksAndMessages(null);
//                }
            }

            @Override
            public void onRotateEnd() {
                getBinding().button.setVisibility(View.VISIBLE);
                LogUtil.d("End");
            }

            @Override
            public void onRatingStart() {

                LogUtil.d("Start");
            }

            @Override
            public void onRatingEnd() {

                LogUtil.d("End");
            }
        });
        getBinding().button.setOnClickListener(v -> presenter.getRandomData());
        getBinding().ivSetting.setOnClickListener(v -> ActivityUtil.startActivity(EatManageActivity.class));
    }

    /**
     * 载入数据操作
     */
    @Override
    protected void initData() {
        presenter.setInitView();
    }

    @Override
    protected void onRestart() {
        presenter.refreshData();
        super.onRestart();
    }

    /**
     * 设置展示对应的驱动结果
     *
     * @param rotateList 传入设置的标签list
     */
    @Override
    public void setRotateView(ArrayList<RxRotateBarBasic> rotateList) {
        boolean isFirst;
        //首次创建
        if (rotateBarBasicTmp == null) {
            rotateBarBasicTmp = new RxRotateBarBasic(0,"不吃了");
            isFirst = true;
        }else {
            isFirst = false;
        }
        getBinding().button.setVisibility(View.GONE);
        rotateBar.removeAll();
        for (RxRotateBarBasic bar : rotateList) {
            rotateBar.addRatingBar(bar);
            if (bar.getRate() > rotateBarBasicTmp.getRate()){
                rotateBarBasicTmp = bar;
            }
        }
        rotateBar.setCenterText("");
        rotateBar.show();

        if (!isFirst) {
            rotateBar.setCenterText(rotateBarBasicTmp.getTitle());
        }else {
            rotateBar.setCenterText("好饿");
        }

//        int delayMillis = 3000+rotateBarBasicTmp.getRate()*350;
//
//        handler = new Handler();
//        handler.postDelayed(() -> {
//            if (!isFirst) {
//                rotateBar.setCenterText(rotateBarBasicTmp.getTitle());
//                LogUtil.d("执行handler"+delayMillis);
//            }else {
//                rotateBar.setCenterText("好饿");
//            }
//        },delayMillis);
    }
}