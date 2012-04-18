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
}
