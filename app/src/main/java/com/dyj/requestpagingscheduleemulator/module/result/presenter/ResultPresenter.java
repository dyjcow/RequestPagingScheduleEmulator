package com.dyj.requestpagingscheduleemulator.module.result.presenter;

import com.dyj.requestpagingscheduleemulator.base.BasePresenter;
import com.dyj.requestpagingscheduleemulator.bean.PageData;
import com.dyj.requestpagingscheduleemulator.common.GlobalConstant;
import com.dyj.requestpagingscheduleemulator.module.result.view.IResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 * @author ：Dyj
 * @date ：Created in 2022/7/1 19:33
 * @description：PS of result
 * @modified By：
 * @version: 1.0
 */
public class ResultPresenter extends BasePresenter<IResult> {

    List<PageData> list = new ArrayList<>();

    List<Integer> datas = new ArrayList<>();

    List<Integer> showData = new ArrayList<>();

    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

    LinkedHashMap<Integer,Integer> link = new LinkedHashMap<Integer,Integer>();

    int lost = 0;

    /**
     * 构造方法的同时绑定View接口
     *
     * @param baseView 传入的BaseView的子类
     */
    public ResultPresenter(IResult baseView) {
        super(baseView);
    }

    /**
     * 载入随机数据
     *
     * @param isShow 是否展示加载动画
     */
    public void random(boolean isShow){
        baseView.showLoading(isShow);
        Random random = new Random();
        datas.clear();
        showData.clear();
        for (int i = 0; i < 320;i++){
            int r = random.nextInt(32)+1;
            datas.add(r);
        }
        showData.addAll(datas);
        baseView.SuccessHideLoading(isShow);
        baseView.showRVData();
        baseView.showInstructions(showData);
    }

    /**
     * 执行算法
     *
     * @param op 来自哪个算法的路径
     * @param isShow 是否展示加载动画
     */
    public void option(int op,boolean isShow){
        lost = 0;
        if (datas.isEmpty()) {
            baseView.showReInit();

        }else {
            list.clear();
            baseView.showLoading(isShow);
            while (datas.size() > 0){
                int waitData = datas.get(0);
                datas.remove(0);
                PageData pre;
                if (list.size() == 0) {
                    PageData pageData = new PageData(waitData,waitData);
                    lost++;
                    list.add(pageData);
                    if (op == GlobalConstant.LRU) makeRecently(waitData,1);
                }else if (list.size() == 1 || lost == 1) {
                    pre = list.get(list.size() - 1);
                    PageData pageData = new PageData(waitData,pre.getData1());
                    if (!check(pre,waitData)){
                        pageData = new PageData(waitData, pre.getData1(), waitData);
                        lost++;
                    }
                    list.add(pageData);
                    if (op == GlobalConstant.LRU) makeRecently(waitData,2);
                }else if (list.size() == 2 || lost == 2) {
                    pre = list.get(list.size() - 1);
                    PageData pageData = new PageData(waitData, pre.getData1(), pre.getData2());
                    if (!check(pre,waitData)){
                        pageData = new PageData(waitData, pre.getData1(), pre.getData2(),waitData);
                        lost++;
                    }
                    list.add(pageData);
                    if (op == GlobalConstant.LRU) makeRecently(waitData,3);
                } else if (list.size() == 3 || lost == 3) {
                    pre = list.get(list.size() - 1);
                    PageData pageData = new PageData(waitData, pre.getData1(), pre.getData2(),pre.getData3());
                    if (!check(pre,waitData)){
                        pageData = new PageData(waitData, pre.getData1(), pre.getData2(),pre.getData3(), waitData);
                        lost++;
                    }
                    list.add(pageData);
                    if (op == GlobalConstant.LRU) makeRecently(waitData,4);
                }else {
                    switch (op){
                        case GlobalConstant.LRU:
                            LRU(waitData);
                            break;
                        case GlobalConstant.OPT:
                            OPT(waitData);
                            break;
                        case GlobalConstant.FIFO:
                            FIFO(waitData);
                            break;
                        default:
                            break;
                    }
                }

            }
            baseView.SuccessHideLoading(isShow);
            baseView.showMissingPageRate(lost);
            baseView.showRVPages();
            baseView.showPages(list);
        }
    }

