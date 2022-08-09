package com.aca.leaflit.model;

import java.time.LocalDateTime;

public class Plant {
	
	private String plantName;
	private Integer id;
	private String growingSeason;
	private String lightRequired;
	private String waterRequired;
	private String hardinessZones;	
	private Plant_Type plantType;
	private LocalDateTime updateDateTime;
	private LocalDateTime createDateTime;
	private String img;
	
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getGrowingSeason() {
		return growingSeason;
	}
	public void setGrowingSeason(String growingSeason) {
		this.growingSeason = growingSeason;
	}
	public String getLightRequired() {
		return lightRequired;
	}
	public void setLightRequired(String lightRequired) {
		this.lightRequired = lightRequired;
	}
	public String getWaterRequired() {
		return waterRequired;
	}
	public void setWaterRequired(String waterRequired) {
		this.waterRequired = waterRequired;
	}
	public Plant_Type getPlantType() {
		return plantType;
	}
	public void setPlantType(Plant_Type plantType) {
		this.plantType = plantType;
	}
	public String getHardinessZones() {
		return hardinessZones;
	}
	public void setHardinessZones(String hardinessZones) {
		this.hardinessZones = hardinessZones;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	


}
