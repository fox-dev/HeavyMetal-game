package project;

import project.Unit;

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
 protected int fullHP;
 protected int numMoves;
 protected int attack;
 protected int locX;
 protected int locY;
 protected int type; // ID's a unit as an air, ground, etc
 protected int range; // The unit's attack range
 protected String description; // A short description (Like "Tank" or "Bomber")
 protected int restriction; // Restricts a unit to certain tiles
 protected boolean isSelected = false; // Is the unit seleceted?
 protected boolean moved = false;
 protected boolean hasUnitShot = false;   //Dan: ensures that unit can only shoot once
 //move restrictions macro to be used in inherited units and Actions
 public static final int NONE = 0;
 public static final int LAND_ONLY = 1;
 public static final int WATER_ONLY = 2;
 
 public Unit(){
   HP = numMoves = type = attack = range = locX = locY;
   type = 255; // Undefined
   restriction = 0; // No restriction
   description = "Undefined";
 }
 
 public Unit(int setHP, int setNumMoves, int Utype, String desc, int restrict, int ATK, int attackRange, int locationX, int locationY){
   HP = setHP;
   fullHP = setHP;
   numMoves = setNumMoves;
   type = Utype;
   description = desc;
   restriction = restrict;
   attack = ATK;
   range = attackRange;
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
 public int getRange() {
	 return range;
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
 public boolean isTargetInRange(Unit u){  //added by Dan 2012May3
   int x = this.getLocationX() - u.getLocationX();
   int y = this.getLocationY() - u.getLocationY();
   int distance = (int)(Math.sqrt(x*x+y*y));
   if(distance <= range)
     return true;
   return false;
 }
 public boolean isXYinRange(int xCoor, int yCoor){
   int x = this.getLocationX() - xCoor;
   int y = this.getLocationY() - yCoor;
   int distance = (int)(Math.sqrt(x*x+y*y));
   if(distance <= range)
     return true;
   return false; 
 }
}