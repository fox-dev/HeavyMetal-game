package project;

import java.util.*;
import java.io.*;
public class Input {
	//Gets user input
	//Checks if move is valid
	//Sends the information to player class
	//Takes in a coordinate for a single unit, coordinate of
	//the location to move it, and the action to be taken 
	//attack, do nothing, cancel.  Initial string can be do nothing.
	
	/*
	 * Input command functions are separate to allow for board updating in between inputs.
	 * -secondCommand should only be accessed if the firstCommand = "Select Unit"
	 * -thirdCommand should only be accessed if the firstCommand = "Select Unit"
	 * -secondCommand and thirdCommand should return to previous commands if they = "cancel"
	 */
	
	
	/*firstCommand = do nothing, or select unit coordinates
	 *secondCommand = cancel, or move to a coordinate
	 *thirdCommand = action (attack, do nothing, cancel) 
	 *
	 *The following have separate helper classes to hold the x and y integer values
	 *unit_Coordinates = coordinates of a selected unit (not always necessary)
	 *move_Coordinates = coordinates to move a selected unit to (not always necessary)
	 *attack_Coordinates are need if the "thirdCommand" is an attack. (not always necessary)
	*/
	private String firstCommand,secondCommand, thirdCommand;
	
	/*activePlayer = the player who has control of the board.
	 *waitingPlayer = the player that is waiting.
	 */
	Player activePlayer, waitingPlayer;
	
	private unitCoordinates unit_Coordinates;
	private moveCoordinates move_Coordinates;
	private attackCoordinates attack_Coordinates;
	
	
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
			if(firstCommand.equalsIgnoreCase("Select Unit")){ //Not fully implemented yet
				try{
					System.out.println("Please enter the X coordinate of a friendly unit.");
					unit_Coordinates.setX(Integer.parseInt(in.readLine()));
				
					System.out.println("Please enter the Y coordinate of a friendly unit.");
					unit_Coordinates.setY(Integer.parseInt(in.readLine()));
					
				}catch(NumberFormatException nfe){ 
					System.out.println("Please enter digits only.");
					unit_Coordinates.resetXY();
					inputFirstCommand();
				}
				
				System.out.println("Checking unit location...");
				if(activePlayer.getUnitAt(unit_Coordinates.getX(), unit_Coordinates.getY()) == null){
					System.out.print("You entered invalid coordinates. ");
					inputFirstCommand();
				}
				else{
					System.out.println("Unit Coordinates are valid.");
				}
			}
	
		}catch(Exception e){//catch any exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	//second Command (This is the movement command, if user inputs Cancel, it should return to the firstCommand)
	/*
	 * If a unit is moved, the "moved" boolean of the unit should be set to true;
	 * If "move to coordinate" is typed and the "moved" boolean is set to true, have function return and print that no further movements for that unit can be made.
	 * If "Next" is inputed, the thirdCommand should be prompted, and the unit can still move after an attack.
	 */
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
				}
				
		
				if(false){ //Need to check if a unit can make that move
					System.out.println("You cannot make that move.");
					inputSecondCommand();
				}
				
			}
			
		}catch(Exception e){//catch any exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	//third Command (This is the attack command)
	/*This command should be prompted after the second command, if do nothing is inputed, this ends the movement of the selected unit.
	 * If cancel is selected, the second Command should be re-prompted to the user.
	 */
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
				}
				
				
				if(waitingPlayer.getUnitAt(attack_Coordinates.getX(), attack_Coordinates.getY()) == null){//not fully implemented
					System.out.print("The coordinates you entered for the enemy unit are invalid. ");
					inputThirdCommand();
				}
				else{
					System.out.println("Enemy Unit Coordinates are valid.");
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
	
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * the Following functions are COORDINATE getter functions
	 * for X and Y coordinates.  These methods can be called
	 * by the Input class without having to reference
	 * the helper classes in other classes.
	 *XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
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
	
	//function for switching the activePlayer with the waitingPlayer, used when activePlayer turn ends.
	public void switchPlayerStatuses(){
		Player temp = activePlayer;
		activePlayer = waitingPlayer;
		waitingPlayer = temp;
		
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
	}
}

/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 *  Helper Classes for Input Class
 * These classes are basically for keeping track of the following:
 * -Selected Unit Coordinates
 * -Movement Coordinates
 * -Attack Coordinates
 * Note: Default values are set to -1 since -1 is off the map and java
 * does not accept null ints.
 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/

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

