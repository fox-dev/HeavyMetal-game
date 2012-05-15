package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import project.UnitDisplay;

public class Drawing {

	//Variables and classes
	private Player player1, player2;
	private Image ground1, ground2, air1, air2, current, ground1selected, ground2selected, air1selected, air2selected;
	private Image moveable, explosion, hpfull, hpempty;
	private World world;
	private Input input;
	private Actions actions;
	private UnitDisplay unitDisplay; 
	
	private int currentx, currenty;
	private int health;
	private int numExplosions1, numExplosions2;
	private int[][] moves;
	private ArrayList<Image> explosions1 = new ArrayList<Image>();
	private ArrayList<Image> explosions2 = new ArrayList<Image>();
	
	
	public Drawing(Player player1, Player player2, World world, UnitDisplay unitDisplay, Actions actions
			, Input input) {
		//Load classes being used
		this.input = input;
		this.unitDisplay = unitDisplay;
		this.world = world;
		this.player1 = player1;
		this.player2 = player2;
		this.actions = actions;
		//Load images
		ground1 = new ImageIcon("images/tank.png").getImage();
		ground2 = new ImageIcon("images/tank2.png").getImage();
		ground1selected = new ImageIcon("images/tankselected.png").getImage();
		ground2selected = new ImageIcon("images/tank2selected.png").getImage();
		air1 = new ImageIcon("images/airplane.png").getImage();
		air2 = new ImageIcon("images/airplane2.png").getImage();
		air1selected = new ImageIcon("images/airplaneselected.png").getImage();
		air2selected = new ImageIcon("images/airplane2selected.png").getImage();
		moveable = new ImageIcon("images/moveable.png").getImage();
		explosion = new ImageIcon("images/explosion.gif").getImage();
		hpfull = new ImageIcon("images/healthfull.png").getImage();
		hpempty = new ImageIcon("images/healthempty.phg").getImage();
		health = 0;
		numExplosions1 = numExplosions2 = 0;
	}
	
	//Cycles through the draw methods to draw everything.
	public void drawAll(Graphics g) {
		unitDisplay.draw(g);
		world.draw(g);
		drawExplosion(g);
		drawPlayer1(g);
		drawPlayer2(g);
		drawcurrentRect(g);
		drawDisplayBox(g);
	}

	//Draws player 1
	public void drawPlayer1(Graphics g) {
		for (int i = 0; i < player1.checkNumUnits(); i++) {
			if(player1.getUnit(i).getType() == 0) {
				//Draws selected version of ground if unit is selected
				if(player1.getUnit(i).isSelected()) {
					current = ground1selected;
					drawMoves(player1.getUnit(i), g);
				}
				else
					current = ground1;
			}
			else if (player1.getUnit(i).getType() == 1) {
				//Draws selected version of air if unit is selected
				if(player1.getUnit(i).isSelected()) {
					current = air1selected;
					drawMoves(player1.getUnit(i), g);
				}
				else
					current = air1;
			}
			g.drawImage(current, player1.getUnit(i).getLocationX()
					* World.TILE_SIZE, player1.getUnit(i).getLocationY()
					* World.TILE_SIZE, null);
		}

	}
	//Draws player 2
	public void drawPlayer2(Graphics g) {
		for (int i = 0; i < player2.checkNumUnits(); i++) {
			if(player2.getUnit(i).getType() == 0) {
				//Draws the selected version of ground if that unit is currently selected
				if(player2.getUnit(i).isSelected()) {
					current = ground2selected;
					drawMoves(player2.getUnit(i), g);
				}
				else
					current = ground2;
			}
			else if (player2.getUnit(i).getType() == 1) {
				//Draws the selected version of air if unit is currently selected
				if(player2.getUnit(i).isSelected()) {
					current = air2selected;
					drawMoves(player2.getUnit(i), g);
				}
				else
					current = air2;
			}
			g.drawImage(current, player2.getUnit(i).getLocationX()
					* World.TILE_SIZE, player2.getUnit(i).getLocationY()
					* World.TILE_SIZE, null);
		}

	}
	
