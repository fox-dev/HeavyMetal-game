package project;
//This program will display texts, Health (HP), Attack Power(AP), points (Coins collected) in the UnitDisplay; 
//It will give the user a chance to end their turn by pressing the end turn button;
//As of May 15, it will display different images depending on the current player.
//Added a 'back to titleScreen' button; currently does not work

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import project.GamePanel;
import project.Player;
import project.Unit;

public class UnitDisplay {
	
	private Player player1, player2;
	private Image endButton, back, p1, p2, p1No, p2No;
	private Image health, ammo, titleButton, points;
	private static String text, hp, type, point;
	private static int playerNum;
	private static String attack;
	
	//Static variables for the location and width of the end turn button to be used by input to end the
	//turn
	public static final int ENDBUTTONX = GamePanel.GWIDTH - 60
			, ENDBUTTONY = GamePanel.GHEIGHT - 83, ENDBUTTONWIDTH = 30, ENDBUTTONHEIGHT = 29;
	
	public static final int TitleButtonX = GamePanel.GWIDTH - 105,
			TitleButtonY = GamePanel.GHEIGHT - 83, TitleButtonWidth = 30, TitleButtonHeight = 29;

	public UnitDisplay(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		endButton = new ImageIcon("images/endbutton.png").getImage();
		back = new ImageIcon("images/background.png").getImage();
		p1 = new ImageIcon("images/player1.gif").getImage();
		p1No = new ImageIcon("images/p1No.png").getImage();
		p2 = new ImageIcon("images/player2.gif").getImage();
		p2No = new ImageIcon("images/p2No.png").getImage();
		health = new ImageIcon("images/healthpot.png").getImage();
		ammo = new ImageIcon("images/ammo.png").getImage();
		titleButton = new ImageIcon("images/titlebutton.png").getImage();
		points = new ImageIcon("images/treasure.png").getImage();
	}

	public void setPlayer(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	//draws panel; Main 
	public void draw(Graphics g) {
		//Drawing Back of the DisplayBoard 
		g.drawImage(back, GamePanel.GHEIGHT - 701, GamePanel.GWIDTH - 7, null);
		//Drawing the image for the end turn button
		g.drawImage(endButton, ENDBUTTONX, ENDBUTTONY, null);
		g.drawImage(titleButton, TitleButtonX, TitleButtonY, null);
		//Color of text is set to Green
		g.setColor(Color.GREEN);
	
		printAll(g);
	
	}

	//Get all Images to print on Display
	public void printAll(Graphics g){
		//Sets Player Icon pictures. 
			if(getPlayer() == 2){
				//If player 2, set p1 icon to gray, and animate p2 icon 
				g.drawImage(p1No,GamePanel.GHEIGHT - 690, GamePanel.GWIDTH + 5, null);
				g.drawImage(p2,GamePanel.GHEIGHT - 690, GamePanel.GWIDTH + 30, null);
			}
			else{
				//If player 1, set p2 to gray, animate p1.
				//The first player is player1 by default
				g.drawImage(p1,GamePanel.GHEIGHT - 690, GamePanel.GWIDTH + 5, null);
				g.drawImage(p2No,GamePanel.GHEIGHT - 690, GamePanel.GWIDTH + 30, null);
			}
			
			
		//Displays Text
			if(getText() != null)
				g.drawString(" > " + getText(), 50,
						GamePanel.GHEIGHT - 75);
			else //Greeting
				g.drawString(" > " + "Player 1 will start. Click a Unit to Play.", 50, GamePanel.GHEIGHT - 75);
			
		//Sets all Player1 and Player2 units for easier access to HP/UnitType/Attack Power
			setUnits();
			
		//Displays HP of Units
			if(Player.unitSelected() != false){
				g.drawImage(health, GamePanel.GHEIGHT - 658, GamePanel.GWIDTH + 21, null);
				g.drawString("HP: " + drawHP(), 65,
						GamePanel.GHEIGHT - 50);
			}
			else{
				g.drawImage(health, GamePanel.GHEIGHT - 658, GamePanel.GWIDTH + 21, null);
				g.drawString("HP: -- ", 65,
						GamePanel.GHEIGHT - 50);
			}
			
		//Displays Attack Power of Unit
			if(Player.unitSelected() != false){
				g.drawImage(ammo, GamePanel.GHEIGHT - 590, GamePanel.GWIDTH + 21, null);
				g.drawString("AP: " + drawAttack(), 140,
						GamePanel.GHEIGHT - 50);
			}
			else{
				g.drawImage(ammo, GamePanel.GHEIGHT - 590, GamePanel.GWIDTH + 21, null);
				g.drawString("AP: -- ", 140,
						GamePanel.GHEIGHT - 50);
			}
			
		//Displays Points of Player
			if(Player.unitSelected() != false){
				g.drawImage(points, GamePanel.GHEIGHT - 520, GamePanel.GWIDTH + 23, null);
				g.drawString("Points: " + getPoint(), 207,
						GamePanel.GHEIGHT - 50);
		}
			else{
				g.drawImage(points, GamePanel.GHEIGHT - 520, GamePanel.GWIDTH + 23, null);
				g.drawString("Points: -- ", 207,
						GamePanel.GHEIGHT - 50);
			}
			
			
	}
	
	//set text from Input
	public static void setText(String text1) {
		text = text1;
	}
	
	//get text string/print it out when called
	public static String getText() {
		return text;
	}
	
	//set current player text
	public static void setPlayer(int num){
		playerNum = num;
	}
	
	//get current player text
	public static int getPlayer(){
		return playerNum;
	}
	
	//Sets Units
	public void setUnits(){
		for (int i = 0; i < player1.checkNumUnits(); i++) {
			//Sets Player1's Unit if selected
				if(player1.getUnit(i).isSelected()) {
					setHP(player1.getUnit(i));
					setType(player1.getUnit(i));
					setAttack(player1.getUnit(i));
					setPoint(player1.getUnit(i));
				}
		}
		for (int i = 0; i < player2.checkNumUnits(); i++) {
			//Sets Player2's Unit if selected
				if(player2.getUnit(i).isSelected()) {
					setHP(player2.getUnit(i));
					setType(player2.getUnit(i));
					setAttack(player2.getUnit(i));
					setPoint(player2.getUnit(i));
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
			type = "Land";
		else if (unit.getType() == 1)
			type = "Air";
		else if(unit.getType() == 2)
			type = "Water";
		else if(unit.getType() == 10)
			type = "Base";
	}
	
	//Gets Unit's Type
	public String drawType(){
		return type;
	}
	
	//Sets Unit's Attack
	public void setAttack(Unit unit){
		attack = Integer.toString(unit.getAttack());
	}
	
	//Gets Unit's Attack
	public String drawAttack(){
		return attack;
	}
	
	//Get and Set unit points; used it just to see if unit points work. They do!
	public void setPoint(Unit unit){
		point = Integer.toString(unit.getPoint());
	}
	
	public String getPoint(){
		return point;
	}
	
}