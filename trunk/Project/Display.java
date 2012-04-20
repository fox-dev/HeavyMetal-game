package project;

import project.Map;
import project.Player;

//This file will display all maps/actions/units
//And update after each action if called on; Displaying moves once the Player class is updated.

public class Display {

	private Map theMap;
	private Player player1;
	private Player player2;
	private String[][] board;


	//set constructor
	public Display(Map m, Player p1, Player p2){
		theMap = m;
		player1 = p1;
		player2 = p2;
		board = new String[m.getX()][m.getY()];
		for(int x = 0; x < 20; x++){
			for(int y = 0; y < 20; y++){
				board[x][y] = Integer.toString(m.getArr(x, y)) + "  ";
			}
		}
	}
	
	//Fill map with content: 
	//Unit Locations; Player1 units O / Player2 units X;
	//Map Terrain (Water: ~ /Land: - );
	
	//board[][] uses (Y,X). This corrects the reversed coordinates problem (19,0) will place
	//	a unit at the bottom-left instead of top-right
	
	public void fillMap(){
		for(int x = 0; x < theMap.getX(); x++){
			for(int y = 0; y < theMap.getY(); y++){
				if(player1.getUnitAt(x, y) != null){ 
					//If player1 units are in Map coordinates (x,y), fill with [o]
					board[y][x] = "O";
				}
				if(player2.getUnitAt(x, y) != null){
					//If player2 units are in Map coordinates (x,y), fill with [x]
					board[y][x] = "X";
				}
				else if(player2.getUnitAt(x, y) == null && player1.getUnitAt(x,y) == null){
					//If unit coordinates are not filled with either players, check...
					if(theMap.getArr(x, y) == 2 ){
						//...if map input is 2 (As stated in Map.java), fill with icon for water
						board[y][x] = "~";
					}
					else{
						//...if map input is 1, or not 2, (As stated in Map.java), 
						//   fill with icon for land
						board[y][x] = "-";
					}
				}
			}
		}
	}

	
	public void printMap(){
		fillMap();
		
		System.out.println("");

		for(int i = 0; i < theMap.getX(); i++){ //Print Numbers;
			if(i < 10){
				if(i == 0)
					System.out.print("  ");	
				System.out.print(i);
				System.out.print("  ");
			}
			else{
				System.out.print(i);
				System.out.print(" ");
			}
		}
		
		for(int x = 0; x < theMap.getX(); x++){
			System.out.println("");	
			for(int y = 0; y < theMap.getY(); y++){
				if(y == 0){
					System.out.print("  ");	
				}
				//Print coordinate icons filled in array by calling fillMap();
				System.out.print(board[x][y] + "  ");
				
				if(y == 19){ //Print Numbers;
					System.out.print(x);
					System.out.print(" ");
				}
			}
		}
		System.out.println();
		System.out.println();
	}
	
	// Francisco Edit: Print the current player and the description of Units
	// P1 = Active player, P2 = Waiting player
	public void printStatus(Player p1, Player p2) {
		System.out.println("Player " + p1.getPlayerNum() + "'s turn");
		System.out.println("Status:"); 
		for (int i = 0; i < p1.checkNumUnits(); i++) {
			switch(p1.getUnit(i).getType()) {
			case 0: System.out.print("Ground Unit at (" + p1.getUnit(i).getLocationX() + "," + p1.getUnit(i).getLocationY() + ") has " + p1.getUnit(i).getHP() + " HP, " + p1.getUnit(i).getAttack() + " ATK and has " + p1.getUnit(i).getMoves() + " moves.");
					if(!p1.getUnit(i).hasMoved())
						System.out.print(" Has not moved yet.");
					else
						System.out.print(" Has already moved.");
					if (!p1.getUnit(i).getHasUnitShot())
						System.out.println(" Has not attacked yet.");
					else
						System.out.println(" Has attacked.");
					break;
			case 1: System.out.print("Air Unit at (" + p1.getUnit(i).getLocationX() + "," + p1.getUnit(i).getLocationY() + ") has " + p1.getUnit(i).getHP() + " HP, " + p1.getUnit(i).getAttack() + " ATK and has " + p1.getUnit(i).getMoves() + " moves.");
					if(!p1.getUnit(i).hasMoved())
						System.out.print(" Has not moved yet.");
					else
						System.out.print(" Has already moved.");
					if (!p1.getUnit(i).getHasUnitShot())
						System.out.println(" Has not attacked yet.");
					else
						System.out.println(" Has attacked.");
					break;
			default: System.out.print("Generic Unit at (" + p1.getUnit(i).getLocationX() + "," + p1.getUnit(i).getLocationY() + ") has " + p1.getUnit(i).getHP() + " HP, " + p1.getUnit(i).getAttack() + " ATK and has " + p1.getUnit(i).getMoves() + " moves.");
					if(!p1.getUnit(i).hasMoved())
						System.out.print(" Has not moved yet.");
					else
						System.out.print(" Has already moved.");
					if (!p1.getUnit(i).getHasUnitShot())
						System.out.println(" Has not attacked yet.");
					else
						System.out.println(" Has attacked.");
					break;
			}
		}
		System.out.println(""); // New line
		System.out.println("Enemy status:");
		for (int i = 0; i < p2.checkNumUnits(); i++) {
			switch(p2.getUnit(i).getType()) {
			case 0: System.out.println("Ground Unit at (" + p2.getUnit(i).getLocationX() + "," + p2.getUnit(i).getLocationY() + ") has " + p2.getUnit(i).getHP() + " HP, " + p2.getUnit(i).getAttack() + " ATK."); break;
			case 1: System.out.println("Air Unit at (" + p2.getUnit(i).getLocationX() + "," + p2.getUnit(i).getLocationY() + ") has " + p2.getUnit(i).getHP() + " HP, " + p2.getUnit(i).getAttack() + " ATK."); break;
			default: System.out.println("Generic Unit at (" + p2.getUnit(i).getLocationX() + "," + p2.getUnit(i).getLocationY() + ") has " + p2.getUnit(i).getHP() + " HP, " + p2.getUnit(i).getAttack() + " ATK."); break;
			}
		}
		System.out.println(""); // New line
	}
}
