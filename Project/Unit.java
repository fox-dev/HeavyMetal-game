 /* Unit.java
  * An interface for all units
  * 
  * A rundown of the functions:
  *		getMoves() =>       returns number of moves the unit has
  *  	getHP() =>          returns how much HP a unit has
  *   	setHP(int HP) =>    sets the number of HP the unit has
  *   	moved() =>          toggle the moved flag to TRUE
  *   	hasMoved() =>       returns the moved flag
  *   	getAttack() =>      returns how much attack the unit has
  *   	setLocationX =>     move the unit to a specific X location
  *   	setLocationY =>     move the unit to a specific Y location
  *   	getLocationX =>     return the unit's X location
  *   	getLocationY =>     return the unit's Y location
  */

package project;

public interface Unit {

	public int getMoves();
	public int getHP();
	public void setHP(int hp);
	public void moved();
	public void movedFalse();
	public boolean hasMoved();
	public int getAttack();
	public void setLocationX(int x);
	public void setLocationY(int y);
	public int getLocationX();
	public int getLocationY();
	public void setHasUnitShot(boolean);
	public boolean getHasUnitShot();
}
