package com.example.myfirstjob_tab;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements
		android.view.View.OnClickListener {

	private ViewPager mViewPager;// �������ý����л�
	private PagerAdapter mPagerAdapter;// ��ʼ��View������
	private List<View> mViews = new ArrayList<View>();// �������Tab01-04
	// �ĸ�Tab��ÿ��Tab����һ����ť
	private LinearLayout mTabinfor;
	private LinearLayout mTabexper;
	private LinearLayout mTabquest;
	private LinearLayout mTabindi;
	// �ĸ���ť
	private ImageButton minforImg;
	private ImageButton mexperImg;
	private ImageButton mquestImg;
	private ImageButton mindiImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initViewPage();
		initEvent();
	}

	private void initEvent() {
		mTabinfor.setOnClickListener(this);
		mTabexper.setOnClickListener(this);
		mTabquest.setOnClickListener(this);
		mTabindi.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            /**
            *ViewPage���һ���ʱ
            */
			@Override
			public void onPageSelected(int arg0) {
				int currentItem = mViewPager.getCurrentItem();
				switch (currentItem) {
				case 0:
					 resetImg();
					minforImg.setImageResource(R.drawable.information1);
					break;
				case 1:
					 resetImg();
					mexperImg.setImageResource(R.drawable.experience1);
					break;
				case 2:
					 resetImg();
					mquestImg.setImageResource(R.drawable.question1);
					break;
				case 3:
					 resetImg();
					mindiImg.setImageResource(R.drawable.individual1);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	/**
	 * ��ʼ������
	 */
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpage);
		// ��ʼ���ĸ�LinearLayout
		mTabinfor = (LinearLayout) findViewById(R.id.infor);
		mTabexper = (LinearLayout) findViewById(R.id.exper);
		mTabquest = (LinearLayout) findViewById(R.id.quest);
		mTabindi = (LinearLayout) findViewById(R.id.indi);
		// ��ʼ���ĸ���ť
		minforImg = (ImageButton) findViewById(R.id.inforimg);
		mexperImg = (ImageButton) findViewById(R.id.experimg);
		mquestImg = (ImageButton) findViewById(R.id.questimg);
		mindiImg = (ImageButton) findViewById(R.id.indiimg);
	}

	/**
	 * ��ʼ��ViewPage
	 */
	private void initViewPage() {

		// ���軯�ĸ�����
		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View tab01 = mLayoutInflater.inflate(R.layout.activity_infor, null);
		View tab02 = mLayoutInflater.inflate(R.layout.activity_exper, null);
		View tab03 = mLayoutInflater.inflate(R.layout.activity_quest, null);
		View tab04 = mLayoutInflater.inflate(R.layout.activity_indi, null);

		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		mViews.add(tab04);

		// ��������ʼ��������
		mPagerAdapter = new PagerAdapter() {

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

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {

				return arg0 == arg1;
			}

			@Override
			public int getCount() {

				return mViews.size();
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
	}

	/**
	 * �ж��ĸ�Ҫ��ʾ�������ð�ťͼƬ
	 */
	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.infor:
			mViewPager.setCurrentItem(0);
			resetImg();
			minforImg.setImageResource(R.drawable.information1);
			break;
		case R.id.exper:
			mViewPager.setCurrentItem(1);
			resetImg();
			mexperImg.setImageResource(R.drawable.experience1);
			break;
		case R.id.quest:
			mViewPager.setCurrentItem(2);
			resetImg();
			mquestImg.setImageResource(R.drawable.question1);
			break;
		case R.id.indi:
			mViewPager.setCurrentItem(3);
			resetImg();
			mindiImg.setImageResource(R.drawable.individual1);
			break;
		default:
			break;
		}
	}

	/**
	 * ������ͼƬ�䰵
	 */
	private void resetImg() {
		minforImg.setImageResource(R.drawable.information2);
		mexperImg.setImageResource(R.drawable.experience2);
		mquestImg.setImageResource(R.drawable.question2);
		mindiImg.setImageResource(R.drawable.individual2);
	}

}
