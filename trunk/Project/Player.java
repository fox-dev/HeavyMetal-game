package project;

import java.util.ArrayList;

public class Player {
  //for public Player(int playerNum, int numAir, int numGround, int numWater)
  private static final int P1_NUM_AIR = 1;
  private static final int P1_NUM_GROUND = 1;
  private static final int P1_NUM_WATER = 1;
  private static final int P2_NUM_AIR = 1;
  private static final int P2_NUM_GROUND = 1;
  private static final int P2_NUM_WATER = 1;
  private static final int P3_NUM_GROUND = 1;
  private static final int P3_NUM_AIR = 1;
  private static final int P3_NUM_WATER = 1;
  private static boolean AImoved;
  public static final int P1 = 1;
  public static final int P2 = 2;
  public static final int P_AI = 3;
  
  // Creates the units
  // Keep track of active units
  // How many units that have been moved during the turn

  protected ArrayList<Unit> units = new ArrayList<Unit>();
  protected ArrayList<Unit> deadunits = new ArrayList<Unit>();
  protected int playNo; // Francisco Edit
  
  // Boolean so that a player can only have 1 selected unit at a time -Andrew
  protected static boolean selected;
  protected int selectedUnitNumber = -1;

  public Player(int playNum){
	  if (playNum == 1)
		  helpConstructor(playNum, P1_NUM_AIR, P1_NUM_GROUND, P1_NUM_WATER);
	  else if (playNum == 2)
		  helpConstructor(playNum, P2_NUM_AIR, P2_NUM_GROUND, P2_NUM_WATER);
	  else if (playNum == 3)
		  helpConstructor(playNum, P3_NUM_AIR, P3_NUM_GROUND, P3_NUM_WATER);
  }
  public Player(int playerNum, int numAir, int numGround, int numWater){
    helpConstructor(playerNum, numAir, numGround, numWater);
  }
  private void helpConstructor(int playerNum, int numAir, int numGround, int numWater){
    selected = false;
    this.playNo = playerNum;
    for(int i = 0; i < numAir; i++)
      units.add(new UnitAir());
    for(int i = 0; i < numGround; i++)
      units.add(new UnitGround());
    for(int i = 0; i < numWater; i++)
      units.add(new UnitWater());
    units.add(new UnitBase());
  }
  
  // Francisco Edit: Returns player number
  public int getPlayerNum() { return playNo; }
  
  //Removes units that have no more HP
  public void removeDeadUnits() {
    for (int i = 0; i < units.size(); i++) {
      Unit tempUnit = units.get(i);
      if(tempUnit.getHP() <= 0) {
    	deadunits.add(tempUnit);
        units.remove(i);
        i--;
      }
    }
  }
  
  public int deadUnitsSize() {
	  return deadunits.size();
  }
  
  public Unit getdeadunit(int unitNum) {
	  if(unitNum > deadunits.size() - 1) {
		  return null;
	  }
	  else
		  return deadunits.get(unitNum);
  }
  
  public void removeExplodingUnit() {
	  deadunits.remove(0);
  }
  
  //Checks to see if the player's turn is over
  public boolean checkTurnOver() {
    for (Unit currentUnit : units) {
      if(currentUnit.hasMoved() == false || currentUnit.getHasUnitShot() == false) //Changed back to || now that we have an end turn button -Andrew 5.3.2012
        return false;
    }
    
    return true;
  }
  
  //Added by Andrew, Force turn over for the player.
  public void forceTurnOver(){
	  for(Unit currentUnit : units){
		  currentUnit.moved();
		  currentUnit.attacked();
		  if(currentUnit instanceof UnitBase)
		  	((UnitBase)(currentUnit)).moved();
	  }
  }
  
  //Returns a unit at a position x, y or null if there is nothing at that position
  public Unit getUnitAt(int x, int y) {
    for(Unit currentUnit : units) {
      if(currentUnit.getLocationX() == x && currentUnit.getLocationY() == y) {
        return currentUnit;
      }
    }
    
    return null;
  }
  
  //Gets a unit called by its index
  public Unit getUnit(int unitNum) {
    if(unitNum > units.size() - 1){
      return null;
    }
    return units.get(unitNum);
  }
  
  //Checks how many active units there are
  public int checkNumUnits() {
    return units.size();
  }
  
  //Reset all units of Player to Unit.moved == false Dan
  //Reset all units of Player to Unit.setHasUnitShot = false Dan
  public void unitsReset(){
    for (Unit currentUnit : units){
    	
      currentUnit.movedFalse();
      currentUnit.setHasUnitShot(false);
      if(currentUnit instanceof UnitBase){
    	  currentUnit.movedFalse();
      }
    }
  }
  
  //Added by Dan 23 Apr 2012
  //returns true if u is a unit of this.Player
  //returns false if u is NOT in Player.units ArrayList<Unit> 
  public boolean isThisMyUnit(Unit testUnit){
    for(Unit thisUnits : units){
      if(thisUnits == testUnit)
        return true;
    }
    return false;
  }
  
	// Checks to see if a unit is selected, only 1 unit selection at a time -Andrew
	public static boolean unitSelected() {
		return selected;
	}
	// Set true selected -Andrew
	public void setSelectedTrue(){
		selected = true;
	}
	
	// Set false selected -Andrew
	public void setSelectedFalse(){
		selected = false;
	}
	
	public boolean getAImoved() {
		return AImoved;
	}

	public void setAImovedTrue() {
		AImoved = true;
	}
	
	public void setAImovedFalse() {
		AImoved = false;
	}
	public void addUnit(Unit u){ units.add(u); } 
}