package com.example.myfirstjob;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ExperDetail extends Activity {
	private WebView webView;
	private ImageView backToMain;
	private Button collectBtn;
	private int display = 0;
	private SQLiteDatabase db;
	private String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_infordetail);
		
		backToMain = (ImageView)findViewById(R.id.backToMain);
		webView = (WebView) findViewById(R.id.detailWebView);
		collectBtn = (Button)findViewById(R.id.collection);
		
		
		
		backToMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
		//获取传递的数据
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		url = bundle.getString("url");
		
		querySqlite();
		queryCollection();
		
		webView.loadUrl(url);
		WebSettings websettings = webView.getSettings();
		websettings.setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient());
		
		collectBtn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(display == 0)
				{
					insertCollection();
					Toast toast = Toast.makeText(ExperDetail.this, "收藏成功", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					collectBtn.setText("取消收藏");
					
					display = 1;
				} 
				else
				{
					updateCollection();
					Toast toast = Toast.makeText(ExperDetail.this, "取消收藏成功", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					collectBtn.setText("收藏");
					display = 0;
				}
				
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
	
	private void queryCollection()
	{
		Cursor cursor=db.rawQuery("select url from Collection where url='"+url+"'", null);
		if(cursor.getCount()==1)
		{
			collectBtn.setText("取消收藏");
			display=1;
		}
		else {
			collectBtn.setText("收藏");
			display=0;
		}
	}
	
	private void insertCollection() 
	{
		db.execSQL("insert into Collection(url) values('"+url+"')");
	}
	
	private void updateCollection() 
	{
		db.execSQL("update Collection set url='' where url='"+url+"'");
	}
}
