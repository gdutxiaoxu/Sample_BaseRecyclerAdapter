package com.xujun.administrator.sample_baserecycleradapter.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xujun.administrator.sample_baserecycleradapter.R;
import com.xujun.administrator.sample_baserecycleradapter.adapters.SinglePersonAdapter;
import com.xujun.administrator.sample_baserecycleradapter.base.HeaderAndFooterWrapper;
import com.xujun.administrator.sample_baserecycleradapter.beans.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class HeaderRecyclerActivity extends AppCompatActivity {

    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private RecyclerView mRecyclerView;
    List<ChatMessage> mDatas = new ArrayList<>();
    private SinglePersonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_recycler);

        mDatas.addAll(ChatMessage.MOCK_DATAS);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SinglePersonAdapter(this, mDatas, R.layout.main_chat_from_msg);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);

        TextView textView = new TextView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setText("head1");
        textView.setBackgroundColor(Color.GRAY);
        mHeaderAndFooterWrapper.addHeaderView(textView);

        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);

    }

    private void initHeaderAndFooter() {

    }
}
