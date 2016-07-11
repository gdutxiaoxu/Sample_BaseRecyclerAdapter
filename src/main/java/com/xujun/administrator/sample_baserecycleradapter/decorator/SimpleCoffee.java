package com.xujun.administrator.sample_baserecycleradapter.decorator;

/**
 * @ explain:
 * @ author：xujun on 2016/7/10 23:20
 * @ email：gdutxiaoxu@163.com
 */
public class SimpleCoffee extends Coffee {

    private int price=50;
    private String name=getClass().getName();

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }
}
