package com.example.myfirstjob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Util.PreferenceUtil;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity
{
	private TextView registerTextView;
	private Button loginButton;
	private SQLiteDatabase db;
	private EditText accountEdt,passEdt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		
		isFirst();
		querySqlite();
		
		
	}
	
	private void isFirst()
	{
		if(PreferenceUtil.getUserID(Login.this, "isFirst").equals(""))
		{
			initData();
			PreferenceUtil.setUserID(Login.this, "isFirst", "no");
		}
	}
	
	private void initView() 
	{
		registerTextView=(TextView)findViewById(R.id.register_text);
		loginButton=(Button)findViewById(R.id.login_button);
		accountEdt=(EditText)findViewById(R.id.account_edt);
		passEdt=(EditText)findViewById(R.id.pass_edt);
		setOnClick();
	} 
	
	private void setOnClick() 
	{
		registerTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Login.this.startActivity(new Intent(Login.this,Register.class));
			}
		});
		
		loginButton.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(!accountEdt.getText().toString().toString().equals("")&&!passEdt.getText().toString().equals(""))
				{
					Cursor cursor=db.rawQuery("select * from User where account='"+accountEdt.getText().toString()+"' and password='"+passEdt.getText().toString()+"'", null);
					if(cursor.getCount()>0)
					{
						PreferenceUtil.setUserID(Login.this, "UserID", accountEdt.getText().toString());
						Login.this.startActivity(new Intent(Login.this,FrameActivity.class));
						finish();
					}
					else {
						Toast.makeText(Login.this, "账号或密码错误", 500).show();
					}
				}
				else {
					Toast.makeText(Login.this, "账号和密码不能为空", 500).show();
				}
				
				
			}
		});
	}
	
	private void initData() 
	{
		//创建sd卡路径
        File cacheDir;
	     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//判断是否存在sd卡 
           cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/db");  //在sd卡的根目录上创建文件"LazyList"         
	     else  
           cacheDir = this.getCacheDir();      
	     if (!cacheDir.exists()) //若不存在，创建目录 
           cacheDir.mkdirs(); 
        
	    String pathString=cacheDir.toString()+"/FirstJob.db";
	    
	    try 
	    {
	    	 
			copyBigDataToSD(pathString);
			
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void copyBigDataToSD(String strOutFileName) throws IOException 
    {  
    	        
        OutputStream myOutput = new FileOutputStream(strOutFileName);          
        InputStream myInput=this.getAssets().open("FirstJob.db"); 
        byte[] buffer = new byte[1024];  
        int length =myInput.read(buffer);      
        while(length > 0)
        {
            myOutput.write(buffer, 0, length); 
            length = myInput.read(buffer);
        }        
        myOutput.flush();  
        myInput.close();  
        myOutput.close();        
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
