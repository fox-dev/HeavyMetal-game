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
		//Unit Locations; Player1 units [o] / Player2 units [x];
		//Map Terrain (Water: ~ /Land: - );
		
		public void fillMap(){
			for(int x = 0; x < theMap.getX(); x++){
				for(int y = 0; y < theMap.getY(); y++){
					if(player1.getUnitAt(x, y) != null){ 
						//If player1 units are in Map coordinates (x,y), fill with [o]
						board[x][y] = "[o]";
					}
					if(player2.getUnitAt(x, y) != null){
						//If player2 units are in Map coordinates (x,y), fill with [x]
						board[x][y] = "[x]";
					}
					else {
						//If unit coordinates are not filled with either players, check...
						if(theMap.getArr(x, y) == 2 ){
							//...if map input is 2 (As stated in Map.java), fill with icon for water
							board[x][y] = "~~~";
						}
						else{
							//...if map input is 1, or not 2, (As stated in Map.java), 
							//   fill with icon for land
							board[x][y] = "   ";
						}
					}
				}
			}
		}
		
		/*public void topGrid(){
			System.out.print("┌──────────────────────────────────────────┐");
			}
		
		public void leftGrid(){
			System.out.print("│");
			}
		
		public void rightGrid(){
			System.out.print("│");
			}
			
		public void bottomGrid(){
			System.out.print("└──────────────────────────────────────────┘");
			}
		*/
		
		public void printMap(){
			fillMap();
			
			//topGrid();
			for(int x = 0; x < theMap.getX(); x++){
				if(x == 0){
					//leftGrid();
				}
				for(int y = 0; y < theMap.getY(); y++){
					//Print coordinate icons filled in array by calling fillMap();
					System.out.print(board[x][y] + "  ");
					if(x == 19){
						//rightGrid();
					}
				}
			}
			//bottomGrid();
			//System.out.println();
			//System.out.println();
		}

	}
