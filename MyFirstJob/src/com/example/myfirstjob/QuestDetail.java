package com.example.myfirstjob;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.myfirstjob.QuestActivity.ReleseReceiver;

import custom_adapter.AnswerAdapter;
import custom_adapter.QuestAdapter;
import Util.PreferenceUtil;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestDetail extends Activity {
	
	private ImageView backToMain;
	private Button interestQuestion;
	private Button answerQuestion;
	private int display = 0;
	private SQLiteDatabase db;
	private TextView titileText,nameText,questDetailsText;
	private int isAtd;
	
	private ListView listViewAnswer;
    private AnswerAdapter answerAdapter;
    ArrayList<String> names=new ArrayList<String>();
	ArrayList<String> times=new ArrayList<String>();
	ArrayList<String> answers=new ArrayList<String>();
	List<Map<String, Object>> listItems;
	private ReleseReceiver releseReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_quest_details);
		backToMain = (ImageView)findViewById(R.id.backToMain);
		interestQuestion = (Button)findViewById(R.id.interestQuestion);
		answerQuestion = (Button)findViewById(R.id.answerQuestion);
		titileText=(TextView)findViewById(R.id.questionTextView);
		nameText=(TextView)findViewById(R.id.name_text);
		questDetailsText=(TextView)findViewById(R.id.quest_details_text);
		listViewAnswer=(ListView)findViewById(R.id.list_answer);
		
		//注册广播
        releseReceiver=new ReleseReceiver();
        IntentFilter loginFilter = new IntentFilter();
        loginFilter.addAction("answerUpdate");
        registerReceiver(releseReceiver, loginFilter); 
		
		querySqlite();
		queryQuest();
		queryAnswer();
		
        backToMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		interestQuestion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(display == 0) 
				{
					if(isAtd==0)
						db.execSQL("insert into QuestAttention(qaID,userID,isAttention) values('"+getIntent().getStringExtra("questID")+"','"+PreferenceUtil.getUserID(QuestDetail.this, "userID")+"','true')");
					else {
						db.execSQL("update QuestAttention set isAttention='true' where qaID='"+getIntent().getStringExtra("questID")+"' and userID='"+PreferenceUtil.getUserID(QuestDetail.this, "userID")+"'");
					}
					Toast toast = Toast.makeText(QuestDetail.this, "关注成功", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					interestQuestion.setText("取消关注");
					display = 1;
				} 
				else 
				{
					db.execSQL("update QuestAttention set isAttention='false' where qaID='"+getIntent().getStringExtra("questID")+"' and userID='"+PreferenceUtil.getUserID(QuestDetail.this, "userID")+"'");
					Toast toast = Toast.makeText(QuestDetail.this, "取消关注成功", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					interestQuestion.setText("关注");
					display = 0;
				}
			}
		});
		
		answerQuestion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestDetail.this, Answer.class);
				intent.putExtra("questID", getIntent().getStringExtra("questID"));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questitem, menu);
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
	
	private void queryQuest()
	{
		Cursor cursor=db.rawQuery("select title,name,qDetails from QA a join User b on a.qPersionID=b.account where a.ID='"+getIntent().getStringExtra("questID")+"'", null);
		cursor.moveToFirst();
		titileText.setText(cursor.getString(0));
		nameText.setText("——"+cursor.getString(1));
		questDetailsText.setText(cursor.getString(2));
		
		Cursor attentionCursor=db.rawQuery("select isAttention from QuestAttention where qaID='"+getIntent().getStringExtra("questID")+"' and userID='"+PreferenceUtil.getUserID(QuestDetail.this, "userID")+"'", null);
		isAtd=attentionCursor.getCount();
		if(attentionCursor.getCount()==0)
		{
			interestQuestion.setText("关注");
			
			display=0;
		}
		else
		{
			attentionCursor.moveToFirst();
			if(attentionCursor.getString(0).equals("false"))
			{
				interestQuestion.setText("关注");
				display=0;
			}
			else {
				interestQuestion.setText("取消关注");
				display=1;
			}
			
		}
	}
	
	private void queryAnswer()
	{
		listItems=new ArrayList<Map<String,Object>>();
		names.clear();
		times.clear();
		answers.clear();
		
		Cursor answerCursor=db.rawQuery("select name,time,answerDetails from Answer a join User b on a.userID=b.account where questID='"+getIntent().getStringExtra("questID")+"'", null);
		while(answerCursor.moveToNext())
		{
			names.add(answerCursor.getString(0));
			times.add(answerCursor.getString(1));
			answers.add(answerCursor.getString(2));
			
			Map<String, Object> listItem=new HashMap<String, Object>();
			listItem.put("name", names.get(answerCursor.getPosition()));
			listItem.put("time", times.get(answerCursor.getPosition()));
			listItem.put("answer", answers.get(answerCursor.getPosition()));
			
			
			listItems.add(listItem);
		}
		
		if (answerAdapter == null) 
		{
			answerAdapter=new AnswerAdapter(QuestDetail.this, listItems);
			listViewAnswer.setAdapter(answerAdapter);
			
		}
		else 
		{
			answerAdapter.updateView(listItems);
		}
		
	}
	
	//接收需求更新广播
    public class ReleseReceiver extends BroadcastReceiver
  	 {  
  	    @Override  
  		public void onReceive(Context context, Intent intent) 
  	    {  
  	    	queryAnswer();
  		}     		  
  	 }  
    
	 @Override  
	 protected void onDestroy() {  
	     super.onDestroy();  
	     unregisterReceiver(releseReceiver); 
	 }  

}
