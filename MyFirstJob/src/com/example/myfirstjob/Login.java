package com.example.myfirstjob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity
{
	private TextView registerTextView;
	private Button loginButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
		
	}
	
	private void initView() 
	{
		registerTextView=(TextView)findViewById(R.id.register_text);
		loginButton=(Button)findViewById(R.id.login_button);
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
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Login.this.startActivity(new Intent(Login.this,FrameActivity.class));
				finish();
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

}
