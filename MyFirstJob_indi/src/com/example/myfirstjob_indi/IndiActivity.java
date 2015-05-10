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
        texts = new String[]{ "������Ϣ", "�ҵ��ղ�",
                "��ע����", "��Ƹ����", 
                "�û�����", "�Ƽ����",
                "�������", "�������","�������"};

        GridView gridview = (GridView) findViewById(R.id.individualGridView);
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 9; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", images[i]);
            map.put("itemText", texts[i]);
            lstImageItem.add(map);
        }
        
        SimpleAdapter saImageItems = new SimpleAdapter(this, 
                lstImageItem,// ����Դ
                R.layout.activity_indiitem,// ��ʾ����
                new String[] { "itemImage", "itemText" }, 
                new int[] { R.id.imageItem, R.id.imageItemTitle }); 
        gridview.setAdapter(saImageItems);
        gridview.setOnItemClickListener(new ItemClickListener());
    }

    class ItemClickListener implements OnItemClickListener {
        /**
         * �����ʱ�����¼�
         * 
         * @param parent  �������������AdapterView
         * @param view ��AdapterView�б��������ͼ(������adapter�ṩ��һ����ͼ)��
         * @param position ��ͼ��adapter�е�λ�á�
         * @param rowid �����Ԫ�ص���id��
         */
        public void onItemClick(AdapterView<?> parent, View view, int position, long rowid) {
            HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
            //��ȡ����Դ������ֵ
            String itemText=(String)item.get("itemText");
            Object object=item.get("itemImage");
            Toast.makeText(IndiActivity.this, itemText, Toast.LENGTH_LONG).show();
            
            //����ͼƬ������Ӧ����ת
            switch (images[position]) {
            case R.drawable.img1:
                startActivity(new Intent(IndiActivity.this, TestActivity.class));//������һ��Activity
                finish();//������Activity���ɻ���
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