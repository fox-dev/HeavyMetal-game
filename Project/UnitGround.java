// UnitGround.java
// Implements Unit.java //changed to inherit DAN
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

public class UnitGround extends Unit {
  //defaults for UnitGround DAN 
  private static final int HP_DEFAULT = 4;
  private static final int MOVES_DEFAULT = 2;
  private static final int UNIT_TYPE = 0; // Ground type
  private static final int ATK_DEFAULT = 2;
  private static final int SPAWN_LOC_X = 0;
  private static final int SPAWN_LOC_Y = 0;
  
	public UnitGround() {
    super(HP_DEFAULT,MOVES_DEFAULT,UNIT_TYPE,ATK_DEFAULT,SPAWN_LOC_X,SPAWN_LOC_Y);
	}
	
	public UnitGround(int setHP, int setNumMoves, int Utype, int ATK, int locationX, int locationY) {
    super(setHP, setNumMoves, Utype, ATK, locationX, locationY);
	}
}