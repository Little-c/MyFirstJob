package com.example.myfirstjob;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateQuest extends Activity {
	
	private ImageView backToMain;
	private Button submitBtn;
	private EditText questionEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_questcreat);
		backToMain = (ImageView)findViewById(R.id.backToMain);
		submitBtn = (Button)findViewById(R.id.submitBtn);
		questionEditText = (EditText)findViewById(R.id.questionEditText);
		
		backToMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		submitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(questionEditText.getText().toString().equals("")) {
					Toast toast = Toast.makeText(CreateQuest.this, "问题不能为空！", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				} else {
					Toast toast = Toast.makeText(CreateQuest.this, "发表成功", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creatquest, menu);
		return true;
	}

}
