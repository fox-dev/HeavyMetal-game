package project;

import java.io.*;

/* Map.java
 * This class will make a 2D array and fill it with land tiles for now. I will add water
 * back into this class soon.
 * 
 * Constructors:
 * 	Default: Makes a 20x20 2D array
 * 	Map(int A,int B): Makes a A*B 2D array
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
 *  
 *  !! MAP LEGEND !!
 *  1 = Normal Land
 *  2 = Water
 */

public class Map {
	private static final int DEFAULT_SIZE_X = 20;
	private static final int DEFAULT_SIZE_Y = 20;
	private int[][] mapArr; // 2D Map array
	private int x; // Size of X
	private int y; // Size of Y
	
	// Constructors //
	
	// Default will make a 20x20 map
	public Map() {
		this(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
	}
	// Map(int I, int J) will make a IxJ map
	public Map(int xSize, int ySize) {
		// Set the sizes of the map and make the array
		x = xSize;
		y = ySize;
		mapArr = new int[x][y];
		// Fill the array with 1's (land tiles)
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				mapArr[i][j] = 1;
	}
	
	// Map(String name) will import a map from a data file
	public Map(String srcName) {
		if (!isMapValid(srcName)) {
			// Make a 20x20 since import failed
			// We can change this later
			x = DEFAULT_SIZE_X;
			y = DEFAULT_SIZE_Y;
			mapArr = new int[x][y];
			// Fill the array with 1's (land tiles)
			for (int i = 0; i < x; i++)
				for (int j = 0; j < y; j++)
					mapArr[i][j] = 1;
		}
		else
			// Import the map
			try{
				BufferedReader br = new BufferedReader(new FileReader("maps/"+srcName));
				String dX = "";
				String dY = "";
				String buffer;
				// Read through the file
				while ((buffer = br.readLine()) != null) {
					// If DIMENSIONS tag is found
					if (buffer.startsWith("# DIMENSIONS #")) {
						buffer = br.readLine();
						// Get XSIZE and YSIZE. There are no checks because map should be valid
						for (int x = buffer.indexOf("XSIZE=")+6; x < buffer.length(); x++)
							dX = dX + (Character.toString(buffer.charAt(x)));
						x = Integer.parseInt(dX);
						buffer = br.readLine();
						for (int y = buffer.indexOf("YSIZE=")+6; y < buffer.length(); y++)
							dY = dY + (Character.toString(buffer.charAt(y)));
						y = Integer.parseInt(dY);
					}
					// If MAP DATA is found
					else if (buffer.startsWith("# MAP DATA #"))
						break; // Go to next step
					// Skip comments
					else if (buffer.startsWith("#"))
						;
				}
				// Make and fill the 2D array
				mapArr = new int[x][y];
				for(int x = 0; x < Integer.parseInt(dX); x++)
					for(int y = 0; y < Integer.parseInt(dY); y++) {
						mapArr[x][y] = Character.getNumericValue((char)br.read());
					}
				br.close();
			}catch(IOException e) {
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
	
	// Checks if a map from data is valid
	// Returns true if it's valid, else false
	
	private boolean isMapValid(String srcName) {
		String dX = ""; // Imported map's X size
		String dY = ""; // Imported map's Y size
		String buffer; // String buffer
		boolean dimensionsFound = false; // True if "# DIMENSIONS #" is found, else false
		boolean mapDataFound = false; // True if "# MAP DATA #" is found, else false
		boolean correctX = false; // True if a correct XSIZE is found, else false
		boolean correctY = false; // True if a correct YSIZE is found, else false
		boolean arrCorrectLength = false; // True if the array is of correct length (length = XSIZE * YSIZE), else false
		boolean arrCorrectData = false; // True if map data is correct (only numbers, no letters or special characters)
		// Open up the map file
		try {
			// Open up the map file
			BufferedReader br = new BufferedReader(new FileReader("maps/"+srcName));
			/*
			 * This loop will go through the file looking for two tags: "# DIMENSIONS #" and "# MAP DATA #"
			 * When it finds the DIMENSIONS tag, it will check if XSIZE= and YSIZE= exist within the tag
			 */
			while ((buffer = br.readLine()) != null) {
				if (buffer.startsWith("# DIMENSIONS #")) {
					if (dimensionsFound)
						return false; // There can only be one # DIMENSIONS # in a map file
					dimensionsFound = true; // Found dimensions data
					buffer = br.readLine();
					if (buffer.startsWith("XSIZE=")) {
						for (int x = buffer.indexOf("XSIZE=")+6; x < buffer.length(); x++) {
							if (buffer.charAt(x) > 48 || buffer.charAt(x) < 57) {
								dX = dX + (Character.toString(buffer.charAt(x))); // dX gets the digits after XSIZE=
								correctX = true; // As long as ASCII chars are 0 - 9, then XSIZE is correct
							}
								else
									return false; // Else XSIZE is not correct
						}
					}
					else
						return false; // There is no XSIZE within DIMENSIONS
					buffer = br.readLine();
					if (buffer.startsWith(("YSIZE="))) {
						for (int y = buffer.indexOf("YSIZE=")+6; y < buffer.length(); y++) {
							if (buffer.charAt(y) > 48 || buffer.charAt(y) < 57) {
								dY = dY + (Character.toString(buffer.charAt(y)));
								correctY = true; // As long as ASCII chars are 0 - 9, then YSIZE is correct
							}
							else
								return false; // Else YSIZE is not correct
						}
					}
					else
						return false; // There is no YSIZE within DIMENSIONS
				}
				else if (buffer.startsWith("# MAP DATA #")) {
					if (!dimensionsFound)
						return false; // Map data must be placed AFTER dimensions or else we can't find the expected number of digits
					if (mapDataFound)
						return false; // There can only be one # MAP DATA # in the map file
					mapDataFound = true; // Map data has been found
					buffer = br.readLine();
					if (buffer.startsWith("#"))
						return false; // There should be no comments in between # MAP DATA # tag and the map data
					if (buffer.length() == (Integer.parseInt(dX) * Integer.parseInt(dY)))
						arrCorrectLength = true; // The array has the correct expected number of digits (X*Y = expected # of digits)
					else
						return false; // Bad map length
					// Go through the data and see if each character is a number and not a letter
					for (int x = 0; x < buffer.length(); x++)
						if (buffer.charAt(x) < 48 || buffer.charAt(x) > 57)
							return false;
					// If everything is correct, the array is correct
					arrCorrectData = true;
				}
				else if (buffer.startsWith("#"))
					; // Skip the line
				else
					return false; // If there are lines without comments, the file is invalid
			}
			br.close();
		}catch(IOException e) {
			;
		}
		// If everything checks out, then the map file is correct otherwise it's a bad file
		if (dimensionsFound && mapDataFound && correctX && correctY && arrCorrectLength && arrCorrectData)
				return true;
		// Display why the map is invalid if needed //
			return false;
	}
	
	// The map's array contents and dimensions will be written to a file with a given name
	
	public void exportMap(String destName) {
		try {
			// Make a file with the specified name
			BufferedWriter bw = new BufferedWriter(new FileWriter("maps/" + destName));
			// # EXPORTED MAP # is the header for exported map
			bw.write("#EXPORTED MAP#");
			bw.newLine();
			bw.write("#");
			bw.newLine();
			bw.write("##############");
			bw.newLine();
			bw.write("# DIMENSIONS #"); // Write dimensions tag
			bw.newLine();
			// Write X and Y sizes
			bw.write("XSIZE=" + x);
			bw.newLine();
			bw.write("YSIZE=" + y);
			bw.newLine();
			bw.write("############");
			bw.newLine();
			bw.write("# MAP DATA #"); // Write map data tag
			bw.newLine();
			// Write the contents of the array
			for(int i = 0; i < x; i++) {
				for (int j = 0; j < y; j++) {
					if (mapArr[i][j] == 1)
						bw.write("1");
					else if (mapArr[i][j] == 2) {
						bw.write("2");
					}
				}
			}
			bw.close(); // Close the stream
		}catch(IOException e) {
			; // Put something to catch the exception
		}
	}
	private void copyMap(Map target){
		//MAKES NO CHECK FOR SIZE, HENCE MADE PRIVATE FOR "COPY" CONSTRUCTOR DAN
	    for(int i = 0; i < target.mapArr.length; i++) {
	        for(int j = 0; j < target.mapArr[i].length; j++){
	          this.mapArr[i][j] = target.mapArr[i][j];
	        }
	    }
	}
	
	// Get methods //
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getArr(int x, int y) {
		return mapArr[x][y];
	}
}