package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.awt.Point;

public class Person {
	private Point PersonPosition;
	Person(int x, int y){
		PersonPosition = new Point(x,y); //Initialise the Point Object
	}
	
	Person(Point random) {
		PersonPosition = random; //Assign Random Point position (from building class)
	}
	
	public Point getPersonPoint() {
		return PersonPosition; //Get Information of Person as a Point
	}
	
	public void setPersonPosition(int x, int y) {
		PersonPosition.setLocation(x, y); //Set X and Y
	}

	public int getPersonPositionX() {
		return (int)PersonPosition.getX(); //Return X Coord of Person
	}
	
	public int getPersonPositionY() {
		return (int)PersonPosition.getY(); //Return Y Coord of person
	}
	
	public String toString(){
		return "" + (int)PersonPosition.getX() + "," + (int)PersonPosition.getY(); //Return PersonPosition Info as a String
	}
	
	public void showPerson(BuildingInterface bi) {
		bi.showIt((int)PersonPosition.getX(), (int)PersonPosition.getY(), 'P'); //(int) P.getX() is replaceable with getPersonPositionX() etc
	}
}
