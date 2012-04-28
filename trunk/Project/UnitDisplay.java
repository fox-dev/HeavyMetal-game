package project;
//This program will post up extra panels onto the game board.
//Currently displays texts: moves/attacks/player 1 and 2's health for each unit
//Problem: The console will fall into a loop, printing the previous command,
//because display will be updating ever few seconds.
//Not sure how to stall the comment section of the program yet. Or if it's just how
//the console runs

//Displaying both Player's Hp makes things easier. However, when either Air unit is dead,
//The HP for the land units switches to the Air unit. Making Air display the land unit's HP,
//instead of 0


import java.awt.Color;
import java.awt.Graphics;

public class UnitDisplay {

	private Player player1;
	private Player player2;
	private static String text;
	private static String textPlayer;

	public UnitDisplay(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public void setPlayer(Player player, Player player2) {
		this.player2 = player;
		this.player2 = player2;
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
			textPlayer = "Player 1: ";
			System.out.println(textPlayer);
			return textPlayer;
		}
		else {
			System.out.println(textPlayer);
			return textPlayer;
		}
	}
	
	//getUnitsHP
	public String getUnitHP1_0() {
			Unit selectedUnit = player1.getUnit(0);
			if(selectedUnit == null)
				return Integer.toString(0);
			return Integer.toString(selectedUnit.getHP());
	}
	
	public String getUnitHP1_1() {
		Unit selectedUnit = player1.getUnit(1);
		if(selectedUnit == null)
			return Integer.toString(0);
		return Integer.toString(selectedUnit.getHP());
	}
	
	public String getUnitHP2_0() {
		Unit selectedUnit = player2.getUnit(0);
		if(selectedUnit == null)
			return Integer.toString(0);
		return Integer.toString(selectedUnit.getHP());
	}
	
	public String getUnitHP2_1() {
		Unit selectedUnit = player2.getUnit(1);
		if(selectedUnit == null)
			return Integer.toString(0);
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
		if(getText() != null)
			g.drawString(" > " + getPlayer() + " " + getText(), 135,
					GamePanel.GHEIGHT - 75);
		else //Greeting
			g.drawString(" > " + "Welcome. Click a Unit to Play. Player 1 will start", 135, GamePanel.GHEIGHT - 75);
		
		
		//Displays HP of Units
	
		g.drawString(" > " + "Player 1 Units:", 45,
					GamePanel.GHEIGHT - 60);
		
		g.drawString("Air: "+ getUnitHP1_0() + "    Land: " + getUnitHP1_1(), 45,
			GamePanel.GHEIGHT - 45);
		
		g.drawString(" > " + "Player 2 Units:", 415,
				GamePanel.GHEIGHT - 60);
		
		g.drawString("Air: " + getUnitHP2_0() + "    Land: " + getUnitHP2_1(), 415,
				GamePanel.GHEIGHT - 45);
	}
	
}