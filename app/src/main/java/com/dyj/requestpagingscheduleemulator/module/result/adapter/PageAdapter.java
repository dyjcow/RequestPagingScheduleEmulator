package com.dyj.requestpagingscheduleemulator.module.result.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dyj.requestpagingscheduleemulator.bean.PageData;
import com.dyj.requestpagingscheduleemulator.databinding.ItemPageBinding;
import com.dylanc.viewbinding.brvah.BaseViewHolderUtilKt;

import java.util.List;

/**
 * @author ：Dyj
 * @date ：Created in 2022/7/1 23:25
 * @description：adapter of page
 * @modified By：
 * @version: 1.0
 */
public class PageAdapter extends BaseQuickAdapter<PageData, BaseViewHolder> {
    public PageAdapter(int layoutResId, @Nullable List<PageData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, PageData data) {
        ItemPageBinding binding = BaseViewHolderUtilKt.getBinding(baseViewHolder,ItemPageBinding::bind);
        binding.page.setText(String.valueOf(data.getWaitData()));
        binding.page1.setText(String.valueOf(data.getData1()));
        if (data.getData2() > 0){
            binding.page2.setText(String.valueOf(data.getData2()));
        }else {
            binding.page2.setText("");
        }
        if (data.getData3() > 0){
            binding.page3.setText(String.valueOf(data.getData3()));
        }else {
            binding.page3.setText("");
        }
        if (data.getData4() > 0){
            binding.page4.setText(String.valueOf(data.getData4()));
        }else {
            binding.page4.setText("");
        }
    }
}
