package com.dyj.requestpagingscheduleemulator.module.result.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dyj.requestpagingscheduleemulator.databinding.ItemDataBinding;
import com.dylanc.viewbinding.brvah.BaseViewHolderUtilKt;

import java.util.List;

/**
 * @author ：Dyj
 * @date ：Created in 2022/7/2 0:16
 * @description：dataAdapter
 * @modified By：
 * @version: 1.0
 */
public class DataAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public DataAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, Integer integer) {
        ItemDataBinding binding = BaseViewHolderUtilKt.getBinding(baseViewHolder,ItemDataBinding::bind);
        binding.dataIt.setText(String.valueOf(integer));
    }
}
