package com.xujun.administrator.sample_baserecycleradapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016-7-1 11:38
 * @ email：gdutxiaoxu@163.com
 */
public abstract class MultiItemAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private  List<T> mDatas;
    Context mContext;

    private MultiItemSupport<T> mTMultiItemAdapter;

    public MultiItemAdapter(Context context){
        this(context,  null);
    }

    public MultiItemAdapter(Context context, List<T> datas){
        mContext=context;
        if(datas==null){
            datas=new ArrayList<>();
        }
        this.mDatas=datas;
        mTMultiItemAdapter=setMultiItemSupport();
    }

    protected abstract MultiItemSupport<T> setMultiItemSupport();

    @Override
    public int getItemViewType(int position) {
        return mTMultiItemAdapter.getItemType(position,mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mTMultiItemAdapter.getLayoutId(viewType);
        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        return new ViewHolder(mContext,view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            convert(holder,mDatas.get(position),position,holder.getConvertView());
    }

    protected abstract void convert(ViewHolder holder, T t, int position, View convertView);

    @Override
    public int getItemCount() {
        return isEmpty()? 0:mDatas.size();
    }

    public boolean isEmpty(){
        if(mDatas==null || mDatas.size()==0){
            return true;
        }
        return false;
    }


    public void setDatas( List<T> datas){
        this.mDatas=datas;
    }
}
