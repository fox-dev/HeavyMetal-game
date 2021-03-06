package project;

import java.awt.*;

import javax.swing.ImageIcon;

import project.Map;

public class World {
	
	//Create variables and classes
	private Rectangle[][] tiles;
	private Image[][] tileImg;
	Map map;
	public static final int TILE_SIZE = 30;
	// We're not using MAP_DEM at this moment
	public static final int MAP_DEM = 20;
	private int x= 0, y = 0;
	public static final String GRASS_PATH = "images/new_grass2.png";
	public static final String WATER_PATH = "images/new_waterRiple.gif";
	public static final String MOUNTAIN_PATH = "images/mountainRocks.png";
	public static final String FOREST_PATH = "images/new_forest.png";
	public static final String BRIDGE_PATH = "images/new_bridge.png";
	
	
	private Image TILE_GRASS, TILE_WATER, TILE_MOUNTAIN, TILE_FOREST, TILE_BRIDGE;
	
	public World() {
		TILE_GRASS = new ImageIcon(GRASS_PATH).getImage();
		TILE_WATER = new ImageIcon(WATER_PATH).getImage();
		TILE_MOUNTAIN = new ImageIcon(MOUNTAIN_PATH).getImage();
		TILE_FOREST = new ImageIcon(FOREST_PATH).getImage();
		TILE_BRIDGE = new ImageIcon(BRIDGE_PATH).getImage();
		// Map is now created before tiles and tileImg so we can get its dimensions
		map = new Map();
		// The rectangles and images now get the dimensions of the map
		// This makes non-square map dimensions like (20,20) or (15,15) possible
		// without things crashing and burning
		tiles = new Rectangle[map.getX()][map.getY()];
		tileImg = new Image[map.getX()][map.getY()];
		loadArrays();
	}
	
	public World(Map m) {
		TILE_GRASS = new ImageIcon(GRASS_PATH).getImage();
		TILE_WATER = new ImageIcon(WATER_PATH).getImage();
		TILE_MOUNTAIN = new ImageIcon(MOUNTAIN_PATH).getImage();
		TILE_FOREST = new ImageIcon(FOREST_PATH).getImage();
		TILE_BRIDGE = new ImageIcon(BRIDGE_PATH).getImage();
		// Map is now created before tiles and tileImg so we can get its dimensions
		map = m;
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
			//	else
				//	;
				
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
