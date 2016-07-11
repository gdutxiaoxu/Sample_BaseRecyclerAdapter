package com.xujun.administrator.sample_baserecycleradapter.decorator;

/**
 * @ explain:
 * @ author：xujun on 2016/7/10 23:22
 * @ email：gdutxiaoxu@163.com
 */
public class SugarDecorator extends Decorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        return mCoffee.getPrice()+2;
    }

    @Override
    public String getName() {
        return "addSugar";

    }
}
