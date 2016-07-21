package com.xujun.administrator.sample_baserecycleradapter.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 *
 * @author xujun
 * @time 2016/7/7 17:29.
 */
public class HeaderWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private boolean mIsShowHeader = true;

    public static final String TAG = "tag";

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public HeaderWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get
                    (viewType));
            return holder;

        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsShowHeader && isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        }
       if(mIsShowHeader){
           position=position-getHeadersCount();
       }
        return mInnerAdapter.getItemViewType(position);
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mIsShowHeader && isHeaderViewPos(position)) {
            return;
        }

        if(mIsShowHeader){
           position=position-getHeadersCount();
        }
        mInnerAdapter.onBindViewHolder(holder,position);


    }

    @Override
    public int getItemCount() {
        if (mIsShowHeader) {
            return getHeadersCount() + getRealItemCount();
        }
        return getRealItemCount();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils
                .SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager
                    .SpanSizeLookup oldLookup, int position) {
                int viewType = getItemViewType(position);
                if (mHeaderViews.get(viewType) != null) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null)
                    return oldLookup.getSpanSize(position);
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position)) {
            WrapperUtils.setFullSpan(holder);
        }
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public void showHeader(boolean isShowHeader) {
        mIsShowHeader = isShowHeader;
//        notifyDataSetChanged();
        if(isShowHeader){
            notifyItemRangeInserted(0,getHeadersCount());
        }else{
            notifyItemRangeRemoved(0,getHeadersCount());
        }
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }


}
