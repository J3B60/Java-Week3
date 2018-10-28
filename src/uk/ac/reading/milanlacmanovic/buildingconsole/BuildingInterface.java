package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.util.Arrays;
import java.util.Scanner;

public class BuildingInterface {
	Scanner s;	//scanner used for input from user
	private char[][] BuildingDraw;
	
	Building myBuilding;
	/**
	 * return as String definition of bOpt'th building
	 * @param bOpt
	 */
	public String buildingString (int bOpt) {
		if (bOpt == 1) {
			return "11 11;0 0 4 4 2 4;6 0 10 10 6 5;0 6 4 10 2 6";
		}
		else {
			return "40 12;0 0 15 4 8 4;15 0 30 4 22 4;0 6 10 11 6 6";
		}
	}
	/**
	 	* constructor for BuildingInterface
	 	* sets up scanner used for input and the arena
	 	* then has main loop allowing user to enter commands
	 */
	public BuildingInterface() {
		  s = new Scanner(System.in);	// set up scanner for user input
	    int bno = 1;			// initially building 1 selected
	
	    myBuilding = new Building(buildingString(bno));// create building
		
	    char ch = ' ';
	    do {
	       	System.out.print("(N)ew buidling, (D)raw, (I)nfo, e(X)it > ");
	    	ch = s.next().charAt(0);
	    	s.nextLine();
	    	switch (ch) {
	   		case 'N' :
	    		case 'n' :
	    				bno = 3 - bno;  // change 1 to 2 or 2 to 1
	    				myBuilding.setBuilding(buildingString(bno));
	    				break;
	    		case 'I' :
	    		case 'i' :
						System.out.print(myBuilding.toString());
						break;
	    		case 'D' :
	    		case 'd' :
	    			System.out.println(doDisplay());
	    			break;
	     		case 'x' : 	ch = 'X';	// when X detected program ends
	    				break;
		 
	    	}
		} while (ch != 'X');			// test if end
	
	   s.close();					// close scanner
	}
	
	public String doDisplay() {
		BuildingDraw = new char[myBuilding.getBuildingx() +2][myBuilding.getBuildingy() + 2]; //Switched x and y around//Setup char array to size of building
		String temp = "";
		showBuildingWall();
		myBuilding.showBuilding(this);
//		for (int i = 0; i < BuildingDraw.length; i++) { //Output as Strings
//			for (int j = 0; j < BuildingDraw[i].length; j++) {
//				temp += String.valueOf(BuildingDraw[i][j]);
//			}
//			temp += "\n";
//		}//NOTE TO SELF: X and Y are flipped, because x,y to j,i not i,j (matricies notation) TODO FIX
		for (int k = 0; k < BuildingDraw.length; k++) { //TEST //OUTPUTS AS ARRAY View
			System.out.println(Arrays.toString(BuildingDraw[k]));//Test
		}//TEST
		return temp;
	}
	public void showBuildingWall() {
		Arrays.fill(BuildingDraw[0], '#');
		for (int i = 1; i < BuildingDraw.length -1; i++) {
			BuildingDraw[i][0] = '#';
			for (int j = 1; j < BuildingDraw[i].length - 1; j++) {
				BuildingDraw[i][j] = ' ';
			}
			BuildingDraw[i][BuildingDraw.length -1] = '#';
		}
		Arrays.fill(BuildingDraw[BuildingDraw.length - 1], '#');
//for (int k = 0; k < BuildingDraw.length; k++) { //TEST
//	System.out.println(Arrays.toString(BuildingDraw[k]));//Test
//}//TEST
	}
	
	public void showIt(int x, int y, char ch) {
		BuildingDraw[x+1][y+1] = ch;//Y is top down//Swapped x and y to convert to matrix row first column next
	}
	
	public void showWall(int xa, int ya, int  xb, int yb) {
		if (ya == yb) {
			for (int i = xa+1; i < xb; i++) {// Fill complete
				showIt(i, ya, '|'); //+1 Added to offset coords by 1 so that rooms are displayed within the building boundaries, Building size should always be bigger than room coords, Y coord can be either ya or yb since equal
			}//Swapped y and x coords around for test
		}
		if (xa == xb) {
			for (int j = ya; j <= yb; j++) {// To fill inbetween horizontal walls just as in task sheet diagram
				showIt(xa, j, '-'); //Can either be xa or xb since equal 
			}
		}
	}
	
	public void showDoor(int x, int y) {
		showIt(x, y, ' ');
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuildingInterface bi = new BuildingInterface();	////###NOTE: Major Change from b to bi
	//just call the interface
	}
}
