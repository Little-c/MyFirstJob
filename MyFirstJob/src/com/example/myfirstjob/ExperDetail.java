package com.example.myfirstjob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ExperDetail extends Activity {
	private WebView webView;
	private ImageView backToMain;
	private Button collectBtn;
	private int display = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_infordetail);
		
		backToMain = (ImageView)findViewById(R.id.backToMain);
		webView = (WebView) findViewById(R.id.detailWebView);
		collectBtn = (Button)findViewById(R.id.collection);
		
		backToMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
		//获取传递的数据
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String url = bundle.getString("url");
		
		webView.loadUrl(url);
		WebSettings websettings = webView.getSettings();
		websettings.setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient());
		
		collectBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(display == 0) {
					Toast toast = Toast.makeText(ExperDetail.this, "收藏成功", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					collectBtn.setText("取消收藏");
					display = 1;
				} else {
					Toast toast = Toast.makeText(ExperDetail.this, "取消收藏成功", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					collectBtn.setText("收藏");
					display = 0;
				}
				
			}
		});
		
	}
}
