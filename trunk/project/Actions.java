/** Program Name: Actions.java
 *  Name: Dan Q. Nguyen
 *  Professor: Yang, David
 *  Class: CS4310
 *  Description:  
 *  Date Start: April 7, 2012
 *  Date Last Modified: mmddyy/time
 *  	-n/a
 *  Modification List: mmddyy/time
 *  	-n/a
*/

package project;

import project.Input;
import project.Player;

public class Actions {
	
	Player player1, player2;
	Input input;
	
	//Check Input, do any actions involved, update Player class
	//Figure out when turn is over by checking player information
	//Calls information on units in the Player passed in
	//Update player locations
	//Update player health
	//Update everything else
	
	public Actions(Player play1, Player play2, Input inp) {
		player1 = play1;
		player2 = play2;
		input = inp;
	}
	
	
}
