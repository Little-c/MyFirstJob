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

public class AnswerAdapter extends BaseAdapter
{
	private  List<Map<String, Object>> listItems;
	private LayoutInflater mInfalter;
	Context context;
	
	
	public AnswerAdapter(Context context,List<Map<String, Object>> listItems){
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
			
     	TextView nameTextView,timeTextView,answerTextView;
     	
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
        HolderView holderView;
		
		if(convertView == null)
		{
			holderView = new HolderView();
			convertView = mInfalter.inflate(R.layout.item_answer_listview, null);
			holderView.nameTextView=(TextView)convertView.findViewById(R.id.name_text);
			holderView.timeTextView=(TextView)convertView.findViewById(R.id.time_text);
			holderView.answerTextView=(TextView)convertView.findViewById(R.id.answer_text);
			

			convertView.setTag(holderView);
			
		}
		else
		{
			holderView =(HolderView) convertView.getTag();
		}
		
		holderView.nameTextView.setText(listItems.get(position).get("name").toString());
		holderView.timeTextView.setText(listItems.get(position).get("time").toString());
		holderView.answerTextView.setText(listItems.get(position).get("answer").toString());


		return convertView;
	}

}
