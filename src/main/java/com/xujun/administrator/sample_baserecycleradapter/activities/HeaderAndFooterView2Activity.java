package com.xujun.administrator.sample_baserecycleradapter.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xujun.administrator.sample_baserecycleradapter.R;
import com.xujun.administrator.sample_baserecycleradapter.adapters.SinglePersonAdapter;
import com.xujun.administrator.sample_baserecycleradapter.base.HeaderAndFooterWrapper3;
import com.xujun.administrator.sample_baserecycleradapter.beans.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class HeaderAndFooterView2Activity extends AppCompatActivity {


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
    private HeaderAndFooterWrapper3 mHeaderAndFooterWrapper3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_and_footer_view2);

        mDatas.addAll(ChatMessage.MOCK_DATAS);
        Log.i(TAG, "HeaderAndFooterView.class:onCreate(): 42:ChatMessage.MOCK_DATAS.size()=" +ChatMessage.MOCK_DATAS.size());
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SinglePersonAdapter(this, mDatas, R.layout.main_chat_from_msg);
        mHeaderAndFooterWrapper3 = new HeaderAndFooterWrapper3(mAdapter);

        TextView headerView = new TextView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerView.setLayoutParams(layoutParams);
        headerView.setText("我是HeaderView");
        headerView.setBackgroundColor(Color.GRAY);



        mHeaderAndFooterWrapper3.addHeaderView(headerView);

        TextView foot = new TextView(this);

        foot.setLayoutParams(layoutParams);
        foot.setText("foot");
        foot.setBackgroundColor(Color.GRAY);
        foot.setPadding(10,10,10,10);



        mHeaderAndFooterWrapper3.addFootView(foot);

        View view = View.inflate(this, R.layout.item_load_more, null);
        mHeaderAndFooterWrapper3.addFootView(view);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper3);
       /* mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHeaderAndFooterWrapper2.showHeader(false);
            }
        },4000);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHeaderAndFooterWrapper2.showFooter(false);
            }
        },8000);*/
    }
}
