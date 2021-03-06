package project;
//
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;


/*Standard input class switched to GUI form.  
 * Read Comments for code explanations below.
 */
public class Input {
	
	private Player activePlayer, waitingPlayer;
	private Actions checkActions; //actions class for checking legal moves/attacks
	private int pressedX,pressedY; //the mouse click locations
	private int mouseX, mouseY; //the mouse locations
	private int realX, realY; //represent pixel locations of the mouseClick
	private Unit selectedUnit; //the selected unit
	
	public Input(Player ap, Player wp, Actions a){
		activePlayer = ap;
		waitingPlayer = wp;
		checkActions = a;
	}
	
	//listener for the mouse that tracks the cursor's current location on the screen.
	public void mouseMoved(MouseEvent e){
		
		mouseX = e.getX() / World.TILE_SIZE;
		mouseY = e.getY() / World.TILE_SIZE;	
	}
	
	//listener for the mouse that tracks where the user last clicked.
	public void mousePressed(MouseEvent e){
		pressedX = e.getX() / World.TILE_SIZE;
		pressedY = e.getY() / World.TILE_SIZE;		
		realX = e.getX();
		realY = e.getY();
	}
	
	
	//Sidra's Update: Added a path to send texts to UnitDisplay.settext("text"); April 26, 2012.
	public int directInput(MouseEvent e){
		mousePressed(e);
		try{
		
		//If the mouse click is within range of the End Turn button, end the player's turn
		if((activePlayer.unitSelected() == false) && realX >= UnitDisplay.ENDBUTTONX  && realY >= UnitDisplay.ENDBUTTONY && realX <= UnitDisplay.ENDBUTTONX + UnitDisplay.ENDBUTTONWIDTH && realY <= UnitDisplay.ENDBUTTONY + UnitDisplay.ENDBUTTONHEIGHT ){
			activePlayer.forceTurnOver();
		}
		
		// If the mouse click is within range of return title button
		if((activePlayer.unitSelected() == false) && realX >= UnitDisplay.TitleButtonX && realY >= UnitDisplay.TitleButtonY && realX <= UnitDisplay.TitleButtonX + UnitDisplay.TitleButtonWidth && realY <= UnitDisplay.TitleButtonY + UnitDisplay.TitleButtonHeight) {
			switch (JOptionPane.showConfirmDialog(null, "Return to title screen?\nThis will end the current game", "", 0, 2)) {
				case 0: return 1;
				case 1: break;
				default: break;
			}
		}
		
		//Initial selected United, select a unit if the player hasn't selected one yet.
		if(!activePlayer.unitSelected()){ 
			//If the clicked area has a friendly unit there, and that unit has not yet --MOVED--, and the user has not yet selected a unit, select that unit.
			if(activePlayer.getUnitAt(pressedX, pressedY) != null && activePlayer.getUnitAt(pressedX, pressedY).hasMoved() == false && activePlayer.unitSelected() == false){
				UnitDisplay.setText("You selected a unit");
				selectedUnit = activePlayer.getUnitAt(pressedX, pressedY);
				selectedUnit.select();
				activePlayer.setSelectedTrue();
			}
			//If the clicked area has a friend unit there, and that unit has not yet --FIRED--, and the user has not yet selected a unit, select that unit.
			if(activePlayer.getUnitAt(pressedX, pressedY) != null && activePlayer.getUnitAt(pressedX, pressedY).getHasUnitShot() == false && activePlayer.unitSelected() == false){ //Check later when End Turn button is added.
				UnitDisplay.setText("You selected a unit");
				selectedUnit = activePlayer.getUnitAt(pressedX, pressedY);
				selectedUnit.select();
				activePlayer.setSelectedTrue();
			}
		}
		//If the user clicks the selected unit again, unselect.
		else if(activePlayer.unitSelected() && selectedUnit != null){  
			if(pressedX == selectedUnit.getLocationX() && pressedY == selectedUnit.getLocationY()){
				UnitDisplay.setText("Unit Unselected");
				selectedUnit.unSelect();
				selectedUnit = null;
				activePlayer.setSelectedFalse();
			}
		}
		

		//If the user has a unit selected, and the place they want to move to is a legal move, and there is no enemy unit there, move to that location.
		if(selectedUnit != null && checkActions.moveLegal(selectedUnit, pressedX, pressedY) == true && waitingPlayer.getUnitAt(pressedX, pressedY) == null){
			UnitDisplay.setText("Move coordinates accepted.");
			selectedUnit.unSelect();
			checkActions.moveUnit(selectedUnit, pressedX, pressedY);
			activePlayer.setSelectedFalse();
			selectedUnit = null;
		}
		//If the user has a unit selected, and the user clicks a spot where an enemy unit is, and the unit the player has selected has not yet fired, check if the unit can fire, then fire.
		if(selectedUnit != null && waitingPlayer.getUnitAt(pressedX, pressedY) != null && selectedUnit.getHasUnitShot() == false && checkActions.fireLegal(selectedUnit, waitingPlayer.getUnitAt(pressedX,pressedY))){ //needs to check if an Attack is valid (range, etc)
			UnitDisplay.setText("Attacking that Unit.");
			checkActions.fire(selectedUnit, waitingPlayer.getUnitAt(pressedX, pressedY));
			activePlayer.setSelectedFalse();
			selectedUnit.unSelect();
			selectedUnit = null;
			waitingPlayer.removeDeadUnits();
			activePlayer.removeDeadUnits();
		}
		
		if(selectedUnit instanceof UnitBase){
			System.out.println("BEFORE");
			if(checkActions.baseSpawnUnits((UnitBase)selectedUnit, pressedX, pressedY)){
  			UnitDisplay.setText("Spawning a unit");
  			activePlayer.setSelectedFalse();
  			selectedUnit.unSelect();
  			selectedUnit = null;
  			System.out.println("Runing");
			}
		}
		}catch(NullPointerException npe){
			UnitDisplay.setText("There is nothing there");
		}
		return 0;
		
	}
	
