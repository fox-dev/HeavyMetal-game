package project;

public class FrameFunctions {
	
	Frame frame;
	
	public FrameFunctions() {
		this.frame = new Frame();
	}
	
	//Functions to add or remove JPanels to a frame here
	public void addGame(GamePanel gp) {
		frame.add(gp);
		frame.invalidate();
		frame.validate();
	}
	

}
