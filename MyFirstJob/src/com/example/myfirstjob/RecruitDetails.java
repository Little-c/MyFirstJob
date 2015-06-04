package com.example.myfirstjob;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class RecruitDetails extends Activity
{
	private ImageView backImageView;
	private SQLiteDatabase db;
	private TextView titleTextView,dateTimeTextView,schoolTextView,roomTextView,jobTextView,introTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recruit_details);
		
		initView();
		Intent getIntent=getIntent();
		querySqlite();
		queryRecruitDetails();
		
	}
	private void initView() 
	{
		backImageView=(ImageView)findViewById(R.id.back_img);
		titleTextView=(TextView)findViewById(R.id.title_text);
		dateTimeTextView=(TextView)findViewById(R.id.date_time_text);
		schoolTextView=(TextView)findViewById(R.id.school_text);
		roomTextView=(TextView)findViewById(R.id.school_text);
		jobTextView=(TextView)findViewById(R.id.job_text);
		introTextView=(TextView)findViewById(R.id.introduction_text);
		
		
		backImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	private void querySqlite() 
	{
		File cacheDir;
	     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//判断是否存在sd卡 
          cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/db");  //在sd卡的根目录上创建文件"LazyList"         
	     else  
          cacheDir = this.getCacheDir();      
	     if (cacheDir.exists()) //若目录 存在
	     {
	    	 
	    	 if(db==null)
	    		 db=SQLiteDatabase.openOrCreateDatabase(cacheDir+"/FirstJob.db", null);
	     }
	}
	
	private void queryRecruitDetails()
	{
		Cursor cursor=db.rawQuery("select title,date,time,xiao,room,job,introduction from Recruit  where ID='"+getIntent().getStringExtra("recruitID")+"'", null);
		cursor.moveToPosition(0);
		titleTextView.setText(cursor.getString(0).toString());
		dateTimeTextView.setText(cursor.getString(1)+" "+cursor.getString(2));
		schoolTextView.setText(cursor.getString(3));
		roomTextView.setText(cursor.getString(4));
		jobTextView.setText(cursor.getString(5));
		introTextView.setText(cursor.getString(6));
		
	}

}
