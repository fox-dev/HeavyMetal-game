package project;

import java.awt.*;

import javax.swing.ImageIcon;

public class World {
	
	//Create variables and classes
	private Rectangle[][] tiles;
	private Image[][] tileImg;
	Map map;
	public static final int TILE_SIZE = 30;
	public static final int MAP_DEM = 20;
	private int x= 0, y = 0;
	
	private Image TILE_GRASS, TILE_WATER;
	
	
	public World() {
		TILE_GRASS = new ImageIcon("images/grass.png").getImage();
		TILE_WATER = new ImageIcon("images/water.png").getImage();
		tiles = new Rectangle[MAP_DEM][MAP_DEM];
		tileImg = new Image[MAP_DEM][MAP_DEM];
		map = new Map();
		loadArrays();
	}

	
	//Take the map and fill in a 2d array with images for drawing
	private void loadArrays() {
		for(int i = 0; i < MAP_DEM; i++) {
			for (int j = 0; j < MAP_DEM; j++){
				if(map.getArr(i, j) == 1) {
					tileImg[i][j] = TILE_GRASS;
				}
				else if(map.getArr(i, j) == 2) {
					tileImg[i][j] = TILE_WATER;
				}
				
				tiles[i][j] = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
				
				x = (j+1)*TILE_SIZE;
			}
			x = 0;
			y = (i+1) *TILE_SIZE;
		}	
	}
	
	//Draw each tile to the screen
	public void draw(Graphics g) {
		for(int i = 0; i < MAP_DEM; i++) {
			for(int j = 0; j < MAP_DEM; j++) {
			g.drawImage(tileImg[i][j], tiles[i][j].x, tiles[i][j].y, null);
			}
		}
	}
	
}
