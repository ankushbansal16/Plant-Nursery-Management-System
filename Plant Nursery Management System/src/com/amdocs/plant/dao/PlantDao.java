package com.amdocs.plant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.amdocs.plant.exception.UserDefinedException;
import com.amdocs.plant.model.Plant;

public class PlantDao {
	PlantJdbcConnection plantJdbcConnection = new PlantJdbcConnection();
	Connection con = plantJdbcConnection.connect();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public int addPlant(Plant p) {
		
		int i=0;
		try {
			
			preparedStatement = con.prepareStatement("insert into plant values(plant_seq.NEXTVAL,?,?,?,?,?,?)");
			
			preparedStatement.setString(1, p.getPlantName());
			preparedStatement.setString(2, p.getOriginCountryName());
			preparedStatement.setInt(3, p.isSunlightReq()?1:0);
			preparedStatement.setString(4, p.getWaterSupplyFreq());
			preparedStatement.setString(5, p.getPlantType());
			preparedStatement.setFloat(6, (float)p.getCost());
			
			i = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public boolean updatePlantCost(int pId, double cost) throws UserDefinedException, SQLException {
		int i=0;
		try {
			preparedStatement = con.prepareStatement("update plant set pcost=? where pId=?");
			
			preparedStatement.setFloat(1, (float)cost);
			preparedStatement.setInt(2, pId);
			
			i = preparedStatement.executeUpdate();
			
			if(i==0) {
				throw new UserDefinedException("Plant ID Not Found!!");
			}
			
		} catch (UserDefinedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return i>=1?true:false;
	}
	
	public int deletePlant(int pid) throws UserDefinedException, SQLException {
		int i=0;
		try {
			preparedStatement = con.prepareStatement("delete from plant where pid=?");
			
			preparedStatement.setInt(1, pid);
			
			i = preparedStatement.executeUpdate();
			
			if(i==0) {
				throw new UserDefinedException("\nPlant ID not found!");
			}
			
		} catch(UserDefinedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return i;
	}
	
	public ArrayList<Plant> showAllPlants() throws UserDefinedException, SQLException {
		ArrayList<Plant> plants = new ArrayList<Plant>();
		try {
			preparedStatement = con.prepareStatement("select * from plant");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				boolean sunReq = false;
				if(resultSet.getInt(4) == 1) {
					sunReq = true;
				}
				
				Plant p = new Plant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), sunReq, resultSet.getString(5), resultSet.getString(6), resultSet.getFloat(7));
				
				plants.add(p);
			}
			
			if(plants.isEmpty()) {
				throw new UserDefinedException("\nNo records to display!!");
			}
			
		} catch (UserDefinedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return plants;
	}
	
	public ArrayList<Plant> searchByOriginCountryName(String countryName) throws SQLException, UserDefinedException {
		ArrayList<Plant> plants = new ArrayList<Plant>();
		
		try {
			preparedStatement = con.prepareStatement("select * from plant where cName=?");
			preparedStatement.setString(1, countryName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				boolean sunReq = false;
				if(resultSet.getInt(4) == 1) {
					sunReq = true;
				}
				
				Plant p = new Plant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), sunReq, resultSet.getString(5), resultSet.getString(6), resultSet.getFloat(7));
				
				plants.add(p);
			}
			
			if(plants.isEmpty()) {
				throw new UserDefinedException("\nNo records found with desired country name!");
			}
			
		} catch (UserDefinedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return plants;
	}
	
	public ArrayList<Plant> searchOutdoorPlantsWithSunlight() throws UserDefinedException, SQLException {
		ArrayList<Plant> plants = new ArrayList<Plant>();
		
		try {
			preparedStatement = con.prepareStatement("select * from plant where plantType='outdoor' and sunReq=1");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				boolean sunReq = false;
				if(resultSet.getInt(4) == 1) {
					sunReq = true;
				}
				
				Plant p = new Plant(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), sunReq, resultSet.getString(5), resultSet.getString(6), resultSet.getFloat(7));
				
				plants.add(p);
			}
			
			if(plants.isEmpty()) {
				throw new UserDefinedException("\nNo records found!");
			}
			
		} catch (UserDefinedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return plants;
	}
	
	public int countPlantsByWaterSupplyFrequency(String freq) throws UserDefinedException, SQLException {
		int count=0;
		try {
			preparedStatement = con.prepareStatement("select count(*) from plant where waterFreq=?");
			preparedStatement.setString(1, freq);
		    resultSet = preparedStatement.executeQuery();
		    resultSet.next();
		    count = resultSet.getInt(1);
			
			if(count==0) {
				throw new UserDefinedException("\nNo records found!");
			}
			
		} catch (UserDefinedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return count;
	}
}
