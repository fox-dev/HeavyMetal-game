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
	private static final int MOVE_RESTRICTION = 0; // Can move anywhere
	private static final int ATK_DEFAULT = 1;
	private static final int SPAWN_LOC_X = 0;
	private static final int SPAWN_LOC_Y = 0;
	
	public UnitAir() {
	  super(HP_DEFAULT,MOVES_DEFAULT,UNIT_TYPE,UNIT_DESCRIPTION,MOVE_RESTRICTION,ATK_DEFAULT,SPAWN_LOC_X,SPAWN_LOC_Y);
	}
	
	public UnitAir(int setHP, int setNumMoves, int Utype, String desc, int restrict, int ATK, int locationX, int locationY) {
		super(setHP, setNumMoves, Utype, desc, restrict, ATK, locationX, locationY);
	}
}