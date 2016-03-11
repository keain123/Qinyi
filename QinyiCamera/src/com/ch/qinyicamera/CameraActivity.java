package com.ch.qinyicamera;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.DownloadListener;
import com.bmob.btp.callback.UploadListener;
import com.ch.qinyicamera.bean.Item;
import com.ch.qinyicamera.bean.LocalItem;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.EditText;
import com.rey.material.widget.RadioButton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.trinea.android.common.util.StringUtils;
import cn.trinea.android.common.util.ToastUtils;

public class CameraActivity extends Activity implements OnCheckedChangeListener {
	@Bind(R.id.photo)
	ImageView ivPhoto;
	final int CODE_CAMERA = 0x333;
	@Bind(R.id.et_item_name)
	EditText etItemName;
	@Bind(R.id.et_material)
	EditText etMaterial;
	@Bind(R.id.et_machine_number)
	EditText etMachineNo;
	@Bind(R.id.et_temperature)
	EditText etTemperature;
	@Bind(R.id.et_color)
	EditText etColor;
	@Bind(R.id.et_card_number)
	EditText etCardNo;
	@Bind(R.id.et_pressure)
	EditText etPressure;
	@Bind(R.id.et_pressure_speed)
	EditText etPressureSpeed;
	@Bind(R.id.et_pressure_time)
	EditText etPressureTime;

	@Bind(R.id.et_keep_pressure)
	EditText etKeepPressure;
	@Bind(R.id.et_keep_pressure_speed)
	EditText etKeepPressureSpeed;
	@Bind(R.id.et_keep_pressure_time)
	EditText etKeepPressureTime;
	@Bind(R.id.et_cool_down)
	EditText etCoolDown;
	@Bind(R.id.et_period)
	EditText etPeriod;
	@Bind(R.id.et_production_weight)
	EditText etProductionWeight;
	@Bind(R.id.et_store_material)
	EditText etStoreMaterial;
	@Bind(R.id.et_store_material_process)
	EditText etStoreMaterialProcess;

	@Bind(R.id.et_open_speed)
	EditText etOpenSpeed;
	@Bind(R.id.et_open_speed_process)
	EditText etOpenSpeedProcess;
	@Bind(R.id.et_prop_speed)
	EditText etPropSpeed;
	@Bind(R.id.et_prop_speed_count)
	EditText etPropSpeedCount;
	@Bind(R.id.et_desc)
	EditText etDesc;

	@Bind(R.id.rb_work_type_1)
	RadioButton rbWorkType1;
	@Bind(R.id.rb_work_type_2)
	RadioButton rbWorkType2;
	@Bind(R.id.rb_work_type_3)
	RadioButton rbWorkType3;

	int workType = 0;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	Dialog mLoadingDialog;
	TextView tvPercent;

	private String sdCardPath;
	private String localFilePath;
	private String localThumbFilePath;
	private String bmobFileName;
	private String bmobThumbFileName;
	private File file;
	private File thumbFile;

	private LocalItem mItem;

	private boolean isCapture;

	Options opts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		ButterKnife.bind(this);
		this.opts = new Options();
		opts.inSampleSize = 4;
		rbWorkType1.setOnCheckedChangeListener(this);
		rbWorkType2.setOnCheckedChangeListener(this);
		rbWorkType3.setOnCheckedChangeListener(this);

