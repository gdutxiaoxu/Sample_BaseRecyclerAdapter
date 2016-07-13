package com.xujun.administrator.sample_baserecycleradapter.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @ explain:
 * @ author：xujun on 2016/5/26 12:13
 * @ email：gdutxiaoxu@163.com
 */
public abstract class BaseMVPActivity<E extends BasePresenter> extends
        AppCompatActivity {

    private static final String DEFAULT_NAME = "DEFAULT_NAME";
    private static final String DEFAULT_PARCEABLE_NAME = "DEFAULT_PARCEABLE_NAME";
    private static final String DEFAULT_PARCEABLE_LIST_NAME = "DEFAULT_PARCEABLE_LIST_NAME";

    protected Context mContext;

    protected ProgressDialog dialog;

    protected E mPresenter;
    private Parcelable mParcelableExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
        // base setup
        Intent intent = getIntent();
        if (intent != null) {
            initIntent(intent);
        }

        mParcelableExtra = intent.getParcelableExtra(DEFAULT_PARCEABLE_NAME);
        if (mParcelableExtra != null) {
            getParcexbleExtras(mParcelableExtra);
        }

        mContext = this;
        dialog = new ProgressDialog(this);

        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            mPresenter = setPresenter();
            if (mPresenter != null) {
                mPresenter.start();
            }

            initView();
            initListener();
            initData();
        } else {
            Log.w("BaseActivity", "onCreate: Error contentView");
        }

    }

    protected void initIntent(Intent intent) {
    }

    protected void initData() {

    }

    protected void getParcexbleExtras(Parcelable parcelable) {

    }

    protected abstract E setPresenter();

    protected abstract void initView();

    protected void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.cancel();
        }

        if (mPresenter != null) {
            mPresenter.stop();
            mPresenter=null;
        }
    }

    /**
     * 在setContentView前初始化Window设置
     */
    protected void initWindows() {
    }

    /**
     * 获取其他组件传来的数据
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 获取Layout的id
     */
    protected abstract int getContentViewLayoutID();


    /**
     * 启动Activity
     */
    public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void readyGo(Class<?> clazz, Parcelable parcelable) {
        Intent intent = new Intent(this, clazz);
        if (null != parcelable) {
            intent = intent.putExtra(DEFAULT_PARCEABLE_NAME, parcelable);
        }
        startActivity(intent);
    }

    public void readyGo(Class<?> clazz, String name, Parcelable parcelable) {
        Intent intent = new Intent(this, clazz);
        if (null != parcelable) {
            intent = intent.putExtra(name, parcelable);
        }
        startActivity(intent);
    }

    public void readyGo(Class<? extends Activity> clazz, ArrayList<? extends Parcelable> parcelableList) {
        Intent intent = new Intent(this, clazz);
        if (null != parcelableList) {
            intent = intent.putExtra(DEFAULT_PARCEABLE_LIST_NAME, parcelableList);
        }
        startActivity(intent);
    }

    public void readyGo(Class<? extends Activity> clazz, String name, ArrayList<? extends Parcelable> parcelableList) {
        Intent intent = new Intent(this, clazz);
        if (null != parcelableList) {
            intent = intent.putExtra(name, parcelableList);
        }
        startActivity(intent);
    }


    /**
     * 启动Activity并传递数据
     */
    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 启动Activity然后结束本Activity
     */
    public void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * 启动Activity并传递数据,然后结束本Activity
     */
    public void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * 显示菊花
     */
    public void showProressDialog() {
        if (dialog != null) {
            dialog.show();
        }
    }

    /**
     * 显示带信息的菊花
     */
    public void showProressDialog(String msg) {
        if (dialog != null) {
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    /**
     * 隐藏菊花
     */
    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.hide();
        }
    }

    /**
     * 根据字符串弹出Toast
     */
    protected void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据资源id弹出Toast
     */
    protected void showToast(int msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
