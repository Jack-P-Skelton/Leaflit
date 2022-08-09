package com.aca.leaflit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.aca.leaflit.model.Plant;
import com.aca.leaflit.model.Plant_Type;

public class PlantDaoImpl implements PlantDao {
	
	private static String selectAllPlants =
		"SELECT id, plantName, hardinessZone, growingSeason, waterRequired, lightRequired, plantTypeId, updateDateTime, createDateTime, img " +
		"FROM plants";
	
	private static String selectPlantsByPlantType =
		"SELECT id, plantName, hardinessZone, growingSeason, waterRequired, lightRequired, plantTypeId, updateDateTime, createDateTime, img " +
		"FROM plants " +
		"WHERE plantTypeId = ? ";
	
	private static String selectPlantsByName =
		"SELECT id, plantName, hardinessZone, growingSeason, waterRequired, lightRequired, plantTypeId, updateDateTime, createDateTime, img " + 
		"FROM plants " + 
		"WHERE plantName LIKE ? ";
	
	private static String selectPlantsById =
		"SELECT id, plantName, hardinessZone, growingSeason, waterRequired, lightRequired, plantTypeId, updateDateTime, createDateTime, img " +
		"FROM plants " +
		"WHERE id = ? ";
	
	private static String selectPlantsByHardinessZone =
		"SELECT * " +
		"FROM plants " +
		"WHERE hardinessZone LIKE ? ";
	
	private static String selectPlantsByLightRequired =
			"SELECT * " +
			"FROM plants " +
			"WHERE lightRequired LIKE ? ";
	
	private static String insertPlant = 
		"INSERT INTO plants (plantName, hardinessZone, growingSeason, waterRequired, lightRequired, plantTypeId, img)  " +
		"VALUES " +
		"(?, ?, ?, ?, ?, ?, ?)";
	
	private static String updatePlantById =
		"UPDATE plants " + 
		"SET plantName = ?, " + 
		"	hardinessZone = ?, " + 
		"	growingSeason = ?, " + 
		"	waterRequired = ?, " + 
		"	lightRequired = ?, " + 
		"	plantTypeId = ?, " + 
		"	img = ? " + 
		"WHERE id = ? ";
	
	private static String deletePlantById =
			"DELETE FROM plants " +
			"WHERE id = ? ";
	
	private static String selectNewPlantId =
			"SELECT LAST_INSERT_ID() AS 'plantId' ";

	@Override
	public List<Plant> getPlants() {
		List<Plant> myPlants = new ArrayList<Plant>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllPlants);
			myPlants = makePlant(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return myPlants;
	}
	
	private List<Plant> makePlant(ResultSet result) throws SQLException {
		List<Plant> plants = new ArrayList<Plant>();
		while(result.next()) {
			Plant plant = new Plant();
			plant.setPlantName(result.getString("plantName"));
			plant.setId(result.getInt("id"));
			plant.setHardinessZones(result.getString("hardinessZone"));
			plant.setGrowingSeason(result.getString("growingSeason"));
			plant.setLightRequired(result.getString("lightRequired"));
			plant.setWaterRequired(result.getString("waterRequired"));
			
			String plantTypeString = result.getString("plantTypeId");
			Plant_Type plantType = Plant_Type.convertStringToPlantType(plantTypeString);
			plant.setPlantType(plantType);
			
			
			plant.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
			plant.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
			
			plant.setImg(result.getString("img"));
			
			plants.add(plant);
		}	
		return plants;
	}

	@Override
	public List<Plant> getPlantsByPlantType(Plant_Type plantType) {
		List<Plant> myPlants = new ArrayList<Plant>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlantsByPlantType);
			ps.setString(1, plantType.toString());
			result = ps.executeQuery();
			myPlants = makePlant(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myPlants;
	}

	@Override
	public List<Plant> getPlantsByHardinessZone(String hardinessZone) {

		List<Plant> myPlants = new ArrayList<Plant>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlantsByHardinessZone);
			ps.setString(1, "%" + hardinessZone + "%");
			result = ps.executeQuery();
			myPlants = makePlant(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myPlants;
	}

	@Override
	public List<Plant> getPlantsById(Integer plantId) {
		List<Plant> myPlants = new ArrayList<Plant>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlantsById);
			ps.setInt(1, plantId);
			result = ps.executeQuery();
			myPlants = makePlant(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myPlants;
	}

	@Override
	public Plant createPlant(Plant newPlant) {

		int updateRowCount = 0;
		PreparedStatement ps = null;	
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(insertPlant);
			ps.setString(1, newPlant.getPlantName());
			ps.setString(2, newPlant.getHardinessZones());
			ps.setString(3, newPlant.getGrowingSeason());
			ps.setString(4, newPlant.getWaterRequired());
			ps.setString(5, newPlant.getLightRequired());
			ps.setString(6, newPlant.getPlantType().toString());
			ps.setString(7, newPlant.getImg());
			updateRowCount = ps.executeUpdate();
			System.out.println("rows inserted: " + updateRowCount);
			int newPlantId = this.getNewPlantId(conn);
			newPlant.setId(newPlantId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newPlant;
	}

	@Override
	public Plant deletePlant(Integer plantId) {
		List<Plant> plants = this.getPlantsById(plantId);
		Plant plantToDelete =  null;
		
		if (plants.size() > 0) {
			plantToDelete = plants.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				ps = conn.prepareStatement(deletePlantById);
				ps.setInt(1, plantId);
				updateRowCount = ps.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
			return plantToDelete;
	}

	@Override
	public Plant updatePlant(Plant updatePlant) {
		List<Plant> plants = this.getPlantsById(updatePlant.getId());
		
		if(plants.size() > 0) {
		
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				ps = conn.prepareStatement(updatePlantById);
				ps.setString(1, updatePlant.getPlantName());
				ps.setString(2, updatePlant.getHardinessZones());
				ps.setString(3, updatePlant.getGrowingSeason());
				ps.setString(4, updatePlant.getWaterRequired());
				ps.setString(5, updatePlant.getLightRequired());
				ps.setString(6, updatePlant.getPlantType().toString());
				ps.setString(7, updatePlant.getImg());
				ps.setInt(8, updatePlant.getId());
				updateRowCount = ps.executeUpdate();
				System.out.println("rows inserted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updatePlant;
	}

	@Override
	public List<Plant> getPlantByName(String plantName) {
		
		List<Plant> myPlants = new ArrayList<Plant>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlantsByName);
			ps.setString(1, "%" + plantName + "%");
			result = ps.executeQuery();
			myPlants = makePlant(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myPlants;
	}

	private int getNewPlantId(Connection conn) {
		ResultSet rs = null;
		Statement statement = null;
		int newPlantId = 0;
		
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectNewPlantId);
			while(rs.next()) {
				newPlantId = rs.getInt("plantId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}		
		return newPlantId;
	}

	@Override
	public List<Plant> getPlantsByLightRequired(String lightRequired) {
		
		List<Plant> myPlants = new ArrayList<Plant>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlantsByLightRequired);
			ps.setString(1, "%" + lightRequired + "%");
			result = ps.executeQuery();
			myPlants = makePlant(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myPlants;
	}
}
