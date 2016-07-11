package com.xujun.administrator.sample_baserecycleradapter.base;

/**
 * @ explain:
 * @ author：xujun on 2016-7-1 11:56
 * @ email：gdutxiaoxu@163.com
 */
public interface MultiItemSupport<T> {
    int getLayoutId(int viewType);
    int getItemType(int position, T t);

}
