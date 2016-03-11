package com.ch.qinyicamera.bean;

import java.io.Serializable;
import java.util.Date;

import cn.bmob.v3.BmobObject;

public class Item extends BmobObject implements Serializable {
	
	private Integer id;

	private String name;
	
	private String fileName;
	
	private String thumbFileName;
	
	private String objId;
	
	private Date createTime;
	
	private String desc;
	
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
	
	/**
	 * 1.全自动
	 * 2.半自动
	 * 3.机械手
	 */
	private int workType;
	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getPressureSpeed() {
		return pressureSpeed;
	}

	public void setPressureSpeed(String pressureSpeed) {
		this.pressureSpeed = pressureSpeed;
	}

	public String getPressureTime() {
		return pressureTime;
	}

	public void setPressureTime(String pressureTime) {
		this.pressureTime = pressureTime;
	}

	public String getKeepPressure() {
		return keepPressure;
	}

	public void setKeepPressure(String keepPressure) {
		this.keepPressure = keepPressure;
	}

	public String getKeepPressureSpeed() {
		return keepPressureSpeed;
	}

	public void setKeepPressureSpeed(String keepPressureSpeed) {
		this.keepPressureSpeed = keepPressureSpeed;
	}

	public String getKeepPressureTime() {
		return keepPressureTime;
	}

	public void setKeepPressureTime(String keepPressureTime) {
		this.keepPressureTime = keepPressureTime;
	}

	public String getCooldown() {
		return cooldown;
	}

	public void setCooldown(String cooldown) {
		this.cooldown = cooldown;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getProductionWeight() {
		return productionWeight;
	}

	public void setProductionWeight(String productionWeight) {
		this.productionWeight = productionWeight;
	}

	public String getStoreMaterial() {
		return storeMaterial;
	}

	public void setStoreMaterial(String storeMaterial) {
		this.storeMaterial = storeMaterial;
	}

	public String getStoreProcess() {
		return storeProcess;
	}

	public void setStoreProcess(String storeProcess) {
		this.storeProcess = storeProcess;
	}

	public String getOpenSpeed() {
		return openSpeed;
	}

	public void setOpenSpeed(String openSpeed) {
		this.openSpeed = openSpeed;
	}

	public String getOpenProcess() {
		return openProcess;
	}

	public void setOpenProcess(String openProcess) {
		this.openProcess = openProcess;
	}

	public String getPropSpeed() {
		return propSpeed;
	}

	public void setPropSpeed(String propSpeed) {
		this.propSpeed = propSpeed;
	}

	public String getPropCount() {
		return propCount;
	}

	public void setPropCount(String propCount) {
		this.propCount = propCount;
	}

	public String getThumbFileName() {
		return thumbFileName;
	}

	public void setThumbFileName(String thumbFileName) {
		this.thumbFileName = thumbFileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
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
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}
	
	
}
