package project;

import java.io.*;

/* Map.java
 * This class will make a 2D array and fill it with land tiles for now. I will add water
 * back into this class soon.
 * 
 * Constructors:
 * 	Default: Makes a 20x20 2D array
 * 	Map(int A,int B): Makes an A*B 2D array with default water chances
 *  Map(int A,int B,boolean flag): Makes an A*B 2D array. If the flag is false, no water is generated, otherwise water is generated
 *  Map(int A,int B, double L, double R): Makes an A*B 2D array with L leftChance and R rightChance for addWater()
 * 	Map(String mapName): Imports a map from a data file with the given name. The class will look
 * 						 in the /maps/ folder for a map with the given name. If none is found, a
 * 						 20x20 array is created instead.
 * Map (Map m): Used for testing and troubleshooting
 * 
 * Public methods:
 * 	exportMap(String mapName): Exports the current 2D array to a file. The file will be stored
 * 							   in the /maps/ folder and will be given the specified name.
 *  getX(): returns the X dimension of the array
 *  getY(): returns the Y dimension of the array
 *  getArr(int X, int Y): returns digit at position (X,Y)
 *  
 * Private methods:
 * 	isMapValid(String mapName): Checks if a map with the given name is valid. Returns true if
 * 								the map is valid, returns false otherwise.
 *  copyMap(Map m): Creates a new map from a previous one. Used for testing and troubleshooting
 *  addWater(double L, double R): An algorithm that adds water to a map. The algorithm will pick
 *  							  a random point (source point) on the map and make a river 
 *  							  going up and then down from that source.
 *  							  LeftChance = What percentage (from 0% til leftChance) of water will generate to the left?
 *  							  RightChance = What percentage (from rightChance til 100%) of water will generate to the right?\
 *  							  Default leftChance = .25 and rightChance = .75
 *  
 *  !! MAP LEGEND !!
 *  1 = Normal Land
 *  2 = Water
 */

public class Map {
	public static final int GROUND = 1; // added Dan, "MACRO" used in Actions
	public static final int WATER = 2; // added Dan, "MACRO" used in Actions
	private static final int DEFAULT_SIZE_X = 20;
	private static final int DEFAULT_SIZE_Y = 20;
	private static final double DEFAULT_WATER_LEFT_CHANCE = .25;
	private static final double DEFAULT_WATER_RIGHT_CHANCE = .75;
	private int[][] mapArr; // 2D Map array
	private int x; // The X dimension
	private int y; // The Y dimension
	
	// Constructors //
	// Default will make a 20x20 map and will add water
	public Map() {
		this(DEFAULT_SIZE_X,DEFAULT_SIZE_Y);
	}
	
