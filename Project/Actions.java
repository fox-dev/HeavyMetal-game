package project;

import project.ActionsRules;
import project.Map;
import project.Player;
import project.Unit;
import project.UnitGround;

/** Program Name: Actions.java
 *  Name: Dan Q. Nguyen
 *  Professor: Yang, David
 *  Class: CS4310 Software Engineering I
 *  Assignment Name: Project for class
 *  Description: 
 *    * public int[][] makeNewMovementDisplay(Unit u) added
 *       -creates an array of sizes from map.java and list with '1's of movable locations
 *    * public boolean moveLegal(Unit u, int destX, int destY)
 *      -has direct correlation to int[][] moveArrayDisplay
 *      -if moveLegal(...) is called it will update/change moveArrayDisplay
 *    * public boolean fire(Unit src, Unit tgt) 
 *      -returns true if "fire" is done and updates appropriate data
 *        to include cleaning up dead units with Player.removeDeadUnits()
 *      -returns false if "fire" is NOT valid and makes no changes
 *      -above methods check if a fire action is legal by:
 *        - if one of the units don't exist
 *        - if one of the units are dead
 *        - if the unit attack has already attacked it can't
 *        - if the target unit is in range of the attacking unit
 *    * public boolean fireLegal(Unit src, Unit tgt)
 *    * public boolean moveUnit(Unit u, int x, int y)
 *      -returns true if "move" is done and updates appropriate data
 *      -returns false if "move" is NOT valid and makes no changes
 *      -method check if a move is legal by:
 *        - unit cannot move if it has already moved
 *        - unit cannot move on top of itself
 *        - unit cannot move on top of any other unit
 *        - unit must not move off of map
 *        - ground unit cannot move on water (Map.WATER)
 *        - water unit cannot move on ground (Map.GROUND)
 *        - unit cannot move through a unit
 *    * public boolean unitCanBeHere(Unit u, int x, int y)
 *        -returns true if a unit can be on map[y][x]
 *        -based on Map.GROUND / Map.WATER / Unit.restrictions
 *    * public void respawn(Player p, int quadrant)
 *        -respawns a player's living units to a quandrant I, II, III, IV or random
*/

public class Actions {
  Player p1, p2;
  Map mapRef;
  int[][] moveArrayDisplay; //used in public boolean moveLegal(Unit u, int destX, int destY)
  private ActionsRules actionsRules;
  private String msg;
  
  public static final int CANT_MOVE_HERE0 = 0;  
  public static final int LEGAL_MOVE_HERE= 1;
  public static final int A_UNIT_IS_HERE = 2;  
  private static final int[][] moveArray = {  {-1, 1, 0,  0},
                                               {0, 0, 1, -1} };

  public Actions(Player p1, Player p2, Map mapRef) {
    this.p1 = p1;
    this.p2 = p2;
    this.mapRef = mapRef;
    actionsRules = new ActionsRules();
    msg = null;
  }

  public int[][] makeNewMovementDisplay(Unit u){
    int[][] tempSwap;
    int[][] temp = moveArrayDisplay;
    moveLegal(u, 0, 0); //makes a new int[][] that is referenced by moveArrayDisplay
    tempSwap = moveArrayDisplay;
    moveArrayDisplay = temp;
    return tempSwap;
  }
  //Attack options
  //check if fire is Legal returns true if legal, false if ILLEGAL
  //updates target HP, units has shot boolean, and cleans map of dead units
  //(add friendly fire?)
  public boolean fire(Unit src, Unit tgt){
    if(fireLegal(src, tgt)){
      tgt.setHP(tgt.getHP() - src.getAttack());
      src.setHasUnitShot(true);
      if(actionsRules.returnFire_On == true)
        actionsRules.returnFire(tgt, src);
      p1.removeDeadUnits();
      p2.removeDeadUnits();
      return true;
    }
    return false;
  }
  
 public boolean fireLegal(Unit src, Unit tgt){ //made public -Andrew
    if(src == null || tgt == null){
      msg = "Your or your opposing unit don't exist"; // units don't exist
      return false; 
    }
    if(src.getHP() <= 0 || tgt.getHP() <= 0){    //one of the units are dead
      msg = "You or your unit is already dead";
      return false;
    }
    if(src.getHasUnitShot()){
      msg = "Your " + src.getDescription() + " has already shot"; //src has already shot
      return false;
    }
    //check range, updated Dan 2012 May 3
    if(!src.isTargetInRange(tgt)){
      msg = "Enemy " + tgt.getDescription() + " is not in range of your " + src.getDescription();
      return false;
    }
    //if friendly fire is OFF check if other unit is a friendly  //CASE NEVER TAKEN INTO CONSIDERATION due to logic in Input.java.  May change
    if(actionsRules.friendlyFire_On == false)
      if(actionsRules.friendlyUnits(this, src,tgt)){
        msg = "Cannot fire at friendly targets";
        return false;
      }
    //all is good, fire away
    msg = null;
    return true;
  }
  
