package com.example.myfirstjob;

import java.io.File;

import Util.PreferenceUtil;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PersionIfo extends Activity
{
	private TextView edtTextView,accountTextView;
	private ImageView backImageView;
	private EditText sexEditText,areaEditText,schoolEditText,nameEditTextView;
	private RadioGroup sexRadioGroup;
	private RadioButton maleRadioButton,femaleRadioButton;
	private SQLiteDatabase db;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_info);
		initView();
		querySqlite();
		queryPersionIfo();
	}
	
	private void initView()
	{
		edtTextView=(TextView)findViewById(R.id.edt_text);
		backImageView=(ImageView)findViewById(R.id.back_img);
		accountTextView=(TextView)findViewById(R.id.account_text);
		
		
		
		sexEditText=(EditText)findViewById(R.id.sex_edt);
		areaEditText=(EditText)findViewById(R.id.area_edt);
		schoolEditText=(EditText)findViewById(R.id.school_edt);
		nameEditTextView=(EditText)findViewById(R.id.name_edt);
		sexRadioGroup=(RadioGroup)findViewById(R.id.sex_rg);
		maleRadioButton=(RadioButton)findViewById(R.id.male_ra);
		femaleRadioButton=(RadioButton)findViewById(R.id.female_ra);
		
		setOnClick();
	}
	
	private void setOnClick()
	{
		edtTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(edtTextView.getText().toString().equals("编辑"))
				{
					edtTextView.setText("保存");
					
					
					sexEditText.setVisibility(View.GONE);
					sexRadioGroup.setVisibility(View.VISIBLE);
					
					
					areaEditText.setEnabled(true);
					
					areaEditText.requestFocus();
					areaEditText.setSelection(areaEditText.getText().toString().length());
					InputMethodManager m = (InputMethodManager) areaEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				    m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
					schoolEditText.setEnabled(true);
					nameEditTextView.setEnabled(true);
				}
				else 
				{
					String sexString="女";
					if(maleRadioButton.isChecked())
					{
						sexString="男";
					}
					db.execSQL("update User set sex='"+sexString+"',"
							+ "area='"+areaEditText.getText().toString()+"',"
									+ "school='"+schoolEditText.getText().toString()+"',name='"+nameEditTextView.getText().toString()+"' "
											+ "where account='"+PreferenceUtil.getUserID(PersionIfo.this, "UserID")+"'");
					sendBroadcast(new Intent("userHeadUpdate"));
					Toast.makeText(PersionIfo.this, "保存成功", 500).show();
					finish();
				}
			}
		});
		
		backImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
	
	private void queryPersionIfo() 
	{
		Cursor cursor=db.rawQuery("select account,sex,area,school,name from User where account='"+PreferenceUtil.getUserID(PersionIfo.this, "UserID")+"'", null);
		cursor.moveToFirst();
		accountTextView.setText(cursor.getString(0));
		sexEditText.setText(cursor.getString(1));
		if(cursor.getString(1).equals("男"))
		{
			maleRadioButton.setChecked(true);
		}
		if (cursor.getString(1).equals("女"))
		{
			femaleRadioButton.setChecked(true);
		}
		areaEditText.setText(cursor.getString(2));
		schoolEditText.setText(cursor.getString(3));
		nameEditTextView.setText(cursor.getString(4));
		
		
	}

}
