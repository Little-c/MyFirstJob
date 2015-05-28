package com.example.myfirstjob;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class QuestDetail extends Activity {
	
	private ImageView backToMain;
	private Button interestQuestion;
	private Button answerQuestion;
	private int display = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_questitem);
		backToMain = (ImageView)findViewById(R.id.backToMain);
		interestQuestion = (Button)findViewById(R.id.interestQuestion);
		answerQuestion = (Button)findViewById(R.id.answerQuestion);
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
				if(display == 0) {
					Toast toast = Toast.makeText(QuestDetail.this, "关注成功", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					interestQuestion.setText("取消关注");
					display = 1;
				} else {
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
				Intent intent = new Intent(QuestDetail.this, CreateQuest.class);
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

}
