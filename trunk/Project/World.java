package project;

import java.awt.*;

import javax.swing.ImageIcon;

public class World {
	
	//Create variables and classes
	private Rectangle[][] tiles;
	private Image[][] tileImg;
	Map map;
	public static final int TILE_SIZE = 30;
	// We're not using MAP_DEM at this moment
	public static final int MAP_DEM = 20;
	private int x= 0, y = 0;
	
	private Image TILE_GRASS, TILE_WATER;
	
	
	public World() {
		TILE_GRASS = new ImageIcon("images/grass.png").getImage();
		TILE_WATER = new ImageIcon("images/water.png").getImage();
		// Map is now created before tiles and tileImg so we can get its dimensions
		map = new Map();
		// The rectangles and images now get the dimensions of the map
		// This makes non-square map dimensions like (20,20) or (15,15) possible
		// without things crashing and burning
		tiles = new Rectangle[map.getX()][map.getY()];
		tileImg = new Image[map.getX()][map.getY()];
		loadArrays();
	}

	
	//Take the map and fill in a 2d array with images for drawing
	private void loadArrays() {
		for(int i = 0; i < map.getX(); i++) {
			for (int j = 0; j < map.getY(); j++){
				// For testing purposes
				//System.out.println("Y = " + i + " X = " + j + " MAPX = " + map.getX() + " MAPY = " + map.getY());
				if(map.getArr(i, j) == 1) {
					tileImg[i][j] = TILE_GRASS;
				}
				else if(map.getArr(i, j) == 2) {
					tileImg[i][j] = TILE_WATER;
				}
				else
					;
				
				tiles[i][j] = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
				
				y = (j+1)*TILE_SIZE;
			}
			y = 0;
			x = (i+1)*TILE_SIZE;
		}	
	}
	
	//Draw each tile to the screen
	public void draw(Graphics g) {
		for(int i = 0; i < map.getX(); i++) {
			for(int j = 0; j < map.getY(); j++) {
			g.drawImage(tileImg[i][j], tiles[i][j].x, tiles[i][j].y, null);
			}
		}
	}
	// Get map
	public Map getMap() {
		return map;
	}
}
