package com.xujun.administrator.sample_baserecycleradapter.base;

import android.content.Context;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/7/9 12:44
 * @ email：gdutxiaoxu@163.com
 */
public abstract class BaseRecyclerAdapter<T> extends MultiItemAdapter<T> {
    protected int mLayoutId = -1;

    public BaseRecyclerAdapter(Context context, List<T> datas, final int layoutId) {
        super(context, datas);
        mLayoutId = layoutId;
    }

    @Override
    protected MultiItemSupport<T> setMultiItemSupport() {
        return new SingleItem<T>();
    }

    private class SingleItem<T> implements MultiItemSupport<T> {

        @Override
        public int getLayoutId(int viewType) {
            return mLayoutId;
        }

        @Override
        public int getItemType(int position, T t) {
            return 0;
        }
    }
}
