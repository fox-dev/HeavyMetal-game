package project;

import java.awt.Color;
import java.awt.Graphics;

public class UnitDisplay {

	Player player;

	public UnitDisplay(Player player) {
		this.player = player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

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

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, GamePanel.GHEIGHT - 100, GamePanel.GWIDTH,
				GamePanel.MHEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect(10, GamePanel.GHEIGHT - 90, GamePanel.GWIDTH - 25,
				GamePanel.MHEIGHT - 20);
		g.setColor(Color.RED);
		//Draw unit HP
		if (isUnit(getUnitHP()))
			g.drawString("Selected Unit HP:" + getUnitHP(), 25,
					GamePanel.GHEIGHT - 75);
		else
			g.drawString("No Selected Unit", 25, GamePanel.GHEIGHT - 75);
	}
}
