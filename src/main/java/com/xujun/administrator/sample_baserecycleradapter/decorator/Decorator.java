package com.xujun.administrator.sample_baserecycleradapter.decorator;

/**
 * @ explain:
 * @ author：xujun on 2016/7/10 23:21
 * @ email：gdutxiaoxu@163.com
 */
public abstract class Decorator extends Coffee{

    protected Coffee mCoffee;

    /**
     * 通过组合的方式把Coffee对象传递进来
     * @param coffee
     */
    public Decorator(Coffee coffee){
        mCoffee=coffee;
    }
}
