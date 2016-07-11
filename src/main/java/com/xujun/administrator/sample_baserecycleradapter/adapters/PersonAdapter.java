package com.xujun.administrator.sample_baserecycleradapter.adapters;

import android.content.Context;
import android.view.View;

import com.xujun.administrator.sample_baserecycleradapter.R;
import com.xujun.administrator.sample_baserecycleradapter.base.MultiItemAdapter;
import com.xujun.administrator.sample_baserecycleradapter.base.MultiItemSupport;
import com.xujun.administrator.sample_baserecycleradapter.base.ViewHolder;
import com.xujun.administrator.sample_baserecycleradapter.beans.ChatMessage;

/**
 * @ explain:
 * @ author：xujun on 2016/7/9 14:44
 * @ email：gdutxiaoxu@163.com
 */
public class PersonAdapter extends MultiItemAdapter<ChatMessage> {

    private final int type_coming = 0;
    private final int type_sending = 1;

    public PersonAdapter(Context context) {
        super(context);
    }

    @Override
    protected MultiItemSupport<ChatMessage> setMultiItemSupport() {
        return new MultiItemSupport<ChatMessage>() {
            @Override
            public int getLayoutId(int viewType) {
                switch (viewType) {
                    case type_coming:
                        return R.layout.main_chat_from_msg;
                    case type_sending:
                        return R.layout.main_chat_send_msg;
                }
                return R.layout.main_chat_from_msg;
            }

            @Override
            public int getItemType(int position, ChatMessage chatMessage) {
                if (chatMessage.isComMeg()) {
                    return type_coming;
                } else {
                    return type_sending;
                }
            }
        };
    }

    @Override
    protected void convert(ViewHolder holder, ChatMessage t, int position, View convertView) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == type_coming) {
            holder.setText(R.id.chat_from_content, t.getContent());
            holder.setText(R.id.chat_from_name, t.getName());
            holder.setImageResource(R.id.chat_from_icon, t.getIcon());
        } else if (itemViewType == type_sending) {
            holder.setText(R.id.chat_send_content, t.getContent());
            holder.setText(R.id.chat_send_name, t.getName());
            holder.setImageResource(R.id.chat_send_icon, t.getIcon());
        }

    }


}
