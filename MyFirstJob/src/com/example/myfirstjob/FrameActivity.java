package com.example.myfirstjob;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/*
 * 软件界面框架
 */

public class FrameActivity extends ActivityGroup {
	private LinearLayout InforBtn,ExperBtn,
						 QuestBtn,IndiBtn;
	private ImageView InforImg,ExperImg,
	 				  QuestImg,IndiImg;
	private TextView InforText,ExperText,
	  				  QuestText,IndiText;
	//相当于数据源
	private List<View> list = new ArrayList<View>();
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	private android.support.v4.view.ViewPager mViewPager;
	// 数据源和viewpager之间的桥梁
	private PagerAdapter pagerAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//是软件全屏，并且没有标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frame);
		initView();
	}
	
	//初始化控件
	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager)findViewById(R.id.FramePager);
		//查找以LinearLayout为按钮的控件
		InforBtn = (LinearLayout)findViewById(R.id.inforBtn);
		ExperBtn = (LinearLayout)findViewById(R.id.experBtn);
		QuestBtn = (LinearLayout)findViewById(R.id.questBtn);
		IndiBtn = (LinearLayout)findViewById(R.id.indiBtn);
		//查找LinearLayout中的ImageView
		InforImg = (ImageView)findViewById(R.id.inforImg);
		ExperImg = (ImageView)findViewById(R.id.experImg);
		QuestImg = (ImageView)findViewById(R.id.questImg);
		IndiImg = (ImageView)findViewById(R.id.indiImg);
		//查找LinearLayout中的TextView
		InforText = (TextView)findViewById(R.id.inforTxt);
		ExperText = (TextView)findViewById(R.id.experTxt);
		QuestText = (TextView)findViewById(R.id.questTxt);
		IndiText = (TextView)findViewById(R.id.indiTxt);
		createView();
		//内部类PagerAdapter
		pagerAdapter = new PagerAdapter() {
			//判断再次添加的View和之前的View是否是同一个View
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			
			//返回数据源的长度
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.size();
			}
			
			//销毁滑动走的View
			@Override 
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// 移除view
				container.removeView(list.get(position));
			}
			
			//通position找到相应的View
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// 获取view添加到容器当中，并返回
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		
		//设置pagerAdapter
		mViewPager.setAdapter(pagerAdapter);
		
		//为每个底部菜单设置监听器
		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		InforBtn.setOnClickListener(mytouchlistener);
		ExperBtn.setOnClickListener(mytouchlistener);
		QuestBtn.setOnClickListener(mytouchlistener);
		IndiBtn.setOnClickListener(mytouchlistener);
		
		//设置ViewPager界面切换监听
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			//arg0获取ViewPager的切换到的第几个界面
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				//先清除按钮样式
				initBottomBtn();
				
				//按照对应的View的tag来判断切换到了那个界面并且更改到对应的button状态
				int flag = (Integer)list.get(arg0).getTag();
				if (flag == 0) {
					InforImg
							.setImageResource(R.drawable.information1);
					InforText.setTextColor(Color.parseColor("#56abe4"));
				} else if (flag == 1) {
					ExperImg
						.setImageResource(R.drawable.experience1);
					ExperText.setTextColor(Color.parseColor("#56abe4"));
				} else if (flag == 2) {
					QuestImg
						.setImageResource(R.drawable.question1);
					QuestText.setTextColor(Color.parseColor("#56abe4"));
				} else if (flag == 3) {
					IndiImg
						.setImageResource(R.drawable.individual1);
					IndiText.setTextColor(Color.parseColor("#56abe4"));
				}
			}
			
			/**
			 * 监听页面滑动的 arg0 表示当前滑动的view arg1 表示滑动的百分比 arg2 表示滑动的距离
			 * */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * 监听滑动状态 arg0 表示我们的滑动状态 0:默认状态 1:滑动状态 2:滑动停止
			 * */
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	
	private void createView() {
		// TODO Auto-generated method stub
		view = this.getLocalActivityManager()
					.startActivity("information", new Intent(FrameActivity.this, Recruit.class))
					.getDecorView();
		view.setTag(0);
		list.add(view);
		view1 = FrameActivity.this.getLocalActivityManager()
					 .startActivity("experience", new Intent(FrameActivity.this, ExperActivity.class))
					 .getDecorView();
		view1.setTag(1);
		list.add(view1);
		view2 = FrameActivity.this.getLocalActivityManager()
						  .startActivity("requestion", new Intent(FrameActivity.this, QuestActivity.class))
						  .getDecorView();
		view2.setTag(2);
		list.add(view2);
		view3 = FrameActivity.this.getLocalActivityManager()
						  .startActivity("individual", new Intent(FrameActivity.this, IndiActivity.class))
						  .getDecorView();
		view3.setTag(3);
		list.add(view3);
		
	}
	
	/**
	 * 用linearlayout作为按钮的监听事件
	 * 
	 **/
	private class MyBtnOnclick implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			//设置我们的viewpager跳转那个界面0这个参数和我们的list相关,相当于list里面的下标
			case R.id.inforBtn:
				mViewPager.setCurrentItem(0);
				initBottomBtn();
				InforImg.setImageResource(R.drawable.information1);
				InforText.setTextColor(Color.parseColor("#56abe4"));
				break;
			case R.id.experBtn:
				mViewPager.setCurrentItem(1);
				initBottomBtn();
				ExperImg.setImageResource(R.drawable.experience1);
				ExperText.setTextColor(Color.parseColor("#56abe4"));
				break;
			case R.id.questBtn:
				mViewPager.setCurrentItem(2);
				initBottomBtn();
				QuestImg.setImageResource(R.drawable.question1);
				QuestText.setTextColor(Color.parseColor("#56abe4"));
				break;
			case R.id.indiBtn:
				mViewPager.setCurrentItem(3);
				initBottomBtn();
				IndiImg.setImageResource(R.drawable.individual1);
				IndiText.setTextColor(Color.parseColor("#56abe4"));
			}
		}
		
	}
	
	/*
	 * 初始化底部菜单栏的颜色
	 */
	private void initBottomBtn() {
		InforImg.setImageResource(R.drawable.information2);
		ExperImg.setImageResource(R.drawable.experience2);
		QuestImg.setImageResource(R.drawable.question2);
		IndiImg.setImageResource(R.drawable.individual2);
		
		InforText.setTextColor(Color.parseColor("#000000"));
		ExperText.setTextColor(Color.parseColor("#000000"));
		QuestText.setTextColor(Color.parseColor("#000000"));
		IndiText.setTextColor(Color.parseColor("#000000"));
	}
	/**
	 * 返回按钮的监听，用来询问用户是否退出程序
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(FrameActivity.this);
				builder.setTitle("提示");
				builder.setMessage("你确定要退出吗？");
				builder.setIcon(R.drawable.ic_launcher);

				DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						if (arg1 == DialogInterface.BUTTON_POSITIVE) {
							arg0.cancel();
						} else if (arg1 == DialogInterface.BUTTON_NEGATIVE) {
							FrameActivity.this.finish();
						}
					}
				};
				builder.setPositiveButton("取消", dialog);
				builder.setNegativeButton("确定", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		}
		return false;
	}
}
