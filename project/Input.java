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
	 */
	
	
	/*firstCommand = do nothing, or coordinate of unit
	 *secondCommand = cancel, or coordinates to move unit to
	 *thirdCommand = action (attack, do nothing, cancel) 
	*/
	private String firstCommand, secondCommand, thirdCommand;
	Player thePlayer;
	
	public Input(Player p){
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
		thePlayer = p;
	}
	
	public Input(){
		firstCommand = "";
		secondCommand = "";
		thirdCommand = "";
	}
	
	
	//first Command
	public void inputFirstCommand(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter your first command (Unit Coordinates, Cancel, or Do nothing)");
			this.firstCommand = in.readLine();
			System.out.println(firstCommand);
			
		}catch(Exception e){//catch any exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	//second Command
	public void inputSecondCommand(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Please enter your second command (Coordinates of a locaiton to move the selected unit to.)");
			this.secondCommand = in.readLine();
			System.out.println(secondCommand);
			
			
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
			System.out.println(thirdCommand);
			
		}catch(Exception e){//catch any exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}

