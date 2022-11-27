package com.dyj.requestpagingscheduleemulator.module.eatwhat.presenter;

import static com.tamsiree.rxkit.RxImageTool.changeColorAlpha;

import com.dyj.requestpagingscheduleemulator.R;
import com.dyj.requestpagingscheduleemulator.base.BasePresenter;
import com.dyj.requestpagingscheduleemulator.bean.DishName;
import com.dyj.requestpagingscheduleemulator.module.eatwhat.view.IEatView;
import com.dyj.requestpagingscheduleemulator.util.MyUtil;
import com.tamsiree.rxui.view.RxRotateBarBasic;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：Dyj
 * @date ：Created in 2022/11/23 10:54
 * @description：eat的P层
 * @modified By：
 * @version: 1.0
 */
public class EatPresenter extends BasePresenter<IEatView> {

    HashMap<Integer,String> nameMap;
    RxRotateBarBasic bar1,bar2,bar3,bar4;
    ArrayList<RxRotateBarBasic> barList;
    List<DishName> dishNameList;

    /**
     * 构造方法的同时绑定View接口
     *
     * @param baseView 传入的BaseView的子类
     */
    public EatPresenter(IEatView baseView) {
        super(baseView);
        initData();
    }

    public void getRandomData(){

        //克隆一份数据
        List<DishName> dishNameListClone = new ArrayList<>();
        for (DishName dishName : dishNameList){
            dishNameListClone.add(dishName.clone());
        }

        //对数据进行第一次抽取，抽四个菜
        Random random = new Random();
        int nameIndex = 0;
        for (int i = 0; i < 4; i++){
            int index = random.nextInt(dishNameListClone.size());
            nameMap.put(i,dishNameListClone.get(index).getName());
            dishNameListClone.remove(index);
        }

        //对第一次抽取的四个菜进行随机赋值
        for (RxRotateBarBasic bar:
                barList) {
            bar.setRate(random.nextInt(10));
            bar.setTitle(Objects.requireNonNull(nameMap.get(nameIndex)));
            nameIndex++;
        }

        baseView.setRotateView(barList);
    }

    private void barAddAll() {
        if (barList == null){
            barList = new ArrayList<>();
        }
        barList.add(bar1);
        barList.add(bar2);
        barList.add(bar3);
        barList.add(bar4);
    }

    public void setInitView() {
        bar1 = new RxRotateBarBasic(2,Objects.requireNonNull(nameMap.get(0)));
        bar2 = new RxRotateBarBasic(3,Objects.requireNonNull(nameMap.get(1)));
        bar3 = new RxRotateBarBasic(5,Objects.requireNonNull(nameMap.get(2)));
        bar4 = new RxRotateBarBasic(8,Objects.requireNonNull(nameMap.get(3)));

        bar1.setRatedColor(MyUtil.AppGetColor(R.color.google_red));
        bar1.setOutlineColor(MyUtil.AppGetColor(R.color.google_red));
        bar1.setRatingBarColor(changeColorAlpha(MyUtil.AppGetColor(R.color.google_red), 130));

        bar2.setRatedColor(MyUtil.AppGetColor(R.color.google_yellow));
        bar2.setOutlineColor(MyUtil.AppGetColor(R.color.google_yellow));
        bar2.setRatingBarColor(changeColorAlpha(MyUtil.AppGetColor(R.color.google_yellow), 130));

        bar3.setRatedColor(MyUtil.AppGetColor(R.color.darkslateblue));
        bar3.setOutlineColor(MyUtil.AppGetColor(R.color.darkslateblue));
        bar3.setRatingBarColor(changeColorAlpha(MyUtil.AppGetColor(R.color.darkslateblue), 130));

        bar4.setRatedColor(MyUtil.AppGetColor(R.color.google_green));
        bar4.setOutlineColor(MyUtil.AppGetColor(R.color.google_green));
        bar4.setRatingBarColor(changeColorAlpha(MyUtil.AppGetColor(R.color.google_green), 130));
        barAddAll();
        baseView.setRotateView(barList);
    }

    private void initData(){
        dishNameList = LitePal.findAll(DishName.class);
        nameMap = new HashMap<>();
        nameMap.put(0,dishNameList.get(0).getName());
        nameMap.put(1,dishNameList.get(1).getName());
        nameMap.put(2,dishNameList.get(2).getName());
        nameMap.put(3,dishNameList.get(3).getName());
    }
}
