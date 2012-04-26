/* UnitGround.java
 * Implements Unit.java //changed to inherit DAN
 *
 * A generic ground unit will have the following:
 * HP = 4
 * NUMBER OF MOVES = 2
 * ATTACK POWER = 2
 * SPAWN AT LOCATION (0,0)
 * GROUND TYPE (Type = 0)
 * DESCRIPTION = TANK
 * RESTRICTED TO LAND MOVEMENT ONLY (Restrict = 1)
 *
 * Specific constructor = (HP, Number of Moves, Unit Type, Unit Description, Move Restriction, ATK, locX, locY)
 *
 *
 * Will add new things as project goes on
*/

package project;

public class UnitGround extends Unit {
  //defaults for UnitGround DAN 
  private static final int HP_DEFAULT = 4;
  private static final int MOVES_DEFAULT = 2;
  private static final int UNIT_TYPE = 0; // Ground type
  private static final String UNIT_DESCRIPTION = "Tank"; // A name for generic ground units
  private static final int MOVE_RESTRICTION = 1; // Only moves on land
  private static final int ATK_DEFAULT = 2;
  private static final int ATTACK_RANGE = 2;
  private static final int SPAWN_LOC_X = 0;
  private static final int SPAWN_LOC_Y = 0;
  
	public UnitGround() {
		  super(HP_DEFAULT,MOVES_DEFAULT,UNIT_TYPE,UNIT_DESCRIPTION,MOVE_RESTRICTION,ATK_DEFAULT,ATTACK_RANGE,SPAWN_LOC_X,SPAWN_LOC_Y);
	}
	
	public UnitGround(int setHP, int setNumMoves, int Utype, String desc, int restrict, int ATK, int range, int locationX, int locationY) {
		super(setHP, setNumMoves, Utype, desc, restrict, ATK, range, locationX, locationY);
	}
}