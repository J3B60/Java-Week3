package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.awt.Point;
import java.util.Arrays;

public class Room{
	private int[] roomCoord = new int[6];
	Room (String S){
		StringSplitter SS = new StringSplitter(S, " ");
		roomCoord = SS.getIntegers();
	}
	public String toString(){
		return Arrays.toString(roomCoord);
	}
	public String toString(Point P, boolean isInRoomResult){
		if (isInRoomResult == true){
			return (int)P.getX() + ", " + (int)P.getY() + " is in the room";
		}
		else{
			return (int)P.getX() + ", " + (int)P.getY() + " is not in the room";
		}
		
	}
	public boolean isInRoom(Point P){
		if ((int)P.getX() > roomCoord[0] && (int)P.getX() < roomCoord[2] && (int)P.getY() > roomCoord[1] && (int)P.getY() < roomCoord[3]){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		Point P = new Point(7,9);
		//(int) P.getX() is an integer returning x coord of Point
		Room r = new Room("0 0 5 5 0 2");
		System.out.println(r.toString(P, r.isInRoom(P)));
	}
}