	// Map (int X, int Y) will make a X*Y map with water added in
	public Map(int xSize, int ySize) {
		// Set the sizes of the map and make the array
		x = xSize;
		y = ySize;
		mapArr = new int[y][x]; // Array coordinates are swapped!
		// Fill the array with ground
		// The for functions are also swapped! (Altering array is not however)
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++)
				mapArr[i][j] = GROUND;
		// Now add water to the map
		addWater(.25,.75);
	}
	
	// Map (int X, int Y, boolean false) will make a X*Y with or without water (default chances)
	public Map(int xSize, int ySize, boolean water) {
		// Set the sizes of the map and make the array
		x = xSize;
		y = ySize;
		mapArr = new int[y][x]; // Array coordinates are swapped!
		// Fill the array with ground
		// The for functions are also swapped! (Altering array is not however)
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++)
				mapArr[i][j] = GROUND;
		// Check boolean
		if (water)
			addWater(DEFAULT_WATER_LEFT_CHANCE,DEFAULT_WATER_RIGHT_CHANCE);
	}
	
	// Map(int I, int J, double L, double R) will make an IxJ map and will pass left and right into addWater()
	public Map(int xSize, int ySize, double left, double right) {
		// Set the sizes of map and make the array
		x = xSize;
		y = ySize;
		mapArr = new int[y][x];
		// Fill the array with ground tiles
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++)
				mapArr[i][j] = GROUND;
		// Add water to the map
		addWater(left,right);
	}
	
	// Map(String mapName) will import a map from a data file
	public Map(String srcName) {
		// Check if map is valid
		// If it fails, make a default map with water generated with default chances
		if (!isMapValid(srcName)) {
			x = DEFAULT_SIZE_X;
			y = DEFAULT_SIZE_Y;
			mapArr = new int[y][x];
			for (int i = 0; i < y; i++)
				for (int j = 0; j < x; j++)
					mapArr[i][j] = 1;
			addWater(DEFAULT_WATER_LEFT_CHANCE,DEFAULT_WATER_RIGHT_CHANCE);
		}
		else
			// Import the map
			try {
				BufferedReader br = new BufferedReader(new FileReader("maps/"+srcName));
				String dX = "";
				String dY = "";
				String buffer;
				// Read through the file
				while ((buffer = br.readLine()) != null) {
					// If DIMENSIONS is found
					if (buffer.startsWith("[DIMENSIONS]") && (buffer.length() == 12)) {
						buffer = br.readLine();
						// Get XSIZE and YSIZE. There are no checks because map should be valid
						for (int x = buffer.indexOf("XSIZE=")+6; x < buffer.length(); x++)
							dX = dX + (Character.toString(buffer.charAt(x)));
						buffer = br.readLine();
						for (int y = buffer.indexOf("YSIZE=")+6; y < buffer.length(); y++)
							dY = dY + (Character.toString(buffer.charAt(y)));
						// Set them to x and y
						x = Integer.parseInt(dX);
						y = Integer.parseInt(dY);
					}
					// If MAP DATA is found
					else if (buffer.startsWith("[MAP DATA]") && (buffer.length() == 10))
						break; // Go to next step
					// If comment is found, skip it
					else if (buffer.startsWith("#"))
						;
				}
				// Make and fill the 2D array
				mapArr = new int[y][x];
				for (int i = 0; i < Integer.parseInt(dY); i++)
					for (int j = 0; j < Integer.parseInt(dX); j++)
						mapArr[i][j] = Character.getNumericValue((char)br.read());
				// Close the file
				br.close();
			} catch(IOException e) {
				; // Will add something later
			}
	}
	
	//Create a new map from a previous map. Made for testing cases and troubleshooting DAN
	public Map(Map m){
		//create a Map object using the constructors
		this(m.getX(),m.getY());
		//copy contents
		m.copyMap(this);
	}
	
	// Map methods //
	// isMapValid(String mapName) will check if the map file mapName is valid. Returns true if it is, else false
	
	private boolean isMapValid(String srcName) {
		String dX = ""; // Imported map's X dimension
		String dY = ""; // Imported map's Y dimension
		String buffer; // String buffer
		boolean dimensionsFound = false; // True if [DIMENSIONS] is found
		boolean mapDataFound = false; // True if [MAP DATA] is found
		boolean correctX = false; // True if correct XSIZE is found
		boolean correctY = false; // True if a correct YSIZE is found
		boolean arrCorrectLength = false; // True if map data's number of digits equals expected number of digits (Expected = XSIZE * YSIZE)
		boolean arrCorrectData = false; // True if map data only contains digits, no letters or special characters
		// Open map file
		try {
			BufferedReader br = new BufferedReader(new FileReader("maps/"+srcName));
			// Loop goes through file looking for [DIMENSIONS] and [MAP DATA]
			// When it finds [DIMENSIONS] it will check if XSIZE= and YSIZE= are followed by a number
			// When it finds [MAP DATA] it will check if the data is the correct length and if the data only contains digits
			while ((buffer = br.readLine()) != null) {
				// If DIMENSIONS is found
				if (buffer.startsWith("[DIMENSIONS]") && (buffer.length() == 12)) {
					// Check if DIMENSIONS was not found in the file already
					if (dimensionsFound)
						return false; // Only one DIMENSIONS can exist in a map
					dimensionsFound = true; // We found DIMENSIONS
					buffer = br.readLine();
					// If XSIZE= is found
					if (buffer.startsWith("XSIZE=")) {
						for (int x = buffer.indexOf("XSIZE=")+6; x < buffer.length(); x++) {
							// If the character has the ASCII values of a number
							if (buffer.charAt(x) > 48 || buffer.charAt(x) < 57) {
								// dX will hold the numbers after XSIZE=
								dX = dX + (Character.toString(buffer.charAt(x)));
							}
							else
								return false; // XSIZE is not a number
						}
						correctX = true; // XSIZE is a number
					}
					else
						return false; // XSIZE was not found
					buffer = br.readLine();
					// If YSIZE= is found
					if (buffer.startsWith("YSIZE=")) {
						for (int y = buffer.indexOf("YSIZE=")+6; y < buffer.length(); y++) {
							// If the character has the ASCII values of a number
							if (buffer.charAt(y) > 48 || buffer.charAt(y) < 57) {
								// dY will hold the numbers after YSIZE=
								dY = dY + (Character.toString(buffer.charAt(y)));
							}
							else
								return false; // Else YSIZE is not a number
						}
						correctY = true; // YSIZE is a number
					}
					else
						return false; // YSIZE was not found
				}
				else if (buffer.startsWith("[MAP DATA]") && (buffer.length() == 10)) {
					// Check if MAP DATA was already found in file
					if (mapDataFound)
						return false; // There can only be one MAP DATA
					// Check if MAP DATA follows DIMENSIONS
					if (!dimensionsFound)
						return false; // MAP DATA must be placed after DIMENSIONS
					mapDataFound = true; // Map data has been found
					buffer = br.readLine();
					// There should be no comments in between MAP DATA tag and its data
					if (buffer.startsWith("#"))
						return false;
					// Check if length of data matches with expected length
					if (buffer.length() == (Integer.parseInt(dX)) * (Integer.parseInt(dY)))
						arrCorrectLength = true; // The data is of correct length
					else
						return false; // Bad MAP DATA length
					// Go through data and see if each character has the ASCII values of a number and nothing else
					for (int i = 0; i < buffer.length(); i++)
						if (buffer.charAt(i) < 48 || buffer.charAt(i) > 57)
							return false;
					// If everything is correct, the data is correct
					arrCorrectData = true;
				}
				// If a comment is found, skip it
				else if (buffer.startsWith("#"))
					; // Skip the line
				else
					return false; // If any other form of text is found, the file is invalid
			}
			// Close the file
			br.close();
		} catch(IOException e) {
			; // Will add something later
		}
		// If everything checks out, then the map file is correct otherwise it's a bad file
		if (dimensionsFound && mapDataFound && correctX && correctY && arrCorrectLength && arrCorrectData)
				return true;
		// Display why the map is invalid if needed //
			return false;
	}
	
	// exportMap(String mapName) will export the current map being used
	public void exportMap(String destName) {
		try {
			// Make a file with the specified name
			BufferedWriter bw = new BufferedWriter(new FileWriter("maps/" + destName));
			bw.write("#EXPORTED MAP#");
			bw.newLine();
			bw.write("[DIMENSIONS]"); // Write dimensions tag
			bw.newLine();
			// Write X and Y sizes
			bw.write("XSIZE=" + x);
			bw.newLine();
			bw.write("YSIZE=" + y);
			bw.newLine();
			bw.write("############");
			bw.newLine();
			bw.write("[MAP DATA]"); // Write map data tag
			bw.newLine();
			// Write the contents of the array
			// Note: will print out any digit even if it's not in use
			for (int i = 0; i < y; i++) {
				for (int j = 0; j < x; j++) {
					switch(mapArr[i][j]) {
					case 1:
						bw.write("1"); break;
					case 2:
						bw.write("2"); break;
					case 3:
						bw.write("3"); break;
					case 4:
						bw.write("4"); break;
					case 5:
						bw.write("5"); break;
					case 6:
						bw.write("6"); break;
					case 7:
						bw.write("7"); break;
					case 8:
						bw.write("8"); break;
					case 9:
						bw.write("9"); break;
					case 0:
						bw.write("0"); break;
					default:
						// Probably change this to something else
						bw.write("1"); break;
					}
				}
			}
			// Close the file
			bw.close();
		} catch(IOException e) {
			; // Put something here
		}
	}
	
	// addWater(double left, double right) will add water to a map
	// The algorithm will pick a random spot on the map and based on a random number
	// 	will go up/down or left/right. If a number lands somewhere in the left chance
	//	percentage or right chance percentage, the river will move in that way. If it
	//  doesn't land within those ranges, the river will go up/down depending on what
	//	way it's going.
	private void addWater(double left, double right) {
		// Booleans to check if loops are done
		boolean doneUp = false;
		boolean doneDown = false;
		// Get a random point on the map to start the river
		int startX = (int)(Math.random() * x);
		int startY = (int)(Math.random() * y);
		double rand; // Random number
		// Temp X and Y to use in loops
		int tempX = startX;
		int tempY = startY;
		// Make the starting coordinate a water tile and start the loops
		mapArr[startY][startX] = WATER;
		// Go up first
		while (!doneUp) {
			// Generate a random number
			rand = Math.random();
			// If it falls at or below the left percentage, river goes left
			if (rand <= left) {
				tempX--; // Move tempX to the left
				// Check if tempX is now out of bounds
				if (tempX < 0)
					tempX++; // Move tempX back
				// Check if there's water already there
				else if (mapArr[tempY][tempX] == WATER)
					; // Don't add water again
				else
					mapArr[tempY][tempX] = WATER; // Add water
			}
			// If it falls in between the left and right percentages, move up
			else if (rand > left && rand < right) {
				tempY--; // Move tempY up
				// Check if tempY is out of bounds
				if (tempY < 0) 
					doneUp = true; // We reached the top so set the flag to true
				// Check if there's water present
				else if (mapArr[tempY][tempX] == WATER)
					; // Don't add water
				else
					mapArr[tempY][tempX] = WATER; // Add water
			}
			// If it doesn't fall in between or the left, it has to be on the right
			else {
				tempX++; // Move tempX to the right
				// Check if tempX is now out of bounds
				if (tempX >= x)
					tempX--; // Reset tempX
				// Check if there's water
				else if (mapArr[tempY][tempX] == WATER)
					; // Don't add water
				else 
					mapArr[tempY][tempX] = WATER; // Add water
			}
		}
		// Reset tempX and tempY
		tempX = startX;
		tempY = startY;
		// Now go down
		// Same deal except tempY is incremented because we're going down now
		while (!doneDown) {
			rand = Math.random();
			if (rand <= left) {
				tempX--;
				if (tempX < 0)
					tempX++;
				else if (mapArr[tempY][tempX] == WATER)
					;
				else
					mapArr[tempY][tempX] = WATER;
			}
			else if (rand > left && rand < right) {
				tempY++;
				if (tempY >= y)
					doneDown = true;
				else if (mapArr[tempY][tempX] == WATER)
					;
				else
					mapArr[tempY][tempX] = WATER;
			}
			else {
				tempX++;
				if (tempX >= x)
					tempX--;
				else if (mapArr[tempY][tempX] == WATER)
					;
				else
					mapArr[tempY][tempX] = WATER;
			}
		}
	}
	
	// copyMap(map Target) is a test function
	private void copyMap(Map target) {
		//MAKES NO CHECK FOR SIZE, HENCE MADE PRIVATE FOR "COPY" CONSTRUCTOR DAN
		for (int i = 0; i < target.mapArr.length; i++)
			for (int j = 0; j < target.mapArr[i].length; j++)
				this.mapArr[i][j] = target.mapArr[i][j];
	}
	
	// Get methods //
	// Returns X dimension
	public int getX() { return x; }
	
	// Returns Y dimension
	public int getY() { return y; }
	
	// Returns the tile at (x,y)
	public int getArr(int x, int y) { return mapArr[y][x]; }
	
	// Set methods //
	// Sets the tile at a given coordinate
	public void setArr(int x, int y, int tileVal) { mapArr[y][x] = tileVal; }
}