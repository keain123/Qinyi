package com.ch.qinyicamera;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.DownloadListener;
import com.ch.qinyicamera.DataSynchronize.SynchronizeCallback;
import com.ch.qinyicamera.bean.LocalItem;
import com.rey.material.widget.FloatingActionButton;
import com.rey.material.widget.TextView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.common.util.ToastUtils;

public class MainActivity extends Activity {

	@Bind(R.id.item_list_view)
	ListView mListView;
	
	@Bind(R.id.fab_add)
	FloatingActionButton fabAdd;

	final int CODE_CAMERA = 0x333;
	ItemAdapter mAdapter;
	String sdCardPath;
	String filePath;
	File file;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	List<LocalItem> mItemList;
	int actionBarHeight;
	Options opts;
	int p = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		this.opts = new Options();
		opts.inSampleSize = 3;
		initListView();
		initData();
//		initPopupListView();
	}

	private void initListView() {
		mListView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LocalItem item = mItemList.get(position);
				Intent intent = new Intent(MainActivity.this, CameraActivity.class);
				intent.putExtra("item", item);
				startActivity(intent);
				finish();
				
			}
		});
	}

	private void initData() {
		mItemList = DataSupport.findAll(LocalItem.class);
		if(mItemList==null||mItemList.size()==0) {
			DataSynchronize.downloadData(getApplicationContext(), new SynchronizeCallback() {
				
				@Override
				public void synchronizeSuccess() {
					mAdapter = new ItemAdapter(getApplicationContext(), mItemList);
					mListView.setAdapter(mAdapter);
					mAdapter.notifyDataSetChanged();
					ToastUtils.show(getApplicationContext(), "notify");
				}
				
				@Override
				public void synchronizeFail() {
				}
			});
		} else {
			mAdapter = new ItemAdapter(getApplicationContext(), mItemList);
			mListView.setAdapter(mAdapter);
		}

	}


	@OnClick(R.id.fab_add)
	void addItem() {
		Intent intent = new Intent(this, CameraActivity.class);
		startActivity(intent);
		finish();
	}
	
	
	
	class ItemAdapter extends BaseAdapter {
		Context mContext;
		List<LocalItem> mItemList;
		LayoutInflater mInflater;
		Options opts;
		public ItemAdapter(Context c, List<LocalItem> l) {
			if(c==null||l==null) {
				throw new IllegalArgumentException("argument can not be null");
			}
			this.mInflater = LayoutInflater.from(c);
			this.mContext = c;
			this.mItemList = l;
			this.opts = new Options();
			opts.inSampleSize = 3;
		}

		@Override
		public int getCount() {
			return mItemList.size();
		}

		@Override
		public LocalItem getItem(int position) {
			return mItemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return mItemList.get(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if(convertView == null) {
				convertView = mInflater.inflate(R.layout.popup_view_item, parent, false);
				holder = new ViewHolder();
				holder.tvDesc = (TextView) convertView.findViewById(R.id.item_desc);
				holder.ivPhoto = (ImageView) convertView.findViewById(R.id.photo);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			LocalItem item = getItem(position);
			if(item != null) {
				holder.tvDesc.setText(item.toString());
				
				String filePath = item.getLocalThumbFilePath();
				File file = new File(filePath);
				if(file.exists()) {
					Bitmap bmp = BitmapFactory.decodeFile(filePath, opts);
					holder.ivPhoto.setImageBitmap(bmp);
					
				} else {
					// 从BMOB下载文件
					BmobProFile.getInstance(mContext).download(item.getThumbFileName(), new DownloadListener() {
						
						@Override
						public void onError(int arg0, String msg) {
							ToastUtils.show(mContext, "下载出错:"+msg);
						}
						
						@Override
						public void onSuccess(String filePath) {
							Bitmap bmp = BitmapFactory.decodeFile(filePath, opts);
							holder.ivPhoto.setImageBitmap(bmp);
						}
						
						@Override
						public void onProgress(String arg0, int arg1) {
							
						}
					});
				}
			}
			return convertView;
		}
		
		
	}
	
	class ViewHolder {
		ImageView ivPhoto;
		TextView tvDesc;
	}

}