		//function for switching the activePlayer with the waitingPlayer, used when activePlayer turn ends.
		public void switchPlayerStatuses(){
			Player temp = activePlayer;
			activePlayer = waitingPlayer;
			waitingPlayer = temp;
			
			selectedUnit = null;
		
		}
		
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		//           GET FUNCTIONS
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		//Get function to get the Active Player
		public Player getActivePlayer(){
			return activePlayer;
		}
		
		//Get function to get the Waiting Player
		public Player getWaitingPlayer(){
			return waitingPlayer;
		}
		
		//Get function for the pixel location of the mouseclick X
		public int getRealX(){
			return realX;
		}
		
		//Get function for the pixel location of the mouseclick Y
		public int getRealY(){
			return realY;
		}
		
		//Get function for the location mouseX
		public int getMouseX(){
			return mouseX;
		}
		
		//Get function for the location mouseY
		public int getMouseY(){
			return mouseY;
		}
		
		//Get function for mouse pressed X location
		public int getPressedX(){
			return pressedX;
		}
		
		//Get function for mouse pressed Y location
		public int getPressedY(){
			return pressedY;
		}
		
		//Get function for selected unit
		public Unit getSelectedUnit(){
			return selectedUnit;
		}
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	
	

}








//import java.util.*;
//import java.io.*;
/*
public class Input {

	//Gets user input
	//Checks if move is valid
	//Sends the information to player class
	//Takes in a coordinate for a single unit, coordinate of
	//the location to move it, and the action to be taken 
	//attack, do nothing, cancel.  Initial string can be do nothing.
	
	+
	 * Input command functions are separate to allow for board updating in between inputs.
	 * -secondCommand should only be accessed if the firstCommand = "Select Unit"
	 * -thirdCommand should only be accessed if the firstCommand = "Select Unit"
	 * -secondCommand and thirdCommand should return to previous commands if they = "cancel"
	 -
	
	
	+firstCommand = do nothing, or select unit coordinates
	 *secondCommand = cancel, or move to a coordinate
	 *thirdCommand = action (attack, do nothing, cancel) 
	 *
	 *The following have separate helper classes to hold the x and y integer values
	 *unit_Coordinates = coordinates of a selected unit (not always necessary)
	 *move_Coordinates = coordinates to move a selected unit to (not always necessary)
	 *attack_Coordinates are need if the "thirdCommand" is an attack. (not always necessary)
	-
	private String firstCommand,secondCommand, thirdCommand;
	
	+activePlayer = the player who has control of the board.
	 *waitingPlayer = the player that is waiting.
	 -
	private Player activePlayer, waitingPlayer;
	
	//The unit that is selected, when moved, will also have its location updated.
	private Unit selectedUnit;
	
	private unitCoordinates unit_Coordinates;
	private moveCoordinates move_Coordinates;
	private attackCoordinates attack_Coordinates;
	
	//This needs to be set after the Actions class is created, using the setActions method
	//XXXXXXXXXXXXXXXXXXX***IMPORTANT***XXXXXXXXXXXXXXXXXXXXXXXXXX
	private Actions checkActions;
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	
	public Input(Player aP, Player wP){
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
		
		unit_Coordinates = new unitCoordinates();
		move_Coordinates = new moveCoordinates();
		attack_Coordinates = new attackCoordinates();
		
		
		activePlayer = aP;
		waitingPlayer = wP;
	}
	
	public Input(){
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
		
		unit_Coordinates = new unitCoordinates();
		move_Coordinates = new moveCoordinates();
		attack_Coordinates = new attackCoordinates();
	}
	
	+This setActions method is needed since we cannot include
	 * an Actions object in the input constructor before an Actions object is made.
	 * Therefore you must set the actions later after both the Input object and Actions object
	 * have both been created with their appropriate constructor.
	 -
	public void setActions(Actions a){
		checkActions = a;
	}
	
	
	//first Command (This is the select unit command, if the player chooses Do nothing, their turn ends.)
	public void inputFirstCommand(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter your first command (Select Unit, or Do nothing)");
			this.firstCommand = in.readLine();
		
			while(!(firstCommand.equalsIgnoreCase("Select Unit") || firstCommand.equalsIgnoreCase("Do nothing"))){
				System.out.println("Your first command was not a valid input. Please enter your first command (Select Unit, or Do nothing)");
				this.firstCommand = in.readLine();
			}
			if(firstCommand.equalsIgnoreCase("Select Unit")){
				try{
					System.out.println("Please enter the X coordinate of a friendly unit.");
					unit_Coordinates.setX(Integer.parseInt(in.readLine()));
				
					System.out.println("Please enter the Y coordinate of a friendly unit.");
					unit_Coordinates.setY(Integer.parseInt(in.readLine()));
					
				}catch(NumberFormatException nfe){ 
					System.out.println("Please enter digits only.");
					unit_Coordinates.resetXY();
					inputFirstCommand();
					return;
				}
				
				System.out.println("Checking unit location...");
				if(activePlayer.getUnitAt(unit_Coordinates.getX(), unit_Coordinates.getY()) == null){
					System.out.print("You entered invalid coordinates. ");
					inputFirstCommand();
					return;
				}
				else{
					System.out.println("Unit Coordinates are valid.");
					selectedUnit = activePlayer.getUnitAt(unit_Coordinates.getX(), unit_Coordinates.getY());
				}
			}
	
		}catch(Exception e){//catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	//second Command (This is the movement command, if user inputs Cancel, it should return to the firstCommand)
	+
	 * If a unit is moved, the "moved" boolean of the unit should be set to true;
	 * If "move to coordinate" is typed and the "moved" boolean is set to true, have function return and print that no further movements for that unit can be made.
	 * If "Next" is inputed, the thirdCommand should be prompted, and the unit can still move after an attack.
	 -
	public void inputSecondCommand(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter your second command (Move to Coordinate, Next, or Cancel)");
			this.secondCommand = in.readLine();
		
			while(!(secondCommand.equalsIgnoreCase("Move to Coordinate") || secondCommand.equalsIgnoreCase("Next") || secondCommand.equalsIgnoreCase("Cancel"))){
				System.out.println("Your second command was not a valid input. Please enter your second command (Move to Coordinate, Next, or Cancel)");
				this.secondCommand = in.readLine();
			}
			
			if(secondCommand.equalsIgnoreCase("Move to Coordinate")){
				
				try{
					System.out.println("Please enter the X coordinate of the location to move the selected unit");
					move_Coordinates.setX(Integer.parseInt(in.readLine()));
				
					System.out.println("Please enter the Y coordinate of the location to move the selected unit");
					move_Coordinates.setY(Integer.parseInt(in.readLine()));
					
				}catch(NumberFormatException nfe){
					System.out.println("Please enter digits only.");
					move_Coordinates.resetXY();
					inputSecondCommand();
					return;
				}
				
		
				if(checkActions.moveUnit(selectedUnit, move_Coordinates.getX(), move_Coordinates.getY()) == false){
					System.out.println("You cannot make that move.");
					if(selectedUnit.hasMoved() == true){
						System.out.println("A unit can only move once per turn.");
					}
					inputSecondCommand();
					return;
				}
				else{
					System.out.println("Move coordinates accepted.");
				}
				
			}
			
		}catch(Exception e){//catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	//third Command (This is the attack command)
	+This command should be prompted after the second command, if do nothing is inputed, this ends the movement of the selected unit.
	 * If cancel is selected, the second Command should be re-prompted to the user.
	 -
	public void inputThirdCommand(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter your third command (Attack, Do nothing, Cancel)");
			this.thirdCommand = in.readLine();
			while(!(thirdCommand.equalsIgnoreCase("Attack") || thirdCommand.equalsIgnoreCase("Do nothing") || thirdCommand.equalsIgnoreCase("cancel"))){
				System.out.println("Your third command was not a valid input. Please enter your third command (Attack, Do nothing, Cancel)");
				this.thirdCommand = in.readLine();
			}
			if(thirdCommand.equalsIgnoreCase("Attack")){
				try{
					System.out.println("Please enter the X coordinate of an enemy unit to attack.");
					attack_Coordinates.setX(Integer.parseInt(in.readLine()));
				
					System.out.println("Please enter the Y coordinate of an enemy unit to attack.");
					attack_Coordinates.setY(Integer.parseInt(in.readLine()));
				}catch(NumberFormatException nfe){
					System.out.println("Please enter digits only.");
					attack_Coordinates.resetXY();
					inputThirdCommand();
					return;
				}
				
				if(waitingPlayer.getUnitAt(attack_Coordinates.getX(), attack_Coordinates.getY()) == null){
					System.out.println("There is nothing at that coordinate");
					if(selectedUnit.getHasUnitShot() == true){
						System.out.println("A unit can only attack once per turn.");
					}
					inputThirdCommand();
					return;
				}
				else if(checkActions.fire(selectedUnit, waitingPlayer.getUnitAt(attack_Coordinates.getX(), attack_Coordinates.getY())) == false){
					System.out.println("You cannot attack that unit");
					if(selectedUnit.getHasUnitShot() == true){
						System.out.println("A unit can only attack once per turn.");
					}
					inputThirdCommand();
					return;
				}
				else{
					System.out.println("Enemy Unit Coordinates are valid. Proceed with attack.");
				}
				
				
			}
			
		}catch(Exception e){//catch any exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	//get function for firstCommand string
	public String getFirstCommand(){
		return firstCommand;
	}
	
	//get function for secondCommand string
	public String getSecondCommand(){
		return secondCommand;
	}
		
	//get function for thirdCommand string
	public String getThirdCommand(){
		return thirdCommand;
	}
	
	+XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * the Following functions are COORDINATE getter functions
	 * for X and Y coordinates.
	 *XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX-
	//get function for the selected unit's coordinates
	public int getUnitCoordinates_X(){
		return unit_Coordinates.getX();
	}
	//get function for move coordinates
	public int getMoveCoordinates_X(){
		return move_Coordinates.getX();
	}
	
	//get function for attackCoordinates string
	public int getAttackCoordinates_X(){
		return attack_Coordinates.getX();
	}
	/////////////////////////////////////////////////////////////
	public int getUnitCoordinates_Y(){
		return unit_Coordinates.getY();
	}
	//get function for move coordinates
	public int getMoveCoordinates_Y(){
		return move_Coordinates.getY();
	}
	
	//get function for attackCoordinates string
	public int getAttackCoordinates_Y(){
		return attack_Coordinates.getY();
	}
	/////////////////////////////////////////////////////////////
	
	//Getter function for the current selected unit
	/+*ONLY RETURNS IF A UNIT HAS BEEN SELECTED, OTHERWISE RETURNS NULL**
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	public Unit getSelectedUnit(){
		return selectedUnit;
	}//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	
	//function for switching the activePlayer with the waitingPlayer, used when activePlayer turn ends.
	public void switchPlayerStatuses(){
		Player temp = activePlayer;
		activePlayer = waitingPlayer;
		waitingPlayer = temp;
		
		unit_Coordinates.resetXY();
		move_Coordinates.resetXY();
		attack_Coordinates.resetXY();
		
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
	}
}


+XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 *  Helper Classes for Input Class
 * These classes are basically for keeping track of the following:
 * -Selected Unit Coordinates
 * -Movement Coordinates
 * -Attack Coordinates
 * Note: Default values are set to -1 since -1 is off the map and java
 * does not accept null ints.
 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX-

class unitCoordinates{
	private int x;
	private int y;
	
	public unitCoordinates(){
		
	}
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void resetXY(){
		x = -1;
		y = -1;
	}
}

class moveCoordinates{
	private int x;
	private int y;
	
	public moveCoordinates(){
		x = -1;
		y = -1;
	}
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void resetXY(){
		x = -1;
		y = -1;
	}
	
}

class attackCoordinates{
	private int x;
	private int y;
	
	public attackCoordinates(){
		x = -1;
		y = -1;
	}
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void resetXY(){
		x = -1;
		y = -1;
	}
	
}

*/