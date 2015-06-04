package custom_adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.example.myfirstjob.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecruitAdapter extends BaseAdapter
{
	private  List<Map<String, Object>> listItems;
	private LayoutInflater mInfalter;
	Context context;
	
	
	public RecruitAdapter(Context context,List<Map<String, Object>> listItems){
		this.listItems = listItems;
		this.mInfalter = LayoutInflater.from(context);
		this.context=context;
		
	}
	
	public void updateView(List<Map<String, Object>> listItems){
		this.listItems = listItems;
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub	
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public class HolderView{
			
     	TextView titleTextView,timeTextView,placeTextView,schoolTextView;
     	
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
        HolderView holderView;
		
		if(convertView == null)
		{
			holderView = new HolderView();
			convertView = mInfalter.inflate(R.layout.item_recruit_listview, null);
			holderView.titleTextView=(TextView)convertView.findViewById(R.id.title_text);
			holderView.timeTextView=(TextView)convertView.findViewById(R.id.time_text);
			holderView.placeTextView=(TextView)convertView.findViewById(R.id.place_text);
			holderView.schoolTextView=(TextView)convertView.findViewById(R.id.school_text);

			convertView.setTag(holderView);
			
		}
		else
		{
			holderView =(HolderView) convertView.getTag();
		}
		
		holderView.titleTextView.setText(listItems.get(position).get("title").toString());
		holderView.timeTextView.setText(listItems.get(position).get("time").toString());
		holderView.placeTextView.setText(listItems.get(position).get("place").toString());
		holderView.schoolTextView.setText(listItems.get(position).get("school").toString());


		return convertView;
	}

}
