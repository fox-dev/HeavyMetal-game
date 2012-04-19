 /* Unit.java
  * An interface for all units //Changed to a super class to all units DAN
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

/* Unit.java
 * An interface for all units //Changed to a super class to all units DAN
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


public class Unit {
 protected int HP;
 protected int numMoves;
 protected int attack;
 protected int locX;
 protected int locY;
 protected boolean moved = false;
 protected boolean hasUnitShot = false;   //Dan: ensures that unit can only shoot once
 
 public Unit(){
   HP = numMoves = attack = locX = locY;
 }
 
 public Unit(int setHP, int setNumMoves, int ATK, int locationX, int locationY){
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
 
 public void attacked(){ //added by Andrew
	  hasUnitShot = true;
 }
 
 public void attackedFalse(){ //added by Andrew
	  hasUnitShot = false;
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
 public void setXY(int x, int y){ //added by DAN for convenience
   setLocationX(x);
   setLocationY(y);
 }
}

