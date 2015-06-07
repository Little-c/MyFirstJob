package com.example.myfirstjob;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import custom_adapter.MyCollectionAdapter;
import custom_adapter.QuestAdapter;
import Util.PreferenceUtil;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class Experience extends Activity
{
	private ListView listCollection;
    private MyCollectionAdapter myCollectionAdapter;
    ArrayList<String> IDs=new ArrayList<String>();
    ArrayList<String> titles=new ArrayList<String>();
	ArrayList<String> times=new ArrayList<String>();
	ArrayList<String> urls=new ArrayList<String>();
	ArrayList<String> names=new ArrayList<String>();
	List<Map<String, Object>> listItems;
	private SQLiteDatabase db;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_experience);
		
		initView();
		querySqlite();
		queryCollection();
		
	}
	
	private void initView()
	{
		listCollection=(ListView)findViewById(R.id.list_collection);
		
		setOnClick();
	}
	
	private void setOnClick()
	{
		
		
		listCollection.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Experience.this, ExperDetail.class);
				intent.putExtra("url", urls.get(arg2));
				
				intent.putExtra("experienceID", IDs.get(arg2));
				startActivity(intent);
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
		listItems=new ArrayList<Map<String,Object>>();
		IDs.clear();
		titles.clear();
		times.clear();		
		urls.clear();
		names.clear();
		
		Cursor cursor=db.rawQuery("select ID,title,time,url from Experience", null);

		while(cursor.moveToNext())
		{
			IDs.add(cursor.getString(0));
			titles.add(cursor.getString(1));
			times.add(cursor.getString(2));
			urls.add(cursor.getString(3));
			names.add("");
			
			Map<String, Object> listItem=new HashMap<String, Object>();
			listItem.put("title", titles.get(cursor.getPosition()));
			listItem.put("time", times.get(cursor.getPosition()));
			listItem.put("name", names.get(cursor.getPosition()));

			listItems.add(listItem);
			
		}
		
		if (myCollectionAdapter== null) 
		{
			myCollectionAdapter=new MyCollectionAdapter(Experience.this, listItems);
			listCollection.setAdapter(myCollectionAdapter);
			
		}
		else 
		{
			myCollectionAdapter.updateView(listItems);
		}
	}

}
