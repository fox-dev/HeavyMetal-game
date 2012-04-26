package project;

/* Unit.java
 * An interface for all units //Changed to a super class to all units DAN
 *   
 * ID Types:
 * 0 = Ground
 * 1 = Air
 * 2 = Water
 * 255 = Undefined
 * 
 * Move Restrictions:
 * 0 = None (Can move anywhere)
 * 1 = Only Land (Can only move on land tiles)
 * 2 = Only Water (Can only move on water tiles)
 * 
 */


public class Unit {
 protected int HP;
 protected int numMoves;
 protected int attack;
 protected int locX;
 protected int locY;
 protected int type; // ID's a unit as an air, ground, etc
 protected String description; // A short description (Like "Tank" or "Bomber")
 protected int restriction; // Restricts a unit to certain tiles
 protected boolean isSelected = false; // Is the unit seleceted?
 protected boolean moved = false;
 protected boolean hasUnitShot = false;   //Dan: ensures that unit can only shoot once
 
 public Unit(){
   HP = numMoves = type = attack = locX = locY;
   type = 255; // Undefined
   restriction = 0; // No restriction
   description = "Undefined";
 }
 
 public Unit(int setHP, int setNumMoves, int Utype, String desc, int restrict, int ATK, int locationX, int locationY){
   HP = setHP;
   numMoves = setNumMoves;
   type = Utype;
   description = desc;
   restriction = restrict;
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
 public int getType() {
	 return type;
 }
 public String getDescription() {
	 return description;
 }
 public int getMRestriction() {
	 return restriction;
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
 public boolean isSelected() {
	 return isSelected;
 }
 public void unSelect() {
	 isSelected = false;
 }
 public void select() {
	 isSelected = true;
 }
}