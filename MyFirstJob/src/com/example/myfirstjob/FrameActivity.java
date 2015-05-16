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
 * ���������
 */

public class FrameActivity extends ActivityGroup {
	private LinearLayout InforBtn,ExperBtn,
						 QuestBtn,IndiBtn;
	private ImageView InforImg,ExperImg,
	 				  QuestImg,IndiImg;
	private TextView InforText,ExperText,
	  				  QuestText,IndiText;
	//�൱������Դ
	private List<View> list = new ArrayList<View>();
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	private android.support.v4.view.ViewPager mViewPager;
	// ����Դ��viewpager֮�������
	private PagerAdapter pagerAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�����ȫ��������û�б���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frame);
		initView();
	}
	
	//��ʼ���ؼ�
	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager)findViewById(R.id.FramePager);
		//������LinearLayoutΪ��ť�Ŀؼ�
		InforBtn = (LinearLayout)findViewById(R.id.inforBtn);
		ExperBtn = (LinearLayout)findViewById(R.id.experBtn);
		QuestBtn = (LinearLayout)findViewById(R.id.questBtn);
		IndiBtn = (LinearLayout)findViewById(R.id.indiBtn);
		//����LinearLayout�е�ImageView
		InforImg = (ImageView)findViewById(R.id.inforImg);
		ExperImg = (ImageView)findViewById(R.id.experImg);
		QuestImg = (ImageView)findViewById(R.id.questImg);
		IndiImg = (ImageView)findViewById(R.id.indiImg);
		//����LinearLayout�е�TextView
		InforText = (TextView)findViewById(R.id.inforTxt);
		ExperText = (TextView)findViewById(R.id.experTxt);
		QuestText = (TextView)findViewById(R.id.questTxt);
		IndiText = (TextView)findViewById(R.id.indiTxt);
		createView();
		//�ڲ���PagerAdapter
		pagerAdapter = new PagerAdapter() {
			//�ж��ٴ���ӵ�View��֮ǰ��View�Ƿ���ͬһ��View
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			
			//��������Դ�ĳ���
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.size();
			}
			
			//���ٻ����ߵ�View
			@Override 
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// �Ƴ�view
				container.removeView(list.get(position));
			}
			
			//ͨposition�ҵ���Ӧ��View
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// ��ȡview��ӵ��������У�������
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		
		//����pagerAdapter
		mViewPager.setAdapter(pagerAdapter);
		
		//Ϊÿ���ײ��˵����ü�����
		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		InforBtn.setOnClickListener(mytouchlistener);
		ExperBtn.setOnClickListener(mytouchlistener);
		QuestBtn.setOnClickListener(mytouchlistener);
		IndiBtn.setOnClickListener(mytouchlistener);
		
		//����ViewPager�����л�����
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			//arg0��ȡViewPager���л����ĵڼ�������
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				//�������ť��ʽ
				initBottomBtn();
				
				//���ն�Ӧ��View��tag���ж��л������Ǹ����沢�Ҹ��ĵ���Ӧ��button״̬
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
			 * ����ҳ�滬���� arg0 ��ʾ��ǰ������view arg1 ��ʾ�����İٷֱ� arg2 ��ʾ�����ľ���
			 * */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * ��������״̬ arg0 ��ʾ���ǵĻ���״̬ 0:Ĭ��״̬ 1:����״̬ 2:����ֹͣ
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
					.startActivity("information", new Intent(FrameActivity.this, TestActivity.class))
					.getDecorView();
		view.setTag(0);
		list.add(view);
		view1 = FrameActivity.this.getLocalActivityManager()
					 .startActivity("experience", new Intent(FrameActivity.this, TestActivity.class))
					 .getDecorView();
		view1.setTag(1);
		list.add(view1);
		view2 = FrameActivity.this.getLocalActivityManager()
						  .startActivity("requestion", new Intent(FrameActivity.this, TestActivity.class))
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
	 * ��linearlayout��Ϊ��ť�ļ����¼�
	 * 
	 **/
	private class MyBtnOnclick implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			//�������ǵ�viewpager��ת�Ǹ�����0������������ǵ�list���,�൱��list������±�
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
	 * ��ʼ���ײ��˵�������ɫ
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
	 * ���ذ�ť�ļ���������ѯ���û��Ƿ��˳�����
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(FrameActivity.this);
				builder.setTitle("��ʾ");
				builder.setMessage("��ȷ��Ҫ�˳���");
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
				builder.setPositiveButton("ȡ��", dialog);
				builder.setNegativeButton("ȷ��", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		}
		return false;
	}
}
