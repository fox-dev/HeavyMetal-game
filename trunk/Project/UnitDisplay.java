package project;
//This program will display texts: moves/attacks/player 1 and 2's health for each unit

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class UnitDisplay {
	
	private Player player1;
	private Player player2;
	private static String text;
	private static String textPlayer;
	private Image endButton;
	//STatic variables for the location and width of the end turn button to be used by input to end the
	//turn
	public static final int ENDBUTTONX = GamePanel.GWIDTH - 82
			, ENDBUTTONY = GamePanel.GHEIGHT - 85, ENDBUTTONWIDTH = 60, ENDBUTTONHEIGHT = 40;

	public UnitDisplay(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		endButton = new ImageIcon("images/endbutton.png").getImage();
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
	//getUnitsHP
		public String getUnitHP() {
			
			if (Player.unitSelected() == true){
				Unit selectedUnit = player1.getUnit(0);
				if(selectedUnit == null)
					return Integer.toString(0);
				return Integer.toString(selectedUnit.getHP());
			}
			else
				return null;
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
		//Drawing the image for the end turn button
		g.drawImage(endButton, ENDBUTTONX, ENDBUTTONY, null);
		g.setColor(Color.GREEN);
		
		//Displays current player
		
	
		//Displays Text
		if(getText() != null)
			g.drawString(" > " + getPlayer() + " " + getText(), 15,
					GamePanel.GHEIGHT - 75);
		else //Greeting
			g.drawString(" > " + "Welcome. Click a Unit to Play. Player 1 will start", 15, GamePanel.GHEIGHT - 75);
				
				
		//Displays HP of Units
				
		if(isUnit(getUnitHP()) == true)
			g.drawString(" > " + "Unit HP: " + getUnitHP(), 15,
					GamePanel.GHEIGHT - 50);
	}
	
}