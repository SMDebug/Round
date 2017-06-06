package com.enterprise.limited.round.UI.Activity;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.enterprise.limited.round.R;
import com.enterprise.limited.round.UI.Activity.Fragment.mainpage.ClassFragment;
import com.enterprise.limited.round.UI.Activity.Fragment.mainpage.EarnFragment;
import com.enterprise.limited.round.UI.Activity.Fragment.mainpage.MainFragment;
import com.enterprise.limited.round.UI.Activity.Fragment.mainpage.ShopFragment;
import com.enterprise.limited.round.UI.Adapter.FragmentAdapter;
import com.enterprise.limited.round.UI.AppManager;
import com.enterprise.limited.round.Utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPager       viewPager;
    private FragmentAdapter mFragmentAdapter;
    private int currentP = 0;
    private TextView mTv_main;
    private TextView mTv_earn;
    private TextView mTv_class;
    private TextView mTv_shop;
    private boolean isBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initData();

        initView();
    }


    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/


    private MainFragment mainFragment;

    private void initData() {
        mainFragment = new MainFragment();
        mFragmentList.add(mainFragment);
        mFragmentList.add(new EarnFragment());
        mFragmentList.add(new ClassFragment());
        mFragmentList.add(new ShopFragment());
    }

    private void initView() {
        mTv_main = (TextView) findViewById(R.id.main_tv_main);
        mTv_earn = (TextView) findViewById(R.id.main_tv_earn);
        mTv_class = (TextView) findViewById(R.id.main_tv_class);
        mTv_shop = (TextView) findViewById(R.id.main_tv_shop);

        mTv_main.setOnClickListener(clickListener);
        mTv_earn.setOnClickListener(clickListener);
        mTv_class.setOnClickListener(clickListener);
        mTv_shop.setOnClickListener(clickListener);
        viewPager = (ViewPager) this.findViewById(R.id.vp_main_fragment);
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentP = position;
                upDateView();
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.main_tv_main:
                    currentP = 0;
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.main_tv_class:
                    currentP = 1;
                    viewPager.setCurrentItem(1, true);
                    break;
                case R.id.main_tv_earn:
                    currentP = 2;
                    viewPager.setCurrentItem(2, true);
                    break;
                case R.id.main_tv_shop:
                    currentP = 3;
                    viewPager.setCurrentItem(3, true);
                    break;
            }
            upDateView();

        }
    };

    private void upDateView() {
        switch (currentP) {
            case 0:

                setTextImage(mTv_main, R.mipmap.home_1_icon, "1");
                setTextImage(mTv_class, R.mipmap.class_0_icon, "0");
                setTextImage(mTv_earn, R.mipmap.earn_0_icon, "0");
                setTextImage(mTv_shop, R.mipmap.shop_0_icon, "0");
                break;
            case 1:
                setTextImage(mTv_main, R.mipmap.home_0_icon, "0");
                setTextImage(mTv_class, R.mipmap.class_1_icon, "1");
                setTextImage(mTv_earn, R.mipmap.earn_0_icon, "0");
                setTextImage(mTv_shop, R.mipmap.shop_0_icon, "0");
                break;
            case 2:
                setTextImage(mTv_main, R.mipmap.home_0_icon, "0");
                setTextImage(mTv_class, R.mipmap.class_0_icon, "0");
                setTextImage(mTv_earn, R.mipmap.earn_1_icon, "1");
                setTextImage(mTv_shop, R.mipmap.shop_0_icon, "0");
                break;
            case 3:
                setTextImage(mTv_main, R.mipmap.home_0_icon, "0");
                setTextImage(mTv_class, R.mipmap.class_0_icon, "0");
                setTextImage(mTv_earn, R.mipmap.earn_0_icon, "0");
                setTextImage(mTv_shop, R.mipmap.shop_1_icon, "1");
                break;
        }
    }

    private void setTextImage(TextView textView, int img, String color) {

        if (color.equals("0")) {
            color = "#999999";
        } else {
            color = "#F789B3";
        }
        Drawable drawable = getResources().getDrawable(img);
        drawable.setBounds(0, 0, DensityUtils.dp2px(getBaseContext(), 21), DensityUtils.dp2px(getBaseContext(), 21));
        textView.setCompoundDrawables(null, drawable, null, null);
        textView.setTextColor(Color.parseColor(color));
    }

    /**
     * 双击回退键退出程序
     *
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void doublePressBackToast() {
        if (!isBackPressed) {
            isBackPressed = true;
            Toast.makeText(this, "再次点击返回退出程序", Toast.LENGTH_SHORT).show();
        } else {
            /*退出*/
            AppManager.getInstance().appExit();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 3000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            doublePressBackToast();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        AppManager.getInstance().finishAllActivity();
    }


}
