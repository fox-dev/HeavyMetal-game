// UnitGround.java
// Implements Unit.java
//
// A generic ground unit will have the following:
// HP = 4
// NUMBER OF MOVES = 2
// ATTACK POWER = 2
// SPAWN AT LOCATION (0,0)
//
// Specific constructor = (HP, Number of Moves, ATK, locX, locY) 
//
// Ground units can have specified attributes by inputting HP, number of moves, attack,
//	and its location
//
// Will add new things as project goes on

package project;

public class UnitGround implements Unit {

	private int HP;
	private int numMoves;
	private int attack;
	private int locX;
	private int locY;
	private boolean moved = false;
	private boolean hasUnitShot = false;   //Dan: ensures that unit can only shoot once
	
	public UnitGround() {
		HP = 4;
		numMoves = 2;
		attack = 2;
		locX = 0;
		locY = 0;
	}
	
	public UnitGround(int setHP, int setNumMoves, int ATK, int locationX, int locationY) {
		HP = setHP;
		numMoves = setNumMoves;
		attack = ATK;
		locX = locationX;
		locY = locationY;
	}
	public int getMoves() {
		return numMoves;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int setHP) {
		HP = setHP;
	}
	public void moved() {
		moved = true;
	}
	public void movedFalse() { //added by Dan
	  moved = false;
	}
	public boolean hasMoved() {
		return moved;
	}
	public int getAttack() {
		return attack;
	}
	public void setLocationX(int x) {
		locX = x;
	}
	public void setLocationY(int y) {
		locY = y;
	}
	public int getLocationX() {
		return locX;
	}	
	public int getLocationY() {
		return locY;
	}
  public void setHasUnitShot(boolean b){
    hasUnitShot = b;
  }
  
  public boolean getHasUnitShot(){
    return hasUnitShot;
  }
}