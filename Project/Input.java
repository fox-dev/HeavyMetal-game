package project;

mport java.util.*;
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
	 *selectUnitCoordinates = coordinates of a selected unit (not always necessary)
	 *moveCoordiantes = coordinates to move a selected unit to (not always necessary)
	 *attackCoordinates are need if the "thirdCommand" is an attack. (not always necessary)
	*/
	private String firstCommand, secondCommand, thirdCommand, selectUnitCoordinates, moveCoordinates, attackCoordinates;
	
	/*activePlayer = the player who has control of the board.
	 *waitingPlayer = the player that is waiting.
	 */
	Player activePlayer, waitingPlayer;
	
	public Input(Player aP, Player wP){
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
		
		selectUnitCoordinates = "";
		moveCoordinates = "";
		attackCoordinates = "";
		
		activePlayer = aP;
		waitingPlayer = wP;
	}
	
	public Input(){
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
		
		selectUnitCoordinates = "";
		moveCoordinates = "";
		attackCoordinates = "";
	}
	
	
	//first Command
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
				System.out.println("Please enter the coordinates of a friendly unit.");
				selectUnitCoordinates = in.readLine();
				
				System.out.println("Checking unit location...");
				if(activePlayer.getUnitAt(10, 6) == null){
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
	
	//second Command
	public void inputSecondCommand(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter your second command (Move to Coordinate, or enter Stay)");
			this.secondCommand = in.readLine();
		
			while(!(secondCommand.equalsIgnoreCase("Move to Coordinate") || secondCommand.equalsIgnoreCase("Stay"))){
				System.out.println("Your second command was not a valid input. Please enter your second command (The coordinates to move your unit to, or enter Stay)");
				this.secondCommand = in.readLine();
			}
			
			if(secondCommand.equalsIgnoreCase("Move to Coordinate")){
				System.out.println("Please enter the coordinates you want to the selected unit");
				this.moveCoordinates = in.readLine();
				
				if(false){ //Need to check if a unit can make that move
					System.out.println("You cannot make that move.");
					inputSecondCommand();
				}
				
			}
			
		}catch(Exception e){//catch any exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	//third Command
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
				System.out.println("Please enter the coordinates of an enemy unit to attack.");
				this.attackCoordinates = in.readLine();
				
				if(waitingPlayer.getUnitAt(1, 0) == null){//not fully implemented
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
	
	//get function for the selected unit's coordinates
	public String getSelectUnitCoordinates(){
		return selectUnitCoordinates;
	}
	//get function for move coordinates
	public String getMoveCoordinates(){
		return moveCoordinates;
	}
	
	//get function for attackCoordinates string
	public String getAttackCoordinates(){
		return attackCoordinates;
	}
	
	//function for switching the activePlayer with the waitingPlayer, used when activePlayer turn ends.
	public void switchPlayerStatuses(){
		Player temp = activePlayer;
		activePlayer = waitingPlayer;
		waitingPlayer = temp;
		
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
		
		selectUnitCoordinates = "";
		moveCoordinates = "";
		attackCoordinates = "";
	}
}

