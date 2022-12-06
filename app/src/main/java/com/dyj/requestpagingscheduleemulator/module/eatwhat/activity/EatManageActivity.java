package com.dyj.requestpagingscheduleemulator.module.eatwhat.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Handler;
import android.text.InputFilter;
import android.util.SparseArray;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.dyj.requestpagingscheduleemulator.R;
import com.dyj.requestpagingscheduleemulator.base.BaseActivity;
import com.dyj.requestpagingscheduleemulator.bean.DishName;
import com.dyj.requestpagingscheduleemulator.databinding.ActivityEatManageBinding;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.adapter.EatManageAdapter;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.adapter.MyItemTouchHelperCallback;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.commonview.DialogSureCancel;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.presenter.EatManagePresenter;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.view.IEatManageView;

import com.dyj.requestpagingscheduleemulator.util.MyUtil;
import com.tamsiree.rxkit.view.RxToast;
import com.tamsiree.rxui.view.dialog.RxDialogEditSureCancel;


import org.litepal.LitePal;

import java.util.List;

public class EatManageActivity extends BaseActivity<EatManagePresenter, ActivityEatManageBinding> implements IEatManageView {

    private EatManageAdapter adapter;
    private List<DishName> dishNameList;

    /**
     * 初始化presenter，也是与Activity的绑定
     *
     * @return 返回new的Presenter层的值
     */
    @Override
    protected EatManagePresenter createPresenter() {
        return new EatManagePresenter(this);
    }

    /**
     * 载入view的一些操作
     */
    @Override
    protected void initView() {
        dishNameList = LitePal.findAll(DishName.class);
//        MyItemTouchHelperCallback callback = new MyItemTouchHelperCallback();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getBinding().rvName.setLayoutManager(layoutManager);
        adapter = new EatManageAdapter(dishNameList);
        getBinding().rvName.setAdapter(adapter);
        //直接将其设置为常量false，因为这里不允许拖动
        getBinding().rvName.setStateCallback(enable -> {
//            callback.dragEnable = enable;
//            callback.dragEnable = false;
        });
//        new ItemTouchHelper(callback).attachToRecyclerView(getBinding().rvName);

        //一定要设置其这个点击事件的监听，因为在事件分发体系中，子View是需要被设置了点击事件才能在
        // onInterceptTouchEvent()中执行到 ACTION_MOVE 的
        // https://blog.csdn.net/qq_39431405/article/details/120823693
        adapter.setOnItemClickListener((adapter1, view, position) -> {});

        getBinding().ivFloatButton.setOnClickListener(v -> editDialogShow());
    }

    private void editDialogShow() {
        RxDialogEditSureCancel editSureCancel = new RxDialogEditSureCancel(this);
        editSureCancel.setTitle("空");
        editSureCancel.getTitleView().setTextSize(0L);
        editSureCancel.getEditText().setBackgroundResource(R.drawable.shape_edit_txt_white);
        editSureCancel.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                editSureCancel.getEditText().setBackgroundResource(R.drawable.shape_edit_txt_slide);
            }
        });
        editSureCancel.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
        //由于左右位置原因，所以将确认和取消按钮对调
        editSureCancel.setSure("取消");
        editSureCancel.setCancel("确定");
        editSureCancel.getCancelView().setTextColor(MyUtil.AppGetColor(R.color.blue));
        editSureCancel.getSureView().setTextColor(MyUtil.AppGetColor(R.color.background_dark));
        editSureCancel.getCancelView().setOnClickListener(v1 -> {
            String editTxt = editSureCancel.getEditText().getText().toString().trim();
            if (editTxt.equals("")){
                RxToast.warning("请正确输入菜名");
            }else {
                DishName dishName = new DishName();
                dishName.setName(editSureCancel.getEditText().getText().toString());
                dishName.save();
                editSureCancel.cancel();
                dishNameList.add(dishName);
                adapter.notifyItemInserted(dishNameList.size()-1);
                RxToast.success("加入成功");
            }
        });
        editSureCancel.getSureView().setOnClickListener(v1 -> {
            editSureCancel.cancel();
            RxToast.info("取消加入");
        });
        editSureCancel.setCancelable(false);
        editSureCancel.show();
    }

    /**
     * 载入数据操作
     */
    @Override
    protected void initData() {
        adapter.listener = pos -> {
            if (adapter.getItemCount() > 4){
                LitePal.delete(DishName.class,adapter.getItem(pos).getId());
                //由于adapter在使用这里list的时候，其未进行深度拷贝，所以移除的时候也要对这个空间列表进行移除。
                //否则会在界面上出现移除之后仍旧残留替补的效果。
                dishNameList.remove(pos);
                adapter.notifyItemRemoved(pos);
            }else {
                RxToast.error("无法删除，不能少于四道菜");
            }
        };
    }

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */
    @Override
    public void onBackPressed() {
        SparseArray<String> cacheMap = adapter.getCacheMap();

        if (cacheMap.size() != 0){
            DialogSureCancel sureCancel = new DialogSureCancel(this);
            sureCancel.setContent("是否需要保存数据");
            sureCancel.setCancel("保存后关闭");
            sureCancel.setSure("不保存");
            sureCancel.setCancelable(false);
            //由于左右位置原因，所以将确认和取消按钮对调
            sureCancel.setCancelListener(v -> {
                for (int i = 0; i < cacheMap.size(); i++){
                    int key = cacheMap.keyAt(i);
                    String value = cacheMap.get(key);
//                  //数据库事异步操作的，所以塞入的需要是一个新建的，占有唯一空间的ContentValues对象，
//                  //不然数据库都没操作，value就变了
                    ContentValues values = new ContentValues();
                    values.put("name",value);
                    LitePal.update(DishName.class,values,key);
                }

//                cacheMap.forEach((key,value)->{
//                    //数据库事异步操作的，所以塞入的需要是一个新建的，占有唯一空间的ContentValues对象，
//                    //不然数据库都没操作，value就变了
//                    ContentValues values = new ContentValues();
//                    values.put("name",value);
//                    LitePal.update(DishName.class,values,key);
//                });
                sureCancel.cancel();
                showLoading(true);
                new Handler().postDelayed(() -> {
                    MyUtil.dismissSuccessLoading();
                    super.onBackPressed();
                }, 500);
            });
            sureCancel.setSureListener(v -> {
                sureCancel.cancel();
                super.onBackPressed();
            });
            sureCancel.show();
        }else {
            super.onBackPressed();
        }

    }
}