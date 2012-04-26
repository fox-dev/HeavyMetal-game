package project;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable{
	//Double Buffering
	private Image dbImage;
	private Graphics dbg;
	//JPanel stuff
	static final int GWIDTH = 606, GHEIGHT = 700, MHEIGHT = 72;
	static final Dimension d = new Dimension(GWIDTH, GHEIGHT);
	//Game Variables
	private Thread game;
	private volatile boolean running = false;
	private long period = 15 * 1000000; //(15 milisecond to nanoseconds)
	private static final int DELAYS_BEFORE_YEILD = 10; 
	
	//Classes for various objects to be drawn and manipulated
	World world;
	Player player1, player2;
	// Input will go where select was in my old one Select select;
	Input testinput;
	Actions testactions;
	UnitDisplay unitDisplay;
	Drawing drawing;
	
	public GamePanel() {
		//Initialize
		player1 = new Player(1);
		player2 = new Player(2);
		world = new World();
		unitDisplay = new UnitDisplay(player1);
		drawing = new Drawing(player1, player2, world, unitDisplay);
		
		testactions = new Actions(player1,player2, world.getMap()); //Added -Andrew
		testinput = new Input(player1,player2,testactions); //Added -Andrew
		
		//Replace with input select = new Select(player1, player2, world, 1, 2);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.requestFocus();
		
		//Mouse listener
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) { //Added -Andrew
				testinput.directInput(e);
				if(player1.checkTurnOver() == true){
					System.out.println("Turn is over. Switching");
					player1.unitsReset();
					testinput.switchPlayerStatuses();
					System.out.println("Player 2's turn.");
				}
				if(player2.checkTurnOver() == true){
					System.out.println("Turn is over. Switching");
					player2.unitsReset();
					testinput.switchPlayerStatuses();
					System.out.println("Player 1's turn.");
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				
			}
			
		});
		
		//Motion listener for current mouse position
		addMouseMotionListener(new MouseAdapter() {
			
			public void mouseMoved(MouseEvent e) {
				testinput.mouseMoved(e); //keeps track of the cursor location on the screen. -Andrew
			}
			//Added the following just incase we want to use them later
			public void mouseDragged(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
	}
	//Start the game thread
	public void addNotify() {
		super.addNotify();
		startGame();
	}
	
	//Creates a new game thread if there is not one running.
	private void startGame() {
		if(game == null || !running) {
			game = new Thread(this);
			game.start();
			running = true;
		}
	}
	
	//Runs the game drawing and update methods and also
	//keeps track of FPS.  If the game runs slow on frame it will catch up on a later one
	@Override
	public void run() {
		long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0;
		int delays = 0;
		while(running) {
			beforeTime = System.nanoTime();
			//Runs the three basic steps for the game
			gameUpdate();
			gameRender();
			paintScreen();
			afterTime = System.nanoTime();
			diff = afterTime - beforeTime;
			sleepTime = (period - diff) - overSleepTime;
			//Checks how long it needs to sleep and if needs to sleep more than 15ms it subtracts
			//sleep time from the next time through
			if(sleepTime < period && sleepTime > 0) {
				try {
					game.sleep(sleepTime / 1000000L);
					overSleepTime = 0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(diff > period) {
				overSleepTime = diff - period;
			}
			else if(++delays >= DELAYS_BEFORE_YEILD){
				game.yield();
				delays = 0;
				overSleepTime = 0;
			}
			else {
				overSleepTime = 0;
			}
			
			
		}
	}
	//Update things in the game
	private void gameUpdate() {
		if(running && game != null) {
			//Update variables here
		}
	}
	
	//Screen rendering stuff
	private void gameRender() {
		if(dbImage == null) {
			dbImage = createImage(GWIDTH, GHEIGHT);
			if(dbImage == null) {
				System.err.println("dbImage is still null");
				return;
			}
			else {
				dbg = dbImage.getGraphics();
			}
		}
		//Clear Screen
		dbg.setColor(Color.WHITE);
		dbg.fillRect(0, 0, GWIDTH, GHEIGHT);
		//Draw game
		draw(dbg);
		
	}
	
	//Draw world, player, and the highlighted rectangle
	public void draw(Graphics g) {
		drawing.drawAll(g);
	}
	
	private void paintScreen(){
		Graphics g;
		try {
			g = this.getGraphics();
			if(dbImage != null  && g != null) {
				g.drawImage(dbImage, 0, 0, null);
			}
		}catch (Exception e) {
		}
	}
	//Stops game
	public void stopGame() {
		if(running) {
			running = false;
		}
	}
	

}

