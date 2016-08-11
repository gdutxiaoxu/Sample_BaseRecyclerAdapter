package com.xujun.administrator.sample_baserecycleradapter.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 *
 * @author xujun
 * @time 2016/7/7 17:29.
 */
public class FooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private boolean mIsShowFooter = true;

    public static final String TAG = "tag";

    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public FooterWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFooterViews.get
                    (viewType));
            return holder;

        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsShowFooter && isFooterPos(position)) {
            return mFooterViews.keyAt(position - getRealItemCount());
        }

        return mInnerAdapter.getItemViewType(position);
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        /***
         * 如果是footerView的话直接返回
         */
        if (mIsShowFooter && isFooterPos(position)) {
            return;
        }

        mInnerAdapter.onBindViewHolder(holder, position);


    }

    @Override
    public int getItemCount() {
        if (mIsShowFooter) {
            return getFootViewCounts() + getRealItemCount();
        }
        return getRealItemCount();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager
                    .getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (mFooterViews.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null)
                        return spanSizeLookup.getSpanSize(position);
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isFooterPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {

                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) lp;

                p.setFullSpan(true);
            }
        }
    }

    private boolean isFooterPos(int position) {

        return position >= getRealItemCount();
    }

    public int getFootViewCounts() {
        return mFooterViews.size();
    }

    public void addFootView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public void showFooter(boolean isShowFooter) {
//        mIsShowFooter = isShowFooter;
//        //        notifyDataSetChanged();
        if (isShowFooter) {
            notifyItemRangeInserted(getRealItemCount(), getFootViewCounts());
        } else {
            notifyItemRangeRemoved(getRealItemCount(), getFootViewCounts());
        }
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }
}
