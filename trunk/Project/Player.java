package project;

import java.util.ArrayList;

public class Player {
  //default coordinates
  private static final int P1_AIR_X = 4;      // ( 4, 5)
  private static final int P1_AIR_Y = 5;
  private static final int P1_GROUND_X = 4;   // ( 4,10)
  private static final int P1_GROUND_Y = 10;
  private static final int P2_AIR_X = 11;     // (11, 5)
  private static final int P2_AIR_Y = 5;
  private static final int P2_GROUND_X = 12;  // (12, 5)
  private static final int P2_GROUND_Y = 5;
  
  // Creates the units
  // Keep track of active units
  // How many units that have been moved during the turn

  private ArrayList<Unit> units = new ArrayList<Unit>();

  private UnitAir air;
  private UnitGround ground;

  //Constructor, creates units, sets their location
  public Player(int playNum) {
    
    air = new UnitAir();
    ground = new UnitGround();
    
    if(playNum == 1) {
      air.setXY(P1_AIR_X, P1_AIR_Y);
      ground.setXY(P1_GROUND_X, P1_GROUND_Y);
    }
    if(playNum == 2) {
      air.setXY(P2_AIR_X, P2_AIR_Y);
      ground.setXY(P2_GROUND_X, P2_GROUND_Y);
    }
    
    units.add(air);
    units.add(ground);
  }
  
  //Added by DAN
  public Player(int airX, int airY, int groundX, int groundY){
    air = new UnitAir();
    ground = new UnitGround();
    air.setXY(airX, airY);
    ground.setXY(groundX, groundY);
    units.add(air);
    units.add(ground);    
  }
  
  //Removes units that have no more HP
  public void removeDeadUnits() {
    for (int i = 0; i < units.size(); i++) {
      Unit tempUnit = units.get(i);
      if(tempUnit.getHP() <= 0) {
        units.remove(i);
        i--;
      }
    }
  }
  
  //Checks to see if the player's turn is over
  public boolean checkTurnOver() {
    for (Unit currentUnit : units) {
      if(currentUnit.hasMoved() == false && currentUnit.getHasUnitShot() == false)
        return false;
    }
    
    return true;
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
    }
  }
}

