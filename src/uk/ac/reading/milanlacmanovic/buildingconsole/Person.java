package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.awt.Point;
import java.util.ArrayList;

public class Person {
	private Point PersonPosition;
	private ArrayList<Point> PointPath;
	private int index=0;
	private Boolean PathisCompleted = false;
	
	Person(int x, int y){
		PersonPosition = new Point(x,y); //Initialise the Point Object
//		PointPath = new ArrayList<Point>();
//		PointPath.clear();
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
		PointPath = new ArrayList<Point>();
		PointPath.clear();
		PointPath.add(p);
	}
	public void movePerson(BuildingInterface bi) {
		int dx = 0, dy = 0;
		int movex = 0, movey =0;
		if(index < PointPath.size()) {
			if (PersonPosition != PointPath.get(index)) {
				dx = (int) PersonPosition.getX() - (int) PointPath.get(index).getX();
				dy = (int) PersonPosition.getY() - (int) PointPath.get(index).getY();
			}
			if (dx > 0 && bi.getBuildingDraw()[(int) PersonPosition.getX()][(int) PersonPosition.getY()+1] == ' ') {
				movex = -1;
			}
			else if (dx < 0 && bi.getBuildingDraw()[(int) PersonPosition.getX()+2][(int) PersonPosition.getY()+1] == ' '){
				movex = 1;
			}
			else {
				movex = 0;
			}
			if (dy > 0 && bi.getBuildingDraw()[(int) PersonPosition.getX()+1][(int) PersonPosition.getY()] == ' ') {
				movey = -1;
			}
			else if (dy < 0 && bi.getBuildingDraw()[(int) PersonPosition.getX()+1][(int) PersonPosition.getY()+2] == ' '){
				movey = 1;
			}
			else {
				movey = 0;
			}
			PersonPosition.translate(movex, movey);
		}
		else {
			PathisCompleted = true;
		}
	}
	public boolean DestinationReached() {
		if (index < PointPath.size()) { //////TODO Need to fix out of bounds with PointPath arraylist and index
			if (PersonPosition.getX() == PointPath.get(index).getX() && PersonPosition.getY() == PointPath.get(index).getY()) {
				if (index < PointPath.size()) {
					index++;
				}
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public boolean CompletePath() {
		if (PointPath.size() == index) {
			return true;
		}
		else {
			return false;
		}
	}
	
//	public void CheckDoorType(BuildingInterface bi) {
//		if (bi.getBuildingDraw()[(int)PointPath.get(index).getX() + 1 ][(int)PointPath.get(index).getY()] == '-' && bi.getBuildingDraw()[(int)PointPath.get(index).getX() - 1 ][(int)PointPath.get(index).getY()] == '-') {
//			//This is a horizontal Door
//		}
//		else if (bi.getBuildingDraw()[(int)PointPath.get(index).getX()][(int)PointPath.get(index).getY()+1] == '|' && bi.getBuildingDraw()[(int)PointPath.get(index).getX()][(int)PointPath.get(index).getY()-1] == '|') {
//			//This is a vertical Door
//		}
//		else{
//			//nothing, point is probably random
//		}
//	}
	
	public void addPointPath(Point p) {
		PointPath.add(p);
	}
	
	public Boolean getPathisCompleted() {
		return PathisCompleted;
	}
}
