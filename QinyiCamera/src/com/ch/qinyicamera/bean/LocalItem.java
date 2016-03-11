package com.ch.qinyicamera.bean;

import java.util.Date;

import org.litepal.crud.DataSupport;

import cn.trinea.android.common.util.StringUtils;

public class LocalItem extends DataSupport {
	
	public LocalItem(Item bmobItem) {
		this.setCreateTime(bmobItem.getCreateTime());
		this.setDesc(bmobItem.getDesc());
		this.setFileName(bmobItem.getFileName());
		this.setThumbFileName(bmobItem.getThumbFileName());
		this.setLocalFilePath("");
		this.setLocalThumbFilePath("");
		this.setName(bmobItem.getName());

		this.setDesc(bmobItem.getDesc());
		this.setMaterial(bmobItem.getMaterial());
		this.setMachineNo(bmobItem.getMachineNo());
		this.setTemperature(bmobItem.getTemperature());
		this.setColor(bmobItem.getColor());
		this.setCardNo(bmobItem.getCardNo());
		this.setPressure(bmobItem.getPressure());
		this.setPressureSpeed(bmobItem.getPressureSpeed());
		this.setPressureTime(bmobItem.getPressureTime());

		this.setKeepPressure(bmobItem.getKeepPressure());
		this.setKeepPressureSpeed(bmobItem.getKeepPressureSpeed());
		this.setKeepPressureTime(bmobItem.getKeepPressureTime());
		this.setCooldown(bmobItem.getCooldown());
		this.setPeriod(bmobItem.getPeriod());
		this.setProductionWeight(bmobItem.getProductionWeight());

		this.setStoreMaterial(bmobItem.getStoreMaterial());
		this.setStoreProcess(bmobItem.getStoreProcess());
		this.setOpenSpeed(bmobItem.getOpenSpeed());
		this.setOpenProcess(bmobItem.getOpenSpeed());
		this.setPropSpeed(bmobItem.getPropSpeed());
		this.setPropCount(bmobItem.getPropCount());
		this.setWorkType(bmobItem.getWorkType());

		this.setObjId(bmobItem.getObjectId());
	}
	
	
	public LocalItem() {
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("名称:"+name+",\n");
		sb.append("材料:"+material+",\t\t");
		sb.append("机台:"+machineNo+",\n");
		
		if(!StringUtils.isBlank(temperature)) {
			sb.append("温度:"+temperature+",\t\t");
		}
		if(!StringUtils.isBlank(color)) {
			sb.append("颜色:"+color+",\t\t");
		}
		if(!StringUtils.isBlank(cardNo)) {
			sb.append("卡号:"+cardNo+",");
		}
		if(!StringUtils.isBlank(temperature)||!StringUtils.isBlank(color)||!StringUtils.isBlank(cardNo)) {
			sb.append("\n");
		}
		
		
		if(!StringUtils.isBlank(pressure)) {
			sb.append("压力:"+pressure+",\t\t");
		}
		if(!StringUtils.isBlank(pressureSpeed)) {
			sb.append("速度:"+pressureSpeed+",\t\t");
		}
		if(!StringUtils.isBlank(pressureTime)) {
			sb.append("时间:"+pressureTime+",");
		}
		if(!StringUtils.isBlank(pressure)||!StringUtils.isBlank(pressureSpeed)||!StringUtils.isBlank(pressureTime)) {
			sb.append("\n");
		}
		
		if(!StringUtils.isBlank(keepPressure)) {
			sb.append("保压:"+keepPressure+",\t\t");
		}
		if(!StringUtils.isBlank(keepPressureSpeed)) {
			sb.append("速度:"+keepPressureSpeed+",\t\t");
		}
		if(!StringUtils.isBlank(keepPressureTime)) {
			sb.append("时间:"+keepPressureTime+",");
		}
		if(!StringUtils.isBlank(keepPressure)||!StringUtils.isBlank(keepPressureSpeed)||!StringUtils.isBlank(keepPressureTime)) {
			sb.append("\n");
		}
		
		if(!StringUtils.isBlank(cooldown)) {
			sb.append("冷却:"+cooldown+",\t\t");
		}
		if(!StringUtils.isBlank(period)) {
			sb.append("周期:"+period+",\t\t");
		}
		if(!StringUtils.isBlank(productionWeight)) {
			sb.append("制品重量:"+productionWeight+",");
		}
		if(!StringUtils.isBlank(cooldown)||!StringUtils.isBlank(period)||!StringUtils.isBlank(productionWeight)) {
			sb.append("\n");
		}
		
		if(!StringUtils.isBlank(storeMaterial)) {
			sb.append("储料:"+storeMaterial+",\t\t");
		}
		if(!StringUtils.isBlank(storeProcess)) {
			sb.append("行程:"+storeProcess+",\t\t");
		}
		if(!StringUtils.isBlank(storeMaterial)||!StringUtils.isBlank(storeProcess)) {
			sb.append("\n");
		}
		
		if(!StringUtils.isBlank(openSpeed)) {
			sb.append("开模速度:"+openSpeed+",\t\t");
		}
		if(!StringUtils.isBlank(openProcess)) {
			sb.append("行程:"+openProcess+",\t\t");
		}
		if(!StringUtils.isBlank(openSpeed)||!StringUtils.isBlank(openProcess)) {
			sb.append("\n");
		}
		
		if(!StringUtils.isBlank(propSpeed)) {
			sb.append("顶出速度:"+propSpeed+",\t\t");
		}
		if(!StringUtils.isBlank(propCount)) {
			sb.append("次数:"+propCount+",\t\t");
		}
		if(!StringUtils.isBlank(propSpeed)||!StringUtils.isBlank(propCount)) {
			sb.append("\n");
		}
		
		switch (workType) {
		case 1:
			sb.append("全自动\n");
			break;
		case 2:
			sb.append("半自动\n");
			break;
		case 3:
			sb.append("机械手\n");
			break;
		default:
			break;
		}
		
		if(!StringUtils.isBlank(desc)) {
			sb.append("备注:"+desc);
		}
		
		return sb.toString();
	}
	
