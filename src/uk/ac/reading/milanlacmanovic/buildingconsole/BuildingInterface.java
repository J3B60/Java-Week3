package uk.ac.reading.milanlacmanovic.buildingconsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

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
	 	* Commands are N, D, M, A, P, U, S, L, I, X
	 */
	public BuildingInterface() {
		  s = new Scanner(System.in);	// set up scanner for user input
	    int bno = 1;			// initially building 1 selected
	
	    myBuilding = new Building(buildingString(bno));// create building
		
	    char ch = ' ';
	    do {
	       	System.out.print("(N)ew buidling, (D)raw, (M)ove, (A)nimate, (P)ath, (U)ser Input New Building, (S)ave, (L)oad, (I)nfo, e(X)it > ");
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
	    		case 'M' :
	    		case 'm' :
	    				doDisplay(); //Cheap way to get the map out ready for person to use Without Feedback
		    			myBuilding.movePersoninBuilding(this);
		    			break;
	    		case 'A' :
	    		case 'a' :
	    				doDisplay();//Same reason as M
		    			if (!myBuilding.PersonCompletePath()) {
		    				animate();
		    			}
		    			break;
	    		case 'P' :
	    		case 'p' :
	    				doDisplay(); //Same reason as M
	    				while(!myBuilding.PersonCompletePath()) {
	    					animate();
	    				}
	    				break;
	    		case 'U' :
	    		case 'u' :
	    				myBuilding = new Building(UserInputBuilding());
	    			break;
	    		case 'S':
	    		case 's':
	    				SaveFile();
	    			break;
	    		case 'L':
	    		case 'l':
	    				myBuilding = new Building(LoadFile());
	    			break;
	     		case 'x' : 	ch = 'X';	// when X detected program ends
	    				break;
		 
	    	}
		} while (ch != 'X');			// test if end
	
	   s.close();					// close scanner
	}
	
	/**
	 * 
	 * @return String containing
	 */
	
	public String doDisplay() {
		BuildingDraw = new char[myBuilding.getBuildingx() +2][myBuilding.getBuildingy() + 2]; //Switched x and y around//Setup char array to size of building
		String temp = "";
		showBuildingWall();
		myBuilding.showBuilding(this);
		for (int i = 0; i < BuildingDraw.length; i++) { //Output as Strings
			for (int j = 0; j < BuildingDraw[i].length; j++) {
				temp += String.valueOf(BuildingDraw[i][j]);
			}
			temp += "\n";
		}
			//NOTE TO SELF: X and Y are flipped, because x,y to j,i not i,j (matricies notation) TODO FIX
//		for (int k = 0; k < BuildingDraw.length; k++) { //TEST //OUTPUTS AS ARRAY View
//			System.out.println(Arrays.toString(BuildingDraw[k]));//Test
//		}//TEST
		return temp;
	}
	public void showBuildingWall() {
		Arrays.fill(BuildingDraw[0], '#');
		for (int i = 1; i < BuildingDraw.length -1; i++) {
			BuildingDraw[i][0] = '#';
			for (int j = 1; j < BuildingDraw[i].length - 1; j++) {
				BuildingDraw[i][j] = ' ';
			}
			BuildingDraw[i][BuildingDraw[0].length -1] = '#';
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
	
	public void animate() {
		while (!myBuilding.CheckPersonReachedDestination()) {
			myBuilding.movePersoninBuilding(this);
			System.out.println(doDisplay());
			try {
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public char[][] getBuildingDraw(){
		return BuildingDraw;
	}
	
	public String UserInputBuilding() {
		String userIn = JOptionPane.showInputDialog(null, "Enter Building using format [Bx By; R1x1 R1y1 R1x2 R1y2 R1Dx R1Dy; ...]");
		return userIn;
	}
	
	public void SaveFile() {
		int option;
		File selectedFile;
		JFileChooser jfc = new JFileChooser();
		option = jfc.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
		}
		else {
			selectedFile = null; //##Bad error handling
		}
		try (PrintWriter out = new PrintWriter(selectedFile)) {
				out.println(myBuilding.getOriginalInput());
				out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String LoadFile() {
		int option;
		File selectedFile;
		JFileChooser jfc = new JFileChooser();
		option = jfc.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
		}
		else {
			selectedFile = null;
		}
		String temp = "";
		try {
			Scanner sc = new Scanner(selectedFile);
			while (sc.hasNextLine()) {
				temp += sc.nextLine();
			}
			sc.close();
//			System.out.println(temp);//TEST
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
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
