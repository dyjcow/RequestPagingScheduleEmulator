package com.dyj.requestpagingscheduleemulator.module.eatwhat.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dyj.requestpagingscheduleemulator.util.LogUtil;

/**
 * @author ：Dyj
 * @date ：Created in 2022/11/26 13:34
 * @description：TouchHelperCallback
 * @modified By：
 * @version: 1.0
 */
public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public boolean dragEnable = false;

    /**
     * Should return a composite flag which defines the enabled move directions in each state
     * (idle, swiping, dragging).
     * <p>
     * Instead of composing this flag manually, you can use {@link #makeMovementFlags(int,
     * int)}
     * or {@link #makeFlag(int, int)}.
     * <p>
     * This flag is composed of 3 sets of 8 bits, where first 8 bits are for IDLE state, next
     * 8 bits are for SWIPE state and third 8 bits are for DRAG state.
     * Each 8 bit sections can be constructed by simply OR'ing direction flags defined in
     * {@link ItemTouchHelper}.
     * <p>
     * For example, if you want it to allow swiping LEFT and RIGHT but only allow starting to
     * swipe by swiping RIGHT, you can return:
     * <pre>
     *      makeFlag(ACTION_STATE_IDLE, RIGHT) | makeFlag(ACTION_STATE_SWIPE, LEFT | RIGHT);
     * </pre>
     * This means, allow right movement while IDLE and allow right and left movement while
     * swiping.
     *
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached.
     * @param viewHolder   The ViewHolder for which the movement information is necessary.
     * @return flags specifying which movements are allowed on this ViewHolder.
     * @see #makeMovementFlags(int, int)
     * @see #makeFlag(int, int)
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(
                (ItemTouchHelper.UP | ItemTouchHelper.DOWN),
                0
        );
    }

    /**
     * Called when ItemTouchHelper wants to move the dragged item from its old position to
     * the new position.
     * <p>
     * If this method returns true, ItemTouchHelper assumes {@code viewHolder} has been moved
     * to the adapter position of {@code target} ViewHolder
     * ({@link ViewHolder#getAbsoluteAdapterPosition()
     * ViewHolder#getAdapterPositionInRecyclerView()}).
     * <p>
     * If you don't support drag & drop, this method will never be called.
     *
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to.
     * @param viewHolder   The ViewHolder which is being dragged by the user.
     * @param target       The ViewHolder over which the currently active item is being
     *                     dragged.
     * @return True if the {@code viewHolder} has been moved to the adapter position of
     * {@code target}.
     * @see #onMoved(RecyclerView, ViewHolder, int, ViewHolder, int, int, int)
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    /**
     * Called when a ViewHolder is swiped by the user.
     * <p>
     * If you are returning relative directions ({@link #START} , {@link #END}) from the
     * {@link #getMovementFlags(RecyclerView, ViewHolder)} method, this method
     * will also use relative directions. Otherwise, it will use absolute directions.
     * <p>
     * If you don't support swiping, this method will never be called.
     * <p>
     * ItemTouchHelper will keep a reference to the View until it is detached from
     * RecyclerView.
     * As soon as it is detached, ItemTouchHelper will call
     * {@link #clearView(RecyclerView, ViewHolder)}.
     *
     * @param viewHolder The ViewHolder which has been swiped by the user.
     * @param direction  The direction to which the ViewHolder is swiped. It is one of
     *                   {@link #UP}, {@link #DOWN},
     *                   {@link #LEFT} or {@link #RIGHT}. If your
     *                   {@link #getMovementFlags(RecyclerView, ViewHolder)}
     *                   method
     *                   returned relative flags instead of {@link #LEFT} / {@link #RIGHT};
     *                   `direction` will be relative as well. ({@link #START} or {@link
     *                   #END}).
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        LogUtil.d("isLongPressDragEnabled: "+dragEnable);
        return dragEnable;
    }


}
