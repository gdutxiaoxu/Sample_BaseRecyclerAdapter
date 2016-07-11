package com.xujun.administrator.sample_baserecycleradapter.decorator;

/**
 * @ explain:
 * @ author：xujun on 2016/7/10 23:26
 * @ email：gdutxiaoxu@163.com
 */
public class MilkDecorator extends Decorator {

    /**
     * 通过组合的方式把Coffee对象传递进来
     *
     * @param coffee
     */
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    /**
     *
     * @return
     */
    @Override
    public int getPrice() {
        return mCoffee.getPrice()+10;
    }

    @Override
    public String getName() {
        return "addMilk";
    }
}
