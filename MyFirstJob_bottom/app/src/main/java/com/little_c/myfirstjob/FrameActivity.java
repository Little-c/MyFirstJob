package com.little_c.myfirstjob;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class FrameActivity extends Activity implements
        android.view.View.OnClickListener{
    private ViewPager mViewPager;//用来放置界面切换
    private PagerAdapter mPagerAdapter;//初始化View适配器
    private List<View> mViews = new ArrayList<View>();//用来存放4个Tab
    //4个Tab，每个Tab包含一个按钮
    private LinearLayout mTabInf;
    private LinearLayout mTabExp;
    private LinearLayout mTabQue;
    private LinearLayout mTabInd;
    //4个按钮
    private ImageButton mImgInf;
    private ImageButton mImgExp;
    private ImageButton mImgQue;
    private ImageButton mImgInd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_information);
        initView();//初始化设置
        initViewPage();//初始化ViewPage
        initEvent();
    }

    private void initEvent() {
        mTabInf.setOnClickListener(this);
        mTabExp.setOnClickListener(this);
        mTabQue.setOnClickListener(this);
        mTabInd.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //ViewPage滑动时
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem){
                    case 0:
                        resetImg();
                        mImgInf.setImageResource(R.drawable.information1);
                        break;
                    case 1:
                        resetImg();
                        mImgExp.setImageResource(R.drawable.experience1);
                        break;
                    case 2:
                        resetImg();
                        mImgQue.setImageResource(R.drawable.question1);
                        break;
                    case 3:
                        resetImg();
                        mImgInd.setImageResource(R.drawable.individual1);
                        break;
                    default:
                        break;
                }

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPage() {
        //初始化4个布局
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        View Inf = mLayoutInflater.inflate(R.layout.activity_information,null);
        View Exp = mLayoutInflater.inflate(R.layout.activity_experience,null);
        View Req = mLayoutInflater.inflate(R.layout.activity_question,null);
        View Ind = mLayoutInflater.inflate(R.layout.activity_individual,null);

        mViews.add(Inf);
        mViews.add(Exp);
        mViews.add(Req);
        mViews.add(Ind);

        //适配器初始化并设置
        mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViews.get(position));

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }
        };
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpage);
        //初始化4个LinearLayout
        mTabInf = (LinearLayout) findViewById(R.id.MyInformationBtn);
        mTabExp = (LinearLayout) findViewById(R.id.MyExperienceBtn);
        mTabQue = (LinearLayout) findViewById(R.id.MyRequestionBtn);
        mTabInd = (LinearLayout) findViewById(R.id.MyIndividualBtn);
        //初始化4个ImageButton
        mImgInf = (ImageButton) findViewById(R.id.MyInformationImg);
        mImgExp = (ImageButton) findViewById(R.id.MyExperienceImg);
        mImgQue = (ImageButton) findViewById(R.id.MyRequestionImg);
        mImgInd = (ImageButton) findViewById(R.id.MyIndividualImg);
    }
    private void resetImg() {
        mImgInf.setImageResource(R.drawable.information2);
        mImgExp.setImageResource(R.drawable.experience2);
        mImgQue.setImageResource(R.drawable.question2);
        mImgInd.setImageResource(R.drawable.individual2);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //判断那个layout要显示
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MyInformationBtn:
                mViewPager.setCurrentItem(0);
                resetImg();
                mImgInf.setImageResource(R.drawable.information1);
                break;
            case R.id.MyExperienceBtn:
                mViewPager.setCurrentItem(1);
                resetImg();
                mImgExp.setImageResource(R.drawable.experience1);
                break;
            case R.id.MyRequestionBtn:
                mViewPager.setCurrentItem(2);
                resetImg();
                mImgQue.setImageResource(R.drawable.question1);
                break;
            case R.id.MyIndividualBtn:
                mViewPager.setCurrentItem(3);
                resetImg();
                mImgInd.setImageResource(R.drawable.individual1);
                break;
            default:
                break;
        }
    }
}
