package com.example.myfirstjob;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import Util.PreferenceUtil;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Answer extends Activity
{
	private ImageView backImg;
	private EditText contentEdt;
	private Button answerBtn;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		
		initView();
		querySqlite();
	}
	
	private void initView() 
	{
		backImg=(ImageView)findViewById(R.id.back_img);
		contentEdt=(EditText)findViewById(R.id.content_edt);
		answerBtn=(Button)findViewById(R.id.answer_btn);
		setOnclick();
	}
	
	private void setOnclick()
	{
		backImg.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		answerBtn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!contentEdt.getText().toString().equals(""))
				{
					final java.util.Date curDate = new Date(System.currentTimeMillis());//��ȡ��ǰʱ�� 			        
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//���Է�����޸����ڸ�ʽ
					
					db.execSQL("insert into Answer(questID,userID,time,answerDetails) "
							+ "values('"+getIntent().getStringExtra("questID")+"',"
									+ "'"+PreferenceUtil.getUserID(Answer.this, "UserID")+"',"
											+ "'"+dateFormat.format(curDate)+"',"
													+ "'"+contentEdt.getText().toString()+"')");
					
					db.execSQL("update QA set answer=answer+1 where ID='"+getIntent().getStringExtra("questID")+"'");
					
					sendBroadcast(new Intent("questUpdate"));
					sendBroadcast(new Intent("answerUpdate"));
					
					Toast.makeText(Answer.this, "�ش�ɹ�", 500).show();
					finish();
				}
				else 
				{
					Toast.makeText(Answer.this, "���ݲ���Ϊ��", 500).show();
				}
			}
		});
	}
	
	private void querySqlite() 
	{
		 File cacheDir;
	     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//�ж��Ƿ����sd�� 
          cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/db");  //��sd���ĸ�Ŀ¼�ϴ����ļ�"LazyList"         
	     else  
          cacheDir = this.getCacheDir();      
	     if (cacheDir.exists()) //��Ŀ¼ ����
	     {
	    	 
	    	 if(db==null)
	    		 db=SQLiteDatabase.openOrCreateDatabase(cacheDir+"/FirstJob.db", null);
	     }
	}

}
