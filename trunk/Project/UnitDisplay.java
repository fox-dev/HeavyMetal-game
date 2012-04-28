package project;
//This program will post up extra panels onto the game board.
//Currently displays texts: moves/attacks by units (As of April 26, 2012)
//Problem: The console will fall into a loop, printing the previous command,
//because display will be updating ever few seconds.
//Not sure how to stall the comment section of the program yet. Or if it's just how
//the console runs


//The first round of the game results in having a null display instead of a player1 display
//Still need to put the text into an upper panel, while having the lower panel display health
//Will take a while to figure that out
//Or perhaps having a lower panel is sufficient for now.

//DisplayHP only displays Player 1's first unit's HP. **Figured out why. Will fix later*


import java.awt.Color;
import java.awt.Graphics;

public class UnitDisplay {

	private Player player;
	private static String text;
	private static String textPlayer;

	public UnitDisplay(Player player) {
		this.player = player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	//set text from Input.projectfile
	public static void setText(String text1) {
		text = text1;
	}
	
	//get text string/print it out when called
	public static String getText() {
		System.out.println(text);
		return text;
	}
	
	//set current player text
	public static void setPlayer(String text2){
		textPlayer = text2;
	}
	
	//get current player text
	public static String getPlayer(){
		
		if(textPlayer == null){
			textPlayer = "Player 1";
			System.out.println(textPlayer);
			return textPlayer;
		}
		else {
			System.out.println(textPlayer);
			return textPlayer;
		}
	}
	
	//getUnitsHP
	public String getUnitHP() {
		
			Unit selectedUnit = player.getUnit(0);
			if (selectedUnit == null) {
				return null;
			} else
				return Integer.toString(selectedUnit.getHP());
	}

	public boolean isUnit(String s) {
		if (s == null)
			return false;
		else
			return true;
	}
	

	//draws panel
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, GamePanel.GHEIGHT - 100, GamePanel.GWIDTH,
				GamePanel.MHEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(5, GamePanel.GHEIGHT - 95, GamePanel.GWIDTH - 15,
				GamePanel.MHEIGHT - 10);
		g.setColor(Color.BLACK);
		g.fillRect(10, GamePanel.GHEIGHT - 90, GamePanel.GWIDTH - 25,
				GamePanel.MHEIGHT - 20);
		g.setColor(Color.GREEN);
		
		//Displays current player
		
		//Displays Text
		if(getText() != null && isUnit(getUnitHP()))
			g.drawString(" > " + getPlayer() + " " + getText(), 25,
					GamePanel.GHEIGHT - 75);
		else //Greeting
			g.drawString(" > " + "Welcome. Click a Unit to Play. Player 1 will start", 25, GamePanel.GHEIGHT - 75);
		
		
		//Displays HP of Units
		if (isUnit(getUnitHP()))
			g.drawString(" > " +"Selected Unit HP:" + getUnitHP(), 25,
					GamePanel.GHEIGHT - 45);
		else
			g.drawString(" > " + "Select a unit to Display HP", 25, GamePanel.GHEIGHT - 45);
	}
	
}