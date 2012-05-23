package project;

import java.awt.*;

import javax.swing.ImageIcon;
public class FrameFunctions {
	
	Frame frame;
	TitleScreen ts;
	Options op;
	GamePanel gp;

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
		gp = new GamePanel(this);
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

	public void removeGame(GamePanel gp) {
		frame.remove(gp);
		frame.invalidate();
		frame.validate();
	}
	public void cleanUp() {
		this.removeGame(gp);
		ts = new TitleScreen(this);
		this.addTitle(ts);
	}
	/*Note: Add remove functions here when making other panels*/
	
	//Add the options panel
	public void addOptions(){
		op = new Options(this);
		frame.add(op);
		this.removeTitle(ts);
	}
	
	//Remove the options panel
	public void removeOptions(Options op){
		frame.remove(op);
		ts = new TitleScreen(this);
		this.addTitle(ts);
		frame.invalidate();
		frame.validate();
	}

	
	
	

}




