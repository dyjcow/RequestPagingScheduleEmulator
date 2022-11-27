package com.dyj.requestpagingscheduleemulator.module.eatwhat.adapter;

public interface ItemTouchMoveListener {

    /**
     * 拖拽的时候回调
     * @param fromPosition 起始位置
     * @param toPosition 最后位置
     * @return 是否移动
     */
    boolean onItemMove(int fromPosition,int toPosition);

    /**
     * 删除的时候回调
     * @param position 删除的位置
     * @return
     */
    boolean onItemRemove(int position);


    /**
     * 更新列表到数据库
     */
    void refreshList();
}
