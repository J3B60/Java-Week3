package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.awt.Point;

/**
 * Building Holds the information of the 
 * building its rooms, the occupant and the random generator
 * @author milan
 *
 */

public class Building {
	private int xSize = 10;				// size of building in x
	private int ySize = 10;				// and y
	private ArrayList<Room> allRooms;	// array of rooms
	private Random randGen;				//Random Generator using Random obj
	private int randRoom;				//Random Room Number
	private Person occupant;			//Person point P on BuildingDraw
	private String OriginalInput; //Original input string for building Constructor to be able to call back for saveing in the building interface
	
	/**
	 * Building Constructor which initialises the Rooms arraylist, Random generator
	 *  and Person, sets up the building calling the setBuilding method.
	 *  Next Path Points also called to be added to the PointPath arraylist in person
	 * @param bS Input string to make Building [Bx By; R1x1 R1y1 R1x2 R1y2 R1Dx R1Dy;...]
	 */
	
	Building(String bS){
		allRooms = new ArrayList<Room>();
		allRooms.clear(); //Arraylist set uo
		OriginalInput = bS; //Input saved
		setBuilding(bS); //Setup the building
		//System.out.println(allRooms);//Test
		randGen = new Random(); //Random generator
		occupant = new Person(allRooms.get(randRoom).getRandom(randGen)); // Occupant start at random point
		occupant.PointSet(allRooms.get(PersonInRoom()-1).getDoorInsidePoint(allRooms.get(PersonInRoom()-1).getDoorPositionRelativetoRoom())); //Set the Persons first point
		nextPathPoint(); //Add the list of Point paths for person to follow next
	}
	
	/**
	 * Returns the Room that the Person is in as an integer, -1 if not in room
	 * @return
	 */
	
	public int PersonInRoom() { /////###Can be changed to have a point argument
		for (int i = 0; i < allRooms.size(); i++) {//Loop to check all rooms
			if (allRooms.get(i).isInRoom(occupant.getPersonPoint())) {//if in a room return room number (The position in array)
				return i + 1; //Rooms start from 1 (not 0)
			}
		}
		return -1; //If not found then return -1
	}
	
	/**
	 * Sets up the building by extracting Building x,y and rooms from input string
	 * Building x,y go into dedicated integer variables
	 * Rooms are put into Room arraylist
	 * @param bS input string from constructor
	 */
	
	public void setBuilding(String bS) {
		StringSplitter S = new StringSplitter(bS, ";"); //Splits between sets of co-ordinates 
		StringSplitter SS = new StringSplitter(S.getStrings()[0], " "); //Splits first co-ordniates again using spaces
		xSize = SS.getIntegers()[0]; //gets and assign the integer x building size
		ySize = SS.getIntegers()[1]; //gets and assign the integer y building size
		//System.out.println(ySize); //Test
		allRooms.clear();
		for (int i = 1; i < S.getStrings().length; i++){
			allRooms.add(new Room(S.getStrings()[i])); //put each separate room as new Room obj in arraylist
		}
		//System.out.println(allRooms);//Test
	}
	
	/**
	 * Getter for the Building x coordinate
	 * @return
	 */
	
	public int getBuildingx() { //Return the Building x dimension
		return xSize;
	}
	
	/**
	 * Getter for the Building y coordinate
	 * @return
	 */
	
	public int getBuildingy() { //Return the Building y dimension
		return ySize;
	}
	
	/**
	 * Outputs Building object information as a string
	 */
	
	public String toString() {
		String temp = "";
		temp += "Building size " + xSize + "," + ySize + "\n";
		//temp += "Room from " + allRooms.toString();//Test
		for (Room r: allRooms) temp = temp + "Room from " + r.toStringRoomX() + " to " + r.toStringRoomY() + " door at " + r.toStringRoomDoor() + "\n";
		temp += "Occupant is in Room: " + PersonInRoom() + "\n";
		return temp;
	}
	
	/**
	 * Selects a random room for occupant to start
	 */
	
	public void RoomRandomSelect() {
		randRoom = randGen.nextInt(allRooms.size());
	}
	
	/**
	 * Passes all rooms and occupant to BuildingInterface for drawing in BuildingDraw
	 * @param bi
	 */
	
	public void showBuilding (BuildingInterface bi) {
		for (Room r: allRooms) r.showRoom(bi);
		occupant.showPerson(bi);
	}
	
	/**
	 * Passes building interface to Person so Person has a 
	 * map of Building and decides how they can move within the building
	 * @param bi
	 */
	
	public void movePersoninBuilding(BuildingInterface bi) {
		occupant.movePerson(bi);//tell Person class to move and the person can know how to move because it has BuildngDraw
	}
	
	/**
	 * A Check if the Person has reached their destination passed to the Building Interface
	 * @return
	 */
	
	public boolean CheckPersonReachedDestination() {
		return occupant.DestinationReached(); //Passed to the building Interface
	}
	
	/**
	 * Adds all the point required for the path to the Person PointPath arraylist (this can be edited to be point by point if required by using an index in building)
	 */
	
	public void nextPathPoint() {
		Point temp = new Point(0,0);
//		temp.setLocation((int)allRooms.get(0).getDoorPoint().getX()+1, (int)allRooms.get(1).getDoorPoint().getY());
//		occupant.addPointPath(temp);
//		temp.setLocation((int)allRooms.get(1).getDoorPoint().getX(), (int)allRooms.get(1).getDoorPoint().getY()-1);
//		occupant.addPointPath(temp);
		occupant.addPointPath(allRooms.get(PersonInRoom()-1).getDoorOutsidePoint(allRooms.get(PersonInRoom()-1).getDoorPositionRelativetoRoom()));
		/////////////////////////////////////////////////////////////////////////////
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
	
	/**
	 * Sent to the building interface to stop calling animate/path if th person has reached
	 * final destination
	 * @return
	 */
	
	public boolean PersonCompletePath() {
		return occupant.CompletePath();//Works as is, sent to BuildingInterface
	}
	
	/**
	 * Gets the Original string input to Building Constructor to be able to take current 
	 * Building and save to file
	 * @return
	 */
	
	public String getOriginalInput() {
		return OriginalInput; //Building Constructor String Input
	}
	
	/**
	 * Building test main
	 * @param args
	 */
	
	public static void main(String[] args) {
		Building b = new Building("11 11;0 0 4 4 2 4;6 0 10 10 6 5;0 6 4 10 2 6");
		System.out.println(b.toString());
	}
}
