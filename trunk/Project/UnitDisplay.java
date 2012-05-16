package project;
//This program will display texts: moves/attacks/player 1 and 2's health for each unit
//It will give the user a chance to end their turn by pressing the end turn button
//As of May 15, it will display different images depending on the current player.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class UnitDisplay {
	
	private Player player1, player2;
	private Image endButton, back, p1, p2, p1No, p2No;
	private static String text, hp, type;
	private static int playerNum;
	private static String attack;
	
	//Static variables for the location and width of the end turn button to be used by input to end the
	//turn
	public static final int ENDBUTTONX = GamePanel.GWIDTH - 60
			, ENDBUTTONY = GamePanel.GHEIGHT - 83, ENDBUTTONWIDTH = 40, ENDBUTTONHEIGHT = 38;

	public UnitDisplay(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		endButton = new ImageIcon("images/button.png").getImage();
		back = new ImageIcon("images/background.png").getImage();
		p1 = new ImageIcon("images/player1.gif").getImage();
		p1No = new ImageIcon("images/p1No.png").getImage();
		p2 = new ImageIcon("images/player2.gif").getImage();
		p2No = new ImageIcon("images/p2No.png").getImage();
		
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
		//Color of text is set to Green
		g.setColor(Color.GREEN);
	
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
		if(Player.unitSelected() != false)
			g.drawString(" > " + "Unit HP: " + drawHP(), 50,
					GamePanel.GHEIGHT - 50);
		else
			g.drawString(" > Unit HP: -- ", 50,
					GamePanel.GHEIGHT - 50);
		
		//Displays Type of Unit
		if(Player.unitSelected() != false)
			g.drawString(" > " + "Unit Type: " + drawType(), 150,
					GamePanel.GHEIGHT - 50);
		else
			g.drawString(" > Unit Type: -- ", 150,
					GamePanel.GHEIGHT - 50);
		
		//Displays Attack Power of Unit
		if(Player.unitSelected() != false)
			g.drawString(" > Attack Power: " + drawAttack(), 250,
					GamePanel.GHEIGHT - 50);
		else
			g.drawString(" > Attack Power: -- ", 250,
					GamePanel.GHEIGHT - 50);
		
		
		//Displays Attack
		/*for(int i = 0; i < attack; i++){
			if(Player.unitSelected() != false){
				if(drawAttack() == 1)
					g.drawImage(bullet, 325,
							GamePanel.GHEIGHT - 75, null);
				else if(drawAttack() == 2){
					g.drawImage(bullet, 325,
							GamePanel.GHEIGHT - 75, null);
					g.drawImage(bullet, 340,
							GamePanel.GHEIGHT - 75, null);
				}
			}
		}*/
	
			
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
			if(player1.getUnit(i).getType() == 0) {
				//Ground
				if(player1.getUnit(i).isSelected()) {
					setHP(player1.getUnit(i));
					setType(player1.getUnit(i));
					setAttack(player1.getUnit(i));
				}
			}
			else if (player1.getUnit(i).getType() == 1) {
				//Air
				if(player1.getUnit(i).isSelected()) {						
					setHP(player1.getUnit(i));
					setType(player1.getUnit(i));
					setAttack(player1.getUnit(i));
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
					setAttack(player2.getUnit(i));
				}
			}
			else if (player2.getUnit(i).getType() == 1) {
				//Air
				if(player2.getUnit(i).isSelected()) {
					setHP(player2.getUnit(i));
					setType(player2.getUnit(i));
					setAttack(player2.getUnit(i));
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
			type = "Land";
		else
			type = "Air";
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
	
}