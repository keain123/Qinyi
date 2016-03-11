package com.ch.qinyicamera;

import com.ch.qinyicamera.bean.LocalItem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.WindowManager;
import android.webkit.WebSettings.TextSize;
import cn.trinea.android.common.util.StringUtils;
import cn.trinea.android.common.util.ToastUtils;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class BitmapUtils {
	
	public static Bitmap makeupBitmapWithInfo(Context context, LocalItem item) {
		if(item==null||StringUtils.isBlank(item.getLocalFilePath())) {
			ToastUtils.show(context, "item为空或本地文件路径为空");
			return null;
		}
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int screenWidth = wm.getDefaultDisplay().getWidth();
		int screenHeight = wm.getDefaultDisplay().getHeight();
		
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(item.getLocalFilePath(), opts);
		int photoWidth = opts.outWidth;
		int photoHeight = opts.outHeight;
		float targetWidth;
		float targetHeight;
		if(photoWidth >= photoHeight) {
			targetWidth = 768;//photoWidth
			targetHeight = photoHeight * (768f / photoWidth);
		} else {
			targetWidth = 768;//photoHeight;
			targetHeight = (float)((float)photoWidth/photoHeight) * (768f);
		}
		opts.outWidth = (int)targetWidth;
		opts.outHeight = (int)targetHeight;
		opts.inJustDecodeBounds = false;
		Bitmap background = Bitmap.createBitmap(768, 1024, Bitmap.Config.ARGB_4444);
		Bitmap photo = BitmapFactory.decodeFile(item.getLocalFilePath(), opts);
		Canvas canvas = new Canvas(background);
		canvas.drawBitmap(photo, null, new Rect(0, 0, (int)targetWidth, (int)targetHeight), null);
		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		paint.setTextSize(28);
		String[] strs = item.toString().split("\n");
		int position = (int)targetHeight+100;
		for(String str : strs) {
			canvas.drawText(str, 30, position, paint);
			position+=50;
		}
		canvas.save();
		canvas.restore();
		
		return background;
	}

}
