package com.aca.leaflit.model;

public enum Plant_Type {
	Flowering,NonFlowering;

	public static Plant_Type convertStringToPlantType(String plantTypeString) {
		Plant_Type myPlantType = null;
		
		for (Plant_Type plantType : Plant_Type.values()) {
			if (plantType.toString().equalsIgnoreCase(plantTypeString)) {
				myPlantType = plantType;
				break;
			}	
		}
		return myPlantType;
	}
}
