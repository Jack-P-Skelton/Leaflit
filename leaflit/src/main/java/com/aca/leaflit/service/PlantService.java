package com.aca.leaflit.service;

import java.util.List;
import com.aca.leaflit.dao.PlantDao;
import com.aca.leaflit.dao.PlantDaoImpl;
import com.aca.leaflit.dao.PlantDaoMock;
import com.aca.leaflit.model.Plant;
import com.aca.leaflit.model.Plant_Type;
import com.aca.leaflit.model.RequestError;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class PlantService {
	
//	private PlantDao plantDao = new PlantDaoMock();
	private PlantDao plantDao = new PlantDaoImpl();
	
	public List<Plant> getPlants() {
		return plantDao.getPlants();
	}
	
	public List<Plant> getPlantsByPlantType(Plant_Type plantType) {
		return plantDao.getPlantsByPlantType(plantType);
	}
	
	public List<Plant> getPlantsByHardinessZone(String hardinessZone) {
//		validateHardinessZone(hardinessZone);
		return plantDao.getPlantsByHardinessZone(hardinessZone);
	}
	
	public List<Plant> getPlantsById(Integer plantId) {
		validatePlantId(plantId);
		return plantDao.getPlantsById(plantId);
	}
	
	public List<Plant> getPlantByName(String name) {
		validatePlantName(name);
		return plantDao.getPlantByName(name);
	}

	private void validateHardinessZone(String hardinessZone) {
		if (!hardinessZone.equals("3")  && !hardinessZone.equals("4") && !hardinessZone.equals("5")
				&& !hardinessZone.equals("6") && !hardinessZone.equals("7") && !hardinessZone.equals("8")
				&& !hardinessZone.equals("9") && !hardinessZone.equals("10")) {
			RequestError error = new RequestError(1,
					"Invalid hardiness zone. Value must be <= 3 and >= 10");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
	}
	
	private void validatePlantId(Integer plantId) {
		if (plantId.intValue() <= 0) {
			RequestError error = new RequestError(2,
					"Invalid plant ID. Value must be > 0");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
	}
	
	private void validatePlantName(String plantName) {
		
		if (null == plantName || plantName.length() < 1 || plantName.length() > 50) {
			RequestError error = new RequestError(3,
					"Invalid value for name: '" + plantName + "' - Value must have length > 1 and <= 50");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}

	}

	public Plant createPlant(Plant newPlant) {
//		validateHardinessZone(newPlant.getHardinessZones());
//		validatePlantName(newPlant.getName());
		return plantDao.createPlant(newPlant);
	}

	public Plant deletePlant(Integer plantId) {
		validatePlantId(plantId);
		List<Plant> plants = plantDao.getPlantsById(plantId);
		
		if (plants.size() == 1) {
			return plantDao.deletePlant(plantId);
		} else {
			RequestError error = new RequestError(5,
					"Invalid plant Id: '" + plantId + "'");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
	}

	public Plant updatePlant(Plant updatePlant) {
//		validatePlantName(updatePlant.getPlantName());
//		validateHardinessZone(updatePlant.getHardinessZones());
//		validatePlantId(updatePlant.getId());
		
		List<Plant> plants = plantDao.getPlantsById(updatePlant.getId());
		
		if (plants.size() == 1) {
			return plantDao.updatePlant(updatePlant);
		} else {
			RequestError error = new RequestError(4,
					"Plant id does not exist: '" + updatePlant.getId() + "'");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}	
	}

	public List<Plant> getPlantsByLightRequired(String lightRequired) {
		// TODO Auto-generated method stub
		return plantDao.getPlantsByLightRequired(lightRequired);
	}

}
