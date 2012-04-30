package project;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Drawing {

	//Variables and classes
	private Player player1, player2;
	private Image ground1, ground2, air1, air2, current, ground1selected, ground2selected, air1selected, air2selected;
	private Image moveable;
	private World world;
	private Actions actions;
	private UnitDisplay unitDisplay; 
	
	private int[][] moves;
	
	
	public Drawing(Player player1, Player player2, World world, UnitDisplay unitDisplay, Actions actions) {
		//Load classes being used
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
	}
	
	//Cycles through the draw methods to draw everything.
	public void drawAll(Graphics g) {
		unitDisplay.draw(g);
		world.draw(g);
		drawPlayer1(g);
		drawPlayer2(g);
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
		
		System.out.println(unit.getHP());
		
		
		moves = actions.makeNewMovementDisplay(unit);
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(moves[i][j] == 1) {
					g.drawImage(moveable, i * 30, j * 30, null);
				}
			}
		}
		
	}
}