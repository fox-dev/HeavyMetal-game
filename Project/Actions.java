/** Program Name: Actions.java
 *  Name: Dan Q. Nguyen
 *  Professor: Yang, David
 *  Class: CS4310 Software Engineering I
 *  Assignment Name: Project for class
 *  Description: CONTAINS ONLY 3 PUBLIC METHODS plus a constructor 
 *    all others are private helpers(you won't need them)
 *    
 *    - public boolean fire(Unit src, Unit tgt) 
 *    - public boolean fire(Unit src, int x, int y)
 *      -returns true if "fire" is done and updates appropriate data
 *        to include cleaning up dead units with Player.removeDeadUnits()
 *      -returns false if "fire" is NOT valid and makes no changes
 *      -2 above methods check if a fire action is legal by:
 *        - if one of the units don't exist
 *        - if one of the units are dead
 *        - if the unit attack has already attacked it can't
 *        - if the target unit is in range of the attacking unit
 *            
 *    - public boolean moveUnit(Unit u, int x, int y)
 *      -returns true if "move" is done and updates appropriate data
 *      -returns false if "move" is NOT valid and makes no changes
 *      -method check if a move is legal by:
 *        - unit cannot move if it has already moved
 *        - unit cannot move on top of itself
 *        - unit cannot move on top of any other unit
 *        - unit must not move off of map
 *        - ground unit cannot move on water
 *        
 *    -3 methods listed make checks for legality of move
 *    note: "helper" classes listed at end of code
*/
package project;

import project.Map;
import project.MoveEl;
import project.Player;
import project.Unit;
import project.UnitGround;

public class Actions {
  Player p1, p2;
  Map mapRef;
  private static final int[][] moveArray = {  {-1, 1, 0,  0},
                                               {0, 0, 1, -1} };
  //Figure out when turn is over by checking player information  ??? done here???
  //Calls information on units in the Player passed in  ???
  //Update everything else ??? What else to update???
  
  public Actions(Player p1, Player p2, Map mapRef) {
    this.p1 = p1;
    this.p2 = p2;
    this.mapRef = mapRef;
  }
  
