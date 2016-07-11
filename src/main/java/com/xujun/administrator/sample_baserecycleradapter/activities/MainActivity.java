package com.xujun.administrator.sample_baserecycleradapter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xujun.administrator.sample_baserecycleradapter.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view){
        switch (view.getId()){
            case  R.id.recycler_view_activity:
                jumpTo(SingleItemActivity.class);

            break;
            case  R.id.multirecycler_view_acyivity:
                jumpTo(MultiItemActivity.class);
                break;
            case  R.id.header_recycler_view_activity:
                jumpTo(HeaderRecyclerActivity.class);
                break;
            case  R.id.coffce_test:
                jumpTo(CoffeeActivity.class);
                break;
        }
    }

    public void jumpTo(Class<? extends Activity> clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
