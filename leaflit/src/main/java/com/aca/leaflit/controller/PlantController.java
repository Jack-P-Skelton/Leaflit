package com.aca.leaflit.controller;

import java.util.List;
import com.aca.leaflit.model.Plant;
import com.aca.leaflit.model.Plant_Type;
import com.aca.leaflit.service.PlantService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/plants")
public class PlantController {
	
	private PlantService service = new PlantService();
	
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Plant> getPlants() {
			return service.getPlants();
		}
		
		@GET
		@Path("/plantType/{plantTypeValue}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Plant> getPlantsByPlantTypeValue(@PathParam("plantTypeValue") Plant_Type plantType) {
			return service.getPlantsByPlantType(plantType);	
		}
		
		@GET
		@Path("/hardinessZone/{hardinessZoneValue}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Plant> getPlantsByHardinessZoneValue(@PathParam("hardinessZoneValue") String hardinessZone) {
			return service.getPlantsByHardinessZone(hardinessZone);	
		}
		
		@GET
		@Path("/lightRequired/{lightRequiredValue}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Plant> getPlantsByLightRequiredValue(@PathParam("lightRequiredValue") String lightRequired) {
			return service.getPlantsByLightRequired(lightRequired);	
		}
		
		@GET
		@Path("/{plantIdValue}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Plant> getPlantsById(@PathParam("plantIdValue") Integer plantId) {
			return service.getPlantsById(plantId);	
		}
		
		@GET
		@Path("/name")
		public List<Plant> getPlantByName(
				@QueryParam("name") String name) {
			return service.getPlantByName(name);
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Plant createPlant(Plant newPlant) {
			System.out.println(newPlant);
			return service.createPlant(newPlant);
		}
		
		@DELETE
		@Path("/{plantIdValue}")
		public Plant deletePlant(@PathParam("plantIdValue") Integer plantId) {
			System.out.println("plantId: " + plantId);
			return service.deletePlant(plantId);
		}
		
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Plant updatePlant(Plant newPlant) {
			System.out.println(newPlant);
			return service.updatePlant(newPlant);
		}
		
		@GET
		@Path("/discoverResults")	
		public List<Plant> getDiscoverResults(@QueryParam("lightRequired") String lightRequired) {
			return service.getPlantsByLightRequired(lightRequired);	
		}
}