  //Attack options
  //check if fire is Legal returns true if legal, false if ILLEGAL
  //updates target HP, units has shot boolean, and cleans map of dead units
  //(add friendly fire?)
  public boolean fire(Unit src, Unit tgt){
    if(fireLegal(src, tgt)){
      tgt.setHP(tgt.getHP() - src.getAttack());
      src.setHasUnitShot(true);
      p1.removeDeadUnits();
      p2.removeDeadUnits();
      return true;
    }
    return false;
  }
  public boolean fire(Unit src, int x, int y){//method overload for convenience
    return fire(src, p1.getUnitAt(x, y));
  }
  private boolean fireLegal(Unit src, Unit tgt){
    //units don't exist
    if(src == null || tgt == null)
      return false;
    //one of the units are dead
    if(src.getHP() <= 0 || tgt.getHP() <= 0)
      return false;
    //src has already shot
    if(src.getHasUnitShot())
      return false;
    //check range, may be changed later if range is changed
    //attack is able to be done in adjacent squares to include diagonal
    double x = (double)Math.abs(src.getLocationX() - tgt.getLocationX());
    double y = (double)Math.abs(src.getLocationY() - tgt.getLocationY());
    int distance = (int)(Math.sqrt(x*x+y*y));
    if(distance > 1)
      return false;
    //all is good, fire away
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
  
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXEDIT AREA START
  //package class variable for displaying map of moves
  //this moveArrayDisplay is used to verify a move rather than the
  //queue class which will be removed
  public static final int CANT_MOVE_HERE0 = 0;  
  public static final int LEGAL_MOVE_HERE= 1;
  public static final int A_UNIT_IS_HERE = 2;
  //if moveArrayDisplay[i][j] == CANT_MOVE_HERE0 then unit CANNOT move to that location (TERRAIN)
  //if moveArrayDisplay[i][j] == LEGAL_MOVE_HERE then unit CAN move to that location
  //if moveArrayDisplay[i][j] == A_UNIT_IS_HERE then unit CANNOT move to that location (OTHER UNIT)
  
  int[][] moveArrayDisplay;
  private boolean moveLegal(Unit u, int destX, int destY){   
    //create an array the size of mapRef.  Fills with CANT_MOVE_HERE0 into all fields by default
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
    if(!u.hasMoved()){ //if unit has not moved then continue to build moveArrayDisplay
      moveArrayDisplay[u.getLocationX()][u.getLocationY()] = LEGAL_MOVE_HERE;  //TEMPORARY to allow recursion!!!! 
      moveArrayBuildRecursive(u, u.getLocationX(), u.getLocationY(), u.getMoves(),
                          destX, destY, moveArrayDisplay);    
    }
    
    //make at the end, unit actual location a A_UNIT_IS_HERE
    //  this will take care of the case "unit cannot move ontop of itself" from moveArrayBuildRecursive(...)
    moveArrayDisplay[u.getLocationX()][u.getLocationY()] = A_UNIT_IS_HERE;
    
    //if (destX ,destY) is LEGAL_MOVE_HERE on moveArrayDisplay then return true. else then false
    if(moveArrayDisplay[destX][destY] == LEGAL_MOVE_HERE)
      return true;
    else
      return false;
  }
  
  private void moveArrayBuildRecursive(Unit u, int currX, int currY, int movesRemaining,
                                    int destX, int destY, int[][] mvArr){
    //XXXXX breaks from recursive "branch" if unit cannot be on currX and currY
    //      makes no change to mvArr if breaking from recursive "branch".
    //      UNIT is allowed to move back on itself....ie move left one, then
    //        move right one.  This will be "checked" for in private boolean moveLegal(...)
    //        as the original unit location will be changed to a CANT_MOVE_HERE0 so that in the end
    //        the unit cannot move back onto itself.  This method is only for CONTINUING to making
    //        the moveArrayDisplay.
    //      MUST NOT BE CALLED ELSEWHERE because of the dependency on
    //        private boolean moveLegal(...)
    
    //unit cannot move ontop of ANY OTHER unit.
    if(mvArr[currX][currY] == A_UNIT_IS_HERE)
      return;
    //unit cannot move off of map
    if( !isXY_onMap(currX, currY) )
      return; 
    //final destination cannot be on water if ground unit
    if( isGroundOnWater(u,currX,currY) )
      return;
    
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
        moveArrayBuildRecursive(u, xTemp, yTemp, movesRemaining - 1, destX, destY, mvArr);
      }
    }
  }
  
  
  
  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXEDIT AREA END
  private boolean isXY_onMap(int x, int y){
    if( x<0 || y<0 || x>mapRef.getX()-1 || y > mapRef.getY()-1 )
      return false;
    return true;
  }
  private boolean isGroundOnWater(Unit u, int x, int y){
    //if ( a ground unit and its on water )
    if((u instanceof UnitGround) && mapRef.getArr(x, y) == 2) //possible syntax/symmatic error instanceof
      return true;     //2 is water according to Map.java (change to a static final WATER in map
    return false;
  }
}
/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 * Helper classes for Actions.moveLegal(Unit u, int x, int y) 
 * 
 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
class QueueMove{
  private MoveEl head, tail;
  public QueueMove() {
    head = tail = null;
  }
  public void enqueue(MoveEl el){
    if(el == null)
      return;
    if(head == null)
      tail = el;
    el.next = head;
    head = el; 
  }
  public MoveEl dequeue(){  //needs refinement
    if(isEmpty())
      return null;
    MoveEl temp;
    if(head == tail){
      temp = head;
      head = tail = null;
    }
    else{
      temp = tail;
      MoveEl tempT = head;
      while(tempT.next != tail)
        tempT = tempT.next;
      tail = tempT;
      tail.next = null;
    }
    return temp;
  }
  public boolean isEmpty(){
    if(head == null)
      return true;
    return false;
  }
  public String toString(){
    String s = "enqueue here->";
    MoveEl temp = head;
    while(temp != null){
      s += temp.toString() + "--";
      temp = temp.next;
    }
    s += "<-dequeue here";
    return s;
  }
}

class MoveEl {
  public final int numMovesLeft;
  public final int x;
  public final int y;
  public MoveEl next;
  
  private MoveEl(){
    this(0, 0, 0, null);
  }
  
  public MoveEl( int n, int locL_R, int locU_D, MoveEl next) {
    numMovesLeft = n;
    x = locL_R;
    y = locU_D; 
    this.next = next;
  }
  public String toString(){
    return "" + numMovesLeft + "[" + x + "]" + "[" + y + "]";
  }
}










