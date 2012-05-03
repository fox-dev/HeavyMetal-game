package project;

import javax.swing.JFrame;

//This class represents the basic frame.  
//Other panels can be added to it such as the title screen, game-play screen, etc.
public class Frame extends JFrame{
	public Frame() { //-Andrew: Removed the initial setup for adding the game panel in this constructor and moved it to FrameFunctions
		int xSize = 606; //Default Size x
		int ySize = 700; //Default Size y
		this.setSize(xSize, ySize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);	
	}

}

