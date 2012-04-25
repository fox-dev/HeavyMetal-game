package project;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	GamePanel gp;
	
	public Frame() {
		gp = new GamePanel();
		this.setSize(gp.GWIDTH, gp.GHEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.add(gp);
	}

}