    /**
     * 内存满时，OPT的替换策略
     *
     * @param waitData 最新等待执行的页号
     */
    private void OPT(int waitData){
        int listSize = list.size();
        PageData pre = list.get(listSize - 1);
        PageData pageData = new PageData(waitData, pre.getData1(), pre.getData2(), pre.getData3(), pre.getData4());
        if (check(pre,waitData)){
            list.add(pageData);
        }else {
            map.put(pre.getData1(),Collections.frequency(datas, pre.getData1()));
            map.put(pre.getData2(),Collections.frequency(datas, pre.getData2()));
            map.put(pre.getData3(),Collections.frequency(datas, pre.getData3()));
            map.put(pre.getData4(),Collections.frequency(datas, pre.getData4()));
            int min = map.get(pre.getData1());
            min = Math.min(min,map.get(pre.getData2()));
            min = Math.min(min,map.get(pre.getData3()));
            min = Math.min(min,map.get(pre.getData4()));
            changeOPT(pageData,min,waitData);
        }
    }

    /**
     * 内存满时，FIFO的替换策略
     *
     * @param waitData 最新等待执行的页号
     */
    private void FIFO(int waitData){
        int listSize = list.size();
        PageData pre = list.get(listSize-1);
        PageData pageData = new PageData(waitData, pre.getData1(), pre.getData2(),pre.getData3(),pre.getData4());
        if (check(pre,waitData)){
            list.add(pageData);
        }else {
            int op = (lost+1)%4;
            changFIFO(pageData,op,waitData);
        }

    }

    /**
     * 内存满时，LRU的替换策略
     *
     * @param waitData 最新等待执行的页号
     */
    private void LRU(int waitData){
        int listSize = list.size();
        PageData pre = list.get(listSize-1);
        PageData pageData = new PageData(waitData, pre.getData1(), pre.getData2(),pre.getData3(),pre.getData4());
        if (check(pre,waitData)){
            list.add(pageData);
        }else {
            int oldestKey = link.keySet().iterator().next();
            int op = link.get(oldestKey);
            changLRU(pageData,op,waitData);
        }
    }


    /**
     * 检查是否不缺页
     * 不缺页返回 true
     * 缺页返回 false
     *
     * @param data 最小的子item
     * @param num 请求的页面号
     * @return 返回是否不缺页
     */
    private boolean check(PageData data, int num){
        if (data.getData1() == num) return true;
        else if (data.getData2() == num) return true;
        else if (data.getData3() == num) return true;
        else return data.getData4() == num;
    }

    /**
     * @param data 载入最新子item
     * @param min 后续data数据列最小的值
     * @param wait 需更替的最新数据
     */
    private void changeOPT(PageData data, int min, int wait){
        if (map.get(data.getData1()) == min) data.setData1(wait);
        else if (map.get(data.getData2()) == min) data.setData2(wait);
        else if (map.get(data.getData3()) == min) data.setData3(wait);
        else data.setData4(wait);
        list.add(data);
        lost++;
    }

    /**
     * FIFO缺页时更改页面
     *
     * @param data 载入最新子item
     * @param op 替换节点
     * @param wait 需更替的最新数据
     */
    private void changFIFO(PageData data,int op,int wait){
        switch (op){
            case 1:
                data.setData1(wait);
                break;
            case 2:
                data.setData2(wait);
                break;
            case 3:
                data.setData3(wait);
                break;
            case 0:
                data.setData4(wait);
                break;
            default:
                break;
        }
        list.add(data);
        lost++;
    }

    /**
     * LRU缺页时更改页面
     *
     * @param data 载入最新子item
     * @param op 替换节点
     * @param wait 需更替的最新数据
     */
    private void changLRU(PageData data,int op,int wait){
        switch (op){
            case 1:
                data.setData1(wait);
                break;
            case 2:
                data.setData2(wait);
                break;
            case 3:
                data.setData3(wait);
                break;
            case 4:
                data.setData4(wait);
                break;
            default:
                break;
        }
        list.add(data);
        makeRecently(wait,op);
        lost++;
    }

    /**
     * 利用linkedHashMap实现核心LRU算法
     *
     * @param key 传入 waitData
     * @param op 传入载入位置
     */
    private void makeRecently(int key,int op) {
        if (link.get(key) == null && link.size() >= 4 ){
            int oldestKey = link.keySet().iterator().next();
            link.remove(oldestKey);
            link.put(key,op);
        }else if (link.get(key) == null && link.size() < 4){
            link.put(key,op);
        } else if (link.get(key) != null){
            int val = link.get(key);
            // 删除 key，重新插入到队尾
            link.remove(key);
            link.put(key, val);
        }

    }

}
