package com.dyj.requestpagingscheduleemulator.bean;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

/**
 * @author ：Dyj
 * @date ：Created in 2022/11/24 19:45
 * @description：记录对应的菜名
 * @modified By：
 * @version: 1.0
 */
public class DishName extends LitePalSupport implements Cloneable{
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public DishName clone() {
        try {
            return (DishName) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
