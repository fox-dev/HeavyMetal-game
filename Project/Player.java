package project;

import java.util.ArrayList;

public class Player {
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
			air.setLocationX(4);
			air.setLocationY(5);
			ground.setLocationX(4);
			ground.setLocationY(10);
		}
		if(playNum == 2) {
			ground.setLocationX(12);
			ground.setLocationY(5);
			air.setLocationX(12);
			air.setLocationY(5);
		}
		
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
		for (int i = 0; i < units.size(); i++) {
      if((units.get(i)).hasMoved() == false)
				return false;
		}
		
		return true;
	}
	
	//Returns a unit at a position x, y or null if there is nothing at that position
	public Unit getUnitAt(int x, int y) {
		for(int i = 0; i < units.size(); i++) {
			if(units.get(i).getLocationX() == x && units.get(i).getLocationY() == y) {
				return units.get(i);
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
    for (int i = 0; i < units.size(); i++){
      units.get(i).movedFalse();
      units.get(i).setHasUnitShot(false);
    }
	}
}

