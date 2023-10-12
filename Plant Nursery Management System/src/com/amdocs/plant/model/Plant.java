package com.amdocs.plant.model;

public class Plant {
	private int plantId;
	private String plantName;
	private String originCountryName;
	private boolean sunlightReq;
	private String waterSupplyFreq;
	private String plantType;
	private double cost;
	
	// Parameterized Constructor
	public Plant(int plantId, String plantName, String originCountryName, boolean sunlightReq,
			String waterSupplyFreq, String plantType, double cost) {
		super();
		this.plantId = plantId;
		this.plantName = plantName;
		this.originCountryName = originCountryName;
		this.sunlightReq = sunlightReq;
		this.waterSupplyFreq = waterSupplyFreq;
		this.plantType = plantType;
		this.cost = cost;
	}
	
	// Getters and Setters

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	
	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getOriginCountryName() {
		return originCountryName;
	}

	public void setOriginCountryName(String originCountryName) {
		this.originCountryName = originCountryName;
	}

	public boolean isSunlightReq() {
		return sunlightReq;
	}

	public void setSunlightReq(boolean sunlightReq) {
		this.sunlightReq = sunlightReq;
	}

	public String getWaterSupplyFreq() {
		return waterSupplyFreq;
	}

	public void setWaterSupplyFreq(String waterSupplyFreq) {
		this.waterSupplyFreq = waterSupplyFreq;
	}

	public String getPlantType() {
		return plantType;
	}

	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	// toString() Method
	@Override
	public String toString() {
		return "Plant [plantId=" + plantId + ", plantName=" + plantName + ", originCountryName=" + originCountryName
				+ ", sunlightReq=" + sunlightReq + ", waterSupplyFreq=" + waterSupplyFreq + ", plantType=" + plantType
				+ ", cost=" + cost + "]";
	}
	
}
