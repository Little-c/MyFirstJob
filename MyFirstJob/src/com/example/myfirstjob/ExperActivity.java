package com.example.myfirstjob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ExperActivity extends Activity {
	
	private ListView list;
	private List<HashMap<String, Object>> experienceData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exper);
		list = (ListView)findViewById(R.id.experienceListView);
		
		experienceData = getData();
		
		experienceAdapter adapter = new experienceAdapter(this);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ExperActivity.this, ExperDetail.class);
				HashMap<String, Object> hash = experienceData.get(position);
				intent.putExtra("url", hash.get("url").toString());
				
				intent.putExtra("experienceID", position+"");
				startActivity(intent);
			}
			
		});
		
	}


	private List<HashMap<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map;
		for (int i = 0; i < 5 ; i++) {
			map = new HashMap<String, Object>();
			if(i % 5 == 0) {
				map.put("image", R.drawable.e0);
				map.put("title", "腾讯前端面试经历");
				map.put("date", "2013-10-25");
				map.put("url", "http://undefinedblog.com/tencent-front-end-interview/");
			}
			if( i % 5 == 1) {
				map.put("image", R.drawable.e1);
				map.put("title", "百度前端实习面试经历 ");
				map.put("date", "2013-3-03");
				map.put("url", "http://undefinedblog.com/baidu-fe-intern-interview/");
			}
			if( i % 5 == 2) {
				map.put("image", R.drawable.e2);
				map.put("title", "阿里巴巴前端校招面试经历");
				map.put("date", "2013-9-25");
				map.put("url", "http://undefinedblog.com/alibaba-fe-intern-interview/");
			
			}
			if( i % 5 == 3) {
				map.put("image", R.drawable.e3);
				map.put("title", "腾讯前端面试经历");
				map.put("date", "2013-10-25");
				map.put("url", "http://undefinedblog.com/tencent-front-end-interview/");
			
				
			}
			if( i % 5 == 4) {
				map.put("image", R.drawable.e4);
				map.put("title", "腾讯前端面试经历");
				map.put("date", "2013-10-25");
				map.put("url", "http://undefinedblog.com/tencent-front-end-interview/");
			

			}
			
			list.add(map);
		}
		return list;
	}
	
	static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView date;
	}
	
	public class experienceAdapter extends BaseAdapter {
		private LayoutInflater mInflater = null;
		 
		private experienceAdapter (Context context) {
			this.mInflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return experienceData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.activity_experitem, null);
				holder.img = (ImageView)convertView.findViewById(R.id.experienceListImg);
				holder.title = (TextView)convertView.findViewById(R.id.experienceListTitle);
				holder.date = (TextView)convertView.findViewById(R.id.experienceListDate);
				convertView.setTag(holder);	
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.img.setBackgroundResource((Integer)experienceData.get(position).get("image"));
			holder.title.setText((String)experienceData.get(position).get("title"));
			holder.date.setText((String)experienceData.get(position).get("date"));
			return convertView;
		}
		
	}
}