	public void drawMoves(Unit unit, Graphics g) {
		
		moves = actions.makeNewMovementDisplay(unit);
		
		for(int i = 0; i < world.getMap().getX(); i++) {
			for(int j = 0; j < world.getMap().getY(); j++) {
				if(moves[i][j] == 1) {
					g.drawImage(moveable, i * 30, j * 30, null);
				}
			}
		}
		
	}
	
	//Drawing a rectangle around the current square with the mouse hovered over.
	public void drawcurrentRect(Graphics g) {
		if(player1.unitSelected() || player2.unitSelected()) {
			g.setColor(Color.BLACK);
			g.drawRect(input.getMouseX() * World.TILE_SIZE, input.getMouseY() * World.TILE_SIZE
					, World.TILE_SIZE, World.TILE_SIZE);
		}
	}
	
	//Basic drawing of display for current health of unit.  Currently only draws a white 
	//rounded rectangle to the top left of the unit.  Needs to draw depending on where the unit is
	//and needs to actually display some information.
	public void drawDisplayBox(Graphics g) {
		for(int i  = 0; i < player1.checkNumUnits(); i++) {
			if(input.getMouseX() == player1.getUnit(i).getLocationX()
					&& input.getMouseY() == player1.getUnit(i).getLocationY()) {
				g.setColor(Color.WHITE);
				g.fillRoundRect((player1.getUnit(i).getLocationX() * 30) - 100,
						(player1.getUnit(i).getLocationY() * 30) - 50, 120, 40, 40, 40);
				//health = (player1.getUnit(i).getHP() / player1.getUnit(i).fullHP) * 100;
				g.setColor(Color.BLACK);
				g.drawString("HP: " + player1.getUnit(i).getHP(), (player1.getUnit(i).getLocationX() * 30) - 55,
						(player1.getUnit(i).getLocationY() * 30) - 25);
			}
		}
		for(int i  = 0; i < player2.checkNumUnits(); i++) {
			if(input.getMouseX() == player2.getUnit(i).getLocationX()
					&& input.getMouseY() == player2.getUnit(i).getLocationY()) {
				g.setColor(Color.WHITE);
				g.fillRoundRect((player2.getUnit(i).getLocationX() * 30) - 100,
						(player2.getUnit(i).getLocationY() * 30) - 50, 120, 40, 40, 40);
				g.setColor(Color.BLACK);
				g.drawString("HP: " + player2.getUnit(i).getHP(), (player2.getUnit(i).getLocationX() * 30) - 55,
						(player2.getUnit(i).getLocationY() * 30) - 25);
			}
		}
	}
	
	//Drawing the explosion when a unit dies
	public void drawExplosion(Graphics g) {
		if(player1.deadUnitsSize() > 0) {
			if(player1.deadUnitsSize() > numExplosions1) {
				Image tempImage = new ImageIcon("images/explosion.gif").getImage();
				explosions1.add(tempImage);
				numExplosions1++;
			}
		}
		if(player2.deadUnitsSize() > 0) {
			if(player2.deadUnitsSize() > numExplosions2) {
				Image tempImage = new ImageIcon("images/explosion.gif").getImage();
				explosions2.add(tempImage);
				numExplosions2++;
			}
		}
		if(explosions1.size() > 0 && player1.deadUnitsSize() > 0) {
			for(int i = 0; i < numExplosions1; i++) {
				g.drawImage(explosions1.get(i), player1.getdeadunit(i).getLocationX() * World.TILE_SIZE, 
						player1.getdeadunit(i).getLocationY() * World.TILE_SIZE, null);
			}
		}
		if(explosions2.size() > 0 && player2.deadUnitsSize() > 0) {
			for(int i = 0; i < numExplosions2; i++) {
				g.drawImage(explosions2.get(i), player2.getdeadunit(i).getLocationX() * World.TILE_SIZE, 
						player2.getdeadunit(i).getLocationY() * World.TILE_SIZE, null);
			}
		}
	}
}
