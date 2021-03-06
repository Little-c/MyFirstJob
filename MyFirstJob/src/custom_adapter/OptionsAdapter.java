package custom_adapter;

import java.util.ArrayList;

import com.example.myfirstjob.R;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//自定义适配器Adapter
public class OptionsAdapter extends BaseAdapter {

	private ArrayList<String> list = new ArrayList<String>(); 
    private Activity activity = null; 
	private Handler handler;
	private int k;
    
	/**
	 * 自定义构造方法
	 * @param activity
	 * @param handler
	 * @param list
	 */
    public OptionsAdapter(Activity activity,Handler handler,ArrayList<String> list,int k){
    	this.activity = activity;
    	this.handler = handler;
    	this.list = list;
    	this.k=k;
    }
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder = null; 
        if (convertView == null) { 
            holder = new ViewHolder(); 
            //下拉项布局
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_popuwindow, null); 
            holder.textView = (TextView) convertView.findViewById(R.id.item_text); 
            
            convertView.setTag(holder); 
        } else { 
            holder = (ViewHolder) convertView.getTag(); 
        } 
        
        holder.textView.setText(list.get(position));
        
        //为下拉框选项文字部分设置事件，最终效果是点击将其文字填充到文本框
        holder.textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Message msg = new Message();
				Bundle data = new Bundle();
				//设置选中索引
				data.putInt("selIndex", position);
				msg.setData(data);
				msg.what = k;
				//发出消息
				handler.sendMessage(msg);
			}
		});
        
        
        
        return convertView; 
	}

}


class ViewHolder { 
    TextView textView; 
  
}
