package com.dyj.requestpagingscheduleemulator.module.eatwhat.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dyj.requestpagingscheduleemulator.R;
import com.dyj.requestpagingscheduleemulator.bean.DishName;
import com.dyj.requestpagingscheduleemulator.databinding.ItemDishBinding;
import com.dylanc.viewbinding.brvah.BaseViewHolderUtilKt;

import java.util.List;

/**
 * @author ：Dyj
 * @date ：Created in 2022/11/25 21:17
 * @description：菜单管理器的页面
 * @modified By：
 * @version: 1.0
 */
public class EatManageAdapter extends BaseQuickAdapter<DishName, BaseViewHolder> {

    public OnListRemoveListener listener;

    public EatManageAdapter(@Nullable List<DishName> data) {
        super(R.layout.item_dish, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DishName dishName) {
        ItemDishBinding binding = BaseViewHolderUtilKt.getBinding(baseViewHolder,ItemDishBinding::bind);
        binding.tvName.setText(dishName.getName());
        binding.tvDelete.setOnClickListener(v -> listener.onListRemove(getItemPosition(dishName)));
    }

    public interface OnListRemoveListener {
        void onListRemove(int pos);
    }
}
