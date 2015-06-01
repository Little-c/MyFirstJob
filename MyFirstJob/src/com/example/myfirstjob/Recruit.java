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
    	
    	initDatas();
    	//PopupWindow���������򲼾�
        View shengWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        shengListView = (ListView) shengWindow.findViewById(R.id.list);         
        //�����Զ���Adapter
        shengOptionsAdapter = new OptionsAdapter(this,shengHandler,shengDatas);  
        shengListView.setAdapter(shengOptionsAdapter);         
        shengSelectPopupWindow = new PopupWindow(shengWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        shengSelectPopupWindow.setOutsideTouchable(true);         
        shengSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
        
        
        View shiWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        shiListView = (ListView) shiWindow.findViewById(R.id.list);         
        //�����Զ���Adapter
        shiOptionsAdapter = new OptionsAdapter(this,shiHandler,shiDatas);  
        shiListView.setAdapter(shiOptionsAdapter);         
        shiSelectPopupWindow = new PopupWindow(shiWindow, shengWidth,LayoutParams.WRAP_CONTENT, true);         
        shiSelectPopupWindow.setOutsideTouchable(true);         
        shiSelectPopupWindow.setBackgroundDrawable(new BitmapDrawable());  
        
        View xiaoWindow = (View)this.getLayoutInflater().inflate(R.layout.options, null); 
        xiaoListView = (ListView) xiaoWindow.findViewById(R.id.list);         
        //�����Զ���Adapter
        xiaoOptionsAdapter = new OptionsAdapter(this,xiaoHandler,xiaoDatas);  
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
		 querySqlite();
         

         shiDatas.clear();		 
         shiDatas.add("����");
         shiDatas.add("�Ϻ�");
         shiDatas.add("����");
         shiDatas.add("����");
         shiDatas.add("����");
         shiDatas.add("�ൺ");
         shiDatas.add("ʯ��ׯ");
         
         xiaoDatas.clear();		 
         xiaoDatas.add("����");
         xiaoDatas.add("�Ϻ�");
         xiaoDatas.add("����");
         xiaoDatas.add("����");
         xiaoDatas.add("����");
         xiaoDatas.add("�ൺ");
         xiaoDatas.add("ʯ��ׯ");
         
         
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
				break;
			
		}
		return false;
	}
	
	private void querySqlite()
	{
		 File cacheDir;
	     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//�ж��Ƿ����sd�� 
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/db");  //��sd���ĸ�Ŀ¼�ϴ����ļ�"LazyList"         
	     else  
            cacheDir = this.getCacheDir();      
	     if (cacheDir.exists()) //��Ŀ¼ ����
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
