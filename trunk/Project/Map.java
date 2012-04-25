package project;

import java.io.*;

/* Map.java
 * This java file will make a 20x20 2D array and (at this time) fill it with
 * land tiles and water tiles. Randomized water tiles are disabled at this time.)
 * 
 * Default constructor makes a 2D array of size 20x20. Randomized water tiles are disabled.
 * 
 * If a constructor is given a string, it will try to find a map with that name in the /maps/
 * folder. 
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
  // Make map from data
  public Map(String srcName) {
	  try{
		  BufferedReader br = new BufferedReader(new FileReader("maps/"+srcName));
		  String bX = ""; // Imported map's X size
		  String bY = ""; // Imported map's Y size
		  String buffer; // String buffer
		  buffer = br.readLine(); // Read a line
		  // Find "XSIZE=" in the file and bX will hold the map's X size
		  for (int x = buffer.indexOf("XSIZE=")+6; x < buffer.length(); x++)
			  bX = bX + (Character.toString(buffer.charAt(x)));
		  buffer = br.readLine(); // Read another line
		  // Find "YSIZE=" in the file and bY will hold the map's Y size
		  for (int y = buffer.indexOf("YSIZE=")+6; y < buffer.length(); y++)
			  bY = bY + (Character.toString(buffer.charAt(y)));
		  // Set the map's X and Y size
		  x = Integer.parseInt(bX);
		  y = Integer.parseInt(bY);
		  mapArr = new int[x][y];
		  // Fill the map with data from file
		  for(int x = 0; x < Integer.parseInt(bX); x++){
			for(int y = 0; y < Integer.parseInt(bY); y++){
				mapArr[x][y] = Character.getNumericValue((char)br.read());
				System.out.println(mapArr[x][y]);
				}
			}
		br.close();
	  }catch(IOException e) {
		  ; // Add something to catch exceptions
			}
  }
  public int getX() { return x; } // Returns X size
  public int getY() { return y; } // Returns y size
  public int getArr(int x, int y) { return mapArr[x][y]; } // Returns # at given coordinates
  
  // Will export a map with the given name in the /maps/ folder
  public void exportMap(String destName, Map srcMap) {
	  try {
		  BufferedWriter bw = new BufferedWriter(new FileWriter ("maps/" + destName));
		  bw.write("XSIZE="+srcMap.getX());
		  bw.newLine();
		  bw.write("YSIZE="+srcMap.getY());
		  bw.newLine();
		  for(int i = 0; i < x; i++)
		      for(int j = 0; j < y; j++) {
		    	  if (srcMap.getArr(i, j) == 1)
		    		  bw.write("1");
		    	  else if (srcMap.getArr(i, j) == 2)
		    		  bw.write("2");
		      }
	  }catch(IOException e) {
    	  ; // Put something to catch the exception
	  }
  }
   
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
}
