package com.aca.leaflit.dao;

import java.util.ArrayList;
import java.util.List;
import com.aca.leaflit.model.Plant;
import com.aca.leaflit.model.Plant_Type;

public class PlantDaoMock implements PlantDao {
	
	private static List<Plant> plants = new ArrayList<Plant>();	
	
	private static Integer LastPlantId = 10;
	
	private static Integer getNextPlantNumber() {
		return LastPlantId++;
	}
	
	static {
		Plant tomato = new Plant();
		tomato.setPlantName("Tomato");
		tomato.setGrowingSeason("May-October");
		tomato.setLightRequired("6-8 hours");
		tomato.setHardinessZones("5,6,7,8");
		tomato.setWaterRequired("1-2 inches per week");
		tomato.setPlantType(Plant_Type.Flowering);
		tomato.setId(1);
		
		Plant lemonTree = new Plant();
		lemonTree.setPlantName("Lemon Tree");
		lemonTree.setGrowingSeason("Year round");
		lemonTree.setLightRequired("8+ hours");
		lemonTree.setWaterRequired("1-2 inches per week");
		lemonTree.setHardinessZones("8,9,10,11");
		lemonTree.setPlantType(Plant_Type.Flowering);
		lemonTree.setId(2);
		
		Plant cannas= new Plant();
		cannas.setPlantName("Cannas");
		cannas.setGrowingSeason("Year round");
		cannas.setLightRequired("6+ hours");
		cannas.setWaterRequired("1-2 inches per week");
		cannas.setHardinessZones("7,8,9,10");
		cannas.setPlantType(Plant_Type.NonFlowering);
		cannas.setId(3);
		
		plants.add(tomato);
		plants.add(lemonTree);
		plants.add(cannas);
		
	}
	
	@Override
	public List<Plant> getPlants() {
		List<Plant> myPlants = new ArrayList<Plant>();
		myPlants.addAll(plants);
		return myPlants;
	}
	
	@Override
	public List<Plant> getPlantsByPlantType(Plant_Type plantType) {
		List<Plant> myPlants = new ArrayList<Plant>();
		for (Plant plant : PlantDaoMock.plants) {
			if (plant.getPlantType().equals(plantType)) {
				myPlants.add(plant);
			}
		}
		return myPlants;
	}
	
	@Override
	public List<Plant> getPlantsByHardinessZone(String hardinessZone) {
		List<Plant> myPlants = new ArrayList<Plant>();
		for (Plant plant : PlantDaoMock.plants) {
			if (plant.getHardinessZones().contains(hardinessZone)) {
				myPlants.add(plant);
			}
		}
		return myPlants;
	}
	
	@Override
	public List<Plant> getPlantsById(Integer plantId) {
		List<Plant> myPlants = new ArrayList<Plant>();
		for (Plant plant : PlantDaoMock.plants) {
			if (plant.getId().intValue() == plantId.intValue()) {
				myPlants.add(plant);
			}
		}
		return myPlants;
	}

	@Override
	public Plant createPlant(Plant newPlant) {
		newPlant.setId(getNextPlantNumber());
		plants.add(newPlant);
		return newPlant;
	}

	@Override
	public Plant deletePlant(Integer plantId) {
		int index = 0;
		for (int i = 0; i < plants.size(); i++) {
			if (plants.get(i).getId().intValue() == plantId.intValue()) {
				index = i;
				break;
			}
		}
		Plant plant = plants.get(index);
		plants.remove(index);
		
		
		return plant;
	}

	@Override
	public Plant updatePlant(Plant updatePlant) {
		int index = 0;
		for (int i = 0; i < plants.size(); i++) {
			if (plants.get(i).getId().intValue() == updatePlant.getId().intValue()) {
				index = i;
				break;
			}
		}
		plants.set(index, updatePlant);
		return updatePlant;
	}

	@Override
	public List<Plant> getPlantByName(String name) {
		List<Plant> myPlants = new ArrayList<Plant>();
		for (Plant plant : plants) {
			if (plant.getPlantName().equalsIgnoreCase(name)) {
				myPlants.add(plant);
			}
		}	
		return myPlants;
	}

	@Override
	public List<Plant> getPlantsByLightRequired(String lightRequired) {
		// TODO Auto-generated method stub
		return null;
	}
}