   /*  returns true if unit was moved successfully
   *   returns false if unit cannot be moved, no data changed in this case */
  public boolean moveUnit(Unit u, int x, int y){
    if(moveLegal(u,x,y)){
      u.setLocationX(x);
      u.setLocationY(y);
      u.moved();
      return true;
    }
    return false;
  }
  
  //if moveArrayDisplay[i][j] == CANT_MOVE_HERE0 then unit CANNOT move to that location (RANGE)
  //if moveArrayDisplay[i][j] == LEGAL_MOVE_HERE then unit CAN move to that location
  //if moveArrayDisplay[i][j] == A_UNIT_IS_HERE then unit CANNOT move to that location (OTHER UNIT)
  public boolean moveLegal(Unit u, int destX, int destY){   
	  // If a click is outside of the map, ignore it
	  if (destX < 0 || destX > (mapRef.getX() - 1) || (destY < 0 || destY > (mapRef.getY() - 1)))
		  return false;
    //create an array the size of mapRef.  Fills with CANT_MOVE_HERE0 into all fields by default
    // only uses mapRef for the size of the map.  Not coordinates of water/ground
    moveArrayDisplay = new int[mapRef.getX()][mapRef.getY()];  
    
    //update moveArrayDisplay to have all units(p1 and p2) with A_UNIT_IS_HERE on moveArrayDisplay
    //update moveArrayDisplay with p1
    Unit tempUnit;
    for(int i = 0; p1.getUnit(i) != null; i++){
      tempUnit = p1.getUnit(i);
      moveArrayDisplay[tempUnit.getLocationX()][tempUnit.getLocationY()] = A_UNIT_IS_HERE;
    }
    //update moveArrayDisplay with p2
    for(int i = 0; p2.getUnit(i) != null; i++){
      tempUnit = p2.getUnit(i);
      moveArrayDisplay[tempUnit.getLocationX()][tempUnit.getLocationY()] = A_UNIT_IS_HERE;
    }
    
    //Call recursive function to CONTINUE building the moveArrayDisplay
    //accounts for move restrictions onto water/land
    if(!u.hasMoved()){ //if unit has not moved then continue to build moveArrayDisplay
      //TEMPORARY to allow recursion!!!!
      moveArrayDisplay[u.getLocationX()][u.getLocationY()] = LEGAL_MOVE_HERE;   
      moveArrayBuildRecursive(u, u.getLocationX(), u.getLocationY(),
                                u.getMoves(), moveArrayDisplay);    
    }
    
    //make at the end, unit actual location a A_UNIT_IS_HERE
    //  this will take care of the case "unit cannot move ontop of itself"
    moveArrayDisplay[u.getLocationX()][u.getLocationY()] = A_UNIT_IS_HERE;
    
    //if (destX ,destY) is LEGAL_MOVE_HERE on moveArrayDisplay then return true
    int spot = moveArrayDisplay[destX][destY]; 
    if(spot == LEGAL_MOVE_HERE){
      msg = null;
      return true;
    }
    //Generate msg message to say why move could not be made
    msg = "" + u.getDescription() + " ";
    if(spot == A_UNIT_IS_HERE)
      msg += "cannot move atop another unit";     
    else{ //(spot == CANT_MOVE_HERE0){
      if(!unitCanBeHere(u, destX, destY))
        msg += "cannot move on that terrain";
      else
        msg +="cannot move that far";
    }
    return false;
  }
  
  private void moveArrayBuildRecursive(Unit u, int currX, int currY, 
                                        int movesRemaining, int[][] mvArr){
    //XXXXX breaks from recursive "branch" if unit cannot be on currX and currY
    //      makes no change to mvArr if breaking from recursive "branch".
    //      UNIT is allowed to move back on itself....ie move left one, then
    //        move right one.  This will be "checked" for in private boolean moveLegal(...)
    //        as the original unit location will be changed to a A_UNIT_IS_HERE so that in the end
    //        the unit cannot move back onto itself.  This method is only for CONTINUING to making
    //        the moveArrayDisplay.
    //      MUST NOT BE CALLED ELSEWHERE because of the dependency on
    //        public boolean moveLegal(...)
    
    if( !isXY_onMap(currX, currY) )  //unit cannot move off of map
      return; 
    if(mvArr[currX][currY] == A_UNIT_IS_HERE)  //unit cannot move ontop of ANY OTHER unit.
      return;
    if( !unitCanBeHere(u, currX, currY) ) //if groundUnit cannot move Map.WATER, 
      return;                             //if waterUnit cannot move to Map.GROUND
      
    //XXXXX currX, currY is a valid location or a "LEGAL_MOVE_HERE"
    int xTemp, yTemp;
    mvArr[currX][currY] = LEGAL_MOVE_HERE;
    //move in each direction (up,down,left,right) 1 space and recursively call 
    //  moveArrayRecursive for that new X, Y  while decrementing 
    //  "movesRemaining" by 1 for each move to end recursive "branches"
    if(movesRemaining > 0) {
      for(int i = 0; i < 4; i++){  // 4 => max moves, left right up down
        //make a move ( cycles for each i, left, right, up, down )
        xTemp = currX + moveArray[0][i];
        yTemp = currY + moveArray[1][i];
        moveArrayBuildRecursive(u, xTemp, yTemp, movesRemaining - 1, mvArr);
      }
    }
  }
  
