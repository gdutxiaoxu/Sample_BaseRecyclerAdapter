package com.xujun.administrator.sample_baserecycleradapter.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xujun.administrator.sample_baserecycleradapter.R;
import com.xujun.administrator.sample_baserecycleradapter.adapters.SinglePersonAdapter;
import com.xujun.administrator.sample_baserecycleradapter.base.FooterWrapper;
import com.xujun.administrator.sample_baserecycleradapter.base.HeaderWrapper;
import com.xujun.administrator.sample_baserecycleradapter.beans.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class HeaderAndFooterView extends AppCompatActivity {
    private HeaderWrapper mHeaderWrapper;
    private RecyclerView mRecyclerView;
    List<ChatMessage> mDatas = new ArrayList<>();
    private SinglePersonAdapter mAdapter;
    static Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

     public static final String TAG="tag";
    private FooterWrapper<Object> mFooterWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_recycler);

        mDatas.addAll(ChatMessage.MOCK_DATAS);
        Log.i(TAG, "HeaderAndFooterView.class:onCreate(): 42:ChatMessage.MOCK_DATAS.size()=" +ChatMessage.MOCK_DATAS.size());
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SinglePersonAdapter(this, mDatas, R.layout.main_chat_from_msg);
        mHeaderWrapper = new HeaderWrapper(mAdapter);

        TextView textView = new TextView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setText("我是HeaderView");
        textView.setBackgroundColor(Color.GRAY);
        mHeaderWrapper.addHeaderView(textView);

        TextView foot = new TextView(this);

        foot.setLayoutParams(layoutParams);
        foot.setText("foot");
        foot.setBackgroundColor(Color.GRAY);
        foot.setPadding(10,10,10,10);
        mFooterWrapper = new FooterWrapper<>(mHeaderWrapper);
        mFooterWrapper.addFootView(foot);

        mRecyclerView.setAdapter(mFooterWrapper);




    }
}
