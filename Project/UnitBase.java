package project;

public class UnitBase extends Unit {
  //defaults for UnitAir DAN
	private static final int HP_DEFAULT = 10;
	private static final int MOVES_DEFAULT = 0;
	private static final int UNIT_TYPE = 10; // Air type
	private static final String UNIT_DESCRIPTION = "Fortess"; // A name for generic Base units
	private static final int MOVE_RESTRICTION = Unit.LAND_ONLY; // LandOnly
	private static final int ATK_DEFAULT = 0;  //This will be used as a unit generator
	private static final int ATK_RANGE = 7;  //this will be used as a unit generator
	private static final int SPAWN_LOC_X = 0;
	private static final int SPAWN_LOC_Y = 0;
	//Modified by dan; May 21.  If UnitBase has points it can attack/generate units at locations of attack
	private static final int POINT = 0;  //change to zero upon completion of testing May 21, 2012
	public static final int PRICE_AIR = 100;
	public static final int PRICE_GROUND = 125;
	public static final int PRICE_HEALER = 150; //NOT implemented yet
			
	
	public UnitBase() {
	  super(HP_DEFAULT,MOVES_DEFAULT,UNIT_TYPE,UNIT_DESCRIPTION,MOVE_RESTRICTION,ATK_DEFAULT,ATK_RANGE,SPAWN_LOC_X,SPAWN_LOC_Y,POINT);
	  baseUnitShot();
	}
	
	public UnitBase(int setHP, int setNumMoves, int Utype, String desc, int restrict, int ATK, int range, int locationX, int locationY, int setPoint) {
		super(setHP, setNumMoves, Utype, desc, restrict, ATK, range, locationX, locationY, setPoint);
		baseUnitShot();
	}
	
	//Method overrides
	
	public void moved(){ setMoved(true);	}
	public void movedFalse(){ setMoved(false); }
	private void setMoved(boolean b){
	  //moved is always true.  A base cannot move.  Data member not necessary to spawn units
		//but may effect other classes.
		baseUnitShot();
		moved = true;
	}
	public void baseMove(boolean b){ moved = true; };

	public void attacked(){ baseUnitShot();	}
	public void attackedFalse(){ baseUnitShot(); }
	public void setHasUnitShot(boolean b) { baseUnitShot(); }
	//only changes hasUnitShot to true if not enough points to generate any kind of unit
	//price of current units are in public static finals PRICE_(TYPE)
	//boolean b is a dummy.
	public void baseUnitShot(){
		int tempMoney = getPoint();
		if(tempMoney < PRICE_GROUND)
			hasUnitShot = true;
		else
			hasUnitShot = false;
	}
	//this is the only method available to force hasUnitShot to be true
	public void baseAttacked(){ super.attacked(); }
}
