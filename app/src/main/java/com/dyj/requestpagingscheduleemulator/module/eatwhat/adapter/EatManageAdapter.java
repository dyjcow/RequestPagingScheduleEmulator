package com.dyj.requestpagingscheduleemulator.module.eatwhat.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.SparseArray;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dyj.requestpagingscheduleemulator.R;
import com.dyj.requestpagingscheduleemulator.bean.DishName;
import com.dyj.requestpagingscheduleemulator.bean.Store;
import com.dyj.requestpagingscheduleemulator.common.GlobalConstant;
import com.dyj.requestpagingscheduleemulator.databinding.ItemDishBinding;
import com.dyj.requestpagingscheduleemulator.util.ActivityUtil;
import com.dyj.requestpagingscheduleemulator.util.LogUtil;
import com.dyj.requestpagingscheduleemulator.util.MyUtil;
import com.dylanc.viewbinding.brvah.BaseViewHolderUtilKt;
import com.tamsiree.rxkit.view.RxToast;

import java.util.HashMap;
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

    //这个更加节省内存
    private final SparseArray<String> cacheMap;
    private final HashMap<Integer,Store> backGroupMap;
//    private KeyListener storedKeyListener;
//    private Drawable storeBackGroup;

    public EatManageAdapter(@Nullable List<DishName> data) {
        super(R.layout.item_dish, data);
        cacheMap = new SparseArray<>();
        backGroupMap = new HashMap<>();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, DishName dishName) {
        ItemDishBinding binding = BaseViewHolderUtilKt.getBinding(baseViewHolder,ItemDishBinding::bind);
        LogUtil.d("convert");
        Store store= new Store();

        Drawable storeBackGroup;
//        if (storedKeyListener == null){
//            LogUtil.d("storeInit");
//            storedKeyListener = binding.tvName.getKeyListener();
//            storeBackGroup = binding.tvName.getBackground();
//        }

        // TODO: 2022/11/30 有大问题
        if (backGroupMap.get(dishName.getId()) == null){
            store.setStoredKeyListener(binding.tvName.getKeyListener());
            store.setStoreBackGroup(binding.tvName.getBackground());
            backGroupMap.put(dishName.getId(),store);
        }else {
            store = backGroupMap.get(dishName.getId());
        }

        binding.tvName.setText(dishName.getName());
        binding.tvDelete.setOnClickListener(v -> listener.onListRemove(getItemPosition(dishName)));
        binding.tvName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE){
                confirmData(dishName,binding);
            }
            return false;
        });
        setReadOnly(binding.tvName);


        Store finalStore = store;
        binding.ivWrite.setOnClickListener(v -> {
//            https://blog.csdn.net/qq_34681580/article/details/119672649
            if (binding.ivWrite.getContentDescription().equals(GlobalConstant.WRITE)){
                setEditable(binding.tvName, finalStore.getStoreBackGroup(), finalStore.getStoredKeyListener());
                binding.ivWrite.setImageResource(R.drawable.ic_confirm);
                binding.ivWrite.setContentDescription(GlobalConstant.CONFIRM);
            }else {
                confirmData(dishName, binding);
            }
        });

        binding.tvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                LogUtil.d("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                LogUtil.d("onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //防止因为其他原因触动这个机制
                if (binding.ivWrite.getContentDescription().equals(GlobalConstant.CONFIRM)){
                    LogUtil.d(s.toString().trim());
                    cacheMap.put(dishName.getId(),s.toString().trim());
                }
            }
        });
    }


    public SparseArray<String> getCacheMap() {
        return cacheMap;
    }

    private void confirmData(DishName dishName, ItemDishBinding binding) {
//        存储数据库方法1
//        ContentValues values = new ContentValues();
//        values.put("name", binding.tvName.getText().toString().trim());
//        LitePal.update(DishName.class,values, dishName.getId());

//        存储数据库方法2
        dishName.setName(binding.tvName.getText().toString().trim());
        dishName.update(dishName.getId());

        setReadOnly(binding.tvName);
        binding.ivWrite.setImageResource(R.drawable.ic_write);
        binding.ivWrite.setContentDescription(GlobalConstant.WRITE);
        MyUtil.closeSoftKeyboard();
        RxToast.success("修改成功");
        cacheMap.remove(dishName.getId());
    }

    public interface OnListRemoveListener {
        void onListRemove(int pos);
    }

    private void setReadOnly(EditText editText){
        // 设置KeyListener为null, 变为不可输入状态
        editText.setKeyListener(null);
        // 如果需要,设置文字可选
        editText.setTextIsSelectable(false);
        editText.setBackground(null);

        //不启动控件
//        editText.setEnabled(false);

    }

    private void setEditable(EditText editText, Drawable storeBackGroup, KeyListener storedKeyListener){
        // 可编辑时弹出软键盘
        final InputMethodManager imm = (InputMethodManager) ActivityUtil.getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 恢复KeyListener
        editText.setKeyListener(storedKeyListener);
//        //恢复背景
        editText.setBackground(storeBackGroup);

//        // 如果需要,设置文字可选
        editText.setTextIsSelectable(true);
        editText.requestFocus();
        imm.showSoftInput(editText, 0);

        // 将光标定位到最后
        editText.setSelection(editText.getText().length());
    }
}
