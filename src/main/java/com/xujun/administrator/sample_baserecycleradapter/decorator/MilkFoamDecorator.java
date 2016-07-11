package com.xujun.administrator.sample_baserecycleradapter.decorator;

/**
 * @ explain:
 * @ author：xujun on 2016/7/10 23:28
 * @ email：gdutxiaoxu@163.com
 */
public class MilkFoamDecorator extends Decorator {

    /**
     * 通过组合的方式把Coffee对象传递进来
     *
     * @param coffee
     */
    public MilkFoamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        return mCoffee.getPrice()+15;
    }

    @Override
    public String getName() {
        return "addMilkFoam";
    }
}
