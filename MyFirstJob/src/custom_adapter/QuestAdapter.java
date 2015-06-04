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

public class QuestAdapter extends BaseAdapter
{
	private  List<Map<String, Object>> listItems;
	private LayoutInflater mInfalter;
	Context context;
	
	
	public QuestAdapter(Context context,List<Map<String, Object>> listItems){
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
			
     	TextView titleTextView,browerTextView,answerTextView;
     	
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
        HolderView holderView;
		
		if(convertView == null)
		{
			holderView = new HolderView();
			convertView = mInfalter.inflate(R.layout.item_quest_listview, null);
			holderView.titleTextView=(TextView)convertView.findViewById(R.id.title_text);
			holderView.browerTextView=(TextView)convertView.findViewById(R.id.brower_text);
			holderView.answerTextView=(TextView)convertView.findViewById(R.id.answer_text);
			

			convertView.setTag(holderView);
			
		}
		else
		{
			holderView =(HolderView) convertView.getTag();
		}
		
		holderView.titleTextView.setText(listItems.get(position).get("title").toString());
		holderView.browerTextView.setText("ä¯ÀÀ£º"+listItems.get(position).get("browse").toString());
		holderView.answerTextView.setText("»Ø´ð£º"+listItems.get(position).get("answer").toString());


		return convertView;
	}

}
