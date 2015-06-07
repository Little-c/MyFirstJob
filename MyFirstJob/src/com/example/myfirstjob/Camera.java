package com.example.myfirstjob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import Util.PreferenceUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera extends Activity
{
	private ImageView closeImageView,takePicImageView,picImageView,userHeadImageView;
	private Bitmap photo=null;
	private String uploadBuffer;
	private File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
	private Button upButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.head_set);
		
		initView();
		
		
	}
	
	private void initView()
	{
		closeImageView=(ImageView)findViewById(R.id.close);
		takePicImageView=(ImageView)findViewById(R.id.take_pic);
		picImageView=(ImageView)findViewById(R.id.pic);
		userHeadImageView=(ImageView)findViewById(R.id.user_head);
		
		upButton=(Button)findViewById(R.id.upButton);
		setOnClick();
		
	}
	
	private void setOnClick() 
	{
		closeImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		 takePicImageView.setOnClickListener(new OnClickListener() 
	   	 {
				
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		            // ָ������������պ���Ƭ�Ĵ���·��
		            cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,
		                    Uri.fromFile(tempFile));
		            startActivityForResult(cameraintent, 1);
				}
			});
		
		picImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);  
				openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");  
				startActivityForResult(openAlbumIntent, 2); 
			}
		});
		
		upButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(photo!=null)
				{
					try {
						saveMyBitmap(PreferenceUtil.getUserID(Camera.this, "UserID"));
						sendBroadcast(new Intent("userHeadUpdate"));
						finish();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else 
				{
					Toast.makeText(Camera.this, "������ͷ��", 500).show();
				}
			}
		});
	}
	
	private String getPhotoFileName() 
	{
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
	
	private void startPhotoZoom(Uri uri) 
	 {
	        Intent intent = new Intent("com.android.camera.action.CROP");
	        intent.setDataAndType(uri, "image/*");
	        // cropΪtrue�������ڿ�����intent��������ʾ��view���Լ���
	        intent.putExtra("crop", "true");

	        // aspectX aspectY �ǿ�ߵı���
	        intent.putExtra("aspectX", 1);
	        intent.putExtra("aspectY", 1);

	        // outputX,outputY �Ǽ���ͼƬ�Ŀ��
	        intent.putExtra("outputX", 300);
	        intent.putExtra("outputY", 300);
	        intent.putExtra("return-data", true);
	        intent.putExtra("noFaceDetection", true);
	        startActivityForResult(intent, 3);
	 }

	    // �����м��ú��ͼƬ���ݵ���һ��������
	    private void sentPicToNext(Intent picdata)
	    {
	        Bundle bundle = picdata.getExtras();
	        if (bundle != null) {
	            photo = bundle.getParcelable("data");
	            if (photo==null) 
	            {
	                userHeadImageView.setImageResource(R.drawable.head);
	            }
	            else 
	            {
	                userHeadImageView.setImageBitmap(photo);
//	                            �����ı�����Ϊ    ͼƬ����·��������
	               // text.setText(tempFile.getAbsolutePath());
	            }
         
	        }
	    }
	    
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	    {
	        switch (requestCode) {
	        case 1:// ��ѡ������ʱ����
	            startPhotoZoom(Uri.fromFile(tempFile));
	            break;
	        case 2:// ��ѡ��ӱ��ػ�ȡͼƬʱ
	            // ���ǿ��жϣ������Ǿ��ò����������¼��õ�ʱ��㲻�ᱨ�쳣����ͬ
	            if (data != null)
	                startPhotoZoom(data.getData());
	            break;
	        case 3:// ���صĽ��
	            if (data != null)
	                // setPicToView(data);
	                sentPicToNext(data);
	            break;
	        }
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	    
	    private String queryFile() 
	    {
	    	//����sd��·��
	        File cacheDir;
		     if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED))//�ж��Ƿ����sd�� 
	           cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "MyFirstJob/img");  //��sd���ĸ�Ŀ¼�ϴ����ļ�"LazyList"         
		     else  
	           cacheDir = this.getCacheDir();      
		     if (!cacheDir.exists()) //�������ڣ�����Ŀ¼ 
	           cacheDir.mkdirs(); 
	        
		    return cacheDir.toString()+"/";
		}
	    
	    private void saveMyBitmap(String bitName) throws IOException 
	    {  
	        File f = new File(queryFile() + bitName + ".png");  
	        f.createNewFile();  
	        FileOutputStream fOut = null;  
	        try {  
	                fOut = new FileOutputStream(f);  
	        } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	        }  
	        photo.compress(Bitmap.CompressFormat.PNG, 100, fOut);  
	        try {  
	                fOut.flush();  
	        } catch (IOException e) {  
	                e.printStackTrace();  
	        }  
	        try {  
	                fOut.close();  
	        } catch (IOException e) {  
	                e.printStackTrace();  
	        }  
	    }  
	    
	    

}