	private Integer id;

	private String name;
	
	private String material;
	
	private String machineNo;
	
	private String temperature;
	
	private String color;
	
	private String cardNo;
	
	private String pressure;
	
	private String pressureSpeed;
	
	private String pressureTime;
	
	private String keepPressure;
	
	private String keepPressureSpeed;
	
	private String keepPressureTime;
	
	private String cooldown;
	
	private String period;
	
	private String productionWeight;
	
	private String storeMaterial;
	
	private String storeProcess;
	
	private String openSpeed;
	
	private String openProcess;
	
	private String propSpeed;
	
	private String propCount;
	
	private String desc;
	
	/**
	 * 1.全自动
	 * 2.半自动
	 * 3.机械手
	 */
	private int workType;
	
	private String fileName;
	
	private String thumbFileName;
	
	private String objId;
	
	private Date createTime;
	
	private String localFilePath;
	
	private String localThumbFilePath;

	public String getThumbFileName() {
		return thumbFileName==null?"":thumbFileName;
	}

	public void setThumbFileName(String thumbFileName) {
		this.thumbFileName = thumbFileName;
	}

	public String getLocalThumbFilePath() {
		return localThumbFilePath==null?"":localThumbFilePath;
	}

	public void setLocalThumbFilePath(String localThumbFilePath) {
		this.localThumbFilePath = localThumbFilePath;
	}

	public String getLocalFilePath() {
		return localFilePath==null?"":localFilePath;
	}

	public void setLocalFilePath(String localFilePath) {
		this.localFilePath = localFilePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name==null?"":name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName==null?"":fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc==null?"":desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMaterial() {
		return material==null?"":material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMachineNo() {
		return machineNo==null?"":machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getTemperature() {
		return temperature==null?"":temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getColor() {
		return color==null?"":color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCardNo() {
		return cardNo==null?"":cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPressure() {
		return pressure==null?"":pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getPressureSpeed() {
		return pressureSpeed==null?"":pressureSpeed;
	}

	public void setPressureSpeed(String pressureSpeed) {
		this.pressureSpeed = pressureSpeed;
	}

	public String getPressureTime() {
		return pressureTime==null?"":pressureTime;
	}

	public void setPressureTime(String pressureTime) {
		this.pressureTime = pressureTime;
	}

	public String getKeepPressure() {
		return keepPressure==null?"":keepPressure;
	}

	public void setKeepPressure(String keepPressure) {
		this.keepPressure = keepPressure;
	}

	public String getKeepPressureSpeed() {
		return keepPressureSpeed==null?"":keepPressureSpeed;
	}

	public void setKeepPressureSpeed(String keepPressureSpeed) {
		this.keepPressureSpeed = keepPressureSpeed;
	}

	public String getKeepPressureTime() {
		return keepPressureTime==null?"":keepPressureTime;
	}

	public void setKeepPressureTime(String keepPressureTime) {
		this.keepPressureTime = keepPressureTime;
	}

	public String getCooldown() {
		return cooldown==null?"":cooldown;
	}

	public void setCooldown(String cooldown) {
		this.cooldown = cooldown;
	}

	public String getPeriod() {
		return period==null?"":period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getProductionWeight() {
		return productionWeight==null?"":productionWeight;
	}

	public void setProductionWeight(String productionWeight) {
		this.productionWeight = productionWeight;
	}

	public String getStoreMaterial() {
		return storeMaterial==null?"":storeMaterial;
	}

	public void setStoreMaterial(String storeMaterial) {
		this.storeMaterial = storeMaterial;
	}

	public String getStoreProcess() {
		return storeProcess==null?"":storeProcess;
	}

	public void setStoreProcess(String storeProcess) {
		this.storeProcess = storeProcess;
	}

	public String getOpenSpeed() {
		return openSpeed==null?"":openSpeed;
	}

	public void setOpenSpeed(String openSpeed) {
		this.openSpeed = openSpeed;
	}

	public String getOpenProcess() {
		return openProcess==null?"":openProcess;
	}

	public void setOpenProcess(String openProcess) {
		this.openProcess = openProcess;
	}

	public String getPropSpeed() {
		return propSpeed==null?"":propSpeed;
	}

	public void setPropSpeed(String propSpeed) {
		this.propSpeed = propSpeed;
	}

	public String getPropCount() {
		return propCount==null?"":propCount;
	}

	public void setPropCount(String propCount) {
		this.propCount = propCount;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}
}
