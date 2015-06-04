package com.example.myfirstjob;

import java.io.File;

import Util.PreferenceUtil;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateQuest extends Activity {
	
	private ImageView backToMain;
	private Button submitBtn;
	private EditText titleEdt,questionEditText;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_questcreat);
		backToMain = (ImageView)findViewById(R.id.backToMain);
		submitBtn = (Button)findViewById(R.id.submitBtn);
		questionEditText = (EditText)findViewById(R.id.questionEditText);
		titleEdt=(EditText)findViewById(R.id.title_edt);
		
		querySqlite();
		
		backToMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		submitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(questionEditText.getText().toString().equals("")&&titleEdt.getText().toString().equals("")) 
				{
					Toast toast = Toast.makeText(CreateQuest.this, "题目和描述不能为空！", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else
				{
					db.execSQL("insert into QA(title,qPersionID,qDetails,browse,answer) values('"+titleEdt.getText().toString()+"','"+PreferenceUtil.getUserID(CreateQuest.this, "UserID")+"','"+questionEditText.getText().toString()+"',0,0)");
					sendBroadcast(new Intent("questUpdate"));
					Toast toast = Toast.makeText(CreateQuest.this, "发表成功", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					finish();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creatquest, menu);
		return true;
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

}
