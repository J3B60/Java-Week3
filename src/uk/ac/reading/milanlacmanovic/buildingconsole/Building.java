package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.awt.Point;

public class Building {
	private int xSize = 10;				// size of building in x
	private int ySize = 10;				// and y
	private ArrayList<Room> allRooms;	// array of rooms
	private Random randGen;				//Random Generator using Random obj
	private int randRoom;
	private Person occupant;
	private String OriginalInput;
	
	Building(String bS){
		allRooms = new ArrayList<Room>();
		allRooms.clear();
		OriginalInput = bS;
		setBuilding(bS);
		//System.out.println(allRooms);//Test
		randGen = new Random();
		occupant = new Person(allRooms.get(randRoom).getRandom(randGen));
		occupant.PointSet(allRooms.get(PersonInRoom()-1).getDoorPoint());
		nextPathPoint();
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
		allRooms.clear();
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
		temp += "Occupant is in Room: " + PersonInRoom() + "\n";
		return temp;
	}
	
	public void RoomRandomSelect() {
		randRoom = randGen.nextInt(allRooms.size());
	}
	
	public void showBuilding (BuildingInterface bi) {
		for (Room r: allRooms) r.showRoom(bi);
		occupant.showPerson(bi);
	}
	
	public void movePersoninBuilding(BuildingInterface bi) {
		occupant.movePerson(bi);
	}
	
	public boolean CheckPersonReachedDestination() {
		return occupant.DestinationReached();
	}
	
	public void nextPathPoint() {
		Point temp = new Point(0,0);
//		temp.setLocation((int)allRooms.get(0).getDoorPoint().getX()+1, (int)allRooms.get(1).getDoorPoint().getY());
//		occupant.addPointPath(temp);
//		temp.setLocation((int)allRooms.get(1).getDoorPoint().getX(), (int)allRooms.get(1).getDoorPoint().getY()-1);
//		occupant.addPointPath(temp);
		occupant.addPointPath(allRooms.get(1).getDoorOutsidePoint(allRooms.get(1).getDoorPositionRelativetoRoom()));
		occupant.addPointPath(allRooms.get(1).getDoorInsidePoint(allRooms.get(1).getDoorPositionRelativetoRoom()));
		occupant.addPointPath(allRooms.get(1).getRandom(randGen));
		occupant.addPointPath(allRooms.get(1).getDoorInsidePoint(allRooms.get(1).getDoorPositionRelativetoRoom()));
		occupant.addPointPath(allRooms.get(1).getDoorOutsidePoint(allRooms.get(1).getDoorPositionRelativetoRoom()));
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		occupant.addPointPath(allRooms.get(2).getDoorOutsidePoint(allRooms.get(2).getDoorPositionRelativetoRoom()));
		occupant.addPointPath(allRooms.get(2).getDoorInsidePoint(allRooms.get(2).getDoorPositionRelativetoRoom()));
		occupant.addPointPath(allRooms.get(2).getRandom(randGen));
//		temp.setLocation((int)allRooms.get(1).getDoorPoint().getX(), (int)allRooms.get(1).getDoorPoint().getY()+1);
//		occupant.addPointPath(temp);
//		temp.setLocation((int)allRooms.get(1).getDoorPoint().getX(), (int)allRooms.get(1).getDoorPoint().getY()-1);
//		occupant.addPointPath(temp);
//		temp.setLocation((int)allRooms.get(2).getDoorPoint().getX()-1, (int)allRooms.get(1).getDoorPoint().getY());
//		occupant.addPointPath(temp);
//		occupant.addPointPath(allRooms.get(2).getDoorPoint());
//		occupant.addPointPath(allRooms.get(2).getRandom(randGen));
	}
	
	public boolean PersonCompletePath() {
		return occupant.CompletePath();//Different Options working on TODO
	}
	
	public String getOriginalInput() {
		return OriginalInput;
	}
	
	public static void main(String[] args) {
		Building b = new Building("11 11;0 0 4 4 2 4;6 0 10 10 6 5;0 6 4 10 2 6");
		System.out.println(b.toString());
	}
}