  public boolean unitCanBeHere(Unit u, int x, int y){
    int uRestriction = u.getMRestriction();
    if( uRestriction != 0){ //skip these if it is has no movement restrictions or Unit.NONE
      int mapYXtype = mapRef.getArr(x, y); //accounts for SWITCHED X Y
      // LAND_ONLY units can only move onto the GROUND
      if( uRestriction == Unit.LAND_ONLY && mapYXtype != Map.GROUND)
        return false;
      // WATER_ONLY units can only move onto WATER
      if( uRestriction == Unit.WATER_ONLY && mapYXtype != Map.WATER)
        return false;    
    }
    return true;
  }
  //added so that a Player's unit(alive ones) can be respawned
  //accounts for units not being on water if land and vise versa
  //
  /*  quadrant == Q#
   *
   *   Q2  X  Q1
   *       X
   *  X X X X X X
   *       X
   *   Q3  X  Q4
   *  
   *   if quandrant == Q0 => random distribution 
   */
  public void respawn(Player p){
    respawn(p, 0);
  }
  public void respawn(Player p, int quadrant){
    if(quadrant < 0 || quadrant > 4)
      quadrant = 0;
    //move all player 's units off of map
    int i = 0;
    Unit u = p.getUnit(i);
    while(u != null){
      u.setXY(-1, -1);
      u = p.getUnit(++i);
    }
    
    //seed random
    java.util.Random r = new java.util.Random( System.currentTimeMillis());
    
    //find min/max XY
    i = 0;
    u = p.getUnit(i);
    int mX = mapRef.getX() - 1 ;
    int mY = mapRef.getY() - 1;;
                 //{xMin, xMax, yMin, yMax}
    int[][] qD = { { 0, mX, 0, mY },       //all Quadrants
                   { mX/2, mX, 0, mY/2},   //Q1
                   { 0, mX/2, 0, mY/2},    //Q2
                   { 0, mX/2, mY/2, mY},   //Q3
                   { mX/2, mX, mY/2, mY}}; //Q4
    int xMin = qD[quadrant][0];
    int xMax = qD[quadrant][1];
    int yMin = qD[quadrant][2];
    int yMax = qD[quadrant][3];
    
    //spawn players based upon quandrant location
    //no spawn on opposing player, water units on water and land units on land
    i = 0;
    u = p.getUnit(i);
    int tempX = 0, tempY = 0;
    boolean goodSpot;
    while(u != null){     
      goodSpot = false;
      while(!goodSpot){
        tempX = (r.nextInt(xMax-xMin) + xMin) % xMax;
        tempY = (r.nextInt(yMax-yMin) + yMin) % yMax;
        //terrain check
        if( unitCanBeHere(u, tempX, tempY) &&    //terrain check
            p1.getUnitAt(tempX,tempY) == null && //other units checks
            p2.getUnitAt(tempX,tempY) == null )
          goodSpot = true;
      }
      u.setXY(tempX, tempY);
      u = p.getUnit(++i);
    }
  }
  //returns the player of a unit
  public Player playerOfUnit(Unit u){
    if(u == null)
      return null;
    Player unitPlayer = null;
    //find src units player#
    if(p1.isThisMyUnit(u))
      unitPlayer = p1;
    else if(p2.isThisMyUnit(u))
      unitPlayer = p2;
    return unitPlayer;  
  }
  public String getMsg(){
    return msg;
  }
  
  private boolean isXY_onMap(int x, int y){
    if( x<0 || y<0 || x>mapRef.getX()-1 || y > mapRef.getY()-1 )
      return false;
    return true;
  }
  private boolean isGroundOnWater(Unit u, int x, int y){
    //if ( a ground unit and its on water )
    if((u instanceof UnitGround) && mapRef.getArr(x, y) == 2) //-Andrew, fixes displaying of all possible moves. Switched X and Y //possible syntax/symmatic error instanceof
      return true;     //2 is water according to Map.java (change to a static final WATER in map
    return false;
  }
}