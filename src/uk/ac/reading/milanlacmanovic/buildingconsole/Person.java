package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.awt.Point;

public class Person {
	private Point PersonPosition;
	private Point DoorPoint;
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
	
	public void PointSet(Point p) {
		DoorPoint = p;
	}
	public void movePerson() {
		int dx = 0, dy = 0;
		int movex = 0, movey =0;
		if (PersonPosition != DoorPoint) {
			dx = (int) PersonPosition.getX() - (int) DoorPoint.getX();
			dy = (int) PersonPosition.getY() - (int) DoorPoint.getY();
		}
		if (dx > 0) {
			movex = -1;
		}
		else if (dx < 0){
			movex = 1;
		}
		else {
			movex = 0;
		}
		if (dy > 0) {
			movey = -1;
		}
		else if (dy < 0){
			movey = 1;
		}
		else {
			movey = 0;
		}
		PersonPosition.translate(movex, movey);
	}
}
