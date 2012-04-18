package project;

public class Main {
	// Create players and map and input
	static Map map = new Map();
	static Player player1 = new Player(1);
	static Player player2 = new Player(2);

	/*
	 * Main game loop 
	 *   Call display to display information 
	 *   Call Input to get user Input 
	 *   Send Input and Player to Actions class 
	 *   Change player turn.
	 */

	public static void main(String[] args) {
		String NL = System.getProperty("line.separator");
		boolean gameDone = false;
		Input input = new Input(player1, player2);
		Display gameDisplay = new Display(map, player1, player2);
		Actions actions = new Actions(player1, player2, map);
		input.setActions(actions);
		Player currentPlayer = player1;
		
		while (!gameDone) {
			gameDisplay.printMap();
			System.out.println(NL);
			input.inputFirstCommand();
			input.inputSecondCommand();
			input.inputThirdCommand();
			player1.removeDeadUnits();
			player2.removeDeadUnits();
			if(currentPlayer.checkTurnOver()) {
			  currentPlayer.unitsReset();  //added by Dan
				if(currentPlayer == player1)
					currentPlayer = player2;
				else
					currentPlayer = player1;
				gameDisplay.printMap(); // Refresh map after turn is over
			}
			if(player1.checkNumUnits() == 0)
				gameDone = true;
			else if (player2.checkNumUnits() == 0)
				gameDone = true;
			
		}

	}

}