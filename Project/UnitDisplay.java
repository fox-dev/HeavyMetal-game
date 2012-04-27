package project;
//This program will post up extra panels onto the game board.
//Currently displays texts: moves/attacks by units (As of April 26, 2012)
//Problem: The console will fall into a loop, printing the previous command,
//because display will be updating ever few seconds.
//Not sure how to stall the comment section of the program yet. Or if it's just how
//the console runs

//Still need to Display if the current Player is Player 1 or 2
//Still need to put the text into an upper panel, while having the lower panel display health
//Will take a while to figure that out

//DisplayHP only displays Player 1's first unit's HP.
//Even after trying to put in if statements to select Player 2's units, it will still display 
//Player 1. After the first unit is dead, DisplayHP will print Player 1's second unit HP.

import java.awt.Color;
import java.awt.Graphics;

public class UnitDisplay {

	private Player player;
	private static String text;

	public UnitDisplay(Player player) {
		this.player = player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	//get text string/print it out when called
	public static String getText() {
		System.out.println(text);
		return text;
	}

	//set text from Input.projectfile
	public static void setText(String text1) {
		text = text1;
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
		g.setColor(Color.BLACK);
		g.fillRect(0, GamePanel.GHEIGHT - 100, GamePanel.GWIDTH,
				GamePanel.MHEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(10, GamePanel.GHEIGHT - 90, GamePanel.GWIDTH - 25,
				GamePanel.MHEIGHT - 20);
		g.setColor(Color.RED);
		
		//Displays Text
		if(getText() != null && isUnit(getUnitHP()))
			g.drawString(getText(), 25,
					GamePanel.GHEIGHT - 75);
		else
			g.drawString("No Selected Unit", 25, GamePanel.GHEIGHT - 75);
		
		
		//Displays HP of Units
		if (isUnit(getUnitHP()))
			g.drawString("Selected Unit HP:" + getUnitHP(), 25,
					GamePanel.GHEIGHT - 45);
		else
			g.drawString("No Selected Unit", 25, GamePanel.GHEIGHT - 45);
	}
	
}
