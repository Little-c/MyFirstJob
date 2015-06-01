package com.example.myfirstjob;

import java.io.File;
import java.util.ArrayList;





import java.util.HashMap;
import java.util.Map;

import custom_adapter.OptionsAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.R.integer;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Recruit extends Activity implements Callback 
{
		//PopupWindow对象
		private PopupWindow shengSelectPopupWindow= null;
		private PopupWindow shiSelectPopupWindow= null;
		private PopupWindow xiaoSelectPopupWindow= null;
		//自定义Adapter
		private OptionsAdapter shengOptionsAdapter = null;
		private OptionsAdapter shiOptionsAdapter = null;
		private OptionsAdapter xiaoOptionsAdapter = null;
		
		//下拉框选项数据源
		private ArrayList<String> shengDatas = new ArrayList<String>();
		private ArrayList<String> shiDatas = new ArrayList<String>();
		private ArrayList<String> xiaoDatas = new ArrayList<String>();
		//下拉框依附组件
		private LinearLayout shengLay;
		private LinearLayout shiLay;
		private LinearLayout xiaoLay;
		//下拉框依附组件宽度，也将作为下拉框的宽度
		private int shengWidth; 
		private int shiWidth; 
		private int xiaoWidth; 
		
		
		//展示所有下拉选项的ListView
		private ListView shengListView = null; 
		private ListView shiListView = null; 
		private ListView xiaoListView = null; 
		
		//用来处理选中或者删除下拉项消息
		private Handler shengHandler;
		private Handler shiHandler;
		private Handler xiaoHandler;
		//是否初始化完成标志  
		private boolean flag = false;
		
		private TextView shengTextView;
		private TextView shiTextView;
		private TextView xiaoTextView;
		
		private SQLiteDatabase db;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recruit);
		
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		while(!flag){
			initWedget();
			flag = true;
		}
		
	}
	
	/**
	 * 初始化界面控件
	 */
	private void initWedget()
	{
		//初始化Handler,用来处理消息
		shengHandler = new Handler(Recruit.this);
		shiHandler = new Handler(Recruit.this);
		xiaoHandler = new Handler(Recruit.this);
		
		
		//初始化界面组件
		shengLay = (LinearLayout)findViewById(R.id.sheng_lay);
		shengTextView=(TextView)findViewById(R.id.sheng_text);
		
		shiLay = (LinearLayout)findViewById(R.id.shi_lay);
		shiTextView=(TextView)findViewById(R.id.shi_text);
		
		xiaoLay = (LinearLayout)findViewById(R.id.xiao_lay);
		xiaoTextView=(TextView)findViewById(R.id.xiao_text);
		
		
		shengLay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shengPopupWindwShowing();
			}
		});
		
		shiLay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shiPopupWindwShowing();
			}
		});
		
		xiaoLay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				xiaoPopupWindwShowing();
			}
		});
		
		
		
		//获取下拉框依附的组件宽度
        int width = shengLay.getWidth();
        shengWidth = width;
        
        
        
       
        
        //初始化PopupWindow
        initPopuWindow();
        
     
	}
	
	/**
     * 初始化PopupWindow
     */ 
    private void initPopuWindow()
    { 
    	
    	initDatas();
    	//PopupWindow浮动下拉框布局
        View shengWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        shengListView = (ListView) shengWindow.findViewById(R.id.list);         
        //设置自定义Adapter
        shengOptionsAdapter = new OptionsAdapter(this,shengHandler,shengDatas);  
        shengListView.setAdapter(shengOptionsAdapter);         
        shengSelectPopupWindow = new PopupWindow(shengWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        shengSelectPopupWindow.setOutsideTouchable(true);         
        shengSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
        
        
        View shiWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        shiListView = (ListView) shiWindow.findViewById(R.id.list);         
        //设置自定义Adapter
        shiOptionsAdapter = new OptionsAdapter(this,shiHandler,shiDatas);  
        shiListView.setAdapter(shiOptionsAdapter);         
        shiSelectPopupWindow = new PopupWindow(shiWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        shiSelectPopupWindow.setOutsideTouchable(true);         
        shiSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
        
        View xiaoWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        xiaoListView = (ListView) xiaoWindow.findViewById(R.id.list);         
        //设置自定义Adapter
        xiaoOptionsAdapter = new OptionsAdapter(this,xiaoHandler,xiaoDatas);  
        xiaoListView.setAdapter(xiaoOptionsAdapter);         
        xiaoSelectPopupWindow = new PopupWindow(xiaoWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        xiaoSelectPopupWindow.setOutsideTouchable(true);         
        xiaoSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
    	
    	
    } 
    
    /**
	 * 初始化填充Adapter所用List数据
	 */
	private void initDatas()
	{
		
	     shengDatas.clear();
		 querySqlite();
         

         shiDatas.clear();		 
         shiDatas.add("北京");
         shiDatas.add("上海");
         shiDatas.add("广州");
         shiDatas.add("深圳");
         shiDatas.add("重庆");
         shiDatas.add("青岛");
         shiDatas.add("石家庄");
         
         xiaoDatas.clear();		 
         xiaoDatas.add("北京");
         xiaoDatas.add("上海");
         xiaoDatas.add("广州");
         xiaoDatas.add("深圳");
         xiaoDatas.add("重庆");
         xiaoDatas.add("青岛");
         xiaoDatas.add("石家庄");
         
         
	}
    
    /**
     * 显示PopupWindow窗口
     * 
     * @param popupwindow
     */ 
    public void shengPopupWindwShowing() { 
       //将selectPopupWindow作为parent的下拉框显示，并指定selectPopupWindow在Y方向上向上偏移3pix，
       //这是为了防止下拉框与文本框之间产生缝隙，影响界面美化
       //（是否会产生缝隙，及产生缝隙的大小，可能会根据机型、Android系统版本不同而异吧，不太清楚）
       shengSelectPopupWindow.showAsDropDown(shengLay,0,-3); 
       
       
    } 
    
    public void shiPopupWindwShowing() {
    	shiSelectPopupWindow.showAsDropDown(shiLay,0,-3);
	}
    
    public void xiaoPopupWindwShowing() {
    	xiaoSelectPopupWindow.showAsDropDown(xiaoLay,0,-3);
	}
    
    /**
     * PopupWindow消失
     */ 
    public void dismiss(){ 
        shengSelectPopupWindow.dismiss(); 
        
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public boolean handleMessage(Message message)
	{
		// TODO Auto-generated method stub
		Bundle data = message.getData();
		switch(message.what)
		{
			case 1:
				//选中下拉项，下拉框消失
				int selIndex = data.getInt("selIndex");
				shengTextView.setText(shengDatas.get(selIndex));
				shengSelectPopupWindow.dismiss();
				break;
			
		}
		return false;
	}
	
	private void querySqlite()
	{
		 File cacheDir;
	     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//判断是否存在sd卡 
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/db");  //在sd卡的根目录上创建文件"LazyList"         
	     else  
            cacheDir = this.getCacheDir();      
	     if (cacheDir.exists()) //若目录 存在
	     {
	    	 
	    	 
	    	 db=SQLiteDatabase.openOrCreateDatabase(cacheDir+"/FirstJob.db", null);
	    	 Cursor cursor=db.rawQuery("select sheng from Area group by sheng", null);
	    	 if(cursor.getCount()<1)
	    	 {
	    	 }
	    	 else 
	    	 {
	    		 
	    		 while(cursor.moveToNext())
	    		 {
	    			 shengDatas.add( cursor.getString(0));
	    		 }
	    		
	    	 }
	    	 
	     }
	     else {
			System.out.println("b");
		}

		 
	}

}
