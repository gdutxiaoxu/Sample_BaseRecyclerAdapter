package com.xujun.administrator.sample_baserecycleradapter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xujun.administrator.sample_baserecycleradapter.R;
import com.xujun.administrator.sample_baserecycleradapter.adapters.SinglePersonAdapter;
import com.xujun.administrator.sample_baserecycleradapter.base.LoadMoreWrapper;
import com.xujun.administrator.sample_baserecycleradapter.beans.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreWraperActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    List<ChatMessage> mDatas = new ArrayList<>();
    private SinglePersonAdapter mAdapter;
    private LoadMoreWrapper<String> mLoadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer_wraper);

        mDatas.addAll(ChatMessage.MOCK_DATAS);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SinglePersonAdapter(this, mDatas, R.layout.main_chat_from_msg);
        mLoadMoreWrapper = new LoadMoreWrapper<>(mAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.item_load_more);

        mRecyclerView.setAdapter(mLoadMoreWrapper);
      /*  mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLoadMoreWrapper.showLoadMore(false);
                    }
                },2000);
            }
        });*/





    }


}
