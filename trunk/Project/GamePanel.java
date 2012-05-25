package project;

import javax.swing.JPanel;

import project.UnitDisplay;

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
	private boolean gameOver = false; // On mouseclick, game will go back to title screen when true
	
	//Classes for various objects to be drawn and manipulated
	World world;
	Player player1, player2;
	// Input will go where select was in my old one Select select;
	Input testinput;
	Actions testactions;
	UnitDisplay unitDisplay;
	Drawing drawing;
	
	public GamePanel(final FrameFunctions ff) {
		//Initialize
		player1 = new Player(Player.P1);
    //accounting for AI being on
		if(ActionsRules.AI_On)
			player2 = new Player_AI();
		else
			player2 = new Player(Player.P2);
		
		world = new World();
		unitDisplay = new UnitDisplay(player1, player2);
    testactions = new Actions(player1,player2, world.getMap()); //Added -Andrew   // reordered -Dan Apr27, 2012

    //testactions.respawn(player1, 1); //added dan  //moved to Actions.java May16, 2012
    //testactions.respawn(player2, 4); //added dan  //moved to Actions.java May16, 2012
    
		testinput = new Input(player1,player2,testactions); //Added -Andrew
		drawing = new Drawing(player1, player2, world, unitDisplay, testactions, testinput);
		
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
					UnitDisplay.setText("Players have switched. Awaiting Unit Selection...");
					player1.unitsReset();
					testinput.switchPlayerStatuses();
					UnitDisplay.setPlayer(2);
					
					//if AI is turned on and activePlayer is an AI run this code and return, else run other code
					if(ActionsRules.AI_On & player1.checkNumUnits() > 0){
						UnitDisplay.setText("AI is Running");
						try	{
							Thread.sleep(500); // do nothing for 500 miliseconds (.5 second)
						}	catch(InterruptedException ex) {
							ex.printStackTrace();
						}
						Player_AI ai = (Player_AI)player2;
						ai.moveAttackAll();
						ai.unitsReset();
						testinput.switchPlayerStatuses();
						UnitDisplay.setPlayer(Player.P1);
						UnitDisplay.setText("AI has completed. Awaiting Unit Selection...");
					}//END AI Edits
				}
				if(player2.checkTurnOver() == true && ActionsRules.AI_On == false){
					UnitDisplay.setText("Players have switched. Awaiting Unit Selection...");
					player2.unitsReset();
					testinput.switchPlayerStatuses();
					UnitDisplay.setPlayer(1);
				}
				// END GAME IF STATEMENT
				if (player1.checkNumUnits() <= 0 || player2.checkNumUnits() <= 0) {
					if (player1.checkNumUnits() <= 0 & !ActionsRules.AI_On)
						UnitDisplay.setText("PLAYER 2 WINS!");
					else if (player2.checkNumUnits() <= 0 & !ActionsRules.AI_On)
						UnitDisplay.setText("PLAYER 1 WINS!");
					else
						UnitDisplay.setText("CPU PLAYER WINS!");
					try {
						Thread.sleep(4000); // Sleep 4 seconds or else the title screen pops on the same mouse click
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					gameOver = true;
				}
				// Go straight to the title screen. Do not pass go
				if (gameOver) {
					UnitDisplay.setText("Player 1 will start. Click a Unit to Play."); // Reset greeting message
					ff.cleanUp();
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
	
	//A lot of repeated code.  Need to clean this up.
	public GamePanel(final FrameFunctions ff, Map m) {
		//Initialize
		player1 = new Player(Player.P1);
    //accounting for AI being on
		if(ActionsRules.AI_On)
			player2 = new Player_AI();
		else
			player2 = new Player(Player.P2);
		
		world = new World(m);
		unitDisplay = new UnitDisplay(player1, player2);
    testactions = new Actions(player1,player2, world.getMap()); //Added -Andrew   // reordered -Dan Apr27, 2012

    //testactions.respawn(player1, 1); //added dan  //moved to Actions.java May16, 2012
    //testactions.respawn(player2, 4); //added dan  //moved to Actions.java May16, 2012
    
		testinput = new Input(player1,player2,testactions); //Added -Andrew
		drawing = new Drawing(player1, player2, world, unitDisplay, testactions, testinput);
		
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
					UnitDisplay.setText("Players have switched. Awaiting Unit Selection...");
					player1.unitsReset();
					testinput.switchPlayerStatuses();
					UnitDisplay.setPlayer(2);
					
					//if AI is turned on and activePlayer is an AI run this code and return, else run other code
					if(ActionsRules.AI_On & player1.checkNumUnits() > 0){
						UnitDisplay.setText("AI is Running");
						try	{
							Thread.sleep(500); // do nothing for 500 miliseconds (.5 second)
						}	catch(InterruptedException ex) {
							ex.printStackTrace();
						}
						Player_AI ai = (Player_AI)player2;
						ai.moveAttackAll();
						ai.unitsReset();
						testinput.switchPlayerStatuses();
						UnitDisplay.setPlayer(Player.P1);
						UnitDisplay.setText("AI has completed. Awaiting Unit Selection...");
					}//END AI Edits
				}
				if(player2.checkTurnOver() == true && ActionsRules.AI_On == false){
					UnitDisplay.setText("Players have switched. Awaiting Unit Selection...");
					player2.unitsReset();
					testinput.switchPlayerStatuses();
					UnitDisplay.setPlayer(1);
				}
				// END GAME IF STATEMENT
				if (player1.checkNumUnits() <= 0 || player2.checkNumUnits() <= 0) {
					if (player1.checkNumUnits() <= 0 & !ActionsRules.AI_On)
						UnitDisplay.setText("PLAYER 2 WINS!");
					else if (player2.checkNumUnits() <= 0 & !ActionsRules.AI_On)
						UnitDisplay.setText("PLAYER 1 WINS!");
					else
						UnitDisplay.setText("CPU PLAYER WINS!");
					try {
						Thread.sleep(4000); // Sleep 4 seconds or else the title screen pops on the same mouse click
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					gameOver = true;
				}
				// Go straight to the title screen. Do not pass go
				if (gameOver) {
					UnitDisplay.setText("Player 1 will start. Click a Unit to Play."); // Reset greeting message
					ff.cleanUp();
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