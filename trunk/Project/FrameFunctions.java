package project;


import java.awt.*;

import javax.swing.ImageIcon;
public class FrameFunctions {
	
	Frame frame;
	TitleScreen ts; 

	public FrameFunctions() {
		ts = new TitleScreen(this);
		this.frame = new Frame();
		this.addTitle(ts);
	}
	
	//Functions to add or remove JPanels to a frame here
	public void addGame(GamePanel gp) {
		frame.add(gp);
		frame.invalidate();
		frame.validate();
	}
	
	/*This Function is the initial call 
	 * for the title screen panel to start a game.
	 */
	public void startGame(){
		GamePanel gp = new GamePanel();
		this.addGame(gp);
		this.removeTitle(ts);
	}
	
	//Add Title panel call
	public void addTitle(TitleScreen ts){
		frame.add(ts);
		frame.invalidate();
		frame.validate();
	}
	
	//This call is to remove the title when it switches to the game panel
	public void removeTitle(TitleScreen ts){
		frame.remove(ts);
		frame.invalidate();
		frame.validate();
	}
	
	/*Note: Add remove functions here when making other panels*/

	
	
	

}

