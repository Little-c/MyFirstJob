package com.example.myfirstjob;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import custom_adapter.QuestAdapter;
import custom_adapter.RecruitAdapter;
import android.R.layout;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class QuestActivity extends Activity 
{
	
	private Button createBtn;
	private int display = 0;
	private ListView listQuest;
    private QuestAdapter questAdapter;
    ArrayList<String> IDs=new ArrayList<String>();
    ArrayList<String> titles=new ArrayList<String>();
	ArrayList<String> browers=new ArrayList<String>();
	ArrayList<String> answers=new ArrayList<String>();
	List<Map<String, Object>> listItems;
	private SQLiteDatabase db;
	private ReleseReceiver releseReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quest);
		
		initView();
		querySqlite();
		queryQuest();
		
	}
	
	private void initView() 
	{
		//注册广播
        releseReceiver=new ReleseReceiver();
        IntentFilter loginFilter = new IntentFilter();
        loginFilter.addAction("questUpdate");
        registerReceiver(releseReceiver, loginFilter); 
        
		
		createBtn = (Button)findViewById(R.id.createBtn);
		listQuest=(ListView)findViewById(R.id.list_quest);
		
		setOnclick();
	}
	
	private void setOnclick()
	{
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestActivity.this, CreateQuest.class);
				startActivity(intent);
			}
		});
		
		listQuest.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				db.execSQL("update QA set browse=browse+1 where ID='"+IDs.get(arg2)+"'");
				queryQuest();
				Intent skipIntent=new Intent(QuestActivity.this,QuestDetail.class);
				skipIntent.putExtra("questID", IDs.get(arg2));
				QuestActivity.this.startActivity(skipIntent);
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
	
	private void queryQuest() 
	{
		listItems=new ArrayList<Map<String,Object>>();
		IDs.clear();
		titles.clear();
		browers.clear();
		answers.clear();
		
		
		Cursor cursor=db.rawQuery("select ID,title,browse,answer from QA", null);
		while(cursor.moveToNext())
		{
			IDs.add(cursor.getString(0));
			titles.add(cursor.getString(1));
			browers.add(cursor.getString(2));
			answers.add(cursor.getString(3));
			
			Map<String, Object> listItem=new HashMap<String, Object>();
			listItem.put("title", titles.get(cursor.getPosition()));
			listItem.put("browse", browers.get(cursor.getPosition()));
			listItem.put("answer", answers.get(cursor.getPosition()));
			
			
			listItems.add(listItem);
		}
		
		if (questAdapter == null) 
		{
			questAdapter=new QuestAdapter(QuestActivity.this, listItems);
			listQuest.setAdapter(questAdapter);
			
		}
		else 
		{
			questAdapter.updateView(listItems);
		}
	}
	
	//接收需求更新广播
    public class ReleseReceiver extends BroadcastReceiver
  	 {  
  	    @Override  
  		public void onReceive(Context context, Intent intent) 
  	    {  
  	    	queryQuest();
  		}     		  
  	 }  
    
	 @Override  
	 protected void onDestroy() {  
	     super.onDestroy();  
	     unregisterReceiver(releseReceiver); 
	 }  
}
