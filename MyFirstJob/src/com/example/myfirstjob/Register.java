package com.example.myfirstjob;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity
{
	private SQLiteDatabase db;
	private  EditText accountEdt,nameEdt,passEdt,confirmEdt;
	private Button registerButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
		querySqlite();
		
	}
	
	private void initView() 
	{
		accountEdt=(EditText)findViewById(R.id.account_edt);
		nameEdt=(EditText)findViewById(R.id.name_edt);
		passEdt=(EditText)findViewById(R.id.pass_edt);
		confirmEdt=(EditText)findViewById(R.id.confirm_edt);
		registerButton=(Button)findViewById(R.id.register_button);
		
		setOnClick();
	} 
	
	private void setOnClick() 
	{
		registerButton.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isEmail(accountEdt.getText().toString())||isMobile(accountEdt.getText().toString()))
				{
					if(!nameEdt.getText().toString().equals(""))
					{
						if(nameEdt.getText().toString().length()<20)
						{
							if(passEdt.getText().toString().length()>=6)
							{
								if(passEdt.getText().toString().equals(confirmEdt.getText().toString()))
								{
									db.execSQL("insert into User(account,name,password) "
											+ "values('"+accountEdt.getText().toString()+"',"
													+ "'"+nameEdt.getText().toString()+"',"
															+ "'"+passEdt.getText().toString()+"')");
									
									PreferenceUtil.setUserID(Register.this, "UserID", accountEdt.getText().toString());
									Toast.makeText(Register.this, "ע��ɹ�", 500).show();
									Register.this.startActivity(new Intent(Register.this,FrameActivity.class));
									finish();
								}
								else
								{
									Toast.makeText(Register.this, "���벻һ��", 500).show();
								}
							}
							else
							{
								Toast.makeText(Register.this, "��������Ϊ6λ", 500).show();
							}
							
						}
						else
						{
							Toast.makeText(Register.this, "�ǳƲ�����20���ַ�", 500).show();
						}
					}
					else
					{
						Toast.makeText(Register.this, "�������ǳ�", 500).show();
					}
				}
				else 
				{
					Toast.makeText(Register.this, "�������ֻ���/����", 500).show();
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
	
	private boolean isEmail(String strEmail)
	{
		
		String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}
	
	private boolean isMobile(String str) 
	{
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) 
		{
			return true;
		}
		else 
		{
			return false;
			
		}
	} 
	
		
		
		
}
