package com.ch.qinyicamera;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.ch.qinyicamera.bean.Item;
import com.ch.qinyicamera.bean.LocalItem;

import android.content.Context;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.trinea.android.common.util.ToastUtils;

public class DataSynchronize {

	public static void downloadData(final Context context, final SynchronizeCallback callback) {
		BmobQuery<Item> query = new BmobQuery<Item>();
		query.findObjects(context, new FindListener<Item>() {
			
			@Override
			public void onSuccess(List<Item> itemList) {
				List<LocalItem> localItemList = new ArrayList<>();
				for(Item bmobItem:itemList) {
					LocalItem localItem = new LocalItem(bmobItem);
					localItemList.add(localItem);
				}
				if(localItemList.size()>0) {
					DataSupport.saveAll(localItemList);
				}
				callback.synchronizeSuccess();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				callback.synchronizeFail();
				ToastUtils.show(context, "查询数据异常");
			}
		});
	}
	
	interface SynchronizeCallback {
		void synchronizeSuccess();
		void synchronizeFail();
	}
	
	
	
	public static void savePrintImage() {
		
	}
	
}
