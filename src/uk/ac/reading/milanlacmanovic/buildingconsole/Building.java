package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Building {
	private int xSize = 10;				// size of building in x
	private int ySize = 10;				// and y
	private ArrayList<Room> allRooms;	// array of rooms
	private Random randGen;				//Random Generator using Random obj
	private int randRoom;
	private Person occupant;
	
	Building(String bS){
		allRooms = new ArrayList<Room>();
		allRooms.clear();
		setBuilding(bS);
		//System.out.println(allRooms);//Test
		randGen = new Random();
		occupant = new Person(allRooms.get(randRoom).getRandom(randGen));
		occupant.PointSet(allRooms.get(PersonInRoom() -1).getDoorPoint());
	}
	
	public int PersonInRoom() { /////###Can be changed to have a point argument
		for (int i = 0; i < allRooms.size(); i++) {//Loop to check all rooms
			if (allRooms.get(i).isInRoom(occupant.getPersonPoint())) {//if in a room return room number (The position in array)
				return i + 1; //Rooms start from 1 (not 0)
			}
		}
		return -1; //If not found then return -1
	}
	
	public void setBuilding(String bS) {
		StringSplitter S = new StringSplitter(bS, ";"); //Splits between sets of co-ordinates 
		StringSplitter SS = new StringSplitter(S.getStrings()[0], " "); //Splits first co-ordniates again using spaces
		xSize = SS.getIntegers()[0]; //gets and assign the integer x building size
		ySize = SS.getIntegers()[1]; //gets and assign the integer y building size
		//System.out.println(ySize); //Test
		for (int i = 1; i < S.getStrings().length; i++){
			allRooms.add(new Room(S.getStrings()[i]));
		}
		//System.out.println(allRooms);//Test
	}
	
	public int getBuildingx() { //Return the Building x dimension
		return xSize;
	}
	
	public int getBuildingy() { //Return the Building y dimension
		return ySize;
	}
	
	public String toString() {
		String temp = "";
		temp += "Building size " + xSize + "," + ySize + "\n";
		//temp += "Room from " + allRooms.toString();//Test
		for (Room r: allRooms) temp = temp + "Room from " + r.toStringRoomX() + " to " + r.toStringRoomY() + " door at " + r.toStringRoomDoor() + "\n";
		temp += "Occupant is at " + PersonInRoom() + "\n";
		return temp;
	}
	
	public void RoomRandomSelect() {
		randRoom = randGen.nextInt(allRooms.size());
	}
	
	public void showBuilding (BuildingInterface bi) {
		for (Room r: allRooms) r.showRoom(bi);
		occupant.showPerson(bi);
	}
	
	public void movePersoninBuilding() {
		occupant.movePerson();
	}
	
	public static void main(String[] args) {
		Building b = new Building("11 11;0 0 4 4 2 4;6 0 10 10 6 5;0 6 4 10 2 6");
		System.out.println(b.toString());
	}
}
