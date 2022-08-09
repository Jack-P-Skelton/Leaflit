package com.aca.leaflit.dao;

import java.util.List;

import com.aca.leaflit.model.Plant;
import com.aca.leaflit.model.Plant_Type;

public interface PlantDao {
	
	public List<Plant> getPlants();
	public List<Plant> getPlantsByPlantType(Plant_Type plantType);
	public List<Plant> getPlantsByHardinessZone(String hardinessZone);
	public List<Plant> getPlantsById(Integer plantId);
	public Plant createPlant(Plant newPlant);
	public Plant deletePlant(Integer plantId);
	public Plant updatePlant(Plant updatePlant);
	public List<Plant> getPlantByName(String name);
	public List<Plant> getPlantsByLightRequired(String lightRequired);

}
