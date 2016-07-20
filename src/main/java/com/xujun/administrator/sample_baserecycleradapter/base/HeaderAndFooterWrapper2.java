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
public class HeaderAndFooterWrapper2<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private boolean mIsShowHeader = true;
    private boolean mIsShowFooter = true;
     public static final String TAG="tag";

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper2(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mIsShowHeader && mHeaderViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get
                    (viewType));
            return holder;

        } else if (mIsShowFooter && mFootViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFootViews.get
                    (viewType));
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsShowHeader && isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (mIsShowFooter && isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }

        return mInnerAdapter.getItemViewType(position );
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mIsShowHeader&& isHeaderViewPos(position)) {
            return;
        }
        if (mIsShowFooter&&isFooterViewPos(position)) {
            return;
        }
        if(mIsShowHeader){
            mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
        }else{
            mInnerAdapter.onBindViewHolder(holder,position);
        }

    }

    @Override
    public int getItemCount() {
        if (mIsShowFooter && mIsShowHeader) {
            return getHeadersCount() + getFootersCount() + getRealItemCount();
        } else if (mIsShowFooter && !mIsShowHeader) {
            return getFootersCount() + getRealItemCount();
        } else if (!mIsShowFooter && mIsShowHeader) {
            return getHeadersCount() + getRealItemCount();
        } else {
            return getRealItemCount();
        }

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
                } else if (mFootViews.get(viewType) != null) {
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
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            WrapperUtils.setFullSpan(holder);
        }
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        if(mIsShowHeader){
            return position >= getHeadersCount() + getRealItemCount();
        }else{
            return position>=getRealItemCount();
        }

    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    public void showHeader(boolean isShowHeader) {
        mIsShowHeader = isShowHeader;
        notifyDataSetChanged();
       /* if(isShowHeader){
            notifyItemRangeInserted(0,getHeadersCount());
        }else{
            notifyItemRangeRemoved(0,getHeadersCount());
        }
*/
    }

    public void showFooter(boolean isShowFooter) {
        mIsShowFooter = isShowFooter;
        notifyDataSetChanged();
      /*  Log.i(TAG, "HeaderAndFooterWrapper2.class:showFooter(): 146:" +getItemCount());
        if(isShowFooter){
            notifyItemRangeInserted(getItemCount()-1,getFootersCount());
        }else{
            notifyItemRangeRemoved(getItemCount()-1,getFootersCount());
        }*/

    }


}
