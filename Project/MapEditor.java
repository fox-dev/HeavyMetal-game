package project;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MapEditor extends JPanel{
	
	//Create variables and classes
	private Rectangle[][] tiles;
	private Image[][] tileImg;
	static final Dimension d = new Dimension(GamePanel.GWIDTH, GamePanel.GHEIGHT);
	Map map;
	public static final int TILE_SIZE = 30;
	// We're not using MAP_DEM at this moment
	public static final int MAP_DEM = 20;
	private int x= 0, y = 0;
	private final int grassx = GamePanel.GWIDTH - 550, blocky = GamePanel.GHEIGHT - 85;
	private final int waterx = grassx + 100, mountainx = grassx + 200, forrestx = grassx + 300,
			bridgex = grassx + 400;
	private final int playButtonx = grassx + 500;
	
	int selectedType = -1;
	FrameFunctions ff;
	
	
	
	private Image TILE_GRASS, TILE_WATER, TILE_MOUNTAIN, TILE_FOREST, TILE_BRIDGE, back;
	
	public MapEditor(FrameFunctions ff) {
		this.ff = ff;
		TILE_GRASS = new ImageIcon(World.GRASS_PATH).getImage();
		TILE_WATER = new ImageIcon(World.WATER_PATH).getImage();
		TILE_MOUNTAIN = new ImageIcon(World.MOUNTAIN_PATH).getImage();
		TILE_FOREST = new ImageIcon(World.FOREST_PATH).getImage();
		TILE_BRIDGE = new ImageIcon(World.BRIDGE_PATH).getImage();
		back = new ImageIcon("images/background.png").getImage();
		// Map is now created before tiles and tileImg so we can get its dimensions
		map = new Map(20, 20, false);
		// The rectangles and images now get the dimensions of the map
		// This makes non-square map dimensions like (20,20) or (15,15) possible
		// without things crashing and burning
		tiles = new Rectangle[map.getX()][map.getY()];
		tileImg = new Image[map.getX()][map.getY()];
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.requestFocus();
		loadArrays();
		
		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				System.out.println("SelectedType = " + selectedType);
				if(selectedType != -1) {
					if(e.getY() < GamePanel.GHEIGHT - 100) {
							map.setArr(e.getX() / TILE_SIZE, e.getY() / TILE_SIZE, selectedType);
							loadArrays();
					}	
				}
				
				//Determine if one of the different tiles was clicked on.
				//If so set it to the current selected tile
				if(e.getX() >= grassx && e.getX() <= grassx + TILE_SIZE) {
					if(e.getY() >= blocky && e.getY() <= blocky + TILE_SIZE) {
						selectedType = Map.GROUND;
					}
				}
				else if(e.getX() >= waterx && e.getX() <= waterx + TILE_SIZE) {
					if(e.getY() >= blocky && e.getY() <= blocky + TILE_SIZE) {
						selectedType = Map.WATER;
					}
				}
				else if(e.getX() >= mountainx && e.getX() <= mountainx + TILE_SIZE) {
					if(e.getY() >= blocky && e.getY() <= blocky + TILE_SIZE) {
						selectedType = Map.MOUNTAIN;
					}
				}
				else if(e.getX() >= forrestx && e.getX() <= forrestx + TILE_SIZE) {
					if(e.getY() >= blocky && e.getY() <= blocky + TILE_SIZE) {
						selectedType = Map.FOREST;
					}
				}
				else if(e.getX() >= bridgex && e.getX() <= bridgex + TILE_SIZE) {
					if(e.getY() >= blocky && e.getY() <= blocky + TILE_SIZE) {
						selectedType = Map.BRIDGE;
					}
				}
				/*
				 * Don't have a button to start the game yet.
				 * Need to change FrameFunctions, GamePanel, and World to add creating a new game
				 * with a map sent in
				else if(e.getX() >= playButtonx && e.getX() <= playButtonx + TILE_SIZE) {
					if(e.getY() >= blocky && e.getY() <= blocky + TILE_SIZE) {
						System.out.println("Starting game");
						start(map);
					}
				}
				*/
				
			}
			
		});
	}

	/*
	 * Not yet implemented.  Need to add everything to create the new panel
	public void start(Map m) {
	}
	*/
	//Take the map and fill in a 2d array with images for drawing
	private void loadArrays() {
		for(int i = 0; i < map.getX(); i++) {
			for (int j = 0; j < map.getY(); j++){
				// For testing purposes
				//System.out.println("Y = " + i + " X = " + j + " MAPX = " + map.getX() + " MAPY = " + map.getY());
				if(map.getArr(i, j) == Map.GROUND) {
					tileImg[i][j] = TILE_GRASS;
				}
				else if(map.getArr(i, j) == Map.WATER) {
					tileImg[i][j] = TILE_WATER;
				}
				else if (map.getArr(i, j) == Map.MOUNTAIN) {
					tileImg[i][j] = TILE_MOUNTAIN;
				}
				else if (map.getArr(i, j) == Map.FOREST) {
					tileImg[i][j] = TILE_FOREST;
				}
				else if (map.getArr(i, j) == Map.BRIDGE) {
					tileImg[i][j] = TILE_BRIDGE;
				}
				
				tiles[i][j] = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
				
				y = (j+1)*TILE_SIZE;
			}
			y = 0;
			x = (i+1)*TILE_SIZE;
		}	
	}
	
	//Call draw to draw everything to the screen.
	public void paint(Graphics g) {
		draw(g);
		repaint();
	}
	
	//Draw everything to the screen.
	public void draw(Graphics g) {
		for(int i = 0; i < map.getX(); i++) {
			for(int j = 0; j < map.getY(); j++) {
			g.drawImage(tileImg[i][j], tiles[i][j].x, tiles[i][j].y, null);
			}
		}
		
		//Draw the background bottom portion of the screen
		g.drawImage(back, GamePanel.GHEIGHT - 701, GamePanel.GWIDTH - 7, null);
		
		//Draw the tiles on the bottom portion of the screen for selection
		g.drawImage(TILE_GRASS, grassx, blocky, null);
		g.drawImage(TILE_WATER, waterx, blocky, null);
		g.drawImage(TILE_MOUNTAIN, mountainx, blocky, null);
		g.drawImage(TILE_FOREST, forrestx, blocky, null);
		g.drawImage(TILE_BRIDGE, bridgex, blocky, null);
		g.drawImage(TILE_GRASS, playButtonx, blocky, null);
		//Set the color of the text to green and draw the labels for the tiles
		g.setColor(Color.GREEN);
		g.drawString("Grass", grassx - 2, blocky + 42);
		g.drawString("Water", waterx - 1, blocky + 42);
		g.drawString("Mountain", mountainx - 10, blocky + 42);
		g.drawString("Forrest", forrestx - 5, blocky + 42);
		g.drawString("Bridge", bridgex - 2, blocky + 42);
	}
}
