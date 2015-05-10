package com.example.myfirstjob_indi;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class IndiActivity extends Activity {
    private String texts[] = null;
    private int images[] = null;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indi);
        
        images=new int[]{R.drawable.img1, R.drawable.img2,
                R.drawable.img3, R.drawable.img4, 
                R.drawable.img5,R.drawable.img6, 
                R.drawable.img7,R.drawable.img8,R.drawable.img9};
        texts = new String[]{ "个人信息", "我的收藏",
                "关注问题", "招聘提醒", 
                "用户反馈", "推荐软件",
                "软件设置", "软件更新","关于软件"};

        GridView gridview = (GridView) findViewById(R.id.individualGridView);
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 9; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", images[i]);
            map.put("itemText", texts[i]);
            lstImageItem.add(map);
        }
        
        SimpleAdapter saImageItems = new SimpleAdapter(this, 
                lstImageItem,// 数据源
                R.layout.activity_indiitem,// 显示布局
                new String[] { "itemImage", "itemText" }, 
                new int[] { R.id.imageItem, R.id.imageItemTitle }); 
        gridview.setAdapter(saImageItems);
        gridview.setOnItemClickListener(new ItemClickListener());
    }

    class ItemClickListener implements OnItemClickListener {
        /**
         * 点击项时触发事件
         * 
         * @param parent  发生点击动作的AdapterView
         * @param view 在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
         * @param position 视图在adapter中的位置。
         * @param rowid 被点击元素的行id。
         */
        public void onItemClick(AdapterView<?> parent, View view, int position, long rowid) {
            HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
            //获取数据源的属性值
            String itemText=(String)item.get("itemText");
            Object object=item.get("itemImage");
            Toast.makeText(IndiActivity.this, itemText, Toast.LENGTH_LONG).show();
            
            //根据图片进行相应的跳转
            switch (images[position]) {
            case R.drawable.img1:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));//启动另一个Activity
                finish();//结束此Activity，可回收
                break;
            case R.drawable.img2:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            case R.drawable.img3:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            case R.drawable.img4:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            case R.drawable.img5:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            case R.drawable.img6:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            case R.drawable.img7:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            case R.drawable.img8:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            case R.drawable.img9:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));
                finish();
                break;
            }
            
        }
    }
}