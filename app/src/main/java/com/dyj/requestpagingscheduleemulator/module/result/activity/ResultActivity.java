package com.dyj.requestpagingscheduleemulator.module.result.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dyj.requestpagingscheduleemulator.R;
import com.dyj.requestpagingscheduleemulator.base.BaseActivity;
import com.dyj.requestpagingscheduleemulator.bean.PageData;
import com.dyj.requestpagingscheduleemulator.common.GlobalConstant;
import com.dyj.requestpagingscheduleemulator.databinding.ActivityResultBinding;
import com.dyj.requestpagingscheduleemulator.module.result.adapter.DataAdapter;
import com.dyj.requestpagingscheduleemulator.module.result.adapter.PageAdapter;
import com.dyj.requestpagingscheduleemulator.module.result.presenter.ResultPresenter;
import com.dyj.requestpagingscheduleemulator.module.result.view.IResult;
import com.dyj.requestpagingscheduleemulator.util.ActivityUtil;
import com.dyj.requestpagingscheduleemulator.util.MyUtil;
import com.dyj.requestpagingscheduleemulator.util.ToastUtil;

import java.util.List;

public class ResultActivity extends BaseActivity<ResultPresenter, ActivityResultBinding> implements IResult {


    /**
     * 初始化presenter，也是与Activity的绑定
     *
     * @return 返回new的Presenter层的值
     */
    @Override
    protected ResultPresenter createPresenter() {
        return new ResultPresenter(this);
    }

    /**
     * 载入view的一些操作
     */
    @Override
    protected void initView() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        String PIC_URL = "https://www.lxtlovely.top/getpic.php?rand=true";
        showPic(PIC_URL);
    }

    /**
     * 载入数据操作
     */
    @Override
    protected void initData() {
        int data = ActivityUtil.getIntentData();
        getBinding().button1CreateData.setOnClickListener(v -> presenter.random());
        switch (data){
            case GlobalConstant.OPT:
                getBinding().button1Todo.setOnClickListener(v -> presenter.OPT());
                break;
            case GlobalConstant.LRU:
                getBinding().button1Todo.setOnClickListener(v -> presenter.LRU());
                break;
            case GlobalConstant.FIFO:
                getBinding().button1Todo.setOnClickListener(v -> presenter.FIFO());
                break;
            default:
                break;

        }
    }

    /**
     * 展示背景图
     */
    @Override
    public void showPic(String url) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.pic_bg)
                .error(R.mipmap.pic_bg)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(this)
                .load(url)
//                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(getBinding().bgPic);
    }

    /**
     * 展示页面串
     */
    @Override
    public void showInstructions(List<Integer> datas) {
        DataAdapter adapter = new DataAdapter(R.layout.item_data,datas);
        getBinding().rvDatas.setAdapter(adapter);
        getBinding().rvDatas.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    /**
     * 展示页面变化
     */
    @Override
    public void showPages(List<PageData> list) {
        PageAdapter adapter = new PageAdapter(R.layout.item_page,list);
        getBinding().rvPages.setAdapter(adapter);
        getBinding().rvPages.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    /**
     * 展示缺页率
     */
    @Override
    public void showMissingPageRate(int lost) {
        double res = (double)lost/320;
        String text = 100*res + "%";
        getBinding().missingPageRate.setText(text);
    }

    @Override
    public void showReInit() {
        ToastUtil.showToast(MyUtil.getString(R.string.reinit));
    }
}