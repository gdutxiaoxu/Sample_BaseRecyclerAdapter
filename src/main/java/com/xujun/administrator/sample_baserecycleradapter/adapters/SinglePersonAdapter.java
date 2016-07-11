package com.xujun.administrator.sample_baserecycleradapter.adapters;

import android.content.Context;
import android.view.View;

import com.xujun.administrator.sample_baserecycleradapter.base.BaseRecyclerAdapter;
import com.xujun.administrator.sample_baserecycleradapter.base.ViewHolder;
import com.xujun.administrator.sample_baserecycleradapter.beans.ChatMessage;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/7/9 15:26
 * @ email：gdutxiaoxu@163.com
 */
public class SinglePersonAdapter extends BaseRecyclerAdapter<ChatMessage> {

    public SinglePersonAdapter(Context context, List<ChatMessage> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void convert(ViewHolder holder, ChatMessage chatMessage, int position, View
            convertView) {

    }
}
