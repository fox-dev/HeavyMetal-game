package project;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Drawing {

	private Player player1, player2;
	private Image ground1, ground2, air1, air2, current, TILE_GRASS;
	private World world;
	private UnitDisplay unitDisplay; 
	
	
	public Drawing(Player player1, Player player2, World world, UnitDisplay unitDisplay) {
		this.unitDisplay = unitDisplay;
		this.world = world;
		this.player1 = player1;
		this.player2 = player2;
		ground1 = new ImageIcon("images/tank.png").getImage();
		ground2 = new ImageIcon("images/tank2.png").getImage();
		air1 = new ImageIcon("images/airplane.png").getImage();
		air2 = new ImageIcon("images/airplane2.png").getImage();
	}
	
	public void drawAll(Graphics g) {
		unitDisplay.draw(g);
		world.draw(g);
		drawPlayer1(g);
		drawPlayer2(g);
	}

	public void drawPlayer1(Graphics g) {
		for (int i = 0; i < player1.checkNumUnits(); i++) {
			if(player1.getUnit(i).getType() == 0) {
				current = ground1;
			}
			else if (player1.getUnit(i).getType() == 1) {
				current = air1;
			}
			g.drawImage(current, player1.getUnit(i).getLocationX()
					* World.TILE_SIZE, player1.getUnit(i).getLocationY()
					* World.TILE_SIZE, null);
		}

	}
	
	public void drawPlayer2(Graphics g) {
		for (int i = 0; i < player2.checkNumUnits(); i++) {
			if(player2.getUnit(i).getType() == 0) {
				current = ground2;
			}
			else if (player2.getUnit(i).getType() == 1) {
				current = air2;
			}
			g.drawImage(current, player2.getUnit(i).getLocationX()
					* World.TILE_SIZE, player2.getUnit(i).getLocationY()
					* World.TILE_SIZE, null);
		}

	}
}
