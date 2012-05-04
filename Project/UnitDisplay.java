package project;
//This program will display texts: moves/attacks/player 1 and 2's health for each unit

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class UnitDisplay {
	
	private Player player1, player2;
	private Image endButton;
	private static String text;
	private static String textPlayer;
	private static String hp;
	private static String type;
	
	//STatic variables for the location and width of the end turn button to be used by input to end the
	//turn
	public static final int ENDBUTTONX = GamePanel.GWIDTH - 82
			, ENDBUTTONY = GamePanel.GHEIGHT - 85, ENDBUTTONWIDTH = 60, ENDBUTTONHEIGHT = 40;

	public UnitDisplay(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		endButton = new ImageIcon("images/endbutton.png").getImage();
	}

	public void setPlayer(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	//draws panel; Main 
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
	
		//Displays Text
		if(getText() != null)
			g.drawString(" > " + getPlayer() + " " + getText(), 15,
					GamePanel.GHEIGHT - 75);
		else //Greeting
			g.drawString(" > " + "Welcome. Click a Unit to Play. Player 1 will start", 15, GamePanel.GHEIGHT - 75);
		
		//Sets all Player1 and Player2 units for easier access to HP/UnitType
		setUnits();
		
		//Displays HP of Units
		if(Player.unitSelected() != false)
			g.drawString(" > " + "Unit HP: " + drawHP(), 15,
					GamePanel.GHEIGHT - 50);
		else
			g.drawString(" > Unit HP: -- ", 15,
					GamePanel.GHEIGHT - 50);
		
		//Displays Type of Unit
		if(Player.unitSelected() != false)
			g.drawString(" > " + "Unit Type: " + drawType(), 110,
					GamePanel.GHEIGHT - 50);
		else
			g.drawString(" > Unit Type: -- ", 110,
					GamePanel.GHEIGHT - 50);
	}

	//set text from Input
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
	
	//Sets Units
	public void setUnits(){
		for (int i = 0; i < player1.checkNumUnits(); i++) {
			//Sets Player1's Unit if selected
			if(player1.getUnit(i).getType() == 0) {
				//Ground
				if(player1.getUnit(i).isSelected()) {
					setHP(player1.getUnit(i));
					setType(player1.getUnit(i));
				}
			}
			else if (player1.getUnit(i).getType() == 1) {
				//Air
				if(player1.getUnit(i).isSelected()) {						
					setHP(player1.getUnit(i));
					setType(player1.getUnit(i));
				}
			}
		}
		for (int i = 0; i < player2.checkNumUnits(); i++) {
			//Sets Player2's Unit if selected
			if(player2.getUnit(i).getType() == 0) {
				//Ground
				if(player2.getUnit(i).isSelected()) {
					setHP(player2.getUnit(i));
					setType(player2.getUnit(i));
				}
			}
			else if (player2.getUnit(i).getType() == 1) {
				//Air
				if(player2.getUnit(i).isSelected()) {
					setHP(player2.getUnit(i));
					setType(player2.getUnit(i));
				}
			}
		}
	}
		
	//Sets Unit's HP
	public void setHP(Unit unit){
		hp = Integer.toString(unit.getHP());
	}
		
	//Gets Unit's HP
	public String drawHP(){
		return hp;
	}
	
	//Sets Unit's Type
	public void setType(Unit unit){
		if(unit.getType() == 0)
			type = "Ground Unit";
		else
			type = "Air Unit";
	}
	
	//Gets Unit's Type
	public String drawType(){
		return type;
	}
}