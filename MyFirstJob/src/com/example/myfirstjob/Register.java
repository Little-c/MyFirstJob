package com.example.myfirstjob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class Register extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
		
	}
	
	private void initView() 
	{
		
		setOnClick();
	} 
	
	private void setOnClick() 
	{
		
	}

}
