package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.util.ArrayList;
import java.util.Arrays;

public class Building {
	private int xSize = 10;				// size of building in x
	private int ySize = 10;				// and y
	private ArrayList<Room> allRooms;	// array of rooms
	
	Building(String bS){
		allRooms = new ArrayList<Room>();
		allRooms.clear();
		StringSplitter S = new StringSplitter(bS, ";");
		StringSplitter SS = new StringSplitter(S.getStrings()[0], " ");
		xSize = SS.getIntegers()[0];
		ySize = SS.getIntegers()[1];
		System.out.println(ySize);
		for (int i = 1; i < S.getStrings().length; i++){
			allRooms(new Room(S.getStrings()[i]));
		}
		
	}
	
	public static void main(String[] args) {
		Building b = new Building("11 11;0 0 5 5 3 5;6 0 10 10 6 6;0 5 5 10 2 5");
	}
}
