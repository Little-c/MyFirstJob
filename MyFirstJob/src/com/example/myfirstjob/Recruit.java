package com.example.myfirstjob;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import custom_adapter.OptionsAdapter;
import custom_adapter.RecruitAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		//PopupWindow����
		private PopupWindow shengSelectPopupWindow= null;
		private PopupWindow shiSelectPopupWindow= null;
		private PopupWindow xiaoSelectPopupWindow= null;
		//�Զ���Adapter
		private OptionsAdapter shengOptionsAdapter = null;
		private OptionsAdapter shiOptionsAdapter = null;
		private OptionsAdapter xiaoOptionsAdapter = null;
		
		//������ѡ������Դ
		private ArrayList<String> shengDatas = new ArrayList<String>();
		private ArrayList<String> shiDatas = new ArrayList<String>();
		private ArrayList<String> xiaoDatas = new ArrayList<String>();
		//�������������
		private LinearLayout shengLay;
		private LinearLayout shiLay;
		private LinearLayout xiaoLay;
		//���������������ȣ�Ҳ����Ϊ������Ŀ��
		private int shengWidth; 
		private int shiWidth; 
		private int xiaoWidth; 
		
		
		//չʾ��������ѡ���ListView
		private ListView shengListView = null; 
		private ListView shiListView = null; 
		private ListView xiaoListView = null; 
		
		//��������ѡ�л���ɾ����������Ϣ
		private Handler shengHandler;
		private Handler shiHandler;
		private Handler xiaoHandler;
		//�Ƿ��ʼ����ɱ�־  
		private boolean flag = false;
		
		private TextView shengTextView;
		private TextView shiTextView;
		private TextView xiaoTextView;
		
		private SQLiteDatabase db;
		
		private ListView listViewRecruit;
		private RecruitAdapter recruitAdapter;
		ArrayList<String> titles=new ArrayList<String>();
		ArrayList<String> times=new ArrayList<String>();
		ArrayList<String> places=new ArrayList<String>();
		ArrayList<String> schools=new ArrayList<String>();
		ArrayList<String> recruitIDs=new ArrayList<String>();
		List<Map<String, Object>> listItems;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recruit);

		initView();
		
	}
	
	private void initView() 
	{
		listViewRecruit=(ListView)findViewById(R.id.list_recruit);
		
		listViewRecruit.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent skipIntent=new Intent(Recruit.this,RecruitDetails.class);
				skipIntent.putExtra("recruitID", recruitIDs.get(arg2));
				Recruit.this.startActivity(skipIntent);
				
			}
		});
		
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
	 * ��ʼ������ؼ�
	 */
	private void initWedget()
	{
		//��ʼ��Handler,����������Ϣ
		shengHandler = new Handler(Recruit.this);
		shiHandler = new Handler(Recruit.this);
		xiaoHandler = new Handler(Recruit.this);
		
		
		//��ʼ���������
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
		
		
		
		//��ȡ������������������
        int width = shengLay.getWidth();
        shengWidth = width;

        //��ʼ��PopupWindow
        initPopuWindow();
        
     
	}
	
	/**
     * ��ʼ��PopupWindow
     */ 
    private void initPopuWindow()
    { 
    	sqlite();
		queryRecruits();
    	initDatas();
    	//PopupWindow���������򲼾�
        View shengWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        shengListView = (ListView) shengWindow.findViewById(R.id.list);    
        //�����Զ���Adapter
        shengOptionsAdapter = new OptionsAdapter(this,shengHandler,shengDatas,1);  
        shengListView.setAdapter(shengOptionsAdapter);         
        shengSelectPopupWindow = new PopupWindow(shengWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        shengSelectPopupWindow.setOutsideTouchable(true);         
        shengSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
        
        
        View shiWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        shiListView = (ListView) shiWindow.findViewById(R.id.list);         
        //�����Զ���Adapter
        shiOptionsAdapter = new OptionsAdapter(this,shiHandler,shiDatas,2);  
        shiListView.setAdapter(shiOptionsAdapter);         
        shiSelectPopupWindow = new PopupWindow(shiWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        shiSelectPopupWindow.setOutsideTouchable(true);         
        shiSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
        
        View xiaoWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        xiaoListView = (ListView) xiaoWindow.findViewById(R.id.list);         
        //�����Զ���Adapter
        xiaoOptionsAdapter = new OptionsAdapter(this,xiaoHandler,xiaoDatas,3);  
        xiaoListView.setAdapter(xiaoOptionsAdapter);         
        xiaoSelectPopupWindow = new PopupWindow(xiaoWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        xiaoSelectPopupWindow.setOutsideTouchable(true);         
        xiaoSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
        
    	
    } 
    
    /**
	 * ��ʼ�����Adapter����List����
	 */
	private void initDatas()
	{
		
	     shengDatas.clear();
	     shiDatas.clear();
	     xiaoDatas.clear();
		 querySqlite();   
         
	}
    
    /**
     * ��ʾPopupWindow����
     * 
     * @param popupwindow
     */ 
    public void shengPopupWindwShowing() { 
       //��selectPopupWindow��Ϊparent����������ʾ����ָ��selectPopupWindow��Y����������ƫ��3pix��
       //����Ϊ�˷�ֹ���������ı���֮�������϶��Ӱ���������
       //���Ƿ�������϶����������϶�Ĵ�С�����ܻ���ݻ��͡�Androidϵͳ�汾��ͬ����ɣ���̫�����
       shengSelectPopupWindow.showAsDropDown(shengLay,0,-3); 
       
       
    } 
    
    public void shiPopupWindwShowing() {
    	shiSelectPopupWindow.showAsDropDown(shiLay,0,-3);
	}
    
    public void xiaoPopupWindwShowing() {
    	xiaoSelectPopupWindow.showAsDropDown(xiaoLay,0,-3);
	}
    
    /**
     * PopupWindow��ʧ
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
				//ѡ���������������ʧ
				int selIndex = data.getInt("selIndex");
				shengTextView.setText(shengDatas.get(selIndex));
				shengSelectPopupWindow.dismiss();
				
				initDatas();
				shiTextView.setText(shiDatas.get(0));
				initDatas();
				xiaoTextView.setText(xiaoDatas.get(0));
				queryRecruits();
				
				break;
			case 2:
				//ѡ���������������ʧ
				shiTextView.setText(shiDatas.get(data.getInt("selIndex")));
				shiSelectPopupWindow.dismiss();				
				initDatas();
				xiaoTextView.setText(xiaoDatas.get(0));
				queryRecruits();
				break;
			case 3:
				//ѡ���������������ʧ
				xiaoTextView.setText(xiaoDatas.get(data.getInt("selIndex")));
				xiaoSelectPopupWindow.dismiss();
				queryRecruits();
				break;
				
			
		}
		return false;
	}
	
	private void sqlite()
	{
		File cacheDir;
	     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//�ж��Ƿ����sd�� 
           cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/db");  //��sd���ĸ�Ŀ¼�ϴ����ļ�"LazyList"         
	     else  
           cacheDir = this.getCacheDir();      
	     if (cacheDir.exists()) //��Ŀ¼ ����
	     {
	    	 
	    	 if(db==null)
	    		 db=SQLiteDatabase.openOrCreateDatabase(cacheDir+"/FirstJob.db", null);
	     }
	}
	
	private void querySqlite()
	{
		 
	    	 Cursor cursor=db.rawQuery("select sheng from Area group by sheng", null);
	    	 Cursor shiCursor=db.rawQuery("select shi from Area where sheng='"+shengTextView.getText().toString()+"' group by shi", null);
	    	 Cursor xiaoCursor=db.rawQuery("select xiao from Area where shi='"+shiTextView.getText().toString()+"' group by xiao", null);
	    	 if(cursor.getCount()>0)
	    	 {
	    		
	    		 while(cursor.moveToNext())
	    		 {
	    			 shengDatas.add( cursor.getString(0));
	    		 }
	    		
	    	 }
	    	 if(shiCursor.getCount()>0)
	    	 {
	    		 while(shiCursor.moveToNext())
	    		 {
	    			 shiDatas.add(shiCursor.getString(0));
	    		 }
	    	 }
	    	 if(xiaoCursor.getCount()>0)
	    	 {
	    		 while(xiaoCursor.moveToNext())
	    		 {
	    			 xiaoDatas.add(xiaoCursor.getString(0));
	    		 }
	    	 }

	}
	
	private void queryRecruits() 
	{
		listItems=new ArrayList<Map<String,Object>>();
		titles.clear();
		times.clear();
		places.clear();
		schools.clear();
		recruitIDs.clear();
		Cursor cursor=db.rawQuery("select title,date,sheng,shi,xiao,ID from Recruit where xiao='"+xiaoTextView.getText().toString()+"' order by date desc", null);
		
		while(cursor.moveToNext())
		{
			titles.add(cursor.getString(0));
			times.add(cursor.getString(1));
			places.add(cursor.getString(2)+"-"+cursor.getString(3));
			schools.add(cursor.getString(4));
			recruitIDs.add(cursor.getString(5));
			
			Map<String, Object> listItem=new HashMap<String, Object>();
			listItem.put("title", titles.get(cursor.getPosition()));
			listItem.put("time", times.get(cursor.getPosition()));
			listItem.put("place", places.get(cursor.getPosition()));
			listItem.put("school", schools.get(cursor.getPosition()));
			listItem.put("recruitID", recruitIDs.get(cursor.getPosition()));
			
			
			listItems.add(listItem);
		}

		if (recruitAdapter == null) 
		{
			recruitAdapter=new RecruitAdapter(Recruit.this, listItems);
			listViewRecruit.setAdapter(recruitAdapter);
			
		}
		else 
		{
			recruitAdapter.updateView(listItems);
		}
	}

}
