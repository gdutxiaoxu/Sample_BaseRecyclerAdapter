package com.xujun.administrator.sample_baserecycleradapter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xujun.administrator.sample_baserecycleradapter.R;
import com.xujun.administrator.sample_baserecycleradapter.decorator.Coffee;
import com.xujun.administrator.sample_baserecycleradapter.decorator.MilkDecorator;
import com.xujun.administrator.sample_baserecycleradapter.decorator.MilkFoamDecorator;
import com.xujun.administrator.sample_baserecycleradapter.decorator.MochaDecorator;
import com.xujun.administrator.sample_baserecycleradapter.decorator.SimpleCoffee;
import com.xujun.administrator.sample_baserecycleradapter.decorator.SugarDecorator;

public class CoffeeActivity extends AppCompatActivity {

    private TextView mTvSimpleCoffee;
    private TextView mTvCoffee;
    private Coffee mCoffee;
    private SimpleCoffee mSimpleCoffee;
    private static final String TAG = "xujun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        mTvSimpleCoffee = (TextView) findViewById(R.id.simple_coffee);
        mTvCoffee = (TextView) findViewById(R.id.coffee);

        mCoffee = new SimpleCoffee();
        mSimpleCoffee = new SimpleCoffee();
        mTvSimpleCoffee.setText(mSimpleCoffee.getPrice()+"");
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_addMilk:
                mCoffee = new MilkDecorator(mCoffee);
                mTvCoffee.setText("价格"+mCoffee.getPrice()+"");
                break;
            case R.id.btn_addMIlkFoam:
                mCoffee = new MilkFoamDecorator(mCoffee);
                mTvCoffee.setText("价格"+mCoffee.getPrice()+"");
                break;
            case R.id.btn_addSugar:
                mCoffee = new SugarDecorator(mCoffee);
                int price = mCoffee.getPrice();

                Log.i(TAG, "CoffeeActivity:onButtonClick:49: price=" + price);
                mTvCoffee.setText("价格"+mCoffee.getPrice()+"");                break;
            case R.id.btn_addMocha:
                mCoffee = new MochaDecorator(mCoffee);
                mTvCoffee.setText("价格"+mCoffee.getPrice()+"");
                int price1 = mCoffee.getPrice();
                System.out.println("price1="+price1);
                break;


        }

    }
}
