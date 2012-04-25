package project;

/* Map.java
 * This java file will make a 20x20 2D array and (at this time) fill it with
 * land tiles and water tiles. Randomized water tiles are disabled at this time.)
 * 
 * Default constructor makes a 2D array of size 20x20. Randomized water tiles are disabled.
 * 
 * Specific constructor = (X Size, Y Size)
 * 
 * !! MAP LEGEND !!
 * 1 = Normal
 * 2 = Water
 */

public class Map {
  private static final int DEFAULT_SIZE_X = 20;
  private static final int DEFAULT_SIZE_Y = 20;
  
  private int[][] mapArr; // 2D Map array
  private int x; // Size of X
  private int y; // Size of Y
  
  public Map() {
    this(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
  }
  
  // Specify a custom X and Y and make array that size
  public Map(int xsize, int ysize) {
    x = xsize;
    y = ysize;
    mapArr = new int[x][y];
    // double rand = 0;
    for(int i = 0; i < x; i++)
      for(int j = 0; j < y; j++) {
        /*
        rand = Math.random();
        if (rand >= 0.8)
          mapArr[i][j] = 2;
        else
          mapArr[i][j] = 1;
        */
        mapArr[i][j] = 1;
      }
  }
  public int getX() { return x; } // Returns X size
  public int getY() { return y; } // Returns y size
  public int getArr(int x, int y) { return mapArr[x][y]; } // Returns # at given coordinates
   
  //Create a new map from a previous map.  Made for testing cases and troubleshooting DAN
  public Map(Map m){ 
    //create a Map object using the constructors
    this(m.getX(),m.getY());
    //copy contents
    m.copyMap(this);
  }
  
  private void copyMap(Map target){
    //MAKES NO CHECK FOR SIZE, HENCE MADE PRIVATE FOR "COPY" CONSTRUCTOR DAN
    for(int i = 0; i < target.mapArr.length; i++) {
      for(int j = 0; j < target.mapArr[i].length; j++){
        this.mapArr[i][j] = target.mapArr[i][j]; 
      }
    }    
    
  }
  
	//Get map -Andrew
	public Map getMap(){
		return map;
	}
}
