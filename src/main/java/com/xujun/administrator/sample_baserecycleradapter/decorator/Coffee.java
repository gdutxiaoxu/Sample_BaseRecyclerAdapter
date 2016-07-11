package com.xujun.administrator.sample_baserecycleradapter.decorator;

/**
 * @ explain:这里Coffee相当于我们的Component，
 * 是要装饰的类
 *
 * @ author：xujun on 2016/7/10 23:16
 * @ email：gdutxiaoxu@163.com
 */
public abstract class Coffee {

    /**
     *
     * @return 返回价格
     */
    public abstract int getPrice();

    /**
     * 返回名字
     * @return
     */
    public abstract String getName();
}
