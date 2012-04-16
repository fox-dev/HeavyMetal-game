package project;

import project.Map;
import project.Player;

//This file will display all maps/actions/units
//And update after each action

public class Display {

		private Map theMap;
		private Player player1;
		private Player player2;
		private String[][] board;


		//constructor
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
		
		public void fillMap(){
			for(int x = 0; x < theMap.getX(); x++){
				for(int y = 0; y < theMap.getY(); y++){
					if(player1.getUnitAt(x, y) != null){
						board[y][x] = "[o]";
					}
					if(player2.getUnitAt(x, y) != null){
						board[y][x] = "[x]";
					}
					else {//If getUnitAt is null
						if(theMap.getArr(x, y) == 2 ){
							board[y][x] = "~~~";
						}
						else{
							board[y][x] = "---";
						}
					}
				}
			}
		}
			
		public void printMap(){
			fillMap();
			for(int x = 0; x < theMap.getX(); x++){
				for(int y = 0; y < theMap.getY(); y++){
					System.out.print(board[x][y] + "  ");
				}
				System.out.println();
				System.out.println();
			}
		}
		

	}
