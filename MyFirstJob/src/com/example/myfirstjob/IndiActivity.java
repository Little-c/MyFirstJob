package com.example.myfirstjob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.myfirstjob.QuestActivity.ReleseReceiver;

import Util.PreferenceUtil;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class IndiActivity extends Activity implements OnItemClickListener {
	GridView gridView;
	private ImageView userHeadImageView;
	private ReleseReceiver releseReceiver;
	private SQLiteDatabase db;
	private TextView userNameTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indi);
		
		//注册广播
        releseReceiver=new ReleseReceiver();
        IntentFilter loginFilter = new IntentFilter();
        loginFilter.addAction("userHeadUpdate");
        registerReceiver(releseReceiver, loginFilter); 
		
		gridView = (GridView)findViewById(R.id.individualGridView);
		userHeadImageView=(ImageView)findViewById(R.id.userHead);
		userNameTextView=(TextView)findViewById(R.id.userName);
		querySqlite();
		queryName();
		
		try {
			displayUserHead();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userHeadImageView.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IndiActivity.this.startActivity(new Intent(IndiActivity.this,Camera.class));
			}
		});
		
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		int image[] = { R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img5,
				R.drawable.img8, R.drawable.img9,};
		String title[] ={"个人信息", "我的收藏", "关注问题", "用户反馈", "软件更新", "关于软件" };
		
		for (int i = 0 ; i < 6 ; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", image[i]);
			map.put("title", title[i]);
			list.add(map);
		}

		SimpleAdapter imageAdapter = new SimpleAdapter(this, list, R.layout.activity_indiitem, new String[] {"image", "title"}, 
														new int[] {R.id.imageItem, R.id.imageItemTitle});
		gridView.setAdapter(imageAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2 == 0 ) {
					Intent intent = new Intent(IndiActivity.this, PersionIfo.class);
					startActivity(intent);
				} else if (arg2 == 1){
					Intent intent = new Intent(IndiActivity.this, MyCollection.class); 
					startActivity(intent);
				} else if (arg2 == 2){
					Intent intent = new Intent(IndiActivity.this, MyAttention.class);
					startActivity(intent);
				} else if( arg2 == 3 ) {
					Intent intent = new Intent(IndiActivity.this, Feedback.class);
					startActivity(intent);				
				} else if (arg2 == 4) {
					Toast toast = Toast.makeText(IndiActivity.this, "已经是最新版本", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else {
					Intent intent = new Intent(IndiActivity.this, AboutUS.class);
					startActivity(intent);
				}
			}
		});
		
		
		
	}
		
	class imageItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			HashMap<String, Object> item= (HashMap<String, Object>) parent.getItemAtPosition(position);  
		    //setTitle((String)item.get("imageItemTitle")); 
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
	private String queryFile() 
    {
    	//创建sd卡路径
        File cacheDir;
	     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//判断是否存在sd卡 
           cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/img");  //在sd卡的根目录上创建文件"LazyList"         
	     else  
           cacheDir = this.getCacheDir();      
	     if (!cacheDir.exists()) //若不存在，创建目录 
           cacheDir.mkdirs(); 
        
	    return cacheDir.toString()+"/";
	}
	
	private void readBitmap(String bitName) throws FileNotFoundException
    {
    	FileInputStream fis = new FileInputStream(queryFile() + bitName + ".png");
    			Bitmap bitmap=BitmapFactory.decodeStream(fis);
    			userHeadImageView.setImageBitmap(bitmap);
	}
	
	private void displayUserHead() throws FileNotFoundException 
	{
		readBitmap(PreferenceUtil.getUserID(IndiActivity.this, "UserID"));
	}
	
	//接收需求更新广播
    public class ReleseReceiver extends BroadcastReceiver
  	 {  
  	    @Override  
  		public void onReceive(Context context, Intent intent) 
  	    {  
  	    	try {
				displayUserHead();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	    	queryName();
  		}     		  
  	 }  
    
	 @Override  
	 protected void onDestroy() {  
	     super.onDestroy();  
	     unregisterReceiver(releseReceiver); 
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
	 
	 private void queryName()
	 {
		Cursor cursor=db.rawQuery("select name from User where account='"+PreferenceUtil.getUserID(IndiActivity.this, "UserID")+"'", null);
		cursor.moveToFirst();
		userNameTextView.setText(cursor.getString(0));
	 }
}
