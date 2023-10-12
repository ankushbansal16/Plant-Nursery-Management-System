package com.amdocs.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.amdocs.plant.dao.PlantDao;
import com.amdocs.plant.exception.UserDefinedException;
import com.amdocs.plant.model.Plant;

public class Main {

	public static void main(String args[]) throws UserDefinedException, SQLException {	
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("------Welcome to PLANT NURSERY MANAGEMENT SYSTEM------");
			
			while(true) {
				System.out.println("\n\n==============================================\n");
				System.out.println("Enter your choice:");
				System.out.println("1. Add new plant");
				System.out.println("2. Update plant cost");
				System.out.println("3. Delete plant");
				System.out.println("4. View all plants");
				System.out.println("5. Find plant by origin country name");
				System.out.println("6. Find outdoor plants which requires sunlight");
				System.out.println("7. Count plants by water supply frequency");
				System.out.println("8. Exit");
				System.out.println("\n===============================================\n\n");
				
				System.out.print("Choice -> ");
				int choice = sc.nextInt();
				PlantDao plantDao = new PlantDao();
				ArrayList<Plant> plants = new ArrayList<Plant>();
				
				switch(choice) {
				case 1:
					System.out.println("\nEnter plant details:");
					
					System.out.print("Enter plant name -> ");
					String name = sc.next();
					
					System.out.print("Enter plant's origin country -> ");
					String originCountryName = sc.next();
					
					System.out.print("Whether plant requires sunlight? Type y for YES and n for NO -> ");
					String req = sc.next();
					boolean sunReq=false;
					if(req.equals("y")) {
						sunReq = true;
					}
					
					System.out.print("Enter water supply frequency (daily/alternate/weekly) -> ");
					String waterSupplyFreq = sc.next();
					
					System.out.print("Enter plant type (indoor/outdoor) -> ");
					String plantType = sc.next();
					
					System.out.print("Enter cost of plant -> ");
					double cost = sc.nextDouble();
					
					Plant plant = new Plant(0,name, originCountryName, sunReq, waterSupplyFreq, plantType, cost);
					
					System.out.println(plantDao.addPlant(plant) + " records have been inserted!");
					break;
				case 2:
					System.out.print("\nEnter plant ID whose cost you want to update -> ");
					int id  = sc.nextInt();
					
					System.out.print("Enter the new cost -> ");
					cost = sc.nextDouble();
					
					if(plantDao.updatePlantCost(id, cost)) {
						System.out.println("Cost updated successfully!");
					}
					
					break;
				case 3:
					System.out.print("\nEnter plant ID that you want to delete -> ");
					id = sc.nextInt();
					
					System.out.println(plantDao.deletePlant(id) + " records have been deleted!");
					break;
				case 4:
					plants = plantDao.showAllPlants();
					
					for(Plant p:plants) {
						System.out.println(p);
//					System.out.println("Plant Name: " + p.getPlantName());
//					System.out.println("Plant Country Origin: " + p.getOriginCountryName());
//					System.out.println("Is Sunlight Required: " + p.isSunlightReq());
//					System.out.println("Water supply Frequency: " + p.getWaterSupplyFreq());
//					System.out.println("Plant Type: " + p.getPlantType());
//					System.out.println("Plant Cost: " + p.getCost());
					}
					break;
				case 5:
					System.out.print("\nEnter country's name whose plants you want to view -> ");
					String countryName = sc.next();
					
					plants = plantDao.searchByOriginCountryName(countryName);
					
//				System.out.println(plants);
					for(Plant p:plants) {
						System.out.println(p);
					}
					
					break;
				case 6:
					plants = plantDao.searchOutdoorPlantsWithSunlight();
					
//				System.out.println(plants);
					for(Plant p:plants) {
						System.out.println(p);
					}
					
					break;
				case 7:
					System.out.print("Enter water supply frequency (daily/alternate/weekly) -> ");
					String freq = sc.next();
					
					int count = plantDao.countPlantsByWaterSupplyFrequency(freq);
					
					if(count!=0)
						System.out.print("\nThere are " + count + " plants with " + freq + " water supply frequency");
					
					break;
				case 8:
					System.out.println("Exiting the program!!");
					return;
			}
			}
		}
	}
}
