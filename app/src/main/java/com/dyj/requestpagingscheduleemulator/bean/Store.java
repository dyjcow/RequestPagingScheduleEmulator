package com.dyj.requestpagingscheduleemulator.bean;

import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;

/**
 * @author ：Dyj
 * @date ：Created in 2022/11/30 21:09
 * @description：储存类
 * @modified By：
 * @version: 1.0
 */
public class Store {
    private KeyListener storedKeyListener;
    private Drawable storeBackGroup;

    public KeyListener getStoredKeyListener() {
        return storedKeyListener;
    }

    public void setStoredKeyListener(KeyListener storedKeyListener) {
        this.storedKeyListener = storedKeyListener;
    }

    public Drawable getStoreBackGroup() {
        return storeBackGroup;
    }

    public void setStoreBackGroup(Drawable storeBackGroup) {
        this.storeBackGroup = storeBackGroup;
    }
}
