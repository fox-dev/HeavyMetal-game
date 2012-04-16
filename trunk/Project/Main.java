package project;

public class Main {
	// Create players and map and input
	static Map map = new Map();
	static Player player1 = new Player(1);
	static Player player2 = new Player(2);
	static Input input = new Input();

	/*
	 * Main game loop 
	 *   Call display to display information 
	 *   Call Input to get user Input 
	 *   Send Input and Player to Actions class 
	 *   Change player turn.
	 */

	public static void main(String[] args) {
		boolean gameDone = false;
		Display gameDisplay = new Display(map, player1, player2);
		Actions actions = new Actions(player1, player2, input);
		Player currentPlayer = player1;
		
		while (!gameDone) {
			// display game window
			// get user input
			// update actions
			if(currentPlayer.checkTurnOver()) {
			  currentPlayer.unitsResetMoved();  //added by Dan
				if(currentPlayer == player1)
					currentPlayer = player2;
				else
					currentPlayer = player1;
			}
			if(player1.checkNumUnits() == 0)
				gameDone = true;
			else if (player2.checkNumUnits() == 0)
				gameDone = true;
			
		}

	}

}
