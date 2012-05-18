/* UnitAir.java
 * Implements Unit.java //changed to inherit DAN
 *
 * A generic air unit will have the following:
 * HP = 3
 * NUMBER OF MOVES = 4
 * ATTACK POWER = 1
 * SPAWN AT LOCATION (0,0)
 * AIR TYPE (Type = 1)
 * DESCRIPTION = BOMBER
 * NO RESTRICTIONS TO MOVEMENT (Restrict = 0)
 *
 * Specific constructor = (HP, Number of Moves, Unit Type, Unit Description, Move Restriction, ATK, locX, locY) 
 *
 *
 * Will add new things as project goes on
*/

package project;

public class UnitAir extends Unit {
  //defaults for UnitAir DAN
	private static final int HP_DEFAULT = 3;
	private static final int MOVES_DEFAULT = 4;
	private static final int UNIT_TYPE = 1; // Air type
	private static final String UNIT_DESCRIPTION = "Bomber"; // A name for generic Air units
	private static final int MOVE_RESTRICTION = Unit.NONE; // Can move anywhere
	private static final int ATK_DEFAULT = 1;
	private static final int ATK_RANGE = 1;
	private static final int SPAWN_LOC_X = 0;
	private static final int SPAWN_LOC_Y = 0;
	private static final int POINT = 50; //Added by Sidra; May 17
	
	public UnitAir() {
	  super(HP_DEFAULT,MOVES_DEFAULT,UNIT_TYPE,UNIT_DESCRIPTION,MOVE_RESTRICTION,ATK_DEFAULT,ATK_RANGE,SPAWN_LOC_X,SPAWN_LOC_Y,POINT);
	}
	
	public UnitAir(int setHP, int setNumMoves, int Utype, String desc, int restrict, int ATK, int range, int locationX, int locationY, int setPoint) {
		super(setHP, setNumMoves, Utype, desc, restrict, ATK, range, locationX, locationY, setPoint);
	}
}