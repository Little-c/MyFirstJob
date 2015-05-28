package com.example.myfirstjob;

import android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class QuestActivity extends Activity {
	
	private Button createBtn = null;
	
	private LinearLayout test;
	private LinearLayout test1;
	private LinearLayout test2;
	private LinearLayout test3;
	private LinearLayout test4;
	private LinearLayout test5;
	private int display = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest);
		createBtn = (Button)findViewById(R.id.createBtn);
		
		test = (LinearLayout)findViewById(R.id.listItem);
		test1 = (LinearLayout)findViewById(R.id.listItem1);
		test2 = (LinearLayout)findViewById(R.id.listItem2);
		test3 = (LinearLayout)findViewById(R.id.listItem3);
		test4 = (LinearLayout)findViewById(R.id.listItem4);
		test5 = (LinearLayout)findViewById(R.id.listItem5);
		
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, CreateQuest.class);
				startActivity(intent);
			}
		});
		test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, QuestDetail.class);
				startActivity(intent);
			}
		});
		test1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, QuestDetail.class);
				startActivity(intent);
			}
		});
		test2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, QuestDetail.class);
				startActivity(intent);
			}
		});
		test3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, QuestDetail.class);
				startActivity(intent);
			}
		});
		test4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, QuestDetail.class);
				startActivity(intent);
			}
		});
		test5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, QuestDetail.class);
				startActivity(intent);
			}
		});
		
		
	}
}
