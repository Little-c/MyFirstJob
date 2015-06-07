package com.example.myfirstjob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Feedback extends Activity
{
	private Button feedbackBtn;
	private EditText contentEdt;
	private ImageView backImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_feedback);
		
		
		
		feedbackBtn=(Button)findViewById(R.id.feedback_btn);
		contentEdt=(EditText)findViewById(R.id.content_edt);
		backImageView=(ImageView)findViewById(R.id.back_img);
		
 	
	 backImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		
		feedbackBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!contentEdt.getText().toString().equals(""))
				{
					Toast.makeText(Feedback.this, "提交成功", 500).show();
					finish();
				}
				else
				{
					Toast.makeText(Feedback.this, "内容不能为空", 500).show();
				}
			}
		});
	}

}
