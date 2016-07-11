package com.xujun.administrator.sample_baserecycleradapter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xujun.administrator.sample_baserecycleradapter.R;
import com.xujun.administrator.sample_baserecycleradapter.adapters.PersonAdapter;
import com.xujun.administrator.sample_baserecycleradapter.beans.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class MultiItemActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PersonAdapter mAdapter;
    private List<ChatMessage> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new PersonAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        mDatas.addAll(ChatMessage.MOCK_DATAS);
        mAdapter.setDatas(mDatas);

    }
}