		mItem = (LocalItem) getIntent().getSerializableExtra("item");
		this.initProgressDialog();
		if (mItem != null) {
			this.loadItem();
			this.loadImage(mItem);
		} else {
			this.initNewFile();
			this.openCamera();
		}
	}

	private void loadItem() {
		etItemName.setText(mItem.getName());
		etMaterial.setText(mItem.getMaterial());
		etCardNo.setText(mItem.getCardNo());
		etTemperature.setText(mItem.getTemperature());
		etColor.setText(mItem.getColor());
		etMachineNo.setText(mItem.getMachineNo());
		etPressure.setText(mItem.getPressure());
		etPressureSpeed.setText(mItem.getPressureSpeed());
		etPressureTime.setText(mItem.getPressureTime());
		etKeepPressure.setText(mItem.getKeepPressure());
		etKeepPressureSpeed.setText(mItem.getKeepPressureSpeed());
		etKeepPressureTime.setText(mItem.getKeepPressureTime());
		etCoolDown.setText(mItem.getCooldown());
		etPeriod.setText(mItem.getPeriod());
		etProductionWeight.setText(mItem.getProductionWeight());
		etStoreMaterial.setText(mItem.getStoreMaterial());
		etStoreMaterialProcess.setText(mItem.getStoreProcess());
		etOpenSpeed.setText(mItem.getOpenSpeed());
		etOpenSpeedProcess.setText(mItem.getOpenProcess());
		etPropSpeed.setText(mItem.getPropSpeed());
		etPropSpeedCount.setText(mItem.getPropCount());
		etDesc.setText(mItem.getDesc());
		workType = mItem.getWorkType();
		switch (workType) {
		case 1:
			rbWorkType1.setChecked(true);
			rbWorkType2.setChecked(false);
			rbWorkType3.setChecked(false);
			break;
		case 2:
			rbWorkType1.setChecked(false);
			rbWorkType2.setChecked(true);
			rbWorkType3.setChecked(false);
			break;
		case 3:
			rbWorkType1.setChecked(false);
			rbWorkType2.setChecked(false);
			rbWorkType3.setChecked(true);
			break;
		default:
			break;
		}
	}

	private void openCamera() {

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri uri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, CODE_CAMERA);
	}

	private void initProgressDialog() {
		Dialog.Builder builder = null;
		builder = new SimpleDialog.Builder(R.style.SimpleDialogLight) {
			@Override
			public void onPositiveActionClicked(DialogFragment fragment) {
				Toast.makeText(CameraActivity.this, "Agreed", Toast.LENGTH_SHORT).show();
				super.onPositiveActionClicked(fragment);
			}

			@Override
			public void onNegativeActionClicked(DialogFragment fragment) {
				Toast.makeText(CameraActivity.this, "Disagreed", Toast.LENGTH_SHORT).show();
				super.onNegativeActionClicked(fragment);
			}
		};

		builder.title("上传中....").contentView(R.layout.loading_progress);
		mLoadingDialog = builder.build(this);
		tvPercent = (TextView) mLoadingDialog.findViewById(R.id.tv_percent);
	}

	private void loadImage(LocalItem item) {
		file = new File(item.getLocalFilePath());
		if (file.exists()) {
			Bitmap bmp = BitmapFactory.decodeFile(item.getLocalFilePath(), opts);
			ivPhoto.setImageBitmap(bmp);

		} else {
			// 从BMOB下载文件
			BmobProFile.getInstance(getApplicationContext()).download(item.getFileName(), new DownloadListener() {

				@Override
				public void onError(int arg0, String msg) {
					ToastUtils.show(getApplicationContext(), "下载出错:" + msg);
				}

				@Override
				public void onSuccess(String filePath) {
					Bitmap bmp = BitmapFactory.decodeFile(filePath, opts);
					ivPhoto.setImageBitmap(bmp);
				}

				@Override
				public void onProgress(String arg0, int arg1) {

				}
			});
		}
	}

	private void initNewFile() {
		sdCardPath = this.checkFolderExists();
		localFilePath = sdCardPath + sdf.format(new Date()) + ".jpg";
		localThumbFilePath = sdCardPath + sdf.format(new Date()) + "_thumb.jpg";
		file = new File(localFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				ToastUtils.show(getApplicationContext(), "创建文件出错");
			}
		}

	}

	private String checkFolderExists() {
		String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/qinyi/image/";
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folderPath;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == CODE_CAMERA) {

			if (file.exists()) {
				Options opts = new Options();
				opts.inSampleSize = 3;
				isCapture = true;
				Bitmap bitmap = BitmapFactory.decodeFile(localFilePath, opts);
				if (bitmap != null) {
					ivPhoto.setImageBitmap(bitmap);
					BufferedOutputStream bos;
					BufferedOutputStream thumbBos;

					try {
						// 大图
						bos = new BufferedOutputStream(new FileOutputStream(file));
						bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);

						thumbFile = new File(localThumbFilePath);
						thumbBos = new BufferedOutputStream(new FileOutputStream(thumbFile));
						bitmap.compress(Bitmap.CompressFormat.JPEG, 10, thumbBos);

						bos.flush();
						bos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@OnClick(R.id.photo)
	void reCapture() {
//		this.initNewFile();
//		this.openCamera();
		if(mItem!=null) {
			Intent intent = new Intent(this, PhotoActivity.class);
			intent.putExtra("item", mItem);
			startActivity(intent);
		}
	}

	@OnClick(R.id.fab_save)
	void save() {
		if (StringUtils.isBlank(etItemName.getText().toString())) {
			ToastUtils.show(getApplicationContext(), "请先填写制品名称");
			return;
		}
		if (StringUtils.isBlank(etMaterial.getText().toString())) {
			ToastUtils.show(getApplicationContext(), "请先填写制品材料");
			return;
		}
		if (StringUtils.isBlank(etMachineNo.getText().toString())) {
			ToastUtils.show(getApplicationContext(), "请先填写机台号");
			return;
		}
		if (mItem != null) {
			// 已存在，修改并保存
			mLoadingDialog.show();
			// 是否重新拍照
			if (isCapture && file.exists()) {
				mLoadingDialog.show();
				uploadImage();
			} else {
				saveBmobData();
			}
		} else if (file.exists()) {
			// 新增
			mLoadingDialog.show();
			uploadImage();

		}

	}

	private void uploadImage() {
		BmobProFile.getInstance(getApplicationContext()).upload(localFilePath, new UploadListener() {

			@Override
			public void onError(int arg0, String errorMsg) {
				mLoadingDialog.dismiss();
				ToastUtils.show(getApplicationContext(), "上传文件失败:" + errorMsg);
			}

			@Override
			public void onSuccess(String fileName, String url, BmobFile arg2) {
				bmobFileName = fileName;
				localFilePath = sdCardPath + fileName;
				file.renameTo(new File(localFilePath));
				ToastUtils.show(getApplicationContext(), "图片上传完毕");
				uploadThumbImage();
			}

			@Override
			public void onProgress(int percent) {
				tvPercent.setText(percent + "%");
			}
		});
	}

	private void saveBmobData() {
		if (mItem == null && !StringUtils.isBlank(bmobFileName) && !StringUtils.isBlank(bmobThumbFileName)) {
			// 新增
			Date createTime = new Date();
			final Item bmobItem = new Item();
			bmobItem.setCreateTime(createTime);
			bmobItem.setDesc(etDesc.getText().toString());
			bmobItem.setMaterial(etMaterial.getText().toString());
			bmobItem.setMachineNo(etMachineNo.getText().toString());
			bmobItem.setTemperature(etTemperature.getText().toString());
			bmobItem.setColor(etColor.getText().toString());
			bmobItem.setCardNo(etCardNo.getText().toString());
			bmobItem.setPressure(etPressure.getText().toString());
			bmobItem.setPressureSpeed(etPressureSpeed.getText().toString());
			bmobItem.setPressureTime(etPressureTime.getText().toString());
			bmobItem.setKeepPressure(etKeepPressure.getText().toString());
			bmobItem.setKeepPressureSpeed(etKeepPressureSpeed.getText().toString());
			bmobItem.setKeepPressureTime(etKeepPressureTime.getText().toString());
			bmobItem.setCooldown(etCoolDown.getText().toString());
			bmobItem.setPeriod(etPeriod.getText().toString());
			bmobItem.setProductionWeight(etProductionWeight.getText().toString());

			bmobItem.setStoreMaterial(etStoreMaterial.getText().toString());
			bmobItem.setStoreProcess(etStoreMaterialProcess.getText().toString());
			bmobItem.setOpenSpeed(etOpenSpeed.getText().toString());
			bmobItem.setOpenProcess(etOpenSpeedProcess.getText().toString());
			bmobItem.setPropSpeed(etPropSpeed.getText().toString());
			bmobItem.setPropCount(etPropSpeedCount.getText().toString());
			bmobItem.setWorkType(workType);
			bmobItem.setFileName(bmobFileName);
			bmobItem.setThumbFileName(bmobThumbFileName);
			bmobItem.setName(etItemName.getText().toString());

			bmobItem.save(getApplicationContext(), new SaveListener() {

				@Override
				public void onSuccess() {

					saveLocalData(bmobItem);
					ToastUtils.show(getApplicationContext(), "数据上传完毕");
				}

				@Override
				public void onFailure(int arg0, String errorMsg) {
					ToastUtils.show(getApplicationContext(), "上传数据失败:" + errorMsg);
					mLoadingDialog.dismiss();
				}
			});
		} else if (mItem != null && !StringUtils.isBlank(mItem.getFileName())) {
			// 修改
			final Item newBmobItem = new Item();
			newBmobItem.setCreateTime(mItem.getCreateTime());
			newBmobItem.setDesc(etDesc.getText().toString());
			newBmobItem.setMaterial(etMaterial.getText().toString());
			newBmobItem.setMachineNo(etMachineNo.getText().toString());
			newBmobItem.setTemperature(etTemperature.getText().toString());
			newBmobItem.setColor(etColor.getText().toString());
			newBmobItem.setCardNo(etCardNo.getText().toString());
			newBmobItem.setPressure(etPressure.getText().toString());
			newBmobItem.setPressureSpeed(etPressureSpeed.getText().toString());
			newBmobItem.setPressureTime(etPressureTime.getText().toString());
			newBmobItem.setKeepPressure(etKeepPressure.getText().toString());
			newBmobItem.setKeepPressureSpeed(etKeepPressureSpeed.getText().toString());
			newBmobItem.setKeepPressureTime(etKeepPressureTime.getText().toString());
			newBmobItem.setCooldown(etCoolDown.getText().toString());
			newBmobItem.setPeriod(etPeriod.getText().toString());
			newBmobItem.setProductionWeight(etProductionWeight.getText().toString());

			newBmobItem.setStoreMaterial(etStoreMaterial.getText().toString());
			newBmobItem.setStoreProcess(etStoreMaterialProcess.getText().toString());
			newBmobItem.setOpenSpeed(etOpenSpeed.getText().toString());
			newBmobItem.setOpenProcess(etOpenSpeedProcess.getText().toString());
			newBmobItem.setPropSpeed(etPropSpeed.getText().toString());
			newBmobItem.setPropCount(etPropSpeedCount.getText().toString());
			newBmobItem.setWorkType(workType);
			newBmobItem.setObjectId(mItem.getObjId());
			newBmobItem.setName(etItemName.getText().toString());
			// 是否重新拍照
			if (isCapture) {
				newBmobItem.setFileName(bmobFileName);
				newBmobItem.setThumbFileName(bmobThumbFileName);
			} else {
				newBmobItem.setFileName(mItem.getFileName());
				newBmobItem.setThumbFileName(mItem.getThumbFileName());
			}
			newBmobItem.update(getApplicationContext(), mItem.getObjId(), new UpdateListener() {

				@Override
				public void onSuccess() {
					saveLocalData(newBmobItem);
					ToastUtils.show(getApplicationContext(), "数据上传完毕");
				}

				@Override
				public void onFailure(int arg0, String errorMsg) {
					ToastUtils.show(getApplicationContext(), "上传数据失败:" + errorMsg);
					mLoadingDialog.dismiss();
				}
			});

		} else {
			ToastUtils.show(getApplicationContext(), "没有照片信息，无法保存.请拍照后重试。");
			return;
		}

	}

	private void uploadThumbImage() {
		BmobProFile.getInstance(getApplicationContext()).upload(localThumbFilePath, new UploadListener() {

			@Override
			public void onError(int arg0, String errorMsg) {
				mLoadingDialog.dismiss();
				ToastUtils.show(getApplicationContext(), "上传文件失败:" + errorMsg);
			}

			@Override
			public void onSuccess(String thumbFileName, String url, BmobFile arg2) {
				// mLoadingDialog.dismiss();
				mLoadingDialog.setTitle("上传数据中...");
				bmobThumbFileName = thumbFileName;
				localThumbFilePath = sdCardPath + thumbFileName;
				thumbFile.renameTo(new File(localThumbFilePath));
				ToastUtils.show(getApplicationContext(), "预览图上传完毕");
				saveBmobData();
			}

			@Override
			public void onProgress(int percent) {
				tvPercent.setText(percent + "%");
			}
		});
	}

	/**
	 * 保存在客户端数据库
	 * 
	 * @param bmobItem
	 */
	private void saveLocalData(Item bmobItem) {
		List<LocalItem> findResult = DataSupport.where("objId = ?", bmobItem.getObjectId()).find(LocalItem.class);
		LocalItem localItem;
		if (findResult != null && findResult.size() > 0) {
			localItem = findResult.get(0);
		} else {
			localItem = new LocalItem();
		}
		localItem.setCreateTime(bmobItem.getCreateTime());
		localItem.setDesc(bmobItem.getDesc());
		localItem.setFileName(bmobItem.getFileName());
		localItem.setLocalFilePath(localFilePath);
		localItem.setLocalThumbFilePath(localThumbFilePath);
		localItem.setName(bmobItem.getName());

		localItem.setDesc(bmobItem.getDesc());
		localItem.setMaterial(bmobItem.getMaterial());
		localItem.setMachineNo(bmobItem.getMachineNo());
		localItem.setTemperature(bmobItem.getTemperature());
		localItem.setColor(bmobItem.getColor());
		localItem.setCardNo(bmobItem.getCardNo());
		localItem.setPressure(bmobItem.getPressure());
		localItem.setPressureSpeed(bmobItem.getPressureSpeed());
		localItem.setPressureTime(bmobItem.getPressureTime());

		localItem.setKeepPressure(bmobItem.getKeepPressure());
		localItem.setKeepPressureSpeed(bmobItem.getKeepPressureSpeed());
		localItem.setKeepPressureTime(bmobItem.getKeepPressureTime());
		localItem.setCooldown(bmobItem.getCooldown());
		localItem.setPeriod(bmobItem.getPeriod());
		localItem.setProductionWeight(bmobItem.getProductionWeight());

		localItem.setStoreMaterial(bmobItem.getStoreMaterial());
		localItem.setStoreProcess(bmobItem.getStoreProcess());
		localItem.setOpenSpeed(bmobItem.getOpenSpeed());
		localItem.setOpenProcess(bmobItem.getOpenSpeed());
		localItem.setPropSpeed(bmobItem.getPropSpeed());
		localItem.setPropCount(bmobItem.getPropCount());
		localItem.setWorkType(bmobItem.getWorkType());

		localItem.setObjId(bmobItem.getObjectId());
		try {
			mLoadingDialog.dismiss();
			localItem.saveThrows();
			ToastUtils.show(getApplicationContext(), "保存数据库成功！");
			startActivity(new Intent(this, MainActivity.class));
			finish();

		} catch (Exception e) {
			ToastUtils.show(getApplicationContext(), "保存数据库失败:" + e.getMessage());
			mLoadingDialog.dismiss();
		}
	}

	@Override
	public void onBackPressed() {

		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ivPhoto.setImageBitmap(null);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {

			rbWorkType1.setChecked(rbWorkType1 == buttonView);
			rbWorkType2.setChecked(rbWorkType2 == buttonView);
			rbWorkType3.setChecked(rbWorkType3 == buttonView);

			switch (buttonView.getId()) {
			case R.id.rb_work_type_1:
				workType = 1;
				break;
			case R.id.rb_work_type_2:
				workType = 2;
				break;
			case R.id.rb_work_type_3:
				workType = 3;
				break;

			default:
				workType = 0;
				break;
			}

		}

	}
}
