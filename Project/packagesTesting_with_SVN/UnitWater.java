/* UnitWater.java
 * Inherits from Unit.java
 *
 * A generic water unit will have the following:
 * HP = 2
 * NUMBER OF MOVES = 4
 * ATTACK POWER = 2
 * SPAWN AT LOCATION (0,0)
 * GROUND TYPE (Type = 0)
 * DESCRIPTION = WARSHIP
 * RESTRICTED TO WATER MOVEMENT ONLY (Restrict = 2)
 *
 * Specific constructor = (HP, Number of Moves, Unit Type, Unit Description, Move Restriction, ATK, locX, locY)
 *
 *
 * Will add new things as project goes on
*/

package unitPackage;

public class UnitWater extends Unit {
  private static final int HP_DEFAULT = 4;
  private static final int MOVES_DEFAULT = 2;
  private static final int UNIT_TYPE = 2; // Water type
  private static final String UNIT_DESCRIPTION = "Warship"; // A name for generic Water units
  private static final int MOVE_RESTRICTION = Unit.WATER_ONLY; // Only moves on water
  private static final int ATK_DEFAULT = 2;
  private static final int ATTACK_RANGE = 2;
  private static final int SPAWN_LOC_X = 0;
  private static final int SPAWN_LOC_Y = 0;
  private static final int POINT = 150; //Added by Sidra; May 17
  
	public UnitWater() {
		  super(HP_DEFAULT,MOVES_DEFAULT,UNIT_TYPE,UNIT_DESCRIPTION,MOVE_RESTRICTION,ATK_DEFAULT,ATTACK_RANGE,SPAWN_LOC_X,SPAWN_LOC_Y,POINT);
	}
	
	public UnitWater(int setHP, int setNumMoves, int Utype, String desc, int restrict, int ATK, int range, int locationX, int locationY, int setPoint) {
		super(setHP, setNumMoves, Utype, desc, restrict, ATK, range, locationX, locationY, setPoint);
	}
}