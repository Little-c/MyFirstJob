package com.example.myfirstjob;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class IndiActivity extends Activity implements OnItemClickListener {
	GridView gridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indi);
		gridView = (GridView)findViewById(R.id.individualGridView);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		int image[] = { R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
				R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9,};
		String title[] ={"个人信息", "我的收藏", "关注问题", "招聘提醒", "用户反馈", "推荐软件", "软件设置", "软件更新", "关于软件" };
		
		for (int i = 0 ; i < 9 ; i++) {
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
					Intent intent = new Intent(IndiActivity.this, TestActivity.class);
					startActivity(intent);
				} else if (arg2 == 1){
					Intent intent = new Intent(IndiActivity.this, TestActivity.class);
					startActivity(intent);
				} else if (arg2 == 2){
					Intent intent = new Intent(IndiActivity.this, TestActivity.class);
					startActivity(intent);
				} else if (arg2 == 3){
					Intent intent = new Intent(IndiActivity.this, TestActivity.class);
					startActivity(intent);
				} else if( arg2 == 4 ) {
					Intent intent = new Intent(IndiActivity.this, TestActivity.class);
					startActivity(intent);
				} else if ( arg2 == 5) {
					Intent intent = new Intent(IndiActivity.this, TestActivity.class);
					startActivity(intent);
				} else if (arg2 == 6) {
					Intent intent = new Intent(IndiActivity.this, TestActivity.class);
					startActivity(intent);
				} else if (arg2 == 7) {
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
}
