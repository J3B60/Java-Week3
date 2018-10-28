package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.awt.Point;
import java.util.Random;
import java.util.Arrays;

/**
 * This class deals with rooms, it takes string input co-ordinates which 
 * include a door and converts to a integer array using string splitter.
 * It can also determine whether a point is inside the room.
 * @author milan
 *
 */

public class Room{
	private int[] roomCoord = new int[6]; //Room Co-ordinates from user input including door
	/**
	 * Creates Splitter object temporarily to get an integer array for
	 * the room from a string input
	 * @param StringSplitter temporary object
	 */
	Room (String S){
		StringSplitter SS = new StringSplitter(S, " "); //String Splitter for user input
		roomCoord = SS.getIntegers(); //Integer Array from string splitter copied
	}
	/**
	 * Returns the information in the Room co-ordinate array as a String
	 * @return roomCoord as String
	 */
	public String toString(){
		return Arrays.toString(roomCoord); //return Array as String
	}
	public String toStringRoomX() {
		return String.valueOf(roomCoord[0]) + "," + String.valueOf(roomCoord[1]);
	}
	public String toStringRoomY() {
		return String.valueOf(roomCoord[2]) + "," + String.valueOf(roomCoord[3]);
	}
	public String toStringRoomDoor() {
		return String.valueOf(roomCoord[4]) + "," + String.valueOf(roomCoord[5]);
	}
	/**
	 * Polymorphed toString to return whether the input point is within the input room
	 * 
	 * @param P is the input Point
	 * @param isInRoomResult is boolean result from running function isInRoom 
	 * @return statement saying if the point is/not in room
	 */
	public String toString(Point P, boolean isInRoomResult){
		if (isInRoomResult == true){
			return (int)P.getX() + ", " + (int)P.getY() + " is in the room"; //getsPoint coords from Point object
		}
		else{
			return (int)P.getX() + ", " + (int)P.getY() + " is not in the room";
		}
		
	}
	/**
	 * Returns a boolean if the point is/not in room 
	 * @param P
	 * @return boolean from if statement
	 */
	public boolean isInRoom(Point P){
		if ((int)P.getX() > roomCoord[0] && (int)P.getX() < roomCoord[2] && (int)P.getY() > roomCoord[1] && (int)P.getY() < roomCoord[3]){ //if its within room boundaries >^v<
			return true;
		}
		else{
			return false;
		}
	}
	
	public Point getRandom(Random randGen) {
		int x;
		int y;
		x = roomCoord[0] + 1 + randGen.nextInt(roomCoord[2] - roomCoord[0] - 2);
		y = roomCoord[1] + 1 + randGen.nextInt(roomCoord[3] - roomCoord[1] - 2);
		Point RandomPoint = new Point(x,y);
		return RandomPoint;
	}
	
	public void showRoom(BuildingInterface bi) {
		bi.showWall(roomCoord[0], roomCoord[3], roomCoord[2], roomCoord[3]);
		bi.showWall(roomCoord[0], roomCoord[1], roomCoord[2], roomCoord[1]);
		bi.showWall(roomCoord[0], roomCoord[1], roomCoord[0], roomCoord[3]);
		bi.showWall(roomCoord[2], roomCoord[1], roomCoord[2], roomCoord[3]);
		bi.showDoor(roomCoord[4], roomCoord[5]);
	}
	
	/**
	 * Used for testing Room class
	 * @param args
	 */
	public static void main(String[] args) {
		Point P = new Point(7,9); //Create test Point object
		// Note to self:(int) P.getX() is an integer returning x coord of Point
		Room r = new Room("0 0 5 5 0 2"); //Create Test Room
		System.out.println(r.toString(P, r.isInRoom(P))); //output to console if point is in room statement
	}
}
