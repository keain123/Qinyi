package com.ch.qinyicamera;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ch.qinyicamera.bean.LocalItem;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.common.util.ToastUtils;

public class PhotoActivity extends Activity {

	@Bind(R.id.photo)
	ImageView ivPhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		ButterKnife.bind(this);
		
		LocalItem item = (LocalItem) getIntent().getSerializableExtra("item");
		Bitmap bitmapWithInfo = BitmapUtils.makeupBitmapWithInfo(this, item);
		if(bitmapWithInfo!=null) {
			ivPhoto.setImageBitmap(bitmapWithInfo);
			
		}
	}
	
	private void saveBitmapWithInfo(String filePath, Bitmap bmp) {
		File file = new File(filePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				ToastUtils.show(getApplicationContext(), "创建信息图片异常:"+e.getMessage());
				e.printStackTrace();
				return;
			}
		}
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bmp.compress(CompressFormat.JPEG, 60, bos);
		} catch (FileNotFoundException e) {
			ToastUtils.show(getApplicationContext(), "写文件异常:"+e.getMessage());
			e.printStackTrace();
		}
	}
}
